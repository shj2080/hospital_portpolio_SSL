<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>율제병원</title>

<link rel="stylesheet" type="text/css" href="style/initStyle.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style/header.css">
	<link rel="stylesheet" type="text/css" href="style/body.css">
	<link rel="stylesheet" type="text/css" href="style/footer.css">

</head>
<body>
	
	<!-- header.jsp 불러오기 -->
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<section>	
		<form method = "post">
		
		<!-- 예약대기자 명단을 보여줄 테이블 시작 -->
			<table id = "treatmentListBox" class = "table mt-5">
			
			<!-- 진료과별로 선택할수 있는 메뉴 시작 -->
				<tr id = "speciality_name">
					<td colspan="4" align="right"><select class="fs-4" id="speciality" name="speciality">
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
						 <input type="submit" class="fs-4" value="검색" formaction="treatmentListSearch.do" />
						</td>
					</tr>
					<!-- 진료과별로 선택할수 있는 메뉴 끝 -->
					
					<!-- 테이블 헤드 시작 -->
					<tr align="center" >
						<th id = "treatment_date" class = "fs-4">진료시간</th>
						<th id = "name" class = "fs-4">환자성함</th>
						<th id = "speciality_name2" class = "fs-4">진료과</th>
						<th id = "doctor_name" class = "fs-4">의사명</th>
					</tr>
					<!-- 테이블 헤드 끝 -->
				
				<!-- 불러온 대기자 명단을 출력 시작 -->
				<c:if test="${treatmentList != null }">
				<c:forEach var = "treatment" items="${treatmentList }">
					<tr align="center" >
						<th class = "fs-4">
							${treatment.treatment_date}
						</th>
						<th class = "fs-4">
							${fn:substring(treatment.name,0,1)}
							<c:forEach var = "maskName" begin="1" end="${fn:length(treatment.name) - 1}">
								*
							</c:forEach>
						</th>
						<th class = "fs-4">
							${treatment.speciality_name}
						</th>
						<th class = "fs-4">
							${treatment.doctor_name}
						</th>
					</tr>
				</c:forEach>
				</c:if>
				<!-- 불어온 대기자 명단을 출력 끝 -->
				
				<!-- --진료대기리스트가 비어 있는 경우--- -->
				<c:if test="${treatmentList == null }">
					<tr id = "treatment" align="center" >
						<td colspan="4" class = "fs-4">진료대기리스트가 비어있습니다.</td>
					</tr>
				</c:if>
				<!-- --진료대기리스트가 비어 있는 경우--- -->
			
			</table>
			<!-- 예약대기자 명단을 보여줄 테이블 시작 -->
			
		</form>
		
		</section>
		</div>
		<%-- 컨텐츠 표시 영역 끝 --%>
	<jsp:include page="footer.jsp" />
</body>
</html>