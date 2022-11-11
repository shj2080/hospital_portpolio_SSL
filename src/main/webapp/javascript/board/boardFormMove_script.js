//
function modifyChk() {

	let modifyBtn = document.getElementById("modifyBtn");
	
	if(modifyBtn !== null) {
		boardWriteForm.action = "userBoardModify.do";
	}
	
	return true;
};