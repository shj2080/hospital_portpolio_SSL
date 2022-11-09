<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제대학병원 - 회원정보조회</title>
<link rel="stylesheet" type="text/css" href = "style/mypage/mypageListTable_style.css">
</head>
<body>
	<c:choose>
		<c:when test="${memList != null}">
		<table class="table mt-5">
			<tr>
				<th>이름</th>
				<th>회원ID</th>
				<th>전화번호</th>
				<th>유저타입</th>
			</tr>
			<c:forEach var = "member" items="${memList}">
			<tr>
				<td>${member.name }</td>
				<td>${member.id }</td>
				<td>${member.phone }</td>
				<td>${member.userType }</td>
			</tr>
			</c:forEach>
		</table>
		</c:when>
		<c:otherwise>
			등록된 회원이 없습니다.
		</c:otherwise>
	</c:choose>
</body>
</html>