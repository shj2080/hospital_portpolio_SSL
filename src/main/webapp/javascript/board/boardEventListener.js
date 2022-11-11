//수정 버튼을 눌렀을 때 특정 게시글 수정하도록 함
window.onload = () => {
	//수정버튼 클릭 리스너
	document.getElementById("modifyPostBtn").addEventListener("click", () => {
		location.href = "userBoardModifyFormAction.do?post_no=" + post_no;
	});
	//삭제버튼 클릭 리스너
	document.getElementById("deletePostBtn").addEventListener("click", () => {
		//location.href = "userBoardDelete.do?post_no=" + post_no;
		
		$.ajax({
			type: 'POST',  // GET or POST 전송방법 
			url: 'userBoardDelete.aj',  // 이쪽으로 보낸다(호출URL)
			data: {'post_no':post_no},  // 폼 내부 값들 대입
			success: function(result){  // 만약 성공적으로 수행되었다면 result로 값반환
				if(result == "true"){  // 결과가 true인 경우 텍스트 출력
					alert("게시글을 삭제했습니다.");
					location.replace("userBoard.do");
				} else {
					alert('게시글 삭제를 실패했습니다.');
				}
			} 
		});
	});
};