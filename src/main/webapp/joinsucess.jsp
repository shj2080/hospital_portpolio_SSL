<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>율제병원</title>

<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/joinSucess.css">
</head>
<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<%-- 컨텐츠 표시 영역 시작 --%>
		<section id = "joinsucess">
			<div>
				<h1 align="center">회원가입에 성공하셨습니다!!!!</h1>
				<div align="center">
					<a href = "index.do">
						<input type = "button" class = "btn btn-primary fs-4" value="홈으로">
					</a>
				</div>
			</div>
		</section>
		<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>