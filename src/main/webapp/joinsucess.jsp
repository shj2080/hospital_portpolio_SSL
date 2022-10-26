<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

body {
	margin: 0px;
	padding: 0px;
}



</style>

</head>
<body>
	<div id = "warpper">
	<jsp:include page="header.jsp" />
		<%-- 컨텐츠 표시 영역 시작 --%>
		<section id = "joinsucess">
			<h1 align="center">회원가입에 성공하셨습니다!!!!</h1>
			<div align="center"><a href = "index.jsp"><input type = "button" value="홈으로"></a></div>
		</section>
		<%-- 컨텐츠 표시 영역 끝 --%>
	<jsp:include page="footer.jsp" />
</body>
</html>