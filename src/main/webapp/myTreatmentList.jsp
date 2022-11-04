<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "java.util.*, vo.MyTreatmentList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/myTreatmentList.css">
</head>

<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<!-- 컨텐츠 표시 영역 시작 -->
		<section>
		<div id = "myReservationBox" align="center">
			<c:if test="${myTreatmentList != null }">
			<table id = "myReservationList">
				<tr>
						<th id = "myReservationData">
							예약일
						</th>
						
						<th id = "mySpecialityName">
							진료과
						</th>
						
						<th id = "mySpecialityDoctorName">
							의사명
						</th>
				</tr>
				<c:forEach var= "mytreatment" items="${myTreatmentList }" varStatus="status">
				<tr>
					<td id = "myReservationData">
						${mytreatment.reservation_date}
					</td>
					<td id = "mySpecialityName">
						${mytreatment.doctor_name}
					</td>
					<td id = "mySpecialityDoctorName">
						${mytreatment.speciality_name}
					</td>
				</tr>
					</c:forEach>
				</table>
				</c:if>
		</div>
			
				<!-- --진료대기리스트가 비어 있는 경우--- -->
				<c:if test="${myTreatmentList == null }">
				<table>
					<tr id = "treatment" align="center" >
						<td>진료대기리스트가 비어있습니다.</td>
					</tr>
				</table>
				</c:if>
		</section>
		</div>
		<%-- 컨텐츠 표시 영역 끝 --%>
	<jsp:include page="footer.jsp" />
</body>
</html>