<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>

<link rel="stylesheet" type="text/css" href="style/header.css">

</head>
<body>
	<header>
		<a href = "index.jsp"><h1>로고가들어갈자리</h1></a>
		<nav id = "headermenu">
			<a href = "reservationSelectView.treat"><span id = "menu">진료예약하기</span></a>
			<a href = "treatmentList.do"><span id = "menu">진료대기자명단</span></a>
			<a href = "#"><span id = "menu">병원소개</span></a>
			<a href = "#"><span id = "menu">의료진소개</span></a>
			<!-- <a href = "#"><span id = "menu">메뉴5</span></a>
			<a href = "#"><span id = "menu">메뉴6</span></a> -->
			<c:if test="${userID  == null}">
			<a href = "login.do" ><span id = "menu">로그인/회원가입</span></a>
			</c:if>
			<c:if test="${userID != null}">
			<a href = "logout.do"><span id = "menu">로그아웃</span></a>
			<a href = "mypage.do"><span id = "menu">마이페이지</span></a>
			</c:if>
			</a>
		</nav>
	</header>
</body>
</html>