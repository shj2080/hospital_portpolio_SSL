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
	<link rel="stylesheet" type="text/css" href="style/init/a_link_style.css">
<script>
function movePage(page) {
	location.href = "userBoard.do?page=" + page;
}
</script>
</head>
<body>
	<!-- header.jsp 불러오기 -->
	<jsp:include page="header.jsp" />
	
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<%-- 제목 부분 --%>
				<div>
					<h2 class = "text-center">공지사항</h2>
				</div>
				
				<div style="margin: 0 auto;">
				<%-- 제목 부분 --%>

				<!-- 게시판 테이블 시작 -->
					<table class="table table-hover" id = "boardListBox">
					
					
					<!-- 테이블 헤더 부분 -->
						<thead>
							<tr>
								<th style="background-color: #eeeeee; text-align: center;">번호</th>
								<th style="background-color: #eeeeee; text-align: center;">제목</th>
								<th style="background-color: #eeeeee; text-align: center;">작성자</th>
								<th style="background-color: #eeeeee; text-align: center;">작성일</th>
							</tr>
						</thead>
					<!-- 테이블 헤더 부분 끝 -->
						
					<!-- 테이블 바디 부분 시작 -->
						<tbody>
						
						<!-- 게시판 목록에 글이 있는다면 출력 시작 -->
							<c:if test="${boardList != null && boardList.size() > 0}">
								<!-- 1.board 메뉴목록이 있으면 -->
								<c:forEach var="board" items="${boardList}" varStatus="status">
									<!-- 행 시작  -->
									<tr>
										<td>${board.post_no}</td>
										<td><a class="linkTextBlackFix" href="showPost.do?post_no=${board.post_no}">${board.post_subject}</a></td>
										<td>${board.id }</td>
										<td>${board.post_date }</td>
									</tr>
									<!-- 행 끝 -->
								</c:forEach>
							</c:if>
						<!-- 게시판 목록에 글이 있는다면 출력 끝 -->
							
						<!-- 게시판 목록에 글이 없다면 출력 시작 -->
							<c:if test="${boardList == null or empty boardList}">
								<!-- 2.board 메뉴목록이 없으면 -->
								<td colspan="4">등록된 게시글이 없습니다.</td>
							</c:if>
						<!-- 게시판 목록에 글이 없다면 출력 시작 -->
							
						</tbody>
					<!-- 테이블 바디 부분 끝 -->
					
					</table>
					<!-- 게시판 테이블 끝 -->
		
					<!-- 각종버튼 부분 시작 -->
					<div id = "butBOX" align="center">
					
					<!-- 페이지 처리 부분 시작 -->
						<c:choose>
							<c:when test="${!hasPrev}">
								<button type="button" class="btn btn-success btn-arraw-left fs-4" disabled>이전</button>&nbsp;
							</c:when>
							<c:otherwise>
								<button type = "button" class = "btn btn-success btn-arraw-left fs-4" onclick="movePage(${nowPage-1});" >이전</button>
							</c:otherwise>
						</c:choose>
	
						<c:forEach var = "p" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1">
							<c:choose>
								<c:when test="${p == pageInfo.currentPage }"> <%--현재페이지[1]가 시작페이지[1]와 같으면 링크x ex:[1]-click링크X--%>
									<button type = "button" class = "btn fs-5" disabled>${p}</button>
								</c:when>
								<c:otherwise>
									<button type = "button" class = "btn fs-5" onclick = "movePage(${p});">${p}</button>
								</c:otherwise>
							</c:choose>
						</c:forEach>
			
						<c:choose>
							<c:when test="${!hasNext}">
								<button type = "button" class = "btn btn-success fs-4" disabled>다음</button>
							</c:when>
							<c:otherwise>
								<button type = "button" class = "btn btn-success fs-4" onclick="movePage(${nowPage+1});" >다음</button>
							</c:otherwise>
						</c:choose>
						
					<!-- 페이지 처리 부분 끝 -->
						
						<!-- 유저 타입이 관리자(M)이라면 출력 시작 -->
					<c:if test="${userType == 'M' }">
					<button type = "button" onclick="location.href='userBoardWrite.do'" class="btn btn-primary fs-4">글쓰기</button>
					</c:if>
					<!-- 유저 타입이 관리자(M)이라면 출력 끝 -->
					
					<!-- 유저 타입이 일반유저(N)이라면 출력 시작 -->
					<c:if test="${userType == 'N' }">
					<button type = "button" class="btn btn-primary fs-4" disabled>글쓰기</button>
					</c:if>
					<!-- 유저 타입이 일반유저(N)이라면 출력 끝 -->
					
					</div>
					<!-- 각종버튼 부분 끝 -->
					
			</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
		
	<!-- footer.jsp 불러오기 -->
	<jsp:include page="footer.jsp" />
</body>
</html>