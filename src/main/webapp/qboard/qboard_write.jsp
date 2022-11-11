<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<title>게시판 글쓰기</title>
<style type="text/css">

h4 {
	text-align: center; margin-top: 20px;
}

h4 {
	text-align: center;
}


#td_left {
    text-align:center;
	font-size: 12px; 
}
#commandCell {
	text-align: center; 
}

p{
	text-align: center;
}

#b1{
	margin-bottom: 10px;
}
#table-first{
	margin:0 auto;
	width: 60%;
	border-color: #e1e4e1;
}

#address{
	width: 50%;
}

</style>
</head>



<body>
	<jsp:include page="../header.jsp" />

	<div id = "contentWrap">

	<section id="writeForm">
		<h4>Q&A</h4>
		  <h4><a href="qboardList.qna" class="btn btn-outline-dark fs-4" style="font-size:12px;">게시판목록</a></h4>
		   	  
		<form action="qboardWritePro.qna" method="post" name="boardform"> <%--multypart/form-data : 파일이나 이미지전송할때 --%>
			<!-- 로그인한 아이디로 게시글 작성시 로그인한 ID를 sql의 MEM_ID에 넣기위해 hidden으로 전송 -->
   	        <input type="hidden" id="loginID" name="loginID" value="${userID}">
			<table id="table-first" class="table table-bordered fs-4" >
	   	        <tr>
		   	          <td id="td_left" class="fs-4">작성자 ID</td>
		   	          <td>${userID}</td>	   	          
	   	        </tr>
				<tr>
					  <td id="td_left"><label for="QBOARD_SUBJECT" class="fs-4">제목</label></td>
					  <td>
				 <input name="QBOARD_SUBJECT" type="text"id="QBOARD_SUBJECT" required="required" size="67" class="form-control fs-4"/>
				      </td>
				</tr>
		   
		 		<tr>
					<td id="td_left"><label for="QBOARD_CONTENT" class="fs-4">내용</label></td>
					<td><textarea id="QBOARD_CONTENT" name="QBOARD_CONTENT"
				cols="80" rows="10" required="required" class="form-control fs-4"style="font-size:12px; resize: none;"></textarea></td>
				</tr>
		

			</table>
			<section id="commandCell">
		      	<!-- <input hidden="hidden" /> --> <!-- 엔터방지 -->
				<input type="submit"  class="btn btn-outline-dark fs-4" id="b1" value="등록" id="b1" style="font-size:12px;">&nbsp;&nbsp;   <!-- 유효성 체크함수로 주소입력버튼을 클릭안하면 등록버튼 누를 수 없게 방지 -->
				<input type="reset" class="btn btn-outline-dark fs-4"  id="b1" value="다시쓰기" id="b1" style="font-size:12px;"/>
			</section>

</form>
</section>
</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>