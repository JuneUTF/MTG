
const showForm = document.getElementById("showForm");
let booleanID = 0;
let showID = false;
function deleteOperation(id, status, tableName) {
  if (booleanID != ("D" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormDelete(id, status, tableName)
    }, 300);
  } else {
    if (!showID) {
      hiddenFormHTML()
    } else {
      hiddenFormHTML()
    }
  }
}
function restoreOperation(id, status, tableName) {
  if (booleanID != ("R" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormreStore(id, status, tableName)
    }, 300);
  } else {
    if (!showID) {
      showFormreStore(id, status, tableName)
    } else {
      hiddenFormHTML()
    }
  }
}

function editOperation(id, text, status, tableName) {
  if (booleanID != ("E" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormEdit(id, text, status, tableName)
    }, 300);
  } else {
    if (!showID) {
      showFormEdit(id, text, status, tableName)
    } else {
      hiddenFormHTML()
    }
  }
}
function showFormDelete(id, status, tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "purpose" ? "内容" : "担当者"}の削除</h3></div><div class="modal-body">ID: ${id}、${status}の削除に解除のＯＫですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <form action="/ad/operation/delete?tableName=${tableName}&id=${id}" method="post"><button type="submit" class="btn btn-danger">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "D" + id;
}
function showFormreStore(id, status, tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "purpose" ? "内容" : "担当者"}の復元</h3></div><div class="modal-body">ID: ${id}、 ${status}の使用中に復元のＯＫですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <form action="/ad/operation/restore?tableName=${tableName}&id=${id}" method="post"><button type="submit" class="btn btn-primary">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "R" + id;
}
function showFormEdit(id, textEdit, text, tableName) {
  tableName == "purpose" ? arrayCheck = purposeArray : arrayCheck = chargeArray;
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "purpose" ? "内容" : "担当者"}の編集</h3></div>
	<div id="msgcheck"></div>
	<form action="/ad/operation/edit?id=${id}&tableName=${tableName}" method="post">
  <div class="modal-body">
	<label for="textEdit" class="form-label">番号: <strong>${id}</strong> - ${text}</label>
	<input type="text" class="form-control" id="textEdit" name="textEdit" value="${textEdit}" required>
  <div id="textEdit-error" class="text-danger  mt-1">　</div>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "E" + id;
}
function registerOperation(tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">内容の新規登録</h3></div>
	<div id="msgcheck"></div>
	<form action="/ad/operation/reg?tableName=${tableName}" method="post">
  <div class="modal-body">
	<label for="textEdit" class="form-label">担当者：</label>
	<input type="text" class="form-control" id="textEdit" name="textEdit"  placeholder="内容を入力ください。" required>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
}
function registeruser(tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">担当者の新規登録</h3></div>
	<div id="msgcheck"></div>
	<form action="/ad/operation/reg?tableName=${tableName}" method="post">
  <div class="modal-body">
	<label for="textEdit" class="form-label">メールアドレス：</label>
	<input type="email" class="form-control mt-2" id="email" name="email"  placeholder="メールアドレスを入力ください。" required>
  	<div id="textEdit-error" class="text-danger  mt-1">　</div>
  	<label for="textEdit" class="form-label">名前：</label>
	<input type="text" class="form-control mt-2" id="fullname" name="fullname"  placeholder="名前を入力ください。" required>
	<label for="textEdit" class="form-label">使用範囲：</label>
	<select class="form-select" aria-label="Default select example" name="publicid">
		<option value="1">共有</option>
		<option value="0">個別</option>
	</select>
	<label for="textEdit" class="form-label mt-2">権限：</label>
	<select class="form-select" aria-label="Default select example" name="role">
		<option value="USER">一般</option>
		<option value="ADMIN">管理者</option>
	</select>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  const email = document.getElementById('email')
  const btnsubmit = document.getElementById('btnsubmit');
  btnsubmit.disabled = true;
  email.addEventListener('blur', checkEmail);
  const fullname = document.getElementById('fullname');
  fullname.addEventListener('input', checkFullName);
}
function hiddenFormHTML() {
  showForm.style.animationName = "deleleForm";
  showID = false;
  booleanID = 0;
}
let emailBoolean = false;
let fullnameBoolean = false;
function checkEmail() {
  const textEdit = document.getElementById('textEdit-error');

  if (!isValidEmail(email.value)) {
    email.classList.remove("is-valid");
    email.classList.add("is-invalid");
    textEdit.textContent = "メールアドレスを入力ください。";
    emailBoolean = true;
    return;
  }
  fetch(`/ad/chargeapi?email=${email.value}`)
    .then(response => {
      if (!response.ok) {
        throw new Error('ネットワークエラー');
      }
      return response.json();
    })
    .then(data => {
      if (!JSON.parse(data.isData)) {
        email.classList.remove("is-valid");
        email.classList.add("is-invalid");
        textEdit.textContent = "メールアドレスが存在しています。";
        emailBoolean = true;
      } else {
        email.classList.add("is-valid");
        email.classList.remove("is-invalid");
        textEdit.textContent = "　";
        emailBoolean = false;
      }
    })
    .catch(error => {
      console.error('エラー:', error);
    });
  btnBolean();
}

function checkFullName(){
	if(fullname.value !=null){
	fullnameBoolean=true
	}else{
	fullnameBoolean=false;
	}
	btnBolean();
}
function btnBolean() {
  if (emailBoolean && fullnameBoolean) {
    btnsubmit.disabled = true;
  } else {
    btnsubmit.disabled = false;
  }
}
function isValidEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
}
function editUser(id,fullname,publicid,role){  
  let publicIDHTML;
  if(publicid == 0){
    publicIDHTML = `<option value="0">個別</option>
		                <option value="1">共有</option>`
  }else{
    publicIDHTML = `<option value="1">共有</option>
                    <option value="0">個別</option>`
  }
  let roleHTML;
  if(role == "ADMIN"){
    roleHTML = `<option value="ADMIN">管理者</option>
		        <option value="USER">一般</option>`
  }else{
    roleHTML = `<option value="USER">一般</option>
                <option value="ADMIN">管理者</option>`
  }
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">担当者の編集</h3></div>
	<div id="msgcheck"></div>
	<form action="/ad/operation/edit?id=${id}&tableName=user_infor" method="post">
  <div class="modal-body">
  <label for="textEdit" class="form-label">名前：</label>
	<input type="text" class="form-control" id="fullname" name="fullname" value="${fullname}" required>
  <label for="textEdit" class="form-label　mt-2">使用範囲：</label>
	<select class="form-select" aria-label="Default select example" name="publicid">
		`+publicIDHTML+`
	</select>
  <label for="textEdit" class="form-label mt2">権限：</label>
  <select class="form-select" aria-label="Default select example" name="role">
		`+roleHTML+`
	</select>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "E" + id;
}