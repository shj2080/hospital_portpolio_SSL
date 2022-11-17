<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>율제병원 - 비밀번호 찾기</title>

<script src="${pageContext.request.contextPath}/javascript/numberOnly.js">
</script>
<script type="text/javascript">
function vaildCheck() {
	
	if(!findForm.u_name.value) {
		alert("이름을 입력해주세요.");
		findForm.front_id_num.focus();
		return false;
	}
	if(!findForm.front_id_num.value) {
		alert("주민번호 앞자리를 입력해주세요.");
		findForm.front_id_num.focus();
		return false;
	}
	if(!findForm.back_id_num.value) {
		alert("주민번호 뒷자리를 입력해주세요.");
		findForm.back_id_num.focus();
		return false;
	}
	
	return true;
}
</script>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("BackBtn").addEventListener("click", () => {
			history.back();
		});
	};
</script>
</head>
<body>

	<form action = "${pageContext.request.contextPath}/find_member/pwFind.do" method = "post" name = "findForm" onsubmit = "return vaildCheck();">
		<div id = "findFormDiv" class="d-flex flex-column">
			<div class="col text-center fw-bold">
				<span class="fs-3">비밀번호를 찾기 위해 회원님의 이름, 주민등록번호, 아이디를 입력해주세요.</span>
			</div>
			<div class = "col mt-3 ml-3 fw-bold">
			<label for="u_name">이름</label>
			</div>
			<div class = "col">
				<input type="text" id = "u_name" name = "u_name" class="textfiled" required="required" placeholder="이름"/>
			</div>
			
			<div class = "col fw-bold">
			<label for="u_id">아이디</label>
			</div>
			<div class = "col">
				<input type="text" id = "u_id" name = "u_id" class="textfiled" required="required" placeholder="아이디"/>
			</div>
			
			<div class = "col fw-bold">
			<label for ="front_id_num">주민등록번호</label>
			</div>
			<div class = "col">
				<input type="text" id = "front_id_num" name = "front_id_num" class="textfiled" maxlength="6" oninput="numberOnly(this)" required="required" placeholder="주민번호 앞자리"/>
				<input type="password" id = "back_id_num" name = "back_id_num" class="textfiled" maxlength="7" oninput="numberOnly(this)" required="required" placeholder="주민번호 뒷자리"/>
			</div>
			
			<div class = "col py-3 text-center">
				<button type = "submit" id = "findBtn" class="btn btn-primary fs-4">찾기</button>
				<button type = "button" id = "BackBtn" class="btn btn-light fs-4">뒤로가기</button>
			</div>
		</div>
	</form>

</body>
</html>