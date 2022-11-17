let selectDayPopup;
	
window.onload = function() {
	
	const selectDayPopupBtn = document.getElementById("selectDayBtn");
	//날짜 선택 클릭시 실행되는 이벤트 리스너
	selectDayPopupBtn.addEventListener("click", () => {
		if(!selectDayPopup || selectDayPopup.closed) {
			//팝업 객체가 없거나 팝업이 닫혀있는 상태인 경우 실행됨
			const url = "SelectReservationDay.treat";
			const windowName = "selectReservationDayWindow";
			const left = (screen.width/2)-(200/2);
			const top = (screen.height/2)-(200/2);
			const popupOption = "width=265, height=260, menubar=no, toolbar=no, location=no, status=no,scrollbars=no, resizable=no, top="+top+", left=" + left;
			
			//현재 창 이름을 지정(opener undefined 문제 해결 용도)
			window.name = "reservationFormWindow";
			selectDayPopup = window.open(url, windowName, popupOption); //날짜 선택 팝업을 띄움
		} else {
			selectDayPopup.focus();
		}
	});
}

function settingTreatmentDay(inputDay) {
	const reservationDay = document.getElementById("reservationDay");
	reservationDay.value = inputDay;
}