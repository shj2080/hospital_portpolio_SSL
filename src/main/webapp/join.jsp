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

		if (!join.address1.value) {
			alert("지번을 입력해주세요.");
			return join.address1.focus();
		}
		
		if (!join.postcode.value) {
			alert("주소를 입력해주세요.");
			return join.postcode.focus();
		}

		if (!join.phone.value) {
			alert("전화번호를 입력해주세요.");
			return join.phone.focus();
		}

		document.join.submit();
	}
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("address2").value = extraAddr;
                
                } else {
                    document.getElementById("address1").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("postcode").value = data.zonecode;
                document.getElementById("address1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address3").focus();
            }
        }).open();
        
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
						<input type="text" name="front_id_num" size = "9" maxlength="6"/> - 
						<input type="password" name="back_id_num" size = "9" maxlength="7"/>
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
					<td>
						<input type="text" id="postcode" name = "postcode" placeholder="우편번호">
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
						<input type="text" id="address1" name = "address1" placeholder="주소" readonly="readonly"><br>
						<input type="text" id="address2" name = "address2" placeholder="건물이름" readonly="readonly">
						<input type="text" id="address3" name = "address3" placeholder="상세주소">
					</td>
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