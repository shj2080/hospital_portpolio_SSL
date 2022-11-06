<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제대학교병원 - 내가 예약한 진료내역</title>
<link rel="stylesheet" type="text/css" href = "style/mypage/myReservationList_style.css">
<%--<script type="text/javascript" src = "javascript/mypage/myReservationList_script.js"></script>--%>
</head>
<body>
	<c:if test="${myReservationList != null}">
	<table class = "table mt-5">
		<tr>
<%--			<th colspan="2" scope="col">선택</th>--%>
			<th scope="col">예약코드</th>
			<th scope="col">진료날짜</th>
			<th scope="col">의사명</th>
			<th scope="col">진료과</th>
		</tr>
		<c:forEach var = "resRow" items="${myReservationList }">
			<tr>
<%--				<td colspan="2">
					<input type="button" name = "modifyReservation" value = "수정" class = "btn btn-info fs-4" onclick = "modifyRes(${resRow.speciality_code},${resRow.reservation_code});"/>
					<input type="button" name = "cancelReservation" value = "취소" class = "btn btn-danger fs-4" onclick = "cancelRes(${resRow.reservation_code}, ${resRow.id});"/>
				</td>--%>
				<td>
					${resRow.reservation_code }
				</td>
				<td>
					${resRow.reservation_date}
				</td>
				<td>
					${resRow.doctor_name }
				</td>
				<td>
					${resRow.speciality_name }
				</td>
			</tr>
		</c:forEach>
	</table>
		<div>
			<span>진료예약 관련 하여 수정/취소는 예약 접수 창구나 전화로 문의하시기 바랍니다.</span>
		</div>
	</c:if>
	<c:if test="${myReservationList == null }">
		<h2>예약된 진료내역이 없습니다.</h2>
	</c:if>
</body>
</html>