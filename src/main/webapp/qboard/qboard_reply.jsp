<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<link rel="stylesheet" type="text/css" href="style/qnaboard/qboardModify.css">
<title>율제병원 문의게시판 - 답변</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	 <div id = "contentWrap">

	<section id="writeForm">
		<span class="tableTitle">답글</span><br>
		<form action="qboardReplyPro.qna" method="post" name="boardform">
			<input type="hidden" name="page"          value="${page}" /> 
			<%-- <input type="hidden" name="qboard_num"    value="${article.QBOARD_NUM}"> --%>
			<input type="hidden" name="QBOARD_RE_REF" value="${article.QBOARD_RE_REF}"> 
			<input type="hidden" name="QBOARD_RE_LEV" value="${article.QBOARD_RE_LEV}"> 
			<input type="hidden" name="QBOARD_RE_SEQ" value="${article.QBOARD_RE_SEQ}">
			<input type="hidden" id="loginID" name="loginID" value="${userID}">
			<table border="1" id="table-first" class="table table-bordered">
		   		<tr>
					<td id="td_left">회원ID</td>
					<td class="td_right fs-4">${userID}</td>
				</tr>
				<tr>
					<td id="td_left"><label for="QBOARD_SUBJECT">제 목</label></td>
					<td class="td_right"><input name="QBOARD_SUBJECT" type="text"
						id="QBOARD_SUBJECT" size="35" class="form-control fs-4" required="required"/></td>
				</tr>
				<tr>
					<td id="td_left"><label for="QBOARD_CONTENT">내 용</label></td>
					<td><textarea id="QBOARD_CONTENT" name="QBOARD_CONTENT"
							cols="40" rows="10" class="form-control fs-4" style="resize: none;" required="required"></textarea></td>
				</tr>
			</table>
			<div id="commandCell">
				<input type="submit" value="답변 등록" class="btn btn-primary fs-4"/>&nbsp;&nbsp;
				<input type="reset" value="다시 작성" class="btn btn-outline-dark fs-4"/>&nbsp;&nbsp;
				<a href="javascript:history.go(-1)" class="btn btn-outline-dark fs-4">뒤로</a>
			</div>
		</form>
	</section>
	</div>
		<jsp:include page="../footer.jsp" />
</body>
</html>
