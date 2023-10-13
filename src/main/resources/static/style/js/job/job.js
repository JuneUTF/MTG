// ジョブテーブルを表示するための要素を取得
const job = document.getElementById("job");
/**
 * Websocketに接続・アクションの行動はplanを呼び出してBodyのパラメータを渡す
 */
// WebSocketを使用してジョブ情報を受信し、表示するための関数
var stompClient = null;
function connectWebSocket() {
    var socket = new SockJS('/ws'); // WebSocketのエンドポイントを指定
    stompClient = Stomp.over(socket); // Stompクライアントを作成
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/job/notification', function (job) {
            var newJob = JSON.parse(job.body);
            setJob(newJob);
        });
    });
}
connectWebSocket();
// ジョブテーブルのHTMLを構築するための変数
let jobtt = "";
/**
 * 予約のチェック
 * @param {Array} 予約内容リスト 
 */
function plan(obj) {
    //配列の長さ==0・予約内容がない（画面に予約内容がないのメッセージを表示されます。）
    if (obj.length == 0) {
        // ジョブテーブルにHTMLを設定
        job.innerHTML = jobtt;
        // 変数をリセット
        jobtt = "";
        // 予約内容がある。（setJobを呼び出して予約内容の配列を渡す。）
    } else {
        setJob(obj);
    }
}
/**
 * ジョブ情報をHTMLテーブルに設定する関数
 * @param {Array} 予約内容リスト 
 */
