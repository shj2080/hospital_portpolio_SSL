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
	top: 0;
}

</style>
<!-- 현재 페이지가 Servlet을 거쳤는지 확인 -->
<script type = "text/javascript" src = "${pageContext.request.contextPath}/javascript/indexCheck.js"></script>
</head>


<body class="d-flex flex-column min-vh-100">
	<!-- header.jsp불러오기 -->
	<jsp:include page="header.jsp" />
	
	<div id = "contentWrap">
		<!-- 컨텐츠 시작 위치 -->
		<section>
			<!-- 병원 메인 이미지 -->
			<div>
				<img src="images/율제병원.jpg" width="100%" height="300px">
			</div>
			<!-- 병원 메인 이미지 끝 -->
			
			<!-- 홈페이지 공지사항 시작 -->
			<div id = "flexBox">
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
				<!-- 홈페이지 공지사항 끝 -->
				
				<!-- 바로가기 메뉴 시작 -->
				<fieldset id = "menuBox">
					<a href = "${pageContext.request.contextPath}/reservationSelectView.treat"><div id = "menu1">진료예약</div></a>
					<a href = "${pageContext.request.contextPath}/treatmentList.do"><div id = "menu2">대기자명단 보기</div></a>
					<a href = "#"><div id = "menu3">병원소개</div></a>
					<a href = "${pageContext.request.contextPath}/userBoard.do"><div id = "menu4">공지사항</div></a>
				</fieldset>
				<!-- 바로가기 메뉴 끝 -->
				
			</div>
		</section>
		<!-- 컨텐츠 끝나는 위치 -->
	</div>
	
	<!-- footer.jsp 불러오기 -->
	<jsp:include page="footer.jsp" />
</body>
</html>