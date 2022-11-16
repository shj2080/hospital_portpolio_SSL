<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	request.setCharacterEncoding("UTF-8");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>율제병원 - 회원가입</title>

<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<!-- <link rel="stylesheet" type="text/css" href="style/join.css"> -->
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">


</head>

<script type="text/javascript">

/* 회원가입 유효성검사 시작 */
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
		
		join.dupCheck();
		document.join.submit();
	}
/* 회원가입 유효성검사 끝 */
	
</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript" src = "javascript/phoneNumberFormat.js"></script>

<!-- 우편번호찾기 카카오API 시작 회원가입 주소용 -->
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
<!-- 우편번호찾기 카카오API 끝 회원가입 주소용 -->


<body>
	<!-- header.jsp불러오기 -->
	<jsp:include page="header.jsp" />
	
	<!-- 본문 시작구간 -->
	<div id = "contentWrap">
	<section>
		<div>
		
		<!-- 회원가입에 필요한 정보들 입력받기 시작 -->
		<form action="join.do" method="post" name = "join">
			
			<!-- 조인 박스 시작 -->
			<table id = "joinBox" class="table table-hover">
			
				<tr>
					<th colspan="2">
						회원가입
					</th>
				</tr>
				
				<!-- 이름 입력창 시작 -->
				<tr class="box1" >
					<td>성함</td>
					<td>
						<div class = "box2 col-sm-3">
							<input type="text" name="name" size = "20" maxlength="20" class = "textfiled form-control fs-4">
						</div>
					</td>
				</tr>
				<!-- 이름 입력창 끝 -->
				
				<!-- 주민등록번호 입력창 시작 -->
				<tr class = "box1">
					<td>주민등록번호</td>
					<td>
						<div class = "row">
							<div class = "box2 col-sm-4">
								<input type="text" name="front_id_num" size = "9" maxlength="6" class = "form-control fs-4" id = "front_id_num"/>
							</div> - 
							<div class = "col-sm-4">
								<input type="password" name="back_id_num" size = "9" maxlength="7" class = "form-control fs-4"  id = "back_id_num"/>
							</div>
						</div>
					</td>
				</tr>
				<!-- 주민등록번호 입력창 끝 -->
				
				<!-- id 입력창 시작 -->
				<tr class="box1" >
					<td>아이디</td>
					<td>
						<div class = "box2 row">
							<div class = "col-sm-3">
								<input type="text" name="id" size=20 required="required" readonly="readonly" placeholder="id중복 확인을 눌러주세요!" class = "form-control fs-4" id = "id" />
							</div>
							<div class = "col-sm-4">
								<input type="button" name="u_idck" id="u_idck" value="id중복 확인" 
								onclick="window.open('idCheck.jsp', '아이디중복확인', 'width=400, height=150')" class="btn btn-secondary btn-lg"/>
							</div>
						</div>
					</td>
				</tr>
				<!-- id 입력창 끝 -->
			
				<!-- pw 입력창 시작 -->
				<tr class=box1>
					<td>비밀번호</td>
					<td>
						<div class = "row">
							<div class = "col-sm-4">
								<input type="password" name="password" size = "20" maxlength="20" class = "textfiled form-control fs-4">
							</div>
						</div>
					</td>
				</tr>
				<!-- pw 입력창 끝 -->
				
				<!-- 주소 입력창 시작 -->
				<tr class="box1" >
					<td>주소</td>
					<td>
						<div class = "box2 row">
							<div class = "col-sm-3">
								<input type="text" id="postcode" name = "postcode" class = "form-control fs-4 my-1" placeholder="우편번호">
							</div>
							<div class = "col-sm-4">
								<button type="button" onclick="sample6_execDaumPostcode()" class="btn btn-secondary btn-lg">우편번호 찾기</button>
							</div>
						</div>
						<div class = "row">
							<div class = "col-sm-5">
								<input type="text" name = "address1" id="address1" class = "form-control fs-4 my-1" placeholder="주소" readonly>
								<input type="text" name = "address2" id="address2" class = "form-control fs-4 my-1" placeholder="건물이름" readonly>
								<input type="text" name = "address3" id="address3" class = "form-control fs-4 my-1" placeholder="상세주소">
							</div>
						</div>
					<td>
				</tr>
				<!-- 주소 입력창 끝 -->
				
				<!-- 전화번호 입력창 시작 -->
				<tr>
					<td>전화번호</td>
					<td>
						<div class = "col-sm-5">
							<input type="text" name="phone" class = "form-control fs-4" maxlength="13" oninput="autoHyphen(this)" />
						</div>
					</td>
				</tr>
				<!-- 전화번호 입력창 끝 -->
				
				<!-- 회원가입 버튼 시작 -->
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type = "submit" value="회원가입" onclick="check(); return false" id="button" class="btn btn-outline-secondary fs-4">
					<td>
				</tr>
				<!-- 회원가입 버튼 끝 -->
				
				</table>
				<!-- 조인박스 끝 -->
				
		</form>
		<!-- 회원가입에 필요한 정보들 입력받기 끝 -->
		
		</div>	
	</section>
	
	<!-- 본문 끝 -->
	</div>
	
	<!-- footer.jsp 불러오기 -->
	<jsp:include page="footer.jsp" />
</body>
</html>