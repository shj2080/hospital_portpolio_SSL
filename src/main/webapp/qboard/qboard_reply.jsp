<%@page import="vo.QBoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	QBoardBean article=(QBoardBean)request.getAttribute("article");

    String nowPage = (String)request.getAttribute("page");
    String loginID = (String)session.getAttribute("userID"); //세션에 있는 ID불러오기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<title>게시판 답변</title>
<style type="text/css">

h4 {
	margin-top: 1rem;
	padding: 1rem;
	font-size: 2.5rem;
	text-align: center;
}

#commandCell {
	text-align: center;
}

#td_left{
 text-align: center; font-size: 12px;

}
#table1{
	border-color: #e1e4e1;
	width: 55%;
	margin:1rem auto auto auto;
}
 
</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	 <div id = "contentWrap">

	<section id="writeForm">
		<h4>답글</h4>
		<form action="qboardReplyPro.qna" method="post" name="boardform">
			<input type="hidden" name="page"          value="${page}" /> 
			<input type="hidden" name="qboard_num"    value="${article.QBOARD_NUM}">
			<input type="hidden" name="QBOARD_RE_REF" value="${article.QBOARD_RE_REF}"> 
			<input type="hidden" name="QBOARD_RE_LEV" value="${article.QBOARD_RE_LEV}"> 
			<input type="hidden" name="QBOARD_RE_SEQ" value="${article.QBOARD_RE_SEQ}">
			<table border="1" id="table1" class="table table-bordered">
		   		<tr>
					<td id="td_left">회원ID</td>
					<td class="td_right"><%=loginID %></td>
				</tr>
		         <input type="hidden" id="loginID" name="loginID" value="${userID}">
				<tr>
					<td id="td_left"><label for="QBOARD_SUBJECT">제 목</label></td>
					<td class="td_right"><input name="QBOARD_SUBJECT" type="text"
						id="QBOARD_SUBJECT" size="35" class="form-control"/></td>
				</tr>
				<tr>
					<td id="td_left"><label for="QBOARD_CONTENT">내 용</label></td>
					<td><textarea id="QBOARD_CONTENT" name="QBOARD_CONTENT"
							cols="40" rows="10" class="form-control" style="resize: none;"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="답변글등록" class="btn btn-outline-dark" style="font-size:12px;"/>&nbsp;&nbsp;
				<input type="reset" value="다시작성" class="btn btn-outline-dark" style="font-size:12px;"/>&nbsp;&nbsp;
				<a href="javascript:history.go(-1)" class="btn btn-outline-dark" style="font-size:12px;">뒤로</a>
			</section>
		</form>
	</section>
	</div>
		<jsp:include page="../footer.jsp" />
</body>
</html>
