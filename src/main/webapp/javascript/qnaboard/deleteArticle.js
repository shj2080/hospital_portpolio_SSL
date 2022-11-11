function deleteArticle(board_num, nowPage) {
	if(confirm("이 게시글을 삭제하시겠습니까?")) {
		
		//동적 form 객체 생성
		let deleteArticleForm = document.createElement("form");

		//from 내장값 설정
		deleteArticleForm.name = "deleteArticleForm";
		deleteArticleForm.method = "post";
		deleteArticleForm.action = "qboardDeletePro.qna";

		//전송에 필요한 hidden inpurt 태그 생성
		let boardCode_data = document.createElement("input");
		boardCode_data.setAttribute("type", "hidden");
		boardCode_data.setAttribute("name", "qboard_num");
		boardCode_data.setAttribute("value", board_num);

		let nowPage_data = document.createElement("input");
		nowPage_data.setAttribute("type", "hidden");
		nowPage_data.setAttribute("name", "page");
		nowPage_data.setAttribute("value", nowPage);

		
		//input 태그를 폼에 추가
		deleteArticleForm.appendChild(boardCode_data);
		deleteArticleForm.appendChild(nowPage_data);

		//폼 요소를 body에 추가함
		document.body.appendChild(deleteArticleForm);

		//추가된 form을 submit
		deleteArticleForm.submit();
	}
	
}