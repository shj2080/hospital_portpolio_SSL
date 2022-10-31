<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 진료예약</title>
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/reservation/reservationForm.css">
<script type="text/javascript" src = "javascript/reservationFormCheck.js"></script>
<script>

	let selectDayPopup;
	
	window.onload = function() {
		
		const selectDayPopupBtn = document.getElementById("selectDayBtn");
		selectDayPopupBtn.addEventListener("click", () => {
			if(!selectDayPopup || selectDayPopup.closed) {
				//팝업 객체가 없거나 팝업이 닫혀있는 상태인 경우 실행됨
				const url = "SelectReservationDay.treat";
				const windowName = "selectReservationDayWindow";
				const popupOption = "width=200, height=200, menubar=no, toolbar=no, location=no, status=no,scrollbars=no, resizable=no";
				
				window.name = "reservationFormWindow";
				selectDayPopup = window.open(url, windowName, popupOption);
			} else {
				selectDayPopup.focus();
			}
		});
	}
	
	function settingTreatmentDay(inputDay) {
		const treatmentDay = document.getElementById("treatmentDay");
		treatmentDay.value = inputDay;
	}
</script>
</head>
<body>
	<div id = "warpper">
		<jsp:include page="../header.jsp" />
		<section>
			<div>
				<h2>진료 예약</h2>
			</div>
			<form action = "reservationInsert.treat" name = "resForm" method="post">
				<table>
					<tr>
						<th>진료일자</th>
						<td>
							<input type = "text" id = "treatmentDay" name = "treatmentDay" size="10" readonly />
							<button type = "button" id="selectDayBtn">날짜 선택</button>
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
							<input type = "text" name = "name" value = "${sessionScope.userName}" size = "10" readonly />
						</td>
					</tr>
					<tr>
						<th>진료과</th>
						<td>
							<input type= "hidden" name = "speciality_code" value = "${param.specialityCode}" readonly/>
							${speciality_nameAttr}
						</td>
					</tr>
					<tr>
						<th>의사명</th>
						<td>
							<select name = "doctor_code">
								<!-- 의사 테이블의 값을 가져와 설정 -->
								<option disabled>==의사 선택==</option>
								<c:forEach var = "doctorInfo" items = "${doctorList}">
									<option value = "${doctorInfo.doctor_code}">${doctorInfo.doctor_name}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<button type="button" onclick="vaildCheck();">예약하기</button>
							<button type="button" onclick="resetCheck();">다시작성</button>
							<button type="button" onclick="javascript:history.back();">진료과 재선택</button>
						</th>
					</tr>
				</table>
			</form>
		</section>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>