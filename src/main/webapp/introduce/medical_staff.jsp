<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
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
<p><h4 style="text-align:center"><strong>율제병원은 모든 고객을 가족같이 생각합니다.</strong></h4></p>
</p>
<hr size="6" width="100%" align="left" color="blue">
<div class="container">
  <div class="row">
    <div class="col-sm-7">
   <div class="s">
			<img src="${pageContext.request.contextPath}/images/introduct/조정석.jpg" style="width:130px; height:180px;">
			</div>
			<div class="mc">
		<p><h3>조정석</h3><h4>교수</h4></p>
		<p><div class="treatTime">진료가능일정</div></p>
		<p><img src="${pageContext.request.contextPath}/images/introduct/일정.png"><br></p>
		<div class="treatment">간담최외과</div><p>
		</div>
    </div>
    <div class="col-sm-5">
    <div class="s">
		<img src="${pageContext.request.contextPath}/images/introduct/석형.jpg" style="width:130px; height:180px;">
	</div>
	<div class="mc">
		<p><h3>양석현</h3><h4>교수</h4></p>
		<p><div class="treatTime">진료가능일정</div></p>
		<p><img src="${pageContext.request.contextPath}/images/introduct/일정.png"></p>
		<div class="treatment">산부인과</div><p>
		</div>
    </div>
    <hr size="5" width="70%" align="left" color="green">
    <div class="col-sm-7">
     <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/김준완.jpg" style="width:130px; height:180px;">
    </div>
		<div class="mc">
		<p><h3>김준완</h3><h4>교수</h4></p>
		<p><div class="treatTime">진료가능일정</div></p>
		<p><img src="${pageContext.request.contextPath}/images/introduct/일정.png"></p>
		<div class="treatment">흉부외과</div><p>
	</div>
    </div>
    <div class="col-sm-5">
    <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/전미도.jpg" style="width:130px; height:180px;">
    </div>
	<div class="mc">
	<p><h3>채송화</h3><h4>교수</h4></p>
		<p><div class="treatTime">진료가능일정</div></p>
		<p><img src="${pageContext.request.contextPath}/images/introduct/일정.png"></p>
		<div class="treatment">신경외과</div><p>
	</div>
    </div>
    <hr size="5" width="70%" align="left" color="green">
    <div class="col-sm-7">
    <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/유연석.jpg" style="width:130px; height:180px;">
    </div>
	<div class="mc">
	<p><h3>안정원</h3><h4>교수</h4></p>
		<p><div class="treatTime">진료가능일정</div></p>
		<p><img src="${pageContext.request.contextPath}/images/introduct/일정.png"></p>
		<p><div class="treatment">소아외과</div></p>
		</div>
    </div>
    
    <div class="col-sm-5">
    <div class="s">
    <img src="${pageContext.request.contextPath}/images/introduct/봉광현.jpg" style="width:130px; height:180px;">
    </div>
	<div class="mc">
	<p><h3>봉광현</h3><h4>교수</h4></p>
		<p><div class="treatTime">진료가능일정</div></p>
		<p><img src="${pageContext.request.contextPath}/images/introduct/일정.png"></p>
		<div class="treatment">응급의학과</div><p>
	</div>
    </div>
    
   <hr size="6" width="70%" align="left" color="blue"><br>
   <p>
   <p>
   <p>
   <p>
   <p>
   <p>
  </div>
  </div>
</section>

<!-- </div> -->
<%-- <jsp:include page="/footer.jsp" /> --%>
</body>
</html>