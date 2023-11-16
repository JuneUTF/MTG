const password = document.getElementById("password");
const newpassword = document.getElementById("newpassword");
const repassword = document.getElementById("repassword");
let passwordBoolean = false;
let newpasswordBoolean = false;
let rePasswordBoolean = false;
const regex = /^[a-zA-Z0-9]{8,16}$/;
password.addEventListener('input', () => {
    if (!regex.test(password.value)) {
        document.getElementById("password-error").textContent = "パスワードは8-16文字の英字または数字でなければなりません。";
        passwordBoolean = false;
        password.classList.add("is-invalid");
        password.classList.remove("is-valid");
    } else {
        document.getElementById("password-error").textContent = "　";
        passwordBoolean = true;
        password.classList.remove("is-invalid");
        password.classList.add("is-valid");
    }
    checkBtn();
});

newpassword.addEventListener('input',()=> {
    if (!regex.test(newpassword.value)) {
        document.getElementById("newpassword-error").textContent = "パスワードは8-16文字の英字または数字でなければなりません。";
        newpasswordBoolean = false;
        newpassword.classList.add("is-invalid");
        newpassword.classList.remove("is-valid");
    } else {
        document.getElementById("newpassword-error").textContent = "　";
        newpasswordBoolean = true;
        newpassword.classList.remove("is-invalid");
        newpassword.classList.add("is-valid");
    }
    checkBtn();
    checkRePass()
})
repassword.addEventListener('input', checkRePass);
function checkRePass() {
    if (newpassword.value !== repassword.value) {
        document.getElementById("repassword-error").textContent = "再パスワードが一致しません.";
        rePasswordBoolean = false;
        repassword.classList.add("is-invalid");
        repassword.classList.remove("is-valid");
    } else {
        document.getElementById("repassword-error").textContent = "　";
        rePasswordBoolean = true;
        repassword.classList.remove("is-invalid");
        repassword.classList.add("is-valid");
    }
    checkBtn();
}

const btn =  document.getElementById('submit');
btn.disabled = true;
function checkBtn() {
    if (passwordBoolean && newpasswordBoolean && rePasswordBoolean) {
        btn.disabled = false;
    } else {
        btn.disabled = true;
    }
}

const showPasswordCheckbox = document.getElementById('showPassword');
const inputFields = document.querySelectorAll('input[type="password"]');
showPasswordCheckbox.addEventListener('change',()=> {
    if (showPasswordCheckbox.checked) {
        inputFields.forEach(function(input) {
            input.type = 'text';
        });
    } else {
        inputFields.forEach(function(input) {
            input.type = 'password'; 
        });
    }
});
