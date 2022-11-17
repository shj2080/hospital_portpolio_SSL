<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "java.util.*, vo.MyTreatmentList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>율제병원</title>

<!-- <link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css" href = "style/mypage/mypageListTable_style.css">

</head>

<body>
<%-- 	<jsp:include page="header.jsp" /> --%>
<!-- 		<div id = "contentWrap">
		컨텐츠 표시 영역 시작
		<section> -->
		<div id = "myReservationBox" align="center">
			<table id = "myReservationList" class = "table mt-5">
				<tr align="center">
						<th id = "myReservationData" class = "fs-4">
							진료일
						</th>
						
						<th id = "mySpecialityName" class = "fs-4">
							진료과
						</th>
						
						<th id = "mySpecialityDoctorName" class = "fs-4">
							의사명
						</th>
				</tr>
				<c:if test="${myTreatmentList != null }">
				<c:forEach var= "mytreatment" items="${myTreatmentList }" varStatus="status">
					<tr align="center" >
						<th class = "fs-4">
							${mytreatment.treatment_date}
						</th>
						<th class = "fs-4">
							${mytreatment.doctor_name}
						</th>
						<th class = "fs-4">
							${mytreatment.speciality_name}
						</th>
					</tr>
		</c:forEach>
			</c:if>
				</table>
				<!-- --진료대기리스트가 비어 있는 경우--- -->
				<c:if test="${myTreatmentList == null }">
				<table class = "text-center">
					<tr id = "treatment" >
						<th class = "fs-4">
							진료리스트가 비어있습니다.
						</th>
					</tr>
				</table>
				</c:if>
		</div>
				
<!-- 		</section>
		</div> -->
		<%-- 컨텐츠 표시 영역 끝 --%>
<%-- 	<jsp:include page="footer.jsp" /> --%>
</body>
</html>