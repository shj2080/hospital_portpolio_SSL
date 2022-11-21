//현재 index페이지가 Servlet을 거친 페이지인지 확인
window.onload = function() {
<<<<<<< HEAD
=======
	ieNotSupport();
>>>>>>> 3da05b3c79a8608d52dd411c814d2cfa66ea29d5
	//주소표시줄에서 마지막 / 뒤의 파일명을 가져옴
	let nowPageFile = document.URL.substring(document.URL.lastIndexOf('/') + 1, document.URL.length);
	
	//index.do로 접근하지 않은 경우 index.do로 리다이렉션함
	if(nowPageFile !== "index.do") {
		location.replace("index.do");
	}
}