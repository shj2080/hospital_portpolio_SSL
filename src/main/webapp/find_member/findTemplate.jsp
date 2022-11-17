<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원 - 회원정보 찾기 템플릿</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/initStyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/body.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/findMemberStyle.css">
</head>
<body>
<jsp:include page="../header.jsp"/>
	<section id = "contentWrap">
		<%-- 출력될 페이지가 유동적으로 바뀌는 부분 --%>
		<jsp:include page="${showPage}"/>
	</section>
<jsp:include page="../footer.jsp"/>
</body>
</html>