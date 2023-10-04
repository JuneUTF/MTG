// フォーム要素のメールアドレス入力フィールドを取得
const emailInput = document.getElementById("email");
// メールアドレスのエラーメッセージを表示する要素を取得
const emailError = document.getElementById("email-error");
// 送信ボタンを取得
const btn = document.getElementById("submit");
// メールアドレスの妥当性をチェックするためのフラグ
let checkemail = true;
// メールアドレス入力フィールドに入力があるたびに実行されるリスナーを追加
emailInput.addEventListener("input", function () {
    // 入力されたメールアドレスを取得
    let email = emailInput.value;
    // メールアドレスの正規表現パターン
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    // 正規表現パターンに一致するかをチェック
    if (emailPattern.test(email)) {
        // メールアドレスが妥当な場合の処理
        emailError.textContent = "　"; // エラーメッセージを空白に設定
        checkemail = false; // メールアドレスは妥当
        emailInput.classList.remove("is-invalid"); // 不正な入力スタイルを削除
        emailInput.classList.add("is-valid"); // 妥当な入力スタイルを追加
    } else {
        // メールアドレスが妥当でない場合の処理
        checkemail = true; // メールアドレスは妥当でない
        emailError.textContent = "メールアドレス型を入力ください。"; // エラーメッセージを設定
        emailInput.classList.remove("is-valid"); // 妥当な入力スタイルを削除
        emailInput.classList.add("is-invalid"); // 不正な入力スタイルを追加
    }
    // フォームの他の入力をチェック
    checkInput();
});
// すべての入力をチェックして送信ボタンの有効性を設定する関数
function checkInput() {
    if (checkemail) {
        // メールアドレスが妥当でない場合、送信ボタンを無効化
        btn.disabled = true;
    } else {
        // メールアドレスが妥当な場合、送信ボタンを有効化
        btn.disabled = false;
    }
}
// ページ読み込み時に入力をチェック
checkInput();
// 送信ボタンにクリックイベントリスナーを追加し、メールアドレスをローカルストレージに保存
btn.addEventListener("click", function (event) {
    localStorage.setItem("sendEmail", emailInput.value);
});
