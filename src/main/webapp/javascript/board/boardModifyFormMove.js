//수정 버튼을 눌렀을 때 특정 게시글 수정하도록 함
window.onload = () => {
	//수정버튼 클릭 리스너
	document.getElementById("modifyPostBtn").addEventListener("click", () => {
		location.href = "userBoardModifyFormAction.do?post_no=" + post_no;
	});
};