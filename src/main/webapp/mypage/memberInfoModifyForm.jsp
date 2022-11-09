<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script type="text/javascript" src = "javascript/mypage/removeChecking.js"></script>
<script type="text/javascript">
	function check() {
		if (!memberform.name.value) {
			alert("성함을 입력해주세요.");
			return memberform.name.focus();
		}

		if (!memberform.front_id_num.value) {
			alert("주민등록번호 앞자리를 입력해주세요.");
			return memberform.front_id_num.focus();
		}
		if (!memberform.back_id_num.value) {
			alert("주민등록번호 뒷자리를 입력해주세요.");
			return memberform.back_id_num.focus();
		}
		
		if (!memberform.id.value) {
			alert("id를 입력해주세요.");
			return memberform.id.focus();
		}

		if (!memberform.address1.value) {
			alert("지번을 입력해주세요.");
			return memberform.address1.focus();
		}
		
		if (!memberform.postcode.value) {
			alert("주소를 입력해주세요.");
			return memberform.postcode.focus();
		}

		if (!memberform.phone.value) {
			alert("전화번호를 입력해주세요.");
			return memberform.phone.focus();
		}

		document.memberform.submit();
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
                    document.getElementById("address2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("address3").focus();
            }
        }).open();
        
    }
</script>
<script type="text/javascript" src = "javascript/phoneNumberFormat.js"></script>
<script type = "text/javascript">
	window.onload = function() {
		document.getElementById("removeMemBtn").addEventListener("click", removeChk);
	};
</script>
<link rel="stylesheet" type="text/css" href = "style/mypage/mypageListTable_style.css">
</head>
<body>
	<form action = "memberInfoModifyAction.do" name = "memberform" method="post">
		<table id = "memberTable" class="table table-hover">
			<tr>
				<th colspan="2">회원수정</th>
			</tr>
			<tr>
				<td>성함</td>
				<td>
					<div class = "col-sm-3">
						<input type="text" name="name" class = "form-control fs-4" value = "${memberInfo.name}" maxlength="20">
					</div>
				</td>
			</tr>
			<tr>
				<td>주민등록번호</td>
				<td>
					<div class = "row">
						<div class = "col-sm-4">
						<input type="text" name="front_id_num" class = "form-control fs-4"  value = "${front_id_num}" maxlength="6"/>
						</div> - 
						<div class = "col-sm-4">
						<input type="password" name="back_id_num" class = "form-control fs-4"  value ="${back_id_num}" maxlength="7"/>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<div class = "col-sm-4">
						<input type="text" name="id"  class = "form-control fs-4"  maxlength="15" value = "${memberInfo.id}" readonly>
					</div>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<div class = "row">
						<div class = "col-sm-4">
					<input type="password" name="password" class = "form-control fs-4" value = "" maxlength="20" placeholder="변경할 비밀번호">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					
					<div class = "row">
						<div class = "col-sm-3">
							<input type="text" name = "postcode" id="postcode" class = "form-control fs-4 my-1" value = "${memberInfo.postcode}" placeholder="우편번호" readonly>
						</div>
						<div class = "col-sm-4">
							<button type="button" onclick="sample6_execDaumPostcode()" class="btn btn-secondary btn-lg">우편번호 찾기</button>
						</div>
					</div>
					<div class = "row">
						<div class = "col-sm-5">
							<input type="text" name = "address1" id="address1" class = "form-control fs-4 my-1" value = "${memberInfo.address1}" placeholder="주소" readonly>
							<input type="text" name = "address2" id="address2" class = "form-control fs-4 my-1" value = "${memberInfo.address2}" placeholder="건물이름" readonly>
							<input type="text" name = "address3" id="address3" class = "form-control fs-4 my-1" value = "${memberInfo.address3}" placeholder="상세주소">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<div class = "col-sm-5">
						<input type="text" name="phone" class = "form-control fs-4" value = "${memberInfo.phone}" maxlength="13" oninput="autoHyphen(this)" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type = "submit" onclick="check(); return false" class="btn btn-outline-secondary fs-4">회원수정</button>
					<button id = "removeMemBtn" type = "button" class="btn btn-danger fs-4">회원탈퇴</button>					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>