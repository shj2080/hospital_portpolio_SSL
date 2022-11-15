<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>병원 회원탈퇴 페이지</title>
<link rel = "stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/init/ul_listStyle_none.css">
<style>

.warningImg {
	width: 5rem;
	height: 5rem;
	margin: 0.5rem;
}

.unregisterDetail {
	width: 35rem;
	height: 10rem;
	overflow: auto;
	margin: 0 auto;
	border: 1px dotted black;
}

button {
	margin: 1rem auto;
}

</style>
<script type="text/javascript" src = "${pageContext.request.contextPath}/javascript/mypage/leaveMemberCheck.js"></script>
<script>
window.onload = () => {
	//회원탈퇴 진행 버튼 클릭시 이벤트리스너
	document.getElementById("removeMemBtn").addEventListener("click", vaildPassword);
}
</script>
</head>
<body>
	<div class="text-center">
		<h2 class = "my-3">회원 탈퇴</h2>
		<img src = "${pageContext.request.contextPath}/images/warning.png" class="warningImg">
		<div class = "unregisterDetail">
		<p>회원탈퇴를 진행할 경우 일부 정보에 더 이상 접근하지 못할 수도 있습니다.
		회원탈퇴를 계속 하시려면 현재 비밀번호를 입력 후 '회원탈퇴 진행' 버튼을 눌러주십시오.</p>
		</div>
			<form action = "memberUnResister.do" method = "post" name = "pwdForm">
				<ul class="my-4">
					<li><label>현재 비밀번호 <input type="password" name="password" size="14" maxlength="20"></label></li>
					<li><button id = "removeMemBtn" type = "button" class="btn btn-danger fs-4">회원탈퇴 진행</button></li>
				</ul>
			</form>
	</div>
</body>
</html>