<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
	function check() { //회원가입 유효성 검사
		if (!checkbox.id.value) {
			alert("id를 입력해주세요.");
			return checkbox.id.focus();
		}
		checkbox.submit();
	}
</script>

<body>
	<h1>아이디를 입력해 주세요</h1>
	<form action="MyTratmentList.do" method="post" name = "checkbox">
			<div>
				<input type = "text" size = "20" name = "id"/>
				<input type = "button" value="확인" onclick="check();" />
			</div>
	</form>
</body>
</html>