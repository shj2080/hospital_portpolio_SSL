//예약 폼 유효성 검사
function vaildCheck() {
	if(!resForm.treatmentDay.value) {
		alert("진료일자를 선택해주세요.");
		return false;
	}
	
	if (!resForm.doctor_code.value) {
		alert("의사를 선택해주세요.");
		return false;
	}
	
	if (!resForm.treatmentHour.value && !resForm.treatmentMinute.value) {
		alert("진료시간을 선택해주세요.");
		return false;
	}
	
	resForm.submit();
}

//폼 작성 리셋
function resetCheck() {
	if(confirm("다시 작성하시겠습니까?")) {
		resForm.reset();
	}
}