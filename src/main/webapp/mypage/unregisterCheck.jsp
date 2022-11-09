<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 회원탈퇴 페이지</title>
<style>

.warningImg {
	width: 5rem;
	height: 5rem;
	margin: 0.5rem;
}

.unregisterDetail {
	width: 300px;
	height: 100px;
	overflow: auto;
	margin: 0 auto;
	border: 1px dotted black;
}

button {
	margin: 1rem auto;
}

</style>
</head>
<body>
	<div class="text-center">
		<h2>회원 탈퇴</h2>
		<img src = "images/warning.png" class="warningImg">
		<div class = "unregisterDetail">
		<p>회원탈퇴를 진행할 경우 일부 정보에 더 이상 접근하지 못할 수도 있습니다.
		정말로 회원탈퇴 하시겠습니까?</p>
		</div>
		<button id = "removeMemBtn" type = "button" class="btn btn-danger fs-4">회원탈퇴 진행</button>
	</div>
</body>
</html>