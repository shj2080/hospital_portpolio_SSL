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
</head>



<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<!-- 컨텐츠 표시 영역 시작 -->
		<section>
			<c:if test="${myTreatmentList != null }">
			<table>
				<tr id = "treatment">
					<c:forEach var= "mytreatment" items="${myTreatmentList }" varStatus="status">
						<th id = "treatment_date">
							${mytreatment.treatment_date}
						</th>
						
						<th id = "speciality_name">
							${mytreatment.speciality_name}
						</th>
						
						<th id = "doctor_name">
							${mytreatment.doctor_name}
						</th>
					</c:forEach>
				</tr>
					
				</table>
			</c:if>
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