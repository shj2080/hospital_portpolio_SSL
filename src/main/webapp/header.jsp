<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css">
		
</head>
	<body>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src = "${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>

		<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
		<div class="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/index.do"><img src="${pageContext.request.contextPath}/images/logo2.png" width="50px" height="50px;"></a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent"
					aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
		<div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
		<ul class="navbar-nav">

			<!-- 로그인 여부에 관계없이 항상 출력되는 메뉴 -->
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/reservationSelectView.treat"><span class = "menu">진료예약하기</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link fs-3" href = "${pageContext.request.contextPath}/treatmentList.do"><span class = "menu">진료대기자명단</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/introduce.do"><span class = "menu">병원소개</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "#"><span class = "menu">의료진소개</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/qboardList.qna"><span class = "menu">Q&A게시판</span></a>
			</li>

			<!-- userID가 null일 떄만 출력되는 메뉴-->
			<c:if test="${userID  == null}">
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/login.do" ><span class = "menu">로그인/회원가입</span></a>
			</li>
			</c:if>

			<!-- userID가 null이 아닐때만 출력되는 메뉴 -->
			<c:if test="${userID != null}">
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/userBoard.do"><span class = "menu">공지사항</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/logout.do"><span class = "menu">로그아웃</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/mypage.do"><span class = "menu">마이페이지</span></a>
			</li>
			</c:if>
		</ul>
		</div>
		</div>
		</nav>
    </body>
</html>