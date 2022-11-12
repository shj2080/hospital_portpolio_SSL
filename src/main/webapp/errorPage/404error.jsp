<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>404 Error</title>
<style>
div {
	text-align: center;
}
div > button {
	margin-top: 1.5rem;
	padding: 0.5rem;
}
 img {
 	width:50px;
 	height:50px;
 }
</style>
</head>
<body>
	<header>
	</header>
	<div>
		<h2>404 Error</h2>
		<img src = "${pageContext.request.contextPath}/images/warning.png"><br>
		페이지를 찾을 수 없습니다.<br>
		<button type="button" onclick = "history.back();">뒤로가기</button>
		<button type="button" onclick = "javascript:location.href='index.do'">홈으로</button>
	</div>
	<footer>
	</footer>
</body>
</html>