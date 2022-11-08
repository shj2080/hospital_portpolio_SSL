<%@page import="vo.QBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	QBoardBean article = (QBoardBean)request.getAttribute("article");
	
    String nowPage = (String)request.getAttribute("page");
    String loginID = (String)session.getAttribute("userID"); //세션에 있는 ID불러오기 
    //String mem_grade = (String)session.getAttribute("mem_grade");
%>

<!DOCTYPE html>
<html>

<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<title>게시글 보기</title>
<style type="text/css">


h4 {
	text-align: center;
	margin-top: 1rem;
	padding: 1rem;
	font-size: 2.5rem;
	}

#staticMap{
 margin:0 auto;
}


#tdleft{
   text-align: center;
}

#table-first{
	margin:1rem auto auto auto; width: 60%; border-color: black; font-size: 12px; border-color: #e1e4e1;
}

#commandList {
	margin: auto;
	width: 500px;
	text-align: center;
	padding-bottom: 20px;
}

</style>
<script src = "javascript/qnaboard/"></script>
<script>
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
</script>
</head>

<body>

	<jsp:include page="../header.jsp" />

	<div id = "contentWrap">
	<!-- 게시판 수정 -->
	<section>
		 <h4>게시판 글보기</h4>
		  <table id="table-first" class="table table-bordered">
			  <tr>
				  <td id="tdleft">작성자 ID</td>
				  <td>${article.MEM_ID}</td>
			  </tr>
		  
			  <tr>
				  <td id="tdleft">제 목 </td>
				  <td>${article.QBOARD_SUBJECT}</td>
			  </tr>	
			  <tr>
			  	<td id="tdleft">내용</td>
			  	<td>
			  		<textarea cols="70" rows="12" class="form-control" style="font-size:12px;" readonly><c:out value="${article.QBOARD_CONTENT }"/></textarea>
			  	</td>
			  </tr>

			</table>
	</section>

	<section id="commandList">
		      <a href="qboardReplyForm.qna?qboard_num=${article.QBOARD_NUM}&page=${page}" class="btn btn-outline-dark" style="font-size:12px;">답변</a> 				   		    
	
				<c:if test="${article.MEM_ID == userID }">
				<a href="qboardModifyForm.qna?qboard_num=${article.QBOARD_NUM }" class="btn btn-outline-dark" style="font-size:12px;">수정</a>
				
				<button type = "button" onclick="deleteArticle(${article.QBOARD_NUM}, ${page})" class="btn btn-outline-dark fs-5">삭제</button>
<%-- 				<a href="qboardDeleteForm.qna?qboard_num=${article.QBOARD_NUM }&page=${page}" class="btn btn-outline-dark" style="font-size:12px;">삭제</a>  --%>
			</c:if>
						
			<%-- <a href="boardList.qna?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp; --%>
			<a href="qboardList.qna" class="btn btn-outline-dark" style="font-size:12px;">목록</a>
						
	</section>
	</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
