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
<!-- favicon 적용 -->
<link rel="manifest" href="${pageContext.request.contextPath}/images/favicon.json">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicons/favicon.ico" type="image/x-icon">
<link rel="icon" href="${pageContext.request.contextPath}/images/favicons/favicon.ico" type="image/x-icon">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> -->

<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css"> --%>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/babel-standalone/6.26.0/babel.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/babel-polyfill/7.10.4/polyfill.min.js"></script>
<!-- required JavaScript polyfills for IE11 -->
<script nomodule>window.MSInputMethodContext && document.documentMode && document.write('<link rel="stylesheet" href="bootstrap_ie/css/bootstrap-ie11.min.css"><script src="https://cdn.jsdelivr.net/combine/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js,npm/ie11-custom-properties@4,npm/element-qsa-scope@1"><\/script><script crossorigin="anonymous" src="https://polyfill.io/v3/polyfill.min.js?features=default%2CNumber.parseInt%2CNumber.parseFloat%2CArray.prototype.find%2CArray.prototype.includes%2CURLSearchParams%2Cfetch"><\/script>');</script>
<script src="${pageContext.request.contextPath}/javascript/polyfill/blob.js"></script>
<script src="${pageContext.request.contextPath}/javascript/polyfill/formdata.min.js"></script>
<!-- Bootstrap JS -->
<script type="text/javascript" src = "${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
	<body>
		
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
			<!-- 로그인한 회원이 관리자라면 진료예약하기 버튼 안 나오게 처리(로그인 안했거나 일반회원이면 출력) -->
			<c:if test="${userID == null or userType == 'N'}">
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/reservationSelectView.treat"><span class = "menu">진료예약하기</span></a>
			</li>
			</c:if>
			<li class="nav-item">
				<a class = "nav-link fs-3" href = "${pageContext.request.contextPath}/treatmentList.do"><span class = "menu">진료대기자명단</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/hospital_introduct.do"><span class = "menu">병원소개</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/medical_staff.do"><span class = "menu">의료진소개</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/qboardList.qna"><span class = "menu">Q&A게시판</span></a>
			</li>
			<li class="nav-item">
				<a class = "nav-link nav-link fs-3" href = "${pageContext.request.contextPath}/userBoard.do"><span class = "menu">공지사항</span></a>
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