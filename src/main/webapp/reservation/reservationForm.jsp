<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 진료예약</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/reservation/reservationForm.css">
<script type="text/javascript" src = "javascript/defaultJavaScript.js"></script>
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
		const reservationDay = document.getElementById("reservationDay");
		reservationDay.value = inputDay;
	}
</script>
<style>
/* box-sizing를 변경한 적이 있는 경우 이 스타일 사용 */
	.selector-for-some-widget {
	  box-sizing: content-box;
	}
</style>
</head>
<body>
		<jsp:include page="../header.jsp" />
		<div id = "contentWrap">
		<section>
			<div>
				<c:if test = "${param.modifyState == null }">
				<h2>진료 예약</h2>
				</c:if>
				<c:if test = "${param.modifyState != null }">
				<h2>진료 예약 수정</h2>
				</c:if>
			</div>
			<form action = "reservationInsertModify.treat" name = "resForm" method="post">
				<c:if test="${resBean != null}">
				<input type="hidden" name = "resUserID" value = "${resBean.id}" readonly/>
				</c:if>
				<table>
					<tr>
						<th>진료일자</th>
						<td>
							<input type = "text" id = "reservationDay" name = "reservationDay" value="${resBean.reservation_date}" size="10" readonly />
							<button type = "button" class = "btn btn-primary fs-4" id="selectDayBtn" >날짜 선택</button>
						</td>
					</tr>
					
					<tr>
						<th>진료시간</th>
						<td>
							<select name = "reservationHour">
								<!-- 9 ~ 17 범위로 1씩 증가하는 정수 출력 -->
								<c:forEach var="reservationHour_loop" begin="9" end="17" step="1">
									<c:if test="${resHour != null and resHour == reservationHour_loop }">
										<option selected value="${reservationHour_loop }">${reservationHour_loop }</option>
									</c:if>
									<c:if test="${resHour == null or resHour != reservationHour_loop}">
										<option value="${reservationHour_loop }">${reservationHour_loop }</option>
									</c:if>
								</c:forEach>
								
							</select>시
							<select name = "reservationMinute">
								<!-- 0 ~ 50 범위로 10씩 증가하는 정수 출력 -->
								<c:forEach var="reservationMinute_loop" begin="0" end="50" step="10">
									<c:if test="${resMinute != null and resMinute == reservationMinute_loop }">
										<option selected value="${reservationMinute_loop }">${reservationMinute_loop}</option>
									</c:if>
									<c:if test="${resMinute == null or resMinute != reservationMinute_loop}">
										<option value="${reservationMinute_loop }">${reservationMinute_loop}</option>
									</c:if>
								</c:forEach>
							</select>분
						</td>
					</tr>
					<tr>
						<th>환자성함</th>
						<td>
							<c:if test="${resUserName == null}">
								<input type = "text" name = "name" value = "${sessionScope.userName}" size = "10" readonly />
							</c:if>
							<c:if test="${resUserName != null}">
								<input type = "text" name = "name" value = "${resUserName}" size = "10" readonly />
							</c:if>
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
									<c:if test="${resBean != null and resBean.doctor_code == doctorInfo.doctor_code}">
										<option selected value = "${doctorInfo.doctor_code}">${doctorInfo.doctor_name}</option>
									</c:if>
									<c:if test="${resBean == null or resBean.doctor_code != doctorInfo.doctor_code}">
										<option value = "${doctorInfo.doctor_code}">${doctorInfo.doctor_name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<button type="button" class="btn btn-outline-secondary fs-4" onclick="vaildCheck();">예약하기</button>
							<button type="button" class="btn btn-outline-secondary fs-4" onclick="resetCheck();">다시작성</button>
							
							<c:if test = "${param.modifyState == null }">
							<button type="button" id = "treatmentHover" class="btn btn-outline-secondary fs-4" onclick="javascript:location.replace('reservationSelectView.treat');">진료과 재선택</button>
							</c:if>
							<input type = "hidden" name = "modifyState_hidden" value = "${param.modifyState}" readonly/>
							<input type = "hidden" name = "reservation_code_hidden" value = "${param.reservation_code}" readonly/>
						</th>
					</tr>
				</table>
			</form>
		</section>
		</div>
		<jsp:include page="../footer.jsp" />
</body>
</html>