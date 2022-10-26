<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "java.util.*, vo.TreatmentList" %>

<%-- <%
	//List<TreatmentList> treatmentList = (ArrayList<TreatmentList>)request.getAttribute("treatmentList");
%>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="styles/treatementList.css">

<style type="text/css">
	
	table {
    border: 1px solid #444444;
    border-collapse: collapse;
    margin-left:auto; 
    margin-right:auto;
    margin-top: 10rem;
	}
	
	tr, td, th {
    border: 1px solid #444444;
	padding: 1rem;
	}
	
</style>

</head>
<body>
	<div id = "warpper">
	<jsp:include page="header.jsp" />
		<section>
	
		<%-- <c:set var="speciality" value="${speciality}"></c:set> --%>
	
		<form method = "post">
			<table>
				<tr>
					<td colspan="4"><select id="speciality" name="speciality">
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
					<th>진료시간</th>
					<th>환자성함</th>
					<th>진료과</th>
					<th>의사명</th>
				</tr>
				<c:if test="${treatmentList != null }">
				<tr>
					<c:forEach var= "treatment" items="${treatmentList }" varStatus="status">
					<td>
						${treatment.treatment_date}
					</td>
					<td>
						${treatment.name}
					</td>
					<td>
						${treatment.speciality_name}
					</td>
					<td>
						${treatment.doctor_name}
					</td>
				</tr>
					</c:forEach>
				</c:if>
				<!-- --진료대기리스트가 비어 있는 경우--- -->
				<c:if test="${treatmentList == null }">
					<tr>
						<td colspan="4">진료대기리스트가 비어있습니다.</td>
					</tr>
				</c:if>
				<!-- --진료대기리스트가 비어 있는 경우--- -->
			</table>
		</form>
		
		</section>
		<%-- 컨텐츠 표시 영역 끝 --%>
	<jsp:include page="footer.jsp" />
	</div>
</body>
</html>