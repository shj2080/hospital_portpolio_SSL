 
//수정 버튼을 눌렀을 때 특정 게시글 수정하도록 함
//문서 로드 시 이벤트 리스너 설정
window.onload = function() {
	
	let modifyPostBtn = document.getElementById("modifyPostBtn");
	let deletePostBtn = document.getElementById("deletePostBtn");
	
	//수정버튼 클릭 리스너
	if(modifyPostBtn) {
		modifyPostBtn.addEventListener("click", function(){
			location.href = "userBoardModifyFormAction.do?post_no=" + post_no;
		});
	}
	//삭제버튼 클릭 리스너
	if(deletePostBtn) {
		deletePostBtn.addEventListener("click", function() {
			//location.href = "userBoardDelete.do?post_no=" + post_no;
			if(confirm("이 게시글을 정말 삭제하시겠습니까?")) {
				//fetch 방식으로 삭제
				fetch("userBoardDelete.fe",{
					method: "POST",
					body: "post_no="+post_no,
					headers: {
						"Content-Type": "application/x-www-form-urlencoded"}
				})
				.then(function(response) {
					return response.json();
					})
				.then(function(data) {
					if(data.result === true) {
						alert(data.message);
						location.replace("userBoard.do");
					}
					//응답은 받았으나 실패
					else {
						alert(data.message);
						location.replace("userBoard.do");
					}
				}).catch(function(err) {
					alert("네트워크 문제가 발생했습니다. 잠시 후 다시 시도해 주세요.\n" + err);
				});

			}else {
				return false;
			}

		});
	}
};