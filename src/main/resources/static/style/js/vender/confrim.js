const showForm = document.getElementById("showForm");
let showDetele = true;
let deleteId = 0;
function deleteJob(id,date,time) {
  	if(deleteId!=id){
  		hiddenFormHTML()
  		setTimeout(function() {
  		showFormDelete(id,date,time)
	}, 300);
  	}else{
  if(showDetele){
    showFormDelete(id,date,time)
  }else{
    hiddenFormHTML()
  } }
}
let restoreId = 0;
let showRestore = true;

function restoreJob(id,date,time) {
  if(restoreId!=id){
    hiddenFormHTML()
    setTimeout(function() {
    showFormreStore(id,date,time)
}, 300);
  }else{
if(showRestore){
  showFormreStore(id,date,time)
}else{
  hiddenFormHTML()
} }
}

function showFormDelete(id,date,time){
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">予約内容削除</h3></div><div class="modal-body">ID: ${id} ${date}の${time}に解除の決定ですか？</div>
  <div class="modal-footer"><button type="button" id="deleteNG" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">中止</button>
  <form action="/kk/job/delete?id=${id}" method="post"><button type="submit" class="btn btn-danger">決定</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
  showRestore = false;
  deleteId = id;
}
function showFormreStore(id,date,time){
  let showHTML = `<div class="modal-dialog"><div class="modal-content"><div class="modal-header"><h3 class="modal-title">予約内容復元</h3></div><div class="modal-body">ID: ${id} ${date}の${time}に復元の決定ですか？</div>
  <div class="modal-footer"><button type="button" id="deleteNG" class="btn btn-secondary" onclick="hiddenFormHTML()" data-dismiss="modal">中止</button>
  <form action="/kk/job/restore?id=${id}" method="post"><button type="submit" class="btn btn-primary">決定</button></form></div></div></div>`;
  showForm.innerHTML = showHTML;
  showForm.style.animationName = "showForm";
 　showRestore = false;
  restoreId = id;
}
function hiddenFormHTML(){
  showForm.style.animationName = "deleleForm";
  showDetele = true;
  showRestore　= true;
}