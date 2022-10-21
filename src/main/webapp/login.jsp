<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시스템 로그인</title>
<link rel="stylesheet" type="text/css" href="./styles/style.css">
<!-- <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/crypto-js.min.js"></script> -->
<script type="text/javascript">
//userID 쿠키 가져오는 함수
function getCookie(name) {
	let cookie = document.cookie;
	
	/*쿠키 여부 확인
	*/
	if(document.cookie != "") {
		let cookie_array = cookie.split("; ");
		for(let i = 0; i < cookie_array.length; i++) {
			//쿠키이름=쿠키값 => =중심으로 문자열 분리하면 index0 쿠키이름, index 1 쿠키값이 저장됨
			let cookie_name_value = cookie_array[i].split("=");
			cookie_name_value = cookie_name_value.replace(/^\s*/,'');//정규식을 이용해 쿠키이름 문자열의 공백(\s) 제거
			
			if(cookie_name_value[0] == "userID") {
				alert(cookie_name_value[1]); //테스트 문구
				return cookie_name_value[1]; //해당 쿠키값을 리턴
			}
		}
	}
}

//자바스크립트에서 아이디 저장 체크박스 설정
window.onload = function() {	
	if(loginForm.id.value) {
		loginForm.remember.checked = false;
	} else {
		loginForm.id.value = getCookie(name);
		loginForm.remember.checked = true;
	}
};

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
	<form action ="loginProcess.do" name = "loginForm" method="post">
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
					<input type="checkbox" name="remember" />아이디 저장
					<button type="button" onclick="loginVaildCheck()">로그인</button>
				</th>
			</tr>
		</table>
	</form>
</body>
</html>