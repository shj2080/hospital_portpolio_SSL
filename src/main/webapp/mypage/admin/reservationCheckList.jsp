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
<link rel="stylesheet" type="text/css" href = "style/mypage/mypageListTable_style.css">
<script>
	window.onload = function () {
		//버튼 이벤트 리스너 설정
		//document.getElementById("searchID").addEventListener("click", memberSearch);
		
		//get 방식으로 넘어온 파라미터 제거함
		history.replaceState({}, null, location.pathname);
	};
</script>
<script type="text/javascript" src = "javascript/mypage/reservationList_script.js"></script>
<script type="text/javascript" src = "javascript/mypage/reservationListSearch_script.js"></script>
</head>
<body>
	<form action = "reservationMemberSearch.ad" id = "searchForm" name = "searchForm" method="post" class = "row g-2" onsubmit="return memberSearch();">
		<div class="col-auto p-3">
			<!-- <label>회원ID <input type="text" id = "u_id" name = "u_id" ></label> -->
			<label>회원이름 <input type="text" id = "u_name" name = "u_name" ></label>
			<button type = "submit" id = "searchID" class="btn btn-info fs-4">검색</button>
		</div>
	</form>
	<c:if test="${resCheckList == null }">
		<h2>예약된 진료내역이 없습니다.</h2>
	</c:if>
	<c:if test="${resCheckList != null}">
	<table id ="resCheckTable" class = "table mt-5">
		<tr>
			<th colspan="2" scope="col">선택</th>
			<th scope="col">예약코드</th>
			<th scope="col">회원이름</th>
			<th scope="col">회원ID</th>
			<th scope="col">진료날짜</th>
			<th scope="col">의사명</th>
			<th scope="col">진료과</th>
			<th scope="col">진료상태</th>
		</tr>
		<c:forEach var = "resRow" items="${resCheckList }">
			<tr>
				<td colspan="2">
					<input type="button" id = "treatConfirm" name = "treatConfirm" value = "진료확인" class = "btn btn-success fs-4" onclick = "treatmentAdd(${resRow.reservation_code},'${resRow.treatment_status}');"/>
					<input type="button" id = "modifyResBtn" name = "modifyReservation" value = "수정" class = "btn btn-info fs-4" onclick = "modifyRes(${resRow.speciality_code},${resRow.reservation_code},'${resRow.treatment_status}');"/>
					<input type="button" id = "cancelResBtn" name = "cancelReservation" value = "취소" class = "btn btn-danger fs-4" onclick = "cancelRes(this);"/>
				</td>
				<td>
					${resRow.reservation_code }
				</td>
				<td>
					${resRow.name}
				</td>
				<td>
					${resRow.id}
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
				<td>
					${resRow.treatment_status}
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>