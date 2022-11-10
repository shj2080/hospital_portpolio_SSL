<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script type="text/javascript">
	window.onload = () => {
		document.getElementById("BackBtn").addEventListener("click", () => {
			history.back();
		});
	};
</script>
</head>
<body>

<form action = "" method = "post" name = "findForm">
	<div class="col text-center">
		<span class="fs-3">ID를 찾기 위해 회원님의 연락처를 입력해주세요.</span>
	</div>
	<div class = "col text-center">
		<input type="tel" name = "phone" maxlength="13"/>
	</div>
	<div class = "row text-center my-3">
		<div class = "col">
			<button type = "button" id = "findBtn" class="btn btn-outline fs-4">찾기</button>
			<button type = "button" id = "BackBtn" class="btn btn-outline fs-4">뒤로가기</button>
		</div>
	</div>
</form>
</body>
</html>