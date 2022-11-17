<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script>  -->
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/qnaboard/qboardModify.css">
<title>율제병원 - 게시글 수정</title>
</head>
<body>
<jsp:include page="../header.jsp" />
	 
<div id = "contentWrap">

<section>
	<div id = "writeForm">
	
	<form action="qboardModifyPro.qna" method="post" name = "modifyform">
	<input type = "hidden" name = "QBOARD_NUM" value = "${article.QBOARD_NUM}"/>
	
	<span class="tableTitle">게시판글수정</span><br>
	<table border="1" id="table-first" class="table table-bordered fs-4">
		<tr>
			<td id="td_left">
				<label for = "QBOARD_NAME">작성자 ID</label>
			</td>
			<td>
				 ${article.MEM_ID}
			</td>
		</tr>
	
		<tr>
			<td id="td_left">
				<label for = "QBOARD_SUBJECT" >제 목</label>
			</td>
			<td>
				<input name="QBOARD_SUBJECT" type="text" id = "QBOARD_SUBJECT" value = "${article.QBOARD_SUBJECT}"  class="form-control fs-4" required="required"/>
			</td>
		</tr>	
		<tr>
			<td id="td_left">
				<label for = "QBOARD_CONTENT">내 용</label>
			</td>
			<td>
				<textarea id = "QBOARD_CONTENT" name="QBOARD_CONTENT" cols="60" rows="12"  class="form-control fs-4" required="required">${article.QBOARD_CONTENT}</textarea>
			</td>
		</tr>
	
	</table>
		<div id = "commandCell">
		         <%--로그인 한 사람의 본인의 작성글만 수정할 수 있는 조건문 --%>
		         <c:choose>
		         	<c:when test="${userID == article.MEM_ID }">
		         		<button type = "submit" class="btn btn-outline-dark fs-4">수정</button>&nbsp;&nbsp;
		         	</c:when>
		         	<c:otherwise>
		         		<button type = "button" onclick="" class="btn btn-outline-dark fs-4" disabled>수정</button>&nbsp;&nbsp;
		         	</c:otherwise>
		         </c:choose>
				
				<a href="javascript:history.back();" class="btn btn-outline-dark fs-4">뒤로</a>
		</div> 
	</form>
	</div>
</section>
</div>
<jsp:include page="../footer.jsp" />

</body>
</html>
