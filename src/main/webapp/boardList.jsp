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
</head>
<body>
	<jsp:include page="header.jsp" />
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div class="text-center">
					<h2>공지사항</h2>
				</div>
				<div class="row" style="margin: 0 auto;">
					<table class="table table-striped" id = "boardListBox">
						<thead>
							<tr>
								<th style="background-color: #eeeeee; text-align: center;">번호</th>
								<th style="background-color: #eeeeee; text-align: center;">제목</th>
								<th style="background-color: #eeeeee; text-align: center;">작성자</th>
								<th style="background-color: #eeeeee; text-align: center;">작성일</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${boardList != null && boardList.size() > 0}">
								<!-- 1.board 메뉴목록이 있으면 -->
								<c:forEach var="board" items="${boardList}" varStatus="status">
									<!-- 행 시작  -->
									<tr>
										<td>${board.post_no}</td>
										<td><a href="showPost.do?post_no=${board.post_no}">${board.post_subject }</a></td>
										<td>${board.id }</td>
										<td>${board.post_date }</td>
									</tr>
									<!-- 행 끝 -->
								</c:forEach>
							</c:if>
							<c:if test="${boardList == null}">
								<!-- 2.board 메뉴목록이 없으면 -->
								<td colspan="4">등록된 게시글이 없습니다.</td>
							</c:if>
						</tbody>
					</table>
					<div id = "butBOX" align="center">
					<c:if test="${userType == 'M' }">
					<button type = "button" onclick="location.href='userBoardWrite.do'" class="btn btn-primary pull-right fs-4">글쓰기</button>
					</c:if>
					<c:if test="${userType == 'N' }">
					<button type = "button" class="btn btn-primary pull-right fs-4" disabled>글쓰기</button>
					</c:if>
					<a href="userBoard.do" class="btn btn-success btn-arraw-left fs-4">이전</a>
					<a href="userBoard.do" class="btn btn-success btn-arraw-left fs-4">다음</a>
					</div>
			</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>