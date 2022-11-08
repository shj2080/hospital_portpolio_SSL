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

<link rel="stylesheet" type="text/css" href="style/header.css">
		
</head>
	<body>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src = "bootstrap/js/bootstrap.bundle.min.js"></script>

		<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.do"><img src="images/logo2.png" width="50px" height="50px;"></a>
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
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "reservationSelectView.treat"><span class = "menu">진료예약하기</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link fs-3" href = "treatmentList.do"><span class = "menu">진료대기자명단</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "introduce.do"><span class = "menu">병원소개</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "#"><span class = "menu">의료진소개</span></a>
			</li>
<!-- 			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "qboardList.qna"><span class = "menu">TEMP게시판</span></a>
			</li> -->
			<c:if test="${userID  == null}">
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "login.do" ><span class = "menu">로그인/회원가입</span></a>
			</li>
			</c:if>
			<c:if test="${userID != null}">
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "userBoard.do"><span class = "menu">게시판</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "logout.do"><span class = "menu">로그아웃</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "mypage.do"><span class = "menu">마이페이지</span></a>
			</li>
			</c:if>
		</ul>
		</div>
		</div>
		</nav>
    </body>
</html>