<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원</title>
	<link rel="stylesheet" type="text/css" href="style/initStyle.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	<!-- <link rel="stylesheet" type="text/css" href="style/header.css"> -->
	<link rel="stylesheet" type="text/css" href="style/body.css">
	<!-- <link rel="stylesheet" type="text/css" href="style/footer.css"> -->
	<link rel="stylesheet" type="text/css" href="style/board.css">
	<script src = "javascript/board/boardFormMove_script.js">
	
	</script>
</head>
<body>
<%-- 수정작업을 하는 경우 수정할 게시글 정보를 얻어옴 --%>
<c:if test="${modifyData != null }">
	<c:set var = "subject" value = "${modifyData.post_subject }" />
	<c:set var = "board_text" value = "${modifyData.post_text }" />
	<c:set var = "post_no" value = "${modifyData.post_no}"/>
</c:if>
	<!-- header.jsp 불러오기 -->
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div class="row">
				
			<!-- userBoardWriteAction.do로 입력받은 값을 보내줄 테이블 시작 -->
				<form method="post" action="userBoardWriteAction.do" name = "boardWriteForm" onsubmit="return modifyChk();">
						<table class="table table-striped" id = "boardWriteBox">
						
						<!-- 헤더 부분 시작 -->
							<thead>
								<tr>
									<th class = "fs-4"colspan ="2" style="background-color : #eeeeee; text-align : center;">게시판 글쓰기 양식</th>
								</tr>
							</thead>
						<!-- 헤더 부분 시작 -->
						
							<tbody>
							<!-- 글제목 입력 부분 시작 -->
								<tr>
									<th class = "fs-4">제목</th>
									<td>
									<input type="text" class="form-control fs-4" placeholder="글제목" name="post_subject" maxlength="50" style="width: 480px;" value = "${subject }" required>
									</td>
								</tr>
							<!-- 글제목 입력 부분 끝 -->
							
							<!-- id 입력 부분 시작 -->
								<tr>
									<th class = "fs-4">ID</th>
									<td>
										<input type="text" class="form-control fs-4" name="id" value = "${userID}" style="width: 480px;" readonly>
									</td>
								</tr>
							<!-- id 입력 부분 끝 -->
							
							<!-- 글내용 입력 부분 시작 -->
								<tr>
									<th class = "fs-4" colspan="2">내용</th>
								</tr>
								<tr>
									<td class = "fs-4" colspan="2"><textarea  class="form-control fs-4" placeholder="글 내용" name="post_text" maxlength="500" style="height:350px;">${board_text}</textarea></td>
								</tr>
							<!-- 글내용 입력 부분 끝 -->
							
<!-- 							게시글에 대한 비밀번호 입력 부분 시작
								<tr>
									<th class = "fs-4">비밀번호</th>
									<td><input type="password" class="form-control fs-4" placeholder="글 비밀번호" name="post_pwd" maxlength="50"></td>
								</tr>
							게시글에 대한 비밀번호 입력 부분 끝 -->
							</tbody>
						</table>
				<!-- 입력받은 값들을 userBoardWriteAction.do로 보내줄 버튼 시작 -->
					<div align="center">
						<a href="userBoard.do" class="btn btn-primary fs-4">목록</a>
					<c:choose>
						<c:when test="${modifyData == null }">
							<input type="submit" class="btn btn-primary pull-right fs-4" value="글쓰기">
						</c:when>
						<c:otherwise>
							<input type="submit" id = "modifyBtn" class="btn btn-secondary fs-4" value="수정">
							<input type = "hidden" name = "post_no" value = "${post_no}" readonly/>
						</c:otherwise>
					</c:choose>
					</div>
				<!-- 입력받은 값들을 userBoardWriteAction.do로 보내줄 버튼 끝 -->
				
				</form>
			<!-- userBoardWriteAction.do로 입력받은 값을 보내줄 테이블 끝 -->
			
				</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>