function treatStatusChecking(treatStatus) {
	if(treatStatus == "Y") {
		alert("이미 진료 확인 처리된 예약 항목이므로 처리할 수 없습니다.");
		return true;
	}
}

//진료확인 버튼 함수
function treatmentAdd(resCode, treatStatus) {
	if(confirm("해당 예약 진료 항목을 진료를 받은 것으로 처리하시겠습니까?")) {
	
		if(treatStatusChecking(treatStatus)) {
			return false;
		}
	
		//form 객체 생성
		let treatmentAddForm = document.createElement("form");

		//from 내장값 설정
		treatmentAddForm.name = "treatmentAddDataForm";
		treatmentAddForm.method = "post";
		treatmentAddForm.action = "treatmentAddConfirm.ad";

		//전송에 필요한 hidden inpurt 태그 생성
		let resCode_data = document.createElement("input");
		resCode_data.setAttribute("type", "hidden");
		resCode_data.setAttribute("name", "reservation_code");
		resCode_data.setAttribute("value", resCode);

		//input 태그를 폼에 추가
		treatmentAddForm.appendChild(resCode_data);

		//폼 요소를 body에 추가함
		document.body.appendChild(treatmentAddForm);

		//추가된 form을 submit
		treatmentAddForm.submit();
	}
}

//수정 버튼 함수
function modifyRes(specCode,resCode, treatStatus) {
	if(confirm("진료예약을 수정하시겠습니까?")) {
		
		if(treatStatusChecking(treatStatus)) {
			return false;
		}
		
		//form 객체 만들기
		let modifyDataForm = document.createElement("form");
	
		//form 내장값 설정
		modifyDataForm.name = "modifyDataForm";
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
function cancelRes(thisRow) {

	//------버튼을 클릭한 같은 행의 정보를 가져오는 방법
	let currentRow = thisRow.closest("tr");
	
	let resCode = currentRow.children[1].innerText;
	let cancel_id = currentRow.children[3].innerText;
	let treatStatus = currentRow.children[7].innerText;
	//------버튼을 클릭한 같은 행의 정보를 가져오는 방법

	if(confirm("해당 진료예약을 취소하시겠습니까?")) {		
		if(treatStatusChecking(treatStatus)) {
			return false;
		}
		
		//form 객체 만들기
		let deleteDataForm = document.createElement("form");
	
		//form 내장값 설정
		deleteDataForm.name = "deleteDataForm";
		deleteDataForm.method = "post";
		deleteDataForm.action = "myReservationCancel.treat";
		
		//전송에 필요한 hidden input 태그 생성
		let reservation_code_input = document.createElement("input");
		let cancel_id_input = document.createElement("input");

		//hidden input 태그 설정
		reservation_code_input.setAttribute("type", "hidden");
		reservation_code_input.setAttribute("name", "reservation_code");
		reservation_code_input.setAttribute("value", resCode);
		deleteDataForm.appendChild(reservation_code_input);

		cancel_id_input.setAttribute("type", "hidden");
		cancel_id_input.setAttribute("name", "cancel_id");
		cancel_id_input.setAttribute("value", cancel_id);
		deleteDataForm.appendChild(cancel_id_input);

		//폼 요소를 body에 추가함
		document.body.appendChild(deleteDataForm);
		
		//추가된 form을 submit
		deleteDataForm.submit();
		
	}
}