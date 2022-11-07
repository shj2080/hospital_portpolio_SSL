<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/body.css">

<style type="text/css">

section{
	top: 0px;
}

</style>

</head>


<body class="d-flex flex-column min-vh-100">
	<jsp:include page="header.jsp" />
	<div id = "contentWrap">
		<section>
			<div>
				<img src="images/율제병원.jpg" width="100%" height="300px">
			</div>
		</section>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>