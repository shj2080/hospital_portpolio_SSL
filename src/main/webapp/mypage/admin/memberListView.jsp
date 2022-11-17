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
<link rel="stylesheet" type="text/css" href = "style/mypage/mypageListTable2_style.css">
</head>
<body>
	<c:choose>
		<c:when test="${memList != null}">
		<table class="memberListTb table mt-5">
			<tr>
				<th>권한부여</th>
				<th>이름</th>
				<th>회원ID</th>
				<th>전화번호</th>
				<th>유저타입</th>
			</tr>
			<c:forEach var = "member" items="${memList}">
			<tr>
				<td>
					<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#msgModal">부여</button>
				</td>
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
	<!-- 모달 컴포넌트 -->
	<div id = "msgModal" class="modal fade" tabindex="-1">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">향후 업데이트 예정</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>준비중인 기능입니다.</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 모달 컴포넌트 -->
</body>
</html>