<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

<!-- <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> -->
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/footer.css"> --%>

<style type="text/css">

body{
	margin: 0;
	padding: 0;
}

footer{
	height: 100px;
}

a {
    color: #842029;
    text-decoration: none;
}

</style>

</head>
<body>
	<div class="mb-5 container-fluid" style="text-align:center";>
		<hr>
		<p><a href = "${pageContext.request.contextPath}/privacyPolicy.jsp"><h3>개인정보처리방침</a> | <a href="TermsOfService.jsp">이용약관</h3></a></p>
		<p>사업자번호 000-00-00000  대표자 : 송호진  서울특별시 종로구 혜화동 율제병원  Tel. 02-xxxx-xxxx Fax. 02-xxxx-xxxx</p>
		COPYRIGHT 2014 YJ hospital. ALL RIGHTS RESERVED.
	</div>
</body>
</html>