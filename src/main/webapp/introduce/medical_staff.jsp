<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의료진소개</title>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/initStyle.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/footer.css"> --%>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/introduce/medical_staff.css">
	<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/body.css"> --%>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/introduce/hospital_introduct.css">
	
</head>
<body>
<%-- <jsp:include page="/header.jsp" /> --%>
<!-- <div id = "contentWrap"> -->
	<section>
	<p>
<h1>의료진소개</h1>
</p>
<hr size="6" width="70%" align="left" color="blue">
<div class="container">
  <div class="row">
    <div class="col-sm-7">
   <div class="s">
			<img src="${pageContext.request.contextPath}/images/introduct/조정석.jpg" style="width:100px; height:150px;">
			</div>
			<div class="mc">
		<h3>조정석</h3><h4>교수</h4>
		<div class="treatTime">진료가능일정</div>
		<img src="${pageContext.request.contextPath}/images/introduct/일정.png">
		<div class="treatment">간담최외과</div>
		</div>
    </div>
    <div class="col-sm-5">
    <div class="s">
		<img src="${pageContext.request.contextPath}/images/introduct/석형.jpg" style="width:100px; height:150px;">
	</div>
	<div class="mc">
		<h3>양석현</h3><h4>교수</h4>
		<div class="treatTime">진료가능일정</div>
		<img src="${pageContext.request.contextPath}/images/introduct/일정.png">
		<div class="treatment">산부인과</div>
		</div>
    </div>
    <hr size="5" width="70%" align="left" color="green">
    <div class="col-sm-7">
     <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/김준완.jpg" style="width:100px; height:150px;">
    </div>
		<div class="mc">
		<h3>김준완</h3><h4>교수</h4>
		<div class="treatTime">진료가능일정</div>
		<img src="${pageContext.request.contextPath}/images/introduct/일정.png">
		<div class="treatment">흉부외과</div>
	</div>
    </div>
    <div class="col-sm-5">
    <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/전미도.jpg" style="width:100px; height:150px;">
    </div>
	<div class="mc">
	<h3>채송화</h3><h4>교수</h4>
		<div class="treatTime">진료가능일정</div>
		<img src="${pageContext.request.contextPath}/images/introduct/일정.png">
		<div class="treatment">신경외과</div>
	</div>
    </div>
    <hr size="5" width="70%" align="left" color="green">
    <div class="col-sm-7">
    <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/유연석.jpg" style="width:100px; height:150px;">
    </div>
	<div class="mc">
	<h3>안정원</h3><h4>교수</h4>
		<div class="treatTime">진료가능일정</div>
		<img src="${pageContext.request.contextPath}/images/introduct/일정.png">
		<div class="treatment">소아외과</div>
		</div>
    </div>
    <hr size="6" width="70%" align="left" color="blue">
  </div>
  </div>
</section>
<!-- </div> -->
<%-- <jsp:include page="/footer.jsp" /> --%>
</body>
</html>