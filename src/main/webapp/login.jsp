<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 로그인</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/login.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<script type="text/javascript">

//userID 쿠키 가져오는 함수 시작지점
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
//userID 쿠키 가져오는 함수 끝

//자바스크립트에서 아이디 저장 체크박스 설정 시작
window.onload = function() {
	let saveid = getCookie(name);

	if(saveid == undefined) {
		loginForm.remember.checked = false;
	} else{
		loginForm.id.value = saveid;
		loginForm.remember.checked = true;
	}

};
//자바스크립트에서 아이디 저장 체크박스 설정 끝

/* 로그인 유효성 검사 시작 */
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
	
	loginForm.submit();
}
/* 로그인 유효성 검사 끝 */

</script>
</head>
<body>
	<!-- header.jsp불러오기 -->
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
		<!-- 컨텐츠 시작 위치 -->
		<section>
		
			<!-- loginProcess.do로 전송할 id pw 입력값 받기시작 -->
			<form action ="loginProcess.do" id = "loginForm" name = "loginForm" method="post">
				<!-- 로그인 박스 시작 -->
				<div id="loginBox">
				
					<!-- id 입력창 시작 -->
					<div class="loginItem">
						<span class="fs-4">아이디</span>
					</div>
					<div class="loginItem">
						<input type="text" name = "id" value="" maxlength="50" id = "idText" class = "textfiled fs-4" />
					</div>
					<!-- id 입력창 끝 -->
					
					<!-- pw 입력창 시작 -->
					<div class="loginItem">
						<span class="fs-4">비밀번호</span>
					</div>
					<div class="loginItem">
						<input type="password" name = "password" value="" maxlength="50" id = "pwText" class = "textfiled fs-4" />
					</div>
					<!-- pw 입력창 끝 -->
					
					<!-- 각종 버튼 시작 -->
					<div class="loginButton">
						<label>
						<input type="checkbox" name="remember" /><span class="fs-4">아이디 저장</span><br>
						</label>
						<button class = "fs-4" type="button" onclick="loginVaildCheck()" id = "loginbut">로그인</button>
						<button class = "fs-4" type = "button" onclick="location.href='join.jsp'" id = "joinbut">회원가입</button>
					</div>
					<!-- 각종 버튼 끝 -->
					
					<!-- 아이디,비밀번호 찾기 영역 시작 -->
					<div>
						<a href = "find_member/idFind.do" class="link-primary"><b>아이디 찾기</b></a>
						<a href = "#" class="link-primary"><b>비밀번호 찾기</b></a>
					</div>
					<!-- 아이디,비밀번호 찾기 영역 끝 -->
				<!-- 로그인 박스 끝 -->
				</div>
				<!-- loginProcess.do로 전송할 id pw 입력값 받기 끝 -->
			</form>
		</section>
		<!-- 컨텐츠 끝나는 위치 -->
		</div>
	<!-- footer.jsp 불러오기 -->
	<jsp:include page="footer.jsp" />
</body>
</html>