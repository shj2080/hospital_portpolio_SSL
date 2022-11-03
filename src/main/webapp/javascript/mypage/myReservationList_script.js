//수정 버튼 함수
function modifyRes(specCode,resCode) {
	if(confirm("진료예약을 수정하시겠습니까?")) {
		//form 객체 만들기
		let modifyDataForm = document.createElement("form");
	
		//form 내장값 설정
		modifyDataForm.name = "modifyDateForm";
		modifyDataForm.method = "post";
		modifyDataForm.action = "reservationForm.treat";
		
		//전송에 필요한 hidden input 태그 생성
		let speciality_code_input = document.createElement("input");
		let reservation_code_input = document.createElement("input");	
		let modifyState = document.createElement("input"); //수정하려는 경우
		
		//hidden input 태그에 값을 담음.
		//타입설정
		speciality_code_input.setAttribute("type", "hidden");
		reservation_code_input.setAttribute("type", "hidden");
		modifyState.setAttribute("type", "hidden");

		//input 태그 이름 설정
		speciality_code_input.setAttribute("name", "specialityCode");
		reservation_code_input.setAttribute("name", "reservation_code");
		modifyState.setAttribute("name", "modifyState");
		
		speciality_code_input.setAttribute("value", specCode);
		reservation_code_input.setAttribute("value", resCode);
		
		//진료예약폼을 출력할 때 수정하려는 상태임을 알림
		modifyState.setAttribute("value", "Y");
	
		//추가된 input 태그들을 폼에 추가함
		modifyDataForm.appendChild(speciality_code_input);
		modifyDataForm.appendChild(reservation_code_input);
		modifyDataForm.appendChild(modifyState);
		
		//폼 요소를 body에 추가함
		document.body.appendChild(modifyDataForm);
		
		//추가된 form을 submit
		modifyDataForm.submit();
	}
}

//취소 버튼 함수
function cancelRes(resCode) {
	if(confirm("해당 진료예약을 취소하시겠습니까?")) {
		//form 객체 만들기
		let deleteDataForm = document.createElement("form");
	
		//form 내장값 설정
		deleteDataForm.name = "deleteDataForm";
		deleteDataForm.method = "post";
		deleteDataForm.action = "myReservationCancel.treat";
		
		//전송에 필요한 hidden input 태그 생성
		let reservation_code_input = document.createElement("input");
		
		//hidden input 태그 설정
		reservation_code_input.setAttribute("type", "hidden");
		reservation_code_input.setAttribute("name", "reservation_code");
		reservation_code_input.setAttribute("value", resCode);
		deleteDataForm.appendChild(reservation_code_input);
		
		//폼 요소를 body에 추가함
		document.body.appendChild(deleteDataForm);
		
		//추가된 form을 submit
		deleteDataForm.submit();
		
	}
}