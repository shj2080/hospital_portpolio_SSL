//예약 폼 유효성 검사
function vaildCheck() {
	let nowDate = new Date();
	let nowHour = nowDate.getHours();
	let nowMinute = nowDate.getMinutes();
	
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
	}else if (resForm.treatmentHour.value < nowHour || resForm.treatmentMinute.value < nowMinute) {
		//현재시간보다 진료 예약시간이 과거인 경우
		alert("진료 예약 시간은 현재 시간 이전으로 선택할 수 없습니다.");
		return false;
	}

	//timeFormatChange(resForm.treatmentHour.value, resForm.treatmentMinute.value);
	
	resForm.submit();
}

//폼 작성 리셋
function resetCheck() {
	if(confirm("다시 작성하시겠습니까?")) {
		resForm.reset();
	}
}