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
// 内容情報を取得する非同期関数
async function callPurpose() {
    try {
        const apiUrl = "/purpose";
        const response = await fetch(apiUrl);
        if (!response.ok) {
            //APIを呼び出しできない場合 input box 表示されます。
            purposeHTML = `<input type="text" id="purpose" name="purposeId" placeholder="内容を入力ください。" required>`;
        }
        purposeArray = await response.json();
        purposeArray.map((e) => {
            //APIを呼び出し場合 選択ボックスを表示されます。
            purposeHTML = purposeHTML + `<option value="${e.id}">${e.purpose}</option>`;
        });
        purposeHTML = `<select id="purpose" name="purposeId"><option value="0">全員</option>${purposeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        purposeHTML = `<input type="text" id="purpose" name="purposeId" placeholder="内容を入力ください。" required>`;
    }
}
let chargeHTML = '';
let chargeTableHTML = '';
// 担当者を取得する非同期関数
async function callCharge() {
    try {
        const apiUrl = "/charge";
        const response = await fetch(apiUrl);
        if (!response.ok) {
            //APIを呼び出しできない場合 input box 表示されます。
            chargeHTML = `<input type="text" id="charge" name="chargeId" placeholder="内容を入力ください。" required>`;
        }
        chargeArray = await response.json();
        chargeArray.map((e) => {
            //APIを呼び出し場合 選択ボックスを表示されます。
            chargeHTML = chargeHTML + `<option value="${e.id}">${e.charge}</option>`;
        });
        chargeHTML = `<select id="charge" name="chargeId"><option value="0">全部</option>${chargeHTML}</select>`;
    } catch (error) {
        //APIを呼び出しできない場合 input box 表示されます。
        chargeHTML = `<input type="text" id="charge" name="chargeId" placeholder="内容を入力ください。" required>`; purpose
    }
}
// 内容情報と担当者を非同期で取得し、フィールドに設定
(async () => {
    await callPurpose();
    purpose.innerHTML = purposeHTML;
    await callCharge();
    charge.innerHTML = chargeHTML;
    //await callJob();
})();

purposeData.map((e) => {
    purposeTableHTML += `<tr><td>${e.id}</td><td>${e.tableName}</td><td>${e.status}</td><td><button type="button" class="btn btn-outline-secondary" onclick="editOperation('${e.id}','${e.tableName}','${e.status}','purpose');">編集</button>・${e.status !== "使用中" ? `<button type="button" class="btn btn-outline-primary" onclick="restoreOperation('${e.id}','${e.tableName}','purpose');">復元</button>` : `<button type="button" class="btn btn-outline-warning" onclick="deleteOperation('${e.id}','${e.tableName}','purpose');">削除</button>`}</td></tr>`;
});
chargeData.map((e) => {
    chargeTableHTML += `<tr><td>${e.id}</td><td>${e.tableName}</td><td>${e.status}</td><td><button type="button" class="btn btn-outline-secondary" onclick="editOperation('${e.id}','${e.tableName}','${e.status}','charge');">編集</button>・${e.status !== "使用中" ? `<button type="button" class="btn btn-outline-primary" onclick="restoreOperation('${e.id}','${e.tableName}','charge');">復元</button>` : `<button type="button" class="btn btn-outline-warning" onclick="deleteOperation('${e.id}','${e.tableName}','charge');">削除</button>`}</td></tr>`;
});
purposeTable.innerHTML = purposeTableHTML;
chargeTable.innerHTML = chargeTableHTML;
