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
	<ul>
		<li><span id = "detailID">회원님의 ID는 <b>'${findID}'</b>입니다.</span></li>
		<li>
			<button id = "loginGo" type="button" class="btn fs-4">로그인으로</button>
			<button id = "homeGo" type="button" class="btn fs-4">홈으로</button>
		</li>
	</ul>
	</div>
</body>
</html>