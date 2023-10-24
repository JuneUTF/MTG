// 内容テーブルを取得
const purposeTable = document.getElementById("purposetable");
// 担当者テーブルを取得
const chargeTable = document.getElementById("chargetable");
//担当者配列
let chargeArray;
//内容配列
let purposeArray;
// 内容情報のHTMLを生成する変数
let purposeHTML = '';
// 内容テーブルのHTMLを生成する変数
let purposeTableHTML = '';
let chargeTableHTML = '';
let chargeData;
// データを取得する関数
function fetchDatacharge(url) {
  // Fetch APIを使用してデータを取得
  fetch("/ad/charge")
    .then(response => {
      // レスポンスが成功した場合、JSONデータを解析
      if (!response.ok) {
        throw new Error('ネットワークエラー');
      }
      return response.json();
    })
    .then(data => {
      // データをコンソールに表示
      chargeData = data;
      chargeData.map((e) => {
      if(e.id == 1){
      chargeTableHTML += `<tr><td>${e.id}</td><td>${e.fullname}</td><td>${e.role == "ADMIN"?"管理者":"一般"}</td><td>${e.publicid == "0"?"個別":"共有"}</td><td>${e.status}</td><td>-</td></tr>`;
      }else{
        chargeTableHTML += `<tr><td>${e.id}</td><td>${e.fullname}</td><td>${e.role == "ADMIN"?"管理者":"一般"}</td><td>${e.publicid == "0"?"個別":"共有"}</td><td>${e.status}</td><td><button type="button" class="btn btn-outline-secondary" onclick="editUser('${e.id}','${e.fullname}','${e.publicid}','${e.role}');">編集</button>・${e.status !== "使用中" ? `<button type="button" class="btn btn-outline-primary" onclick="restoreOperation('${e.id}','${e.fullname}','user_infor');">復元</button>` : `<button type="button" class="btn btn-outline-warning" onclick="deleteOperation('${e.id}','${e.fullname}','user_infor');">削除</button>`}</td></tr>`;
      	}
      }); chargeTable.innerHTML = chargeTableHTML;
    })
    .catch(error => {
      // エラーが発生した場合、エラーメッセージを表示
      console.error('エラー:', error);
    });
}
fetchDatacharge();
let purposeData;
// データを取得する関数
function fetchDatapurpose(url) {
  // Fetch APIを使用してデータを取得
  fetch("/ad/purpose")
    .then(response => {
      // レスポンスが成功した場合、JSONデータを解析
      if (!response.ok) {
        throw new Error('ネットワークエラー');
      } 
      return response.json();
    })
    .then(data => {
      // データをコンソールに表示
      purposeData = data;
      purposeData.map((e) => {
        purposeTableHTML += `<tr><td>${e.id}</td><td>${e.tableName}</td><td>${e.status}</td><td><button type="button" class="btn btn-outline-secondary" onclick="editOperation('${e.id}','${e.tableName}','${e.status}','purpose');">編集</button>・${e.status !== "使用中" ? `<button type="button" class="btn btn-outline-primary" onclick="restoreOperation('${e.id}','${e.tableName}','purpose');">復元</button>` : `<button type="button" class="btn btn-outline-warning" onclick="deleteOperation('${e.id}','${e.tableName}','purpose');">削除</button>`}</td></tr>`;
      }); purposeTable.innerHTML = purposeTableHTML;
    })
    .catch(error => {
      // エラーが発生した場合、エラーメッセージを表示
      console.error('エラー:', error);
    });
}
fetchDatapurpose();
