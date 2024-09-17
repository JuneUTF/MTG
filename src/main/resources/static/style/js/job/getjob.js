// 内容テーブルを表示するための要素を取得
const job = document.getElementById("job");
/**
 * Websocketに接続・アクションの行動はplanを呼び出してBodyのパラメータを渡す
 */
// WebSocketを使用して内容情報を受信し、表示するための関数
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
// 内容テーブルのHTMLを構築するための変数
let jobtt = "";

//検索機能設定
//開始日付入力フィールドを取得
const date_start = document.getElementById("date_start");
//完了日付入力フィールドを取得
const date_end = document.getElementById("date_end");
//開始日付の初期値を現在の日付に設定
date_start.value = new Date().toISOString().substring(0, 10);
// 内容入力フィールドを取得
const purpose = document.getElementById("purpose");
// 担当者入力フィールドを取得
const charge = document.getElementById("charge");
// 内容情報のHTMLを生成する変数
let purposeHTML = '';
// 内容情報を取得する非同期関数
async function callPurpose() {
    try {
        const apiUrl = "/purpose";
        const response = await fetch(apiUrl);
        if (!response.ok) {
            //APIを呼び出しできない場合 input box 表示されます。
            purposeHTML = `<input type="text" id="purpose" name="purpose" placeholder="内容を入力ください。" required>`;
        }
        const data = await response.json();
        data.map((e) => {
            //APIを呼び出し場合 選択ボックスを表示されます。
            purposeHTML = purposeHTML + `<option value="${e.id}">${e.purpose}</option>`;
        });
        purposeHTML = `<select id="purpose" name="purposeId"><option value="0">全員</option>${purposeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        purposeHTML = `<input type="text" id="purpose" name="purpose" placeholder="内容を入力ください。" required>`;
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
            chargeHTML = `<input type="text" id="charge" name="chargeId" placeholder="内容を入力ください。" required>`;
        }
        const data = await response.json();
        data.map((e) => {
            //APIを呼び出し場合 選択ボックスを表示されます。
            chargeHTML = chargeHTML + `<option value="${e.id}">${e.charge}</option>`;
        });
        chargeHTML = `<select id="charge" name="chargeId"><option value="0">全部</option>${chargeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        chargeHTML = `<input type="text" id="charge" name="chargeId" placeholder="内容を入力ください。" required>`;
    }
}
async function callJob() {
    try {
        const apiUrl = "/job";
        const response = await fetch(apiUrl);
        const data = await response.json();
        plan(data);
    } catch (error) {
        //APIを呼び出しできない
    }
}
// 内容情報と担当者を非同期で取得し、フィールドに設定
(async () => {
    await callPurpose();
    purpose.innerHTML = purposeHTML;
    await callCharge();
    charge.innerHTML = chargeHTML;
    await callJob();
})();


//検索機能
function setJobAPI(obj) {
    let ArrayJob = {};
    let newArrayJob = [];

    // 内容情報を日付ごとにグループ化
    obj.forEach(function (item) {
        let datePlan = item.date_plan;
        if (!ArrayJob[datePlan]) {
            ArrayJob[datePlan] = [];
        }
        ArrayJob[datePlan].push(item);
    });
    // 各日付ごとの内容を時間順にソートし、新しい配列に追加
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
    // 内容情報をHTMLテーブルに追加
    newArrayJob.forEach(element => {
        element.forEach(e => {
            //完了場合
            if (e.status == '完了') {
                jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end} </td><td>${e.purpose}</td><td>${e.charge}</td><td>${e.status}</td><td>-</td></tr>`
            } else
                //現在使用場合
                if (timeLine >= new Date(e.date_plan + 'T' + e.time_start + 'Z').getTime() && timeLine <= new Date(e.date_plan + 'T' + e.time_end + 'Z').getTime()) {
                    jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end} </td><td>${e.purpose}</td><td>${e.charge}</td><td><span class='yoyaku'>利用中</span></td><td><form method="post" action="/kanryo?id=${e.id}"><button type="submit" class="btn btn-success">完了</button></form></td></tr>`
                } else
                    // データは１つだけある場合
                    if (element.length == 1 && newArrayJob.length == 1) {
                        jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end}</td><td>${e.purpose}</td><td>${e.charge}</td><td>${e.status}</td><td><a class="btn btn-outline-warning" href="/kk/job/edit/?id=${e.id}">変更</a>・${e.status == '予約中' ? `<button  class="btn btn-outline-danger" onclick="deleteJob('${e.id}','${e.date_plan}','${e.time_start}')">取消</button>` : `<button  class="btn btn-outline-primary" onclick="restoreJob('${e.id}','${e.date_plan}','${e.time_start}')">復元</button>`}</td></tr>`
                    } else if (e.status == 'キャンセル') {
                        //キャンセル場合
                        jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end} </td><td>${e.purpose}</td><td>${e.charge}</td><td>${e.status}</td><td>-</td></tr>`
                    } else {
                        //普通場合
                        jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end}</td><td>${e.purpose}</td><td>${e.charge}</td><td>${e.status}</td><td><a class="btn btn-outline-warning" href="/kk/job/edit/?id=${e.id}">変更</a>・${e.status == '予約中' ? `<button  class="btn btn-outline-danger" onclick="deleteJob('${e.id}','${e.date_plan}','${e.time_start}')">取消</button>` : `<button  class="btn btn-outline-primary" onclick="restoreJob('${e.id}','${e.date_plan}','${e.time_start}')">復元</button>`}</td></tr>`
                    }
        })
    });
    //配列の長さ==0・予約内容がない（画面に予約内容がないのメッセージを表示されます。）
    if (newArrayJob.length == 0) {
        jobtt = `<tr><td colspan="4">現在、会議室の予約はございません。</td></tr>`;
    }
    // 内容テーブルにHTMLを設定
    job.innerHTML = jobtt;
    jobtt = ""; // 変数をリセット
}
document.getElementById("searchButton").addEventListener("click", function (event) {
    event.preventDefault();
    searchData = {
        date_start: document.getElementById("date_start").value,
        date_end: document.getElementById("date_end").value,
        purpose: document.getElementById("purpose").value,
        charge: document.getElementById("charge").value,
        status: document.getElementById("status").value
    };
    // リクエストを送信
    fetch("/kk/job/searchAPI", {
        method: 'POST',
        body: JSON.stringify(searchData),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            setJobAPI(data);
        })
        .catch(error => {
            console.error('エラー:', error);
        });
});