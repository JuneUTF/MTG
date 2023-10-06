const showForm = document.getElementById("showForm");
let booleanID = 0;
let showID = false;
function deleteOperation(id, nameid, TNode) {
  if (booleanID != ("D" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormDelete(id, nameid, TNode)
    }, 300);
  } else {
    if (!showID) {
      hiddenFormHTML()
    } else {
      hiddenFormHTML()
    }
  }
}
function restoreOperation(id, nameid, TNode) {
  if (booleanID != ("R" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormreStore(id, nameid, TNode)
    }, 300);
  } else {
    if (!showID) {
      showFormreStore(id, nameid, TNode)
    } else {
      hiddenFormHTML()
    }
  }
}

function editOperation(id, nameid, status, TNode) {
  if (booleanID != ("E" + id)) {
    hiddenFormHTML()
    setTimeout(function () {
      showFormEdit(id, nameid, status, TNode)
    }, 300);
  } else {
    if (!showID) {
      showFormEdit(id, nameid, status, TNode)
    } else {
      hiddenFormHTML()
    }
  }
}
function showFormDelete(id, nameid, TNode) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${TNode == "purpose" ? "内容" : "担当者"}の削除</h3></div><div class="modal-body">ID: ${id}、${nameid}の削除に解除の決定ですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">中止</button>
  <form action="/kk/operation/delete?nameID=${TNode}&id=${id}" method="post"><button type="submit" class="btn btn-danger">決定</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "D" + id;
}
function showFormreStore(id, nameid, TNode) {
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${TNode == "purpose" ? "内容" : "担当者"}の復元</h3></div><div class="modal-body">ID: ${id}、 ${nameid}の使用中に復元の決定ですか？</div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">中止</button>
  <form action="/kk/operation/restore?nameID=${TNode}&id=${id}" method="post"><button type="submit" class="btn btn-primary">決定</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showID = true;
  booleanID = "R" + id;
}
function showFormEdit(id, nameId, status, TNode) {
  TNode == "purpose" ? arrayCheck = purposeArray : arrayCheck = chargeArray;
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${TNode == "purpose" ? "内容" : "担当者"}の編集</h3></div>
	<div id="msgcheck"></div>
	<form action="/kk/operation/edit?id=${id}&nameID=${TNode}" method="post">
  <div class="modal-body">
	<label for="password" class="form-label">番号: <strong>${id}</strong> - ${status}</label>
	<input type="text" class="form-control" id="status" name="status" value="${nameId}" placeholder="パスワードを入力ください。" required>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">中止</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">決定</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  statusBTN = document.getElementById('status');
  statusBTN.addEventListener('input', checkStatus)
  showID = true;
  booleanID = "E" + id;
}
function registerOperation(TNode) {
  TNode == "purpose" ? arrayCheck = purposeArray : arrayCheck = chargeArray;
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">${TNode == "purpose" ? "内容" : "担当者"}の新規登録</h3></div>
	<div id="msgcheck"></div>
	<form action="/kk/operation/reg?nameID=${TNode}" method="post">
  <div class="modal-body">
	<label for="password" class="form-label">${TNode == "purpose" ? "内容" : "担当者"}：</label>
	<input type="text" class="form-control" id="status" name="status"  placeholder="${TNode == "purpose" ? "内容" : "担当者"}を入力ください。" required>
  </div>
  <div class="modal-footer"><button type="button" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">中止</button>
  <button type="submit" class="btn btn-primary" id="btnsubmit">決定</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  statusBTN = document.getElementById('status');
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
    if (statusBTN.value.trim() == Object.values(e)[1] && statusBTN.value.trim()!=null) {
      msg.innerText = `${statusBTN.value}がありました。`;
      btnsubmit.disabled = true;
    }
  })
}