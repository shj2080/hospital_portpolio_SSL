<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원 - 회원 탈퇴 완료</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/initStyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/body.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/footer.css">
<!-- 자바스크립트에서 contextPath 사용하기 위한 구문 -->
<script>const contextPath = "${pageContext.request.contextPath}";</script>
<script src = "${pageContext.request.contextPath}/javascript/timeoutMainPage.js"></script>
</head>
<body>
	<jsp:include page="/header.jsp"/>
	<div id = "contentWrap">
	<section>
		<div class="text-center">
		회원탈퇴가 완료되었습니다. 그동안 저희 병원을 이용해주셔서 감사드립니다.<br>
		<span id = "time"></span> 초 후 메인페이지로 이동합니다.
		</div>
	</section>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>