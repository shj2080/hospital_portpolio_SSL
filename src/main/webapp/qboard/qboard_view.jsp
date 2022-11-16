<%@page import="vo.QBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
<title>율제병원 - 문의게시글 보기</title>
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
	margin:1rem auto auto auto;
	width: 60%;
	border-color: black;
	font-size: 1.2rem;
	border-color: #e1e4e1;
}

#commandList {
	margin: auto;
	width: 50rem;
	text-align: center;
	padding-bottom: 2rem;
}

</style>
<script src = "javascript/qnaboard/deleteArticle.js"></script>
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
		<c:if test="${userType == 'M' }">
			<a href="qboardReplyForm.qna?qboard_num=${article.QBOARD_NUM}&page=${page}" class="btn btn-primary fs-4">답변</a> 				   		    
		</c:if>
				<c:if test="${article.MEM_ID == userID }">
				<a href="qboardModifyForm.qna?qboard_num=${article.QBOARD_NUM }" class="btn btn-secondary fs-4">수정</a>
				
				<button type = "button" onclick="deleteArticle(${article.QBOARD_NUM}, ${page})" class="btn btn-danger fs-4">삭제</button>
<%-- 				<a href="qboardDeleteForm.qna?qboard_num=${article.QBOARD_NUM }&page=${page}" class="btn btn-outline-dark" style="font-size:12px;">삭제</a>  --%>
			</c:if>
						
			<%-- <a href="boardList.qna?page=<%=nowPage%>">[목록]</a>&nbsp;&nbsp; --%>
			<a href="qboardList.qna" class="btn btn-outline-dark fs-4" style="font-size:12px;">목록</a>
						
	</section>
	</div>
<jsp:include page="../footer.jsp" />
</body>
</html>
