<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/index.css">

<style type="text/css">

section{
	top: 0px;
}

</style>

</head>


<body class="d-flex flex-column min-vh-100">
	<jsp:include page="header.jsp" />
	<div id = "contentWrap">
		<section>
			<div>
				<img src="images/율제병원.jpg" width="100%" height="300px">
			</div>
			
			<fieldset id = "boardBox">
			<legend align="center">공지사항</legend>
				<table class="table table-striped" id = "boardListBox">
					<thead>
						<tr>
							<th style="background-color: #eeeeee; text-align: center;">제목</th>
							<th style="background-color: #eeeeee; text-align: center;">작성자</th>
							<th style="background-color: #eeeeee; text-align: center;">작성일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${boardList != null && boardList.size() > 0}">
							<!-- 1.board 메뉴목록이 있으면 -->
							<c:forEach var="board" items="${boardList}" varStatus="status">
								<!-- 행 시작  -->
								<tr align="center">
									<td><a href="showPost.do?post_no=${board.post_no}">${board.post_subject }</a></td>
									<td>${board.id }</td>
									<td>${board.post_date }</td>
								</tr>
								<!-- 행 끝 -->
							</c:forEach>
						</c:if>
						<c:if test="${boardList == null}">
							<!-- 2.board 메뉴목록이 없으면 -->
							<td colspan="4">등록된 게시글이 없습니다.</td>
						</c:if>
					</tbody>
				</table>
			</fieldset>
			
			<fieldset id = "menuBox">
				<div id = "menu1">예약하러 가기</div>
				<div id = "menu2">대기자명단 보기</div>
				<div id = "menu3">병원소개</div>
				<div id = "menu4">의료진 소개</div>
			</fieldset>
		</section>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>