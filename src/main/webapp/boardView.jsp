<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/board.css">
<script>const post_no = "${param.post_no}";</script>
<script src = "${pageContext.request.contextPath}/javascript/board/boardModifyFormMove.js"></script>
</head>
<body>

	<!-- header.jsp 불러오기 -->
	<jsp:include page="header.jsp" />
	
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div class="row">
				
				<!-- 게시판 글보기 테이블 시작 -->
					<table class="table table-striped"  id = "boardViewBox">
					
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
								<td style="width: 20%;">글 제목</td>
								<td colspan="2">${showPost.post_subject}</td>
							</tr>
						<!-- 글제목 출력 끝 -->
						
						<!-- 작성자 출력 시작 -->
							<tr>
								<td>작성자</td>
								<td colspan="2">${showPost.id }</td>
							</tr>
						<!-- 작성자 출력 끝 -->
						
						<!-- 작성일자 출력 시작 -->
							<tr>
								<td>작성일자</td>
								<td colspan="2">${showPost.post_date }</td>
							</tr>
						<!-- 작성일자 출력 끝 -->
						
						<!-- 게시글 내용 출력 시작 -->
							<tr class = "text-center">
								<td colspan="3">내용</td>
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
				<a href="userBoard.do" class="btn btn-primary fs-4">목록</a>
				<c:if test="${userID == showPost.id}">
				<button type="button" id = "modifyPostBtn" class = "btn btn-secondary fs-4">수정</button>
				</c:if>
			</div>
		</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>