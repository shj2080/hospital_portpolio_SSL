<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 진료예약</title>
<link rel="stylesheet" type="text/css" href="/styles/reservationForm.css">
</head>
<body>
	<div id = "warpper">
	    <jsp:include page="header.jsp" />
	    <%-- 컨텐츠 표시 영역 시작 --%>
	    <section>
	        <div>
	            <h2>진료 예약</h2>
	            <form action = "" name = "" method="post">
	            	<table>
	            	</table>
	            </form>
	        </div>
	    </section>
	    <%-- 컨텐츠 표시 영역 끝 --%>
	    <jsp:include page="footer.jsp" />
	</div>
</body>
</html>