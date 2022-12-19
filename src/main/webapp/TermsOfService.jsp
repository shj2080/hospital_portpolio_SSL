<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0"><meta charset="UTF-8">
<title>이용약관</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/init/big_textarea_style.css">
<style>
	#logoImg {
		width: 45rem;
		height: 10rem;
	}
</style>
</head>
<body>

<jsp:include page="header.jsp" />
	<div class = "text-center">
		<img src="images/logo.jpg" id="logoImg">
	</div>
<div class="container">
<section>

<h1>이용약관</h1><br>

<textarea spellcheck="false" class="bigTextareaBox fs-4" readonly>
<jsp:include page = "/siteInfo/TermsOfService.txt"/>
</textarea>
</section>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>