<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/board.css">
<script type="text/babel" data-presets="es2015,stage-2" src = "${pageContext.request.contextPath}/javascript/board/fileDownload.js"></script>
</head>
<body>
	<!-- header.jsp 불러오기 -->
	<jsp:include page="header.jsp" />
	
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div class="row">
				
				<!-- 게시판 글보기 테이블 시작 -->
					<table class="table"  id = "boardViewBox">
					
					<!-- 헤더 부분 시작 -->
						<thead>
							<tr>
								<th colspan="3"style="background-color: #eeeeee; text-align: center;">게시판 글보기</th>
							</tr>
						</thead>
					<!-- 헤더 부분 끝 -->
						
					<!-- 게시글 내용이 있다면 출력 시작 -->
						<c:if test="${showPost != null}">
						<tbody>
						
						<!-- 글제목 출력 시작 -->
							<tr>
								<th style="width: 20%;">글 제목</th>
								<td colspan="2">${showPost.post_subject}</td>
							</tr>
						<!-- 글제목 출력 끝 -->
						
						<!-- 작성자 출력 시작 -->
							<tr>
								<th>작성자</th>
								<td colspan="2">${showPost.id }</td>
							</tr>
						<!-- 작성자 출력 끝 -->
						
						<!-- 작성일자 출력 시작 -->
							<tr>
								<th>작성일자</th>
								<td colspan="2">${showPost.post_date }</td>
							</tr>
						<!-- 작성일자 출력 끝 -->
						
							
						<c:if test="${attachFiles != null }">
						<!-- 첨부파일 출력 시작 -->
						<tr class="text-center">
							<th>첨부파일</th>
						</tr>
						<c:forEach var = "attachFile" items="${attachFiles}" varStatus = "stat">
							<tr>
								<td colspan="3">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
									  <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
									  <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
									</svg>
									<span id = "attach${stat.count}" class = "attachDownLoad">${attachFile.original_name}</span>
								<script>
								document.getElementById("attach${stat.count}").onclick = function(e) {
									e.stopPropagation();
									fileDown(${attachFile.file_idx})
								};
								</script>
								</td>
							</tr>
						</c:forEach>
						<!-- 첨부파일 출력 끝 -->
						</c:if>
						<!-- 게시글 내용 출력 시작 -->
							<tr>
								<th>내용</th>
							</tr>
							<tr>
							
								<td colspan="2" style="min-height: 200px; text-align: left;">
									${showPost.post_text }
								</td>
							
							</tr>
						<!-- 게시글 내용 출력 끝 -->
						</tbody>
						</c:if>
					<!-- 게시글 내용이 있다면 출력 끝 -->
					
				<!-- 게시글 내용이 없다면 출력 시작 -->
					<c:if test="${showPost == null}">
					<tbody>
						<tr>
							<td colspan="3">post 정보를 불러오지 못했습니다.</td>
						</tr>
					</tbody>
					</c:if>
				<!-- 게시글 내용이 없다면 출력 끝 -->
				
				</table>
			<!-- 게시판 글보기 테이블 끝 -->
				
			<div align="center">
				<a href="userBoard.do" class="btn btn-outline-dark fs-4">목록</a>
				<c:if test="${userID == showPost.id}">
				<button type="button" id = "modifyPostBtn" class = "btn btn-secondary fs-4">수정</button>
				<button type="button" id = "deletePostBtn" class = "btn btn-danger fs-4">삭제</button>
				</c:if>
			</div>
		</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script>  -->
<script>const post_no = "${param.post_no}";</script>
<script type = "text/javascript" src = "${pageContext.request.contextPath}/javascript/board/boardEventListener.js"></script>
</body>
</html>