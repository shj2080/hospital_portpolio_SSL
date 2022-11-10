//
function modifyChk() {

	let modifyBtn = document.getElementById("modifyBtn");
	
	if(modifyBtn !== null) {
		boardWriteForm.action = "userBoardModifyAction.do";
	}
	
	return true;
};