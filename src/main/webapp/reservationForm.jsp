<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 진료예약</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/reservationForm.css">
</head>
<body>
	<div id = "warpper">
		<jsp:include page="header.jsp" />
		<section>
			<div>
				<h2>진료 예약</h2>
			</div>
			<form action = "" name = "resForm" method="post">
				<table>
					<tr>
						<th>진료일자</th>
						<td>
							<!-- 구현중 -->
						</td>
					</tr>
					<tr>
						<th>진료시간</th>
						<td>
							<select name = "treatmentHour">
								<!-- 9 ~ 17 범위로 1씩 증가하는 정수 출력 -->
								<c:forEach var="treatmentHour_loop" begin="9" end="17" step="1"> 
									<option value="${treatmentHour_loop }">${treatmentHour_loop }</option>
								</c:forEach>
								
							</select>시
							<select name = "treatmentMinute">
								<!-- 0 ~ 50 범위로 10씩 증가하는 정수 출력 -->
								<c:forEach var="treatmentMinute_loop" begin="0" end="50" step="10">
								<option value="${treatmentMinute_loop }">${treatmentMinute_loop}</option>
								</c:forEach>
							</select>분
						</td>
					</tr>
					<tr>
						<th>환자성함</th>
						<td>
							<input type = "text" name = "name" value = "" size = "10" readonly />
						</td>
					</tr>
					<tr>
						<th>진료과</th>
						<td>
							<input type=hidden name = "speciality_code" value = "${speciality_code}" readonly/>
							${speciality_nameAttr}
						</td>
					</tr>
					<tr>
						<th>의사명</th>
						<td>
							<select name = "doctor_code">
								<!-- 의사 테이블의 값을 가져와 설정 -->
								<option value = "${doctor_codeAttr }">${doctor_nameAttr }</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</section>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>