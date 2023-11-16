// 送信ボタンを取得
const btn = document.getElementById("submit");
// パスワード入力フィールドを取得
const passwordInput = document.getElementById("password");
// パスワードのエラーメッセージを表示する要素を取得
const passwordError = document.getElementById("password-error");
// パスワードの妥当性をチェックするためのフラグ
let checkPassword = true;
// パスワード入力フィールドに入力があるたびに実行されるリスナーを追加
passwordInput.addEventListener("input", function () {
    // 入力されたパスワードを取得
    let password = passwordInput.value;
    // パスワードの正規表現パターン
    const passwordPattern = /^[a-zA-Z0-9]{8,18}$/;
    // 正規表現パターンに一致するかをチェック
    if (passwordPattern.test(password)) {
        // パスワードが妥当な場合の処理
        checkPassword = false; // パスワードは妥当
        passwordError.textContent = "　"; // エラーメッセージを空白に設定
        passwordInput.classList.remove("is-invalid"); // 不正な入力スタイルを削除
        passwordInput.classList.add("is-valid"); // 妥当な入力スタイルを追加
    } else {
        // パスワードが妥当でない場合の処理
        checkPassword = true; // パスワードは妥当でない
        passwordError.textContent = "パスワードは8-18文字の英文字または数字のみです。"; // エラーメッセージを設定
        passwordInput.classList.remove("is-valid"); // 妥当な入力スタイルを削除
        passwordInput.classList.add("is-invalid"); // 不正な入力スタイルを追加
    }
    // フォームの他の入力をチェック
    checkInput();
});
// すべての入力をチェックして送信ボタンの有効性を設定する関数
function checkInput() {
    if (checkPassword) {
        // パスワードが妥当でない場合、送信ボタンを無効化
        btn.disabled = true;
    } else {
        // パスワードが妥当な場合、送信ボタンを有効化
        btn.disabled = false;
    }
}
// ページ読み込み時に入力をチェック
checkInput();
