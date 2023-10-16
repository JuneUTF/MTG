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
// ジョブ情報をHTMLテーブルに設定する関数
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
    newArrayJob.forEach(e => {
        e.forEach(e => {
            if (timeLine <= new Date(e.date_plan + 'T' + e.time_end + 'Z').getTime()) {
                if (timeLine >= new Date(e.date_plan + 'T' + e.time_start + 'Z').getTime()) {
                    jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日(${e.date_day}曜日)</td><td>${e.time_start}～${e.time_end} <span class='yoyaku'>利用中</span> </td><td>${e.purpose}</td><td>${e.charge} - <form method="post" action="/kanryo?id=${e.id}"><button type="submit" class="btn btn-success">完了</button></form></td></tr>`
                } else {
                    jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日(${e.date_day}曜日)</td><td>${e.time_start}～${e.time_end}</td><td>${e.purpose}</td><td>${e.charge}</td></tr>`
                }
            }
        })
    });
    // ジョブテーブルにHTMLを設定
    job.innerHTML = jobtt;
    jobtt = ""; // 変数をリセット
}
// 全画面表示イベント処理
const fullWidth = document.getElementById("fullWidth");
let fullWidthBoolean = false;

fullWidth.addEventListener('click', setFullWidth);

function setFullWidth() {
  if (fullWidthBoolean) {
    if (document.exitFullscreen) {
      document.exitFullscreen();
    // Firefox
    } else if (document.mozCancelFullScreen) { 
      document.mozCancelFullScreen();
    // Chrome, Safari, and Opera
    } else if (document.webkitExitFullscreen) { 
      document.webkitExitFullscreen();
    // Internet Explorer/Edge
    } else if (document.msExitFullscreen) { 
      document.msExitFullscreen();
    }
    fullWidthBoolean = false;
    fullWidth.innerText = "全画面表示";
  } else {
    const element = document.body;
    if (element.requestFullscreen) {
      element.requestFullscreen();
    } else if (element.mozRequestFullScreen) { 
      element.mozRequestFullScreen();
    } else if (element.webkitRequestFullscreen) {
      element.webkitRequestFullscreen();
    } else if (element.msRequestFullscreen) {
      element.msRequestFullscreen();
    }
    fullWidthBoolean = true;
    fullWidth.innerText = "普通表示";
  }
}

