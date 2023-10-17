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
  <form action="/kk/operation/delete?tableName=${tableName}&id=${id}" method="post"><button type="submit" class="btn btn-danger">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "D" + id;
}
function showFormreStore(id, status, tableName) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "purpose" ? "内容" : "担当者"}の復元</h3></div><div class="modal-body">ID: ${id}、 ${status}の使用中に復元のＯＫですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <form action="/kk/operation/restore?tableName=${tableName}&id=${id}" method="post"><button type="submit" class="btn btn-primary">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "R" + id;
}
function showFormEdit(id, textEdit, text, tableName) {
  tableName == "purpose" ? arrayCheck = purposeArray : arrayCheck = chargeArray;
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "purpose" ? "内容" : "担当者"}の編集</h3></div>
	<div id="msgcheck"></div>
	<form action="/kk/operation/edit?id=${id}&tableName=${tableName}" method="post">
  <div class="modal-body">
	<label for="password" class="form-label">番号: <strong>${id}</strong> - ${text}</label>
	<input type="text" class="form-control" id="textEdit" name="textEdit" value="${textEdit}" placeholder="パスワードを入力ください。" required>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  statusBTN = document.getElementById('textEdit');
  statusBTN.addEventListener('input', checkStatus)
  showID = true;
  booleanID = "E" + id;
}
function registerOperation(tableName) {
  tableName == "purpose" ? arrayCheck = purposeArray : arrayCheck = chargeArray;
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${tableName == "purpose" ? "内容" : "担当者"}の新規登録</h3></div>
	<div id="msgcheck"></div>
	<form action="/kk/operation/reg?tableName=${tableName}" method="post">
  <div class="modal-body">
	<label for="password" class="form-label">${tableName == "purpose" ? "内容" : "担当者"}：</label>
	<input type="text" class="form-control" id="textEdit" name="textEdit"  placeholder="${tableName == "purpose" ? "内容" : "担当者"}を入力ください。" required>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">Cancel</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">ＯＫ</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  statusBTN = document.getElementById('textEdit');
  statusBTN.addEventListener('input', checkStatus)
}
function hiddenFormHTML() {
  showForm.style.animationName = "deleleForm";
  showID = false;
  booleanID = 0;
}
let statusBTN;
let arrayCheck;
function checkStatus() {
  let btnsubmit = document.getElementById('btnsubmit');
  let msg = document.getElementById('msgcheck');
  msg.innerText = "";
  btnsubmit.disabled = false;
  arrayCheck.map((e) => {
    if (statusBTN.value.trim() == Object.values(e)[1] && statusBTN.value.trim() != null) {
      msg.innerText = `${statusBTN.value}がありました。`;
      btnsubmit.disabled = true;
    }
  })
}