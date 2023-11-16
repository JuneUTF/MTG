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

// 内容情報をHTMLテーブルに設定する関数
function setJob(obj) {
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
  newArrayJob.forEach(e => {
    e.forEach(e => {
      if (timeLine <= new Date(e.date_plan + 'T' + e.time_end + 'Z').getTime()) {
        if (timeLine >= new Date(e.date_plan + 'T' + e.time_start + 'Z').getTime()) {
          jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end} <span class='yoyaku'>利用中</span> </td><td>${e.purpose}</td><td>${e.charge} - <button  class="btn btn-success" onclick="getOK('${e.id}')">完了</button></td></tr>`
        } else {
          jobtt += `<tr><td>${e.date_plan.substring(0, 4)}年${e.date_plan.substring(5, 7)}月${e.date_plan.substring(8, 10)}日 (${e.date_day})</td><td>${e.time_start}～${e.time_end}</td><td>${e.purpose}</td><td>${e.charge}</td></tr>`
        }
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
    fullWidth.innerText = "通常表示";
  }
}
// `fetch`を使用してAPIを呼び出します
function callAPI() {
  fetch("/job")
    .then(response => {
      // HTTP応答のステータスを確認
      if (!response.ok) {
        throw new Error("APIを呼び出せません。エラーコード: " + response.status);
      }
      // JSONデータをJavaScriptオブジェクトに変換
      return response.json();
    })
    .then(data => {
      // ここでデータを処理します
      setJob(data);
    })
    .catch(error => {
      // エラーを処理します
      console.error("エラー: " + error.message);
    });
}
function getOK(id) { 
  const postData = {
    id: `${id}`
  };

  fetch('/kanryo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json' 
    },
    body: JSON.stringify(postData)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("APIを呼び出せません。エラーコード: " + response.status);
    }
    return response.json();
  })
  .then(data => {
    console.log(data);
  })
  .catch(error => {
    console.error("エラー: " + error.message);
  });
}

callAPI();
//リフレッシュボタン処理
const refresh = document.getElementById("refresh");
refresh.addEventListener('click', () => { callAPI() });
// 10分ごとにページをリロードします（600,000ミリ秒）
setInterval(function () {
  callAPI(); // ページをリロード
}, 600000); // 10分 = 10 * 60 * 1000ミリ秒