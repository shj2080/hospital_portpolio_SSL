<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 진료예약</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/reservationView.css">
</head>
<body>
	<div id = "warpper">
	    <jsp:include page="header.jsp" />
	    <%-- 컨텐츠 표시 영역 시작 --%>
	    <section>
	        <div>
	            <h2>진료 예약 - 진료과 선택</h2>
	            <div id = "treatmentListConteinerBox">
	            	<div>
	            		<a href="reservationForm.treat?specialityCode=1">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment2.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">내과</span></li>
	            		</ul>
	            		</a>
	            	</div>
	            	<div>
	            		<a href="reservationForm.treat?specialityCode=2">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment4.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">외과</span></li>
	            		</ul>
	            		</a>
	            	</div>
	            	<%-- <div>
	            		<a href="reservationForm.treat?specialityCode=3">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment5.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">신경과</span></li>
	            		</ul>
	            		</a>
	            	</div> --%>
	            	<div>
	            		<a href="reservationForm.treat?specialityCode=3">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment6.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">산부인과</span></li>
	            		</ul>
	            		</a>
	            	</div>
	            	<div>
	            		<a href="reservationForm.treat?specialityCode=4">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment7.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">영상의학과</span></li>
	            		</ul>
	            		</a>
	            	</div>
	            	<div>
	            		<a href="reservationForm.treat?specialityCode=5">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment8.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">마취통증의학과</span></li>
	            		</ul>
	            		</a>
	            	</div>
	            </div>
	        </div>
	    </section>
	    <%-- 컨텐츠 표시 영역 끝 --%>
	    <jsp:include page="footer.jsp" />
	</div>
</body>
</html>