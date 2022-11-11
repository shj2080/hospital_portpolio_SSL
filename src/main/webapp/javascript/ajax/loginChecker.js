//중복체크 버튼 AJAX <-> 서블릿으로 보내서 결과 가져오기
function loginProcess(){
	// 폼의 값들을 가져가게 함
	let loginString = $('#loginForm').serialize();
	$.ajax({
		type: 'POST',  // GET or POST 전송방법 
		url: 'loginProcess.aj',  // 이쪽으로 보낸다(호출URL)
		data: loginString,  // 폼 내부 값들 대입
		success: function(result){  // 만약 성공적으로 수행되었다면 result로 값반환
			if(result == "true"){  // id가 checkMessage인 것에 아래 텍스트 출력
				location.replace("index.do");
			} else {
				alert('아이디나 비밀번호가 일치하지 않습니다.');
			}
		} 
	});
}