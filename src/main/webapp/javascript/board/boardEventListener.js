//수정 버튼을 눌렀을 때 특정 게시글 수정하도록 함
window.onload = () => {
	//수정버튼 클릭 리스너
	document.getElementById("modifyPostBtn").addEventListener("click", () => {
		location.href = "userBoardModifyFormAction.do?post_no=" + post_no;
	});
	//삭제버튼 클릭 리스너
	document.getElementById("deletePostBtn").addEventListener("click", () => {
		//location.href = "userBoardDelete.do?post_no=" + post_no;
		
		//fetch 방식으로 삭제
		fetch("userBoardDelete.fe",{
			method: "POST",
			body: "post_no="+post_no,
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"}
		})
		.then((response) => response.json())
		.then((data) => {
			if(data.result == true) {
				alert("게시글을 삭제했습니다.");
				location.replace("userBoard.do");
			}
			else {
				alert('게시글 삭제를 실패했습니다.');
			}
		});
	
	});
};