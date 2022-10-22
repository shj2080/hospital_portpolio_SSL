<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="styles/defaultStyle.css">
	<link rel="stylesheet" type="text/css" href="styles/style.css">
	<link rel="stylesheet" type="text/css" href="styles/header.css">
</head>
<body>
	<header>
		<nav>
			<ul id = "headerMenu"> <!-- 상단 메뉴바 -->
					<li>
					<c:if test="${userID  == null}">
						<a href = "login.do" >로그인/회원가입</a>
					</li>
					</c:if>
					<c:if test="${userID != null}">
						<a href="#">마이페이지</a>
					</li>
					<li>
						<a href="logout.do">로그아웃</a>
					</li>

					</c:if>

				<li><a href = "#" >의료진소개</a></li>
				<li><a href = "#" >병원소개</a></li>
				<li><a href = "#" >대기자 현황</a></li>
				<li><a href = "#" >접수/예약</a></li>
				<li class = "logo"><a href = "index.jsp" ><img src="images/로고.jpg"></a></li>
			</ul>
		</nav>
	</header>
</body>
</html>