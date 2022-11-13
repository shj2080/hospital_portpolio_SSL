//
function modifyChk() {

	event.preventDefault();
	let formAction = "userBoardWriteAction.fe";

	let modifyBtn = document.getElementById("modifyBtn");
	
	let boardForm = document.querySelector("#boardWriteForm");
	let boardFormData = new FormData(boardForm);
	
	for (var i = 0; i < filesArr.length; i++) {
	    // 삭제되지 않은 파일만 폼데이터에 담기
	    if (!filesArr[i].is_delete) {
	        boardFormData.append("post_file", filesArr[i]);
	    }
	}

	
	if(modifyBtn !== null) {
		formAction = "userBoardModifyAction.fe";
	}
	
	//const boardFormDataQueryString = new URLSearchParams(boardFormData).toString();
	
	fetch(formAction,{
		method: "POST",
		body: boardFormData,
		cache: "no-cache",
		headers: {}
	})
	.then((response) => {
		//console.log(response);
		return response.json();
		
		})
	.then((data) => {
		//console.log(data);
		
		if(data.type == "ok") {
			alert(data.message);
			location.replace("showPost.do?post_no="+data.result.post_no);
		}
		else if (data.type == "login") {
			alert(data.message);
			location.replace("login.do");
		}
		else {
			alert(data.message);
			location.replace("userBoard.do");
		}
		
	})
	.catch((err) => {
		console.warn("Fetch Error:" + err);
	});
	
	return true;
};