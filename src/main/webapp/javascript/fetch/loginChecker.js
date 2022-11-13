//로그인 처리 버튼 FETCH <-> 서블릿으로 보내서 결과 가져오기
function loginProcess(){
	// 폼의 값들을 가져가게 함
	let loginForm = document.getElementById("loginForm");
	
	
	const loginFormData = new FormData(loginForm);
	//폼 데이터들을 QueryString으로 만듬
	const loginQueryString = new URLSearchParams(loginFormData).toString();
	
	fetch("loginProcess.fe",{
		method: "POST",
		body: loginQueryString,
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"}
	})
	.then((response) => {return response.json()})
	.then((data) => {
		if(data.result === true) {
			location.replace("index.do");
		}
		else {
			alert("아이디나 비밀번호가 일치하지 않습니다.");
		}
	});
}