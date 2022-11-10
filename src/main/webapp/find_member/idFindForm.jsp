<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script type="text/javascript" src = "${pageContext.request.contextPath}/javascript/phoneNumberFormat.js"></script>
<script type="text/javascript">
	window.onload = () => {
		document.getElementById("findBtn").addEventListener("click", () => {
			//휴대폰 정규식 (01~로 시작하고 중간자리 끝자리가 3,4자리인 번호)
			let regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
			
			if(!findForm.phone.value) {
				alert("연락처를 입력해주세요!");
				findForm.phone.focus();
				return false;
			}else if(!regPhone.test(findForm.phone.value)) {
				alert("연락처 규격이 맞지 않습니다.");
				findForm.phone.select();
				return false;
			}
			
			findForm.submit();
		});
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
		<input type="tel" name = "phone" maxlength="13" oninput="autoHyphen(this)" required="required"/>
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