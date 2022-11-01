<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "java.util.*, vo.TreatmentList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/treatmentList.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">

</head>
<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<section>	
		<form method = "post">
			<table id = "treatmentListBox">
				<tr id = "speciality_name">
					<td colspan="4" align="right"><select id="speciality" name="speciality">
						<option>=나의 진료과=</option>
							<c:choose>
									<c:when test="${speciality=='내과' }">
										<option value ="내과" selected="selected">내과</option>
										<option>외과</option>
										<option>산부인과</option>
										<option>영상의학과</option>
										<option>마취통증의학과</option>
									</c:when>

									<c:when test="${speciality=='외과' }">
										<option>내과</option>
										<option value ="외과" selected="selected">외과</option>
										<option>산부인과</option>
										<option>영상의학과</option>
										<option>마취통증의학과</option>
									</c:when>

									<c:when test="${speciality=='산부인과' }">
										<option>내과</option>
										<option>외과</option>
										<option value ="산부인과" selected="selected">산부인과</option>
										<option>영상의학과</option>
										<option>마취통증의학과</option>
									</c:when>

									<c:when test="${speciality=='영상의학과' }">
										<option>내과</option>
										<option>외과</option>
										<option>산부인과</option>
										<option value ="영상의학과" selected="selected">영상의학과</option>
										<option>마취통증의학과</option>
									</c:when>
									
									<c:when test="${speciality=='마취통증의학과' }">
										<option>내과</option>
										<option>외과</option>
										<option>산부인과</option>
										<option>영상의학과</option>
										<option value ="마취통증의학과" selected="selected">마취통증의학과</option>
									</c:when>

									<c:otherwise>
										<option>내과</option>
										<option>외과</option>
										<option>산부인과</option>
										<option>영상의학과</option>
										<option>마취통증의학과</option>
									</c:otherwise>

								</c:choose>
							</select>
						 <input type="submit" value="검색" formaction="treatmentListSearch.do" />
						</td>
					</tr>
					<tr>
						<th id = "treatment_date">진료시간</td>
						<th id = "name">환자성함</td>
						<th id = "speciality_name2">진료과</td>
						<th id = "doctor_name">의사명</td>
					</tr>
				<c:if test="${treatmentList != null }">
				<tr id = "treatment">
					<c:forEach var= "treatment" items="${treatmentList }" varStatus="status">
						<th id = "treatment_date">
							${treatment.treatment_date}
						</th>
						
						<th id = "name">
							${treatment.name}
						</th>
						
						<th id = "speciality_name2">
							${treatment.speciality_name}
						</th>
						
						<th id = "doctor_name">
							${treatment.doctor_name}
						</th>
				</tr>
					</c:forEach>
				</c:if>
				<!-- --진료대기리스트가 비어 있는 경우--- -->
				<c:if test="${treatmentList == null }">
					<tr id = "treatment" align="center" >
						<td>진료대기리스트가 비어있습니다.</td>
					</tr>
				</c:if>
				<!-- --진료대기리스트가 비어 있는 경우--- -->
			</table>
		</form>
		
		</section>
		</div>
		<%-- 컨텐츠 표시 영역 끝 --%>
	<jsp:include page="footer.jsp" />
</body>
</html>