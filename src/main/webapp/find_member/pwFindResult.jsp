<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/init/ul_listStyle_none.css">
<style>
	#detailID {
		font-size:2.5rem;
	}
	#loginGo {
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
	}
	
	
</style>
<script>
	const contextPath = "${pageContext.request.contextPath}";
	window.onload = () => {
		document.getElementById("loginGo").addEventListener("click", () => {
			location.href = contextPath + "/login.do";
		});
		document.getElementById("homeGo").addEventListener("click", () => {
			location.href = contextPath + "/index.do";
		});
	};
</script>
</head>
<body>
	<div class="text-center my-5">
		<h1>비밀번호를 변경해 주세요</h1>
		<div>
			<form action="${pageContext.request.contextPath}/find_member/pwUpdate.do" method="post">
				<div class = "col my-3 mx-3">
					<label>아이디 <input type="text" name = "u_id" required="required"/></label>
				</div>
				<div>
					변경할 비밀번호 : <input type = "password" maxlength="20" size="20" name = "password" required="required"/>
				</div>
				<div>
					<button id = "loginGo" type="submit" class="btn fs-4">변경하기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>