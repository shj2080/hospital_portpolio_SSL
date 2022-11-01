<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
	<!-- 반응형 웹을 위한 기본 태그 부분 -->
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<style>
	.contentTitle {
		font-size: 2rem;
	}
	ul {
		margin-left: 1rem;
		list-style:none;
	}
	section div:nth-child(2) {
		margin-left: 3rem;
		margin-top: 1rem;
		border: 0.1rem solid lightblue;
		width: 30rem;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div>
					<img src="images/병원배경1.jpg" id = "frontimg">
				</div>
				<div>
					<span class = "contentTitle">공지사항</span>
					<ul>
						<li>1111</li>
						<li>12345</li>
						<li>12345</li>
					</ul>
				</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>