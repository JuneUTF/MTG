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
            purposeHTML = purposeHTML + `<option value="${e.purpose}">${e.purpose}</option>`;
        });
        purposeHTML = `<select id="purpose" name="purpose"><option value="">全員</option>${purposeHTML}</select>`;
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
            chargeHTML = chargeHTML + `<option value="${e.charge}">${e.charge}</option>`;
        });
        chargeHTML = `<select id="charge" name="charge"><option value="">全部</option>${chargeHTML}</select>`;
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