<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 로그인</title>
<link rel="stylesheet" type="text/css" href="./styles/style.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script>
<script type="text/javascript">
/* 로그인 유효성 검사 */
function loginVaildCheck() {
	if(!loginForm.id.value) {
		alert("아이디를 입력해주세요!");
		loginForm.id.focus();
		return false;
	}
	if(!loginForm.password.value) {
		alert("비밀번호를 입력해주세요!");
		loginForm.password.focus();
		return false;
	}
	
	//유효성 검사 통과 시 비밀번호를 암호화 하여 submit
	//loginForm.password.value = CryptoJS.SHA256(loginForm.password.value);
	
	loginForm.submit();
}
</script>
</head>
<body>
	<form action ="loginProcess" name = "loginForm" method="post">
		<table class="FormTable">
			<tr>
				<th colspan="2"><h2>시스템 로그인</h2></th>
			</tr>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name = "id" value="" size = "16rem" maxlength="50" />
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name = "password" value="" size = "16rem" maxlength="50" />
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="button" onclick="loginVaildCheck()">로그인</button>
				</th>
			</tr>
		</table>
	</form>
</body>
</html>