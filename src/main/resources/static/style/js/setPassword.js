// メールアドレス入力フィールドを取得
const emailInput = document.querySelector("#email");
// メールアドレスのエラーメッセージを表示する要素を取得
const emailError = document.getElementById("email-error");
// 送信ボタンを取得
const btn = document.getElementById("submit");
// フォーム全体の妥当性をチェックするためのフラグ
let checkForm = true;
// メールアドレス入力フィールドにローカルストレージから取得した値を設定
emailInput.value = localStorage.getItem("sendEmail");
// リンクのhref属性を設定
const linkSend = document.querySelector("#linkSend");
linkSend.href = "/sendtoken?email=" + encodeURIComponent(localStorage.getItem("sendEmail"));
// メールアドレスの妥当性をチェックするためのフラグ
let checkemail = true;
// トークン入力フィールドを取得
const tokenInput = document.querySelector("#token");
// トークンのエラーメッセージを表示する要素を取得
const tokenError = document.getElementById("token-error");
// トークンの妥当性をチェックするためのフラグ
let checktoken = true;
// トークン入力フィールドに入力があるたびに実行されるリスナーを追加
tokenInput.addEventListener("input", function () {
    let token = tokenInput.value;
    // トークンの正規表現パターン
    const tokenPattern = /^[0-9]{6}$/;
    if (tokenPattern.test(token)) {
        // トークンが妥当な場合の処理
        tokenError.textContent = "　";
        checktoken = false;
        tokenInput.classList.remove("is-invalid");
        tokenInput.classList.add("is-valid");
    } else {
        // トークンが妥当でない場合の処理
        checktoken = true;
        tokenError.textContent = "トークンは6文字の数字のみです。";
        tokenInput.classList.remove("is-valid");
        tokenInput.classList.add("is-invalid");
    }
    // フォームの他の入力をチェック
    checkInput();
});
// パスワード入力フィールドを取得
const passwordInput = document.querySelector("#password");
// パスワードのエラーメッセージを表示する要素を取得
const passwordError = document.getElementById("password-error");
// パスワードの妥当性をチェックするためのフラグ
let checkpassword = true;
// パスワード入力フィールドに入力があるたびに実行されるリスナーを追加
passwordInput.addEventListener("input", function () {
    let password = passwordInput.value;
    // パスワードの正規表現パターン
    const passwordPattern = /^[a-zA-Z0-9]{8,18}$/;
    if (passwordPattern.test(password)) {
        // パスワードが妥当な場合の処理
        passwordError.textContent = "　";
        checkpassword = false;
        passwordInput.classList.remove("is-invalid");
        passwordInput.classList.add("is-valid");
    } else {
        // パスワードが妥当でない場合の処理
        checkpassword = true;
        passwordError.textContent = "パスワードは8-18文字の英文字または数字のみです。";
        passwordInput.classList.remove("is-valid");
        passwordInput.classList.add("is-invalid");
    }
    //ボタンの有効性の実行
    checkInput();
});
// パスワード再入力フィールドを取得
const rePasswordInput = document.querySelector("#rePassword");
// パスワード再入力のエラーメッセージを表示する要素を取得
const rePasswordError = document.getElementById("rePassword-error");
// パスワード再入力の妥当性をチェックするためのフラグ
let checkrePassword = true;
// パスワード再入力フィールドに入力があるたびに実行されるリスナーを追加
rePasswordInput.addEventListener("input", function () {
    if (passwordInput.value === rePasswordInput.value) {
        // パスワード再入力が一致する場合の処理
        rePasswordError.textContent = "　";
        checkrePassword = false;
        rePasswordInput.classList.remove("is-invalid");
        rePasswordInput.classList.add("is-valid");
    } else {
        // パスワード再入力が一致しない場合の処理
        checkrePassword = true;
        rePasswordError.textContent = "パスワード再入力が一致しません。";
        rePasswordInput.classList.remove("is-valid");
        rePasswordInput.classList.add("is-invalid");
    }
    //ボタンの有効性の実行
    checkInput();
});
// すべての入力をチェックして送信ボタンの有効性を設定する関数
function checkInput() {
    if (checktoken || checkpassword || checkrePassword) {
        // どれかの入力が妥当でない場合、送信ボタンを無効化
        btn.disabled = true;
    } else {
        // すべての入力が妥当な場合、送信ボタンを有効化
        btn.disabled = false;
    }
}
// ページ読み込み時に入力をチェック
checkInput();
