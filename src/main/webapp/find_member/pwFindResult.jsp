<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>율제병원 - 비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/init/ul_listStyle_none.css">
<style>
/* 	#loginGo {
		background-color: lightblue;
		color: black;
		font-weight: bold;
		transform: all 0.5s;
	}
	#homeGo {
		transform: all 0.5s;
		background-color: lightgray;
		color: black;
		font-weight: bold;
	}
	
	#loginGo:hover {
		transform: all 0.5s;
		background-color: #00B4DB;
	}
	#homeGo:hover {
		transform: all 0.5s;
		background-color: #5D5D5D;
		color: white;
	} */
	
	
</style>
<script>
	const contextPath = "${pageContext.request.contextPath}";
	window.onload = function() {
		//뒤로가기 클릭 리스너
		document.getElementById("BackBtn").addEventListener("click", function() {
			history.back();
		});
	};
</script>
</head>
<body>
	<div class="my-5">
		<h1 class="text-center">비밀번호를 변경해 주세요</h1>
		<div>
			<form action="${pageContext.request.contextPath}/find_member/pwUpdate.do" method="post">
				<div id = "findFormDiv" class="d-flex flex-column">
					<div class = "col mt-3 ml-3 fw-bold">
						<label for="u_id">아이디</label>
					</div>
					<div class = "col">
						<input type="text" id = "u_id" name = "u_id" class="textfiled" required="required" placeholder="아이디"/>
					</div>
					
					<div class = "col fw-bold">
						<label for="u_id">변경할 비밀번호</label>
					</div>
					<div class = "col">
						<input type="password" maxlength="20" size="20" id = "password" name = "password" class="textfiled" required="required" placeholder="변경할 비밀번호"/>
					</div>
					
					<div class = "col py-3 text-center">
						<button id = "loginGo" type="submit" class="btn btn-primary fs-4">변경하기</button>
						<button type = "button" id = "BackBtn" class="btn btn-light fs-4">뒤로가기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>