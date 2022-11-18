//인터넷 익스플로러 감지 시 엣지 브라우저 자동 실행
function ieNotSupport() {
	let url = window.location.href;
	
	if(navigator.userAgent.indexOf("Trident") > 0){
		alert("현재 페이지는 Microsoft Edge, Chrome 브라우저에 최적화 되어있습니다.\n" +
		"원할한 사용을 원하시면 Microsoft Edge, Chrome 브라우저를 권장합니다.");
		window.location = 'microsoft-edge:' + url;
	}else if(/MSIE \d |Trident.*rv:/.test(navigator.userAgent)){
		alert("현재 페이지는 Microsoft Edge, Chrome 브라우저에 최적화 되어있습니다.\n" +
		"원할한 사용을 원하시면 Microsoft Edge, Chrome 브라우저를 권장합니다.");
		window.location = 'microsoft-edge:' + url;
	}
}