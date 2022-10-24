<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	request.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<style type="text/css">
	
	table {
    border: 1px solid #444444;
    border-collapse: collapse;
    margin-left:auto; 
    margin-right:auto;
    margin-top: 10rem;
	}
	
	tr, td, th {
    border: 1px solid #444444;
	padding: 1rem;
	}
	
</style>
</head>

<script type="text/javascript">
	function check() {
		if (!join.name.value) {
			alert("성함을 입력해주세요.");
			return join.name.focus();
		}

		if (!join.front_id_num.value) {
			alert("주민등록번호 앞자리를 입력해주세요.");
			return join.front_id_num.focus();
		}
		if (!join.back_id_num.value) {
			alert("주민등록번호 뒷자리를 입력해주세요.");
			return join.back_id_num.focus();
		}
		
		if (!join.id.value) {
			alert("id를 입력해주세요.");
			return join.id.focus();
		}

		if (!join.password.value) {
			alert("비밀번호를 입력해주세요.");
			return join.password.focus();
		}

		if (!join.address.value) {
			alert("주소를 입력해주세요.");
			return join.address.focus();
		}

		if (!join.phone.value) {
			alert("전화번호를 입력해주세요.");
			return join.phone.focus();
		}

		document.join.submit();
	}
</script>

<body>
	<jsp:include page="header.jsp" />
	<div id = "warpper">
	<section>
		<div>
		<form action="join.do" method="post" name = "join">
			<table>
				<tr>
					<th colspan="2">회원가입</th>
				</tr>
				<tr>
					<td>성함</td>
					<td><input type="text" name="name" size = "20" maxlength="20"></td>
				</tr>
				<tr>
					<td>주민등록번호</td>
					<td>
						<input type="text" name="front_id_num" size = "20" maxlength="6"/> - 
						<input type="password" name="back_id_num" size = "20" maxlength="7"/>
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" size = "20" maxlength="15"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" size = "20" maxlength="20"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" size = "20" maxlength="50"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" size = "20" maxlength="14"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type = "submit" value="회원가입" onclick="check(); return false">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</section>
	<jsp:include page="footer.jsp" />
	</div>
</body>
</html>