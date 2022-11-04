<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Title</title>
</head>
<body>
    <div id = "mypageMainView">
        <h2>마이페이지</h2>
        <img src="${pageContext.request.contextPath}/images/stethoscope.jpg"><br><br>
        <h3>
        ${userName}님은 
        <c:if test="${userType eq 'M' }">
        관리자 
        </c:if>
        <c:if test="${userType eq 'N' }">
        일반회원 
        </c:if>
        등급 이십니다.
        </h3>
    </div>
</body>
</html>
