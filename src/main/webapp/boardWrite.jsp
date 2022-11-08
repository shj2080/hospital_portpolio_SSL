<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원</title>
	<link rel="stylesheet" type="text/css" href="style/initStyle.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style/header.css">
	<link rel="stylesheet" type="text/css" href="style/body.css">
	<link rel="stylesheet" type="text/css" href="style/footer.css">
	<link rel="stylesheet" type="text/css" href="style/board.css">
</head>
<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div class="row">
				<form method="post" action="userBoardWriteAction.do">
						<table class="table table-striped" id = "boardWriteBox">
							<thead>
								<tr>
									<th class = "fs-4"colspan ="2" style="background-color : #eeeeee; text-align : center;">게시판 글쓰기 양식</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th class = "fs-4">제목</th>
									<td><input type="text" class="form-control fs-4" placeholder="글제목" name="post_subject" maxlength="50" style="width: 480px;" required></td>
								</tr>
								<tr>
									<th class = "fs-4">ID</th>
									<td>
										<input type="text" class="form-control fs-4" name="id" value = "${userID}" style="width: 480px;" readonly>
									</td>
								</tr>
								<tr>
									<th class = "fs-4" colspan="2">내용</th>
								</tr>
								<tr>
									<td class = "fs-4" colspan="2"><textarea  class="form-control fs-4" placeholder="글 내용" name="post_text" maxlength="500" style="height:350px;"></textarea></td>
								</tr>	
								<tr>
									<th class = "fs-4">비밀번호</th>
									<td><input type="password" class="form-control fs-4" placeholder="글 비밀번호" name="post_pwd" maxlength="50"></td>
								</tr>
<%-- 								유저타입이 'M'인 경우(관리자)에만 출력되는 메뉴
								<c:if test="${userType == 'M'}">
									<tr>
										<th colspan="2" class = "fs-4">
										공지사항 등록
										<input type="checkbox" name = "notice_status" value = "1"/>
										</th>
									</tr>
								</c:if> --%>
							</tbody>
						</table>
					<div align="center">
					<input type="submit"class="btn btn-primary pull-right fs-4" value="글쓰기">
					</div>
				</form>
				</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>