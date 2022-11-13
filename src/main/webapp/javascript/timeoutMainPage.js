//일정 시간 후 메인페이지 이동
let timer = 5; //초단위
window.onload = function() {

	//1초마다 실행됨
	setInterval(function() {
		timer--;
		document.getElementById("time").innerHTML = timer;
		
		//timer가 0이 되면 index로 이동
		if(timer === 0) {
			location.replace(contextPath + "/index.do");
		}
	}, 1000);
}