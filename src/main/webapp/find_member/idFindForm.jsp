<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script src="${pageContext.request.contextPath}/javascript/numberOnly.js">
</script>
<script type="text/javascript">
function vaildCheck() {
	
	if(!findForm.u_name.value) {
		alert("주민번호 앞자리를 입력해주세요.");
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
	window.onload = () => {
		document.getElementById("BackBtn").addEventListener("click", () => {
			history.back();
		});
	};
</script>
<style>
#findFormDiv {
	width: 80%;
	margin: 5rem auto;
	border: 1px dotted black;
}
</style>
</head>
<body>

<form action = "${pageContext.request.contextPath}/find_member/idFind.do" method = "post" name = "findForm" onsubmit = "return vaildCheck();">
	<div id = "findFormDiv" class="d-flex flex-column text-center">
		<div class="col my-3 mx-3">
			<span class="fs-3">ID를 찾기 위해 회원님의 이름과 주민등록번호를 입력해주세요.</span>
		</div>
		<div class = "col my-3 mx-3">
			<label>이름 <input type="text" name = "u_name" required="required"/></label>
		</div>
		<div class = "row my-3 ">
			<div class = "col my-3 mx-3 flex-fill">
			주민등록번호
			</div>
			<div class = "col my-3 mx-3 flex-fill">
				<input type="text" name = "front_id_num" maxlength="6" oninput="numberOnly(this)" required="required"/>
				<input type="password" name = "back_id_num" maxlength="7" oninput="numberOnly(this)" required="required"/>
			</div>
		</div>
		
		<div class = "row my-3 mx-3">
			<div class = "col">
				<button type = "submit" id = "findBtn" class="btn btn-outline fs-4">찾기</button>
				<button type = "button" id = "BackBtn" class="btn btn-outline fs-4">뒤로가기</button>
			</div>
		</div>
	</div>
</form>
</body>
</html>