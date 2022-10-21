<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<c:if test="${userID  == null}">
		<a href="login.do">로그인</a>
	</c:if>
	
	<c:if test="${userName  != null}">
		${userName }님 환영합니다. <a href="logout.do">로그아웃</a>
	</c:if>
</body>
</html>