function setJob(obj) {
    let ArrayJob = {};
    let newArrayJob = [];

    // ジョブ情報を日付ごとにグループ化
    obj.forEach(function (item) {
        let datePlan = item.date_plan;
        if (!ArrayJob[datePlan]) {
            ArrayJob[datePlan] = [];
        }
        ArrayJob[datePlan].push(item);
    });
    // 各日付ごとのジョブを時間順にソートし、新しい配列に追加
    Object.values(ArrayJob).forEach(e => {
        e.sort(function (a, b) {
            const timeA = new Date('1970-01-01T' + a.time_start + 'Z').getTime();
            const timeB = new Date('1970-01-01T' + b.time_start + 'Z').getTime();
            return timeA - timeB;
        });
        newArrayJob.push(e)
    });
    // 現在の時間と日付を取得
    const timenow = new Date().getHours().toString().padStart(2, '0') + ":" + new Date().getMinutes().toString().padStart(2, '0');
    const dayNow = new Date().toISOString().substring(0, 10);
    const timeLine = new Date(dayNow + 'T' + timenow + 'Z').getTime();
    // ジョブ情報をHTMLテーブルに追加
    newArrayJob.forEach(element => {
        element.forEach(e => {
            if (timeLine <= new Date(e.date_plan + 'T' + e.time_end + 'Z').getTime()) {
                if (timeLine >= new Date(e.date_plan + 'T' + e.time_start + 'Z').getTime()&& e.status =="予約中") {
                    jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日　(${e.date_day}曜日)</td><td>${e.time_start}～${e.time_end} </td><td>${e.purpose}</td><td>${e.charge}</td><td><span class='yoyaku'>利用中</span></td><td><form method="post" action="/kanryo?id=${e.id}"><button type="submit" class="btn btn-success">完了</button></form></td></tr>`
                } else {
                    jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日　(${e.date_day}曜日)</td><td>${e.time_start}～${e.time_end}</td><td>${e.purpose}</td><td>${e.charge}</td><td>${e.status}</td><td><a class="btn btn-outline-warning" href="/kk/job/edit/?id=${e.id}">編集</a>・${e.status == '予約中' ? `<button  class="btn btn-outline-danger" onclick="deleteJob('${e.id}','${e.date_plan}','${e.time_start}')">削除</button>` : `<button  class="btn btn-outline-primary" onclick="restoreJob('${e.id}','${e.date_plan}','${e.time_start}')">復元</button>`}</td></tr>`
                }
            } else
                if (element.length == 1 && newArrayJob.length == 1) {
                    jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日　(${e.date_day}曜日)</td><td>${e.time_start}～${e.time_end}</td><td>${e.purpose}</td><td>${e.charge}</td><td>${e.status}</td><td><a class="btn btn-outline-warning" href="/kk/job/edit/?id=${e.id}">編集</a>・${e.status == '予約中' ? `<button  class="btn btn-outline-danger" onclick="deleteJob('${e.id}','${e.date_plan}','${e.time_start}')">削除</button>` : `<button  class="btn btn-outline-primary" onclick="restoreJob('${e.id}','${e.date_plan}','${e.time_start}')">復元</button>`}</td></tr>`
                }
        })
    });
    // ジョブテーブルにHTMLを設定
    job.innerHTML = jobtt;
    jobtt = ""; // 変数をリセット
}

//検索機能設定
//開始日付入力フィールドを取得
const date_start = document.getElementById("date_start");
//完了日付入力フィールドを取得
const date_end = document.getElementById("date_end");
//開始日付の初期値を現在の日付に設定
date_start.value = new Date().toISOString().substring(0, 10);
// 目的入力フィールドを取得
const purpose = document.getElementById("purpose");
// 担当者入力フィールドを取得
const charge = document.getElementById("charge");
// 目的情報のHTMLを生成する変数
let purposeHTML = '';
// 目的情報を取得する非同期関数
async function callPurpose() {
    try {
        const apiUrl = "/purpose";
        const response = await fetch(apiUrl);
        if (!response.ok) {
            //APIを呼び出しできない場合 input box 表示されます。
            purposeHTML = `<input type="text" id="purpose" name="purpose" placeholder="目的を入力ください。" required>`;
        }
        const data = await response.json();
        data.map((e) => {
            //APIを呼び出し場合 選択ボックスを表示されます。
            purposeHTML = purposeHTML + `<option value="${e.id}">${e.purpose}</option>`;
        });
        purposeHTML = `<select id="purpose" name="purposeId"><option value="0">全員</option>${purposeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        purposeHTML = `<input type="text" id="purpose" name="purpose" placeholder="目的を入力ください。" required>`;
    }
}
let chargeHTML = '';
// 担当者を取得する非同期関数
async function callCharge() {
    try {
        const apiUrl = "/charge";
        const response = await fetch(apiUrl);
        if (!response.ok) {
            //APIを呼び出しできない場合 input box 表示されます。
            chargeHTML = `<input type="text" id="charge" name="charge" placeholder="目的を入力ください。" required>`;
        }
        const data = await response.json();
        data.map((e) => {
            //APIを呼び出し場合 選択ボックスを表示されます。
            chargeHTML = chargeHTML + `<option value="${e.id}">${e.charge}</option>`;
        });
        chargeHTML = `<select id="charge" name="chargeId"><option value="0">全部</option>${chargeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        chargeHTML = `<input type="text" id="charge" name="charge" placeholder="目的を入力ください。" required>`;
    }
}
// 目的情報と担当者を非同期で取得し、フィールドに設定
(async () => {
    await callPurpose();
    purpose.innerHTML = purposeHTML;
    await callCharge();
    charge.innerHTML = chargeHTML;
    //await callJob();
})();
/**
 * 選択日付日付を比較する関数
 * @returns {boolean} 開始日付<=完了日付はtrue、それ以外の場合はfalseを返します。
 */
function compareDates() {
    // 開始日付を取得してISOString形式に変換し、日付部分のみを抽出
    const d1 = new Date(date_start.value);
    // 完了日付を取得してISOString形式に変換し、日付部分のみを抽出
    const d2 = new Date(date_end.value);
    // 比較して結果を返す
    return d1 <= d2;
}

async function callJob() {
    try {
        const apiUrl = "/job";
        const response = await fetch(apiUrl);
        if (!response.ok) {
            //APIを呼び出しできない場合 input box 表示されます。
            // chargeHTML = `<input type="text" id="charge" name="charge" placeholder="目的を入力ください。" required>`;
        }
        const data = await response.json();
        plan(data);
        // chargeHTML = `<select id="charge" name="charge"><option>全部</option>${chargeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        // chargeHTML = `<input type="text" id="charge" name="charge" placeholder="目的を入力ください。" required>`;
    }
}