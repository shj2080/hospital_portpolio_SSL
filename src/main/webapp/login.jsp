<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 반응형 웹을 위한 기본 태그 부분 -->
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 로그인</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/login.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<script type="text/javascript">
//userID 쿠키 가져오는 함수
function getCookie(name) {
	let cookie = document.cookie;
	
	/*쿠키 여부 확인
	*/
	if(document.cookie !== undefined) {
		let cookie_array = cookie.split("; ");
		for(let i = 0; i < cookie_array.length; i++) {
			//쿠키이름=쿠키값 => =중심으로 문자열 분리하면 index0 쿠키이름, index 1 쿠키값이 저장됨
			let cookie_name_value = cookie_array[i].split("=");
			cookie_name_value[0] = cookie_name_value[0].replace(/^\s*/,'');//정규식을 이용해 쿠키이름 문자열의 공백(\s) 제거
			
			if(cookie_name_value[0] == "userID") {
				return cookie_name_value[1]; //해당 쿠키값을 리턴
			}
		}
	}
}

//자바스크립트에서 아이디 저장 체크박스 설정
window.onload = function() {
	let saveid = getCookie(name);

	if(saveid == undefined) {
		loginForm.remember.checked = false;
	} else{
		loginForm.id.value = saveid;
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
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<section>
			<form action ="loginProcess.do" id = "loginForm" name = "loginForm" method="post">
				<div id="loginBox">
					<div class="loginItem">
						아이디
					</div>
					<div class="loginItem">
						<input type="text" name = "id" value="" maxlength="50" id = "textfiled" />
					</div>
					<div class="loginItem">
						비밀번호
					</div>
					<div class="loginItem">
						<input type="password" name = "password" value="" maxlength="50" id = "textfiled"  />
					</div>
					<div class="loginButton">

						<label><input type="checkbox" name="remember" />아이디 저장<br></label>
						<button type="button" onclick="loginVaildCheck()" id = "loginbut">로그인</button>
						<button type = "button" onclick="location.href='join.jsp'" id = "joinbut">회원가입</button>
					</div>
				</div><!-- 로그인 박스 끝 -->
			</form>
		</section>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>