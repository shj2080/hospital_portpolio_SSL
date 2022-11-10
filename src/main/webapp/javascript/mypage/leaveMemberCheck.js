//pwd 체크
function vaildPassword() {
	if(!pwdForm.password.value) {
		alert("비밀번호를 입력하세요.");
		pwdForm.password.value.focus();
		return false;
	}
	
	pwdForm.submit();
}