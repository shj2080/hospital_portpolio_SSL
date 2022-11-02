<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제대학교병원 - 내가 예약한 진료내역</title>
<link rel="stylesheet" type="text/css" href = "style/mypage/myReservationList_style.css">
<script type="text/javascript" src = "javascript/mypage/myReservationList_script.js"></script>
</head>
<body>
	<table class = "table mt-5">
		<tr>
			<th colspan="2" scope="col">선택</th>
			<th scope="col">예약코드</th>
			<th scope="col">진료날짜</th>
			<th scope="col">의사명</th>
			<th scope="col">진료과</th>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" name = "modifyReservation" value = "수정" class = "btn btn-info fs-4" onclick = "modifyRes();"/>
				<input type="button" name = "cancelReservation" value = "취소" class = "btn btn-danger fs-4" onclick = "cancelRes();"/>
			</td>
			<td>
				Code
			</td>
			<td>
				yyyy-mm-dd hh:mm
			</td>
			<td>
				샘플데이터
			</td>
			<td>
				샘플데이터2
			</td>
		</tr>
	</table>
</body>
</html>