<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 진료예약</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/reservation/reservationView.css">
</head>
<body>
	<div id = "warpper">
	    <jsp:include page="../header.jsp" />
	    <%-- 컨텐츠 표시 영역 시작 --%>
	    <section>
	        <div>
	            <h2>진료 예약 - 진료과 선택</h2>
	            <div id = "treatmentListConteinerBox">
	            	<c:forEach var = "specList" items="${specialityList }">
	            	<div>
	            		<a href="reservationForm.treat?specialityCode=${specList.speciality_code }">
	            		<ul>
	            			<li>
				            	<img src = "${pageContext.request.contextPath}/images/treatment/treatment${specList.speciality_code}.png">
	            			</li>
	            			<li><span class = "treatment_names_Style">${specList.speciality_name}</span></li>
	            		</ul>
	            		</a>
	            	</div>
	            	</c:forEach>
	            </div>
	        </div>
	    </section>
	    <%-- 컨텐츠 표시 영역 끝 --%>
	    <jsp:include page="../footer.jsp" />
	</div>
</body>
</html>