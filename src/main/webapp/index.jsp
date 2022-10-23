<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>it's 병원</title>
</head>
<body>
	<div id = "warpper">
	<jsp:include page="header.jsp" />
		<%-- 컨텐츠 표시 영역 시작 --%>
		<section>
			<div>
				<img src="images/병원배경1.jpg">
			</div>
		</section>
		<%-- 컨텐츠 표시 영역 끝 --%>
	<jsp:include page="footer.jsp" />
	</div>
</body>
</html>