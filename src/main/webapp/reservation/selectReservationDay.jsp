<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원 - 진료예약일 선택</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/reservation/selectReservationDay.css">
<style type="text/css">
	table, th, td { 
		border: 0.1rem solid black; 
	}
	
	table {
		margin: 0 auto;
		border-collapse: collapse;
	}
	
</style>
<script>
	function selectTreatmentDay(year, month, day) {
		//opener null 문제 해결
		if(!window.opener) {
			window.opener = window.open("", "reservationFormWindow");
		}
		
		//오늘 날짜
		let nowDate = new Date();
		nowDate = nowDate.setHours(0,0,0,0); //날짜만 비교하기 위해 시간값을 0으로 설정
		
		//10월 보다 작거나 10일 미만
		if(month < 10) {
			month = "0" + month;
		}
		
		if(day < 10) {
			day = "0" + day;
		}
		
		let settingDate = year + "-" + month + "-" + day;
		
		//선택한 날짜
		let reservationDate = new Date(settingDate);
		
		if(reservationDate < nowDate) {
			opener.alert("이전 날짜를 선택할 수 없습니다.");
			self.close();
			return;
		}
		
		//부모 콘솔의 로그에 선택한 날짜를 남김
		opener.console.info(settingDate);
		
		opener.document.getElementById("reservationDay").value = settingDate;
		self.close(); // 창 닫음
	}
</script>
</head>
<body>
<table>
	<caption class="text-center">
		<button type="button" class="btn btn-info" onclick="location.href='SelectReservationDay.treat?year=${before_year}&month=${before_month}'">◀</button>
			${year}년 ${month+1}월
		<button type="button" class="btn btn-info" onclick="location.href='SelectReservationDay.treat?year=${after_year}&month=${after_month}'">▶</button>
	</caption>
	<tr class="text-center">
		<th style="color:red">일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th style="color:blue">토</th>
	</tr>
	<tr>
	<c:set var="count" value="0"/>
	<c:forEach var = "i" begin = "1" end = "${dayOfweek - 1}" step="1" >
		<td></td>
		<c:set var="count" value="${count + 1 }"/>
	</c:forEach>
	
	<c:forEach var = "days" begin = "1" end = "${lastday}" step = "1" >
		<c:set var = "count" value = "${count + 1}"/>
		
		<td class="text-center">
			<input type="button" class="btn btn-light fs-4" value="${days}" onclick="selectTreatmentDay(${year},${month+1},${days});"/>
		</td>
		
		<c:if test="${count % 7 == 0}">
			</tr><tr>
		</c:if>
	</c:forEach>
	</tr>
</table>
</body>
</html>