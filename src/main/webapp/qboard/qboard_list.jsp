<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/qnaboard/qnaStyle.css">
<title>게시판 글목록</title>
<script>
function movePage(page) {
	location.href = "qboardList.qna?page=" + page;
}
</script>
</head>

<body>
	<jsp:include page="../header.jsp" />
	 
<div id = "contentWrap">
	<section>
		
		<div id = "listForm">
		 <h2>병원문의 게시판</h2>
		     <h4>
			 	<a href="qboardWriteForm.qna" class="btn btn-primary fs-5">문의하기</a>
		     </h4>
	 	<c:if test="${articleList != null and 0 < pageInfo.totalPosts}">
	 	<c:set var="nowPage" value="${pageInfo.currentPage}"/>
	 	<table border="1" id="first-table"  class="table table-hover">
			<tr id="tr_top">
				<th>번호</th>
				<th>작성자ID</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>

			<c:forEach var = "boardList" items="${articleList}">
			<tr id="tr_down">
				<td>${boardList.QBOARD_NUM}</td>
				
                <td>${boardList.MEM_ID}</td>
                
				<td>
					<%--답글이 있으면  --%>
					<c:choose>
						<c:when test="${boardList.QBOARD_RE_LEV != 0 }">
							<c:forEach var = "r" begin="0" end="${boardList.QBOARD_RE_LEV * 2}" step="1">
							&nbsp;
							</c:forEach>
							RE:▷
						</c:when>
						<c:otherwise>
							 ▶
						</c:otherwise>
					</c:choose>
					<a href="qboardDetail.qna?qboard_num=${boardList.QBOARD_NUM}&page=${nowPage}">
						${boardList.QBOARD_SUBJECT} <%-- 게시글 제목 클릭 시 글번호와 현재 페이지 전달 --%>
					</a>
				    
				</td>
		
				<td>${boardList.QBOARD_DATE}</td>
				<td>${boardList.QBOARD_READCOUNT }</td>
			</tr>
	      </c:forEach>
		</table>
		</c:if>
		</div>
		
		<c:choose>
		<c:when test="${articleList != null and 0 < pageInfo.totalPosts}">
		<div id="pageList">
			<c:choose>
				<c:when test="${nowPage <= 1 }">
					<button type="button" class="btn btn-success btn-arraw-left fs-4" disabled>이전</button>&nbsp;
				</c:when>
				<c:otherwise>
					<button type = "button" class = "btn btn-success btn-arraw-left fs-4" onclick="movePage(${nowPage-1});" >이전</button>
				</c:otherwise>
			</c:choose>
	
			<c:forEach var = "p" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1">
				<c:choose>
					<c:when test="${p == nowPage }"> <%--현재페이지[1]가 시작페이지[1]와 같으면 링크x ex:[1]-click링크X--%>
						<button type = "button" class = "btn fs-5" disabled>${p}</button>
					</c:when>
					<c:otherwise>
						<button type = "button" class = "btn fs-5" onclick = "movePage(${p});">${p}</button>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${nowPage >= pageInfo.totalPages }"> <%--현재페이지가 마지막 페이지일경우 링크X [다음] --%>
					<button type = "button" class = "btn btn-success fs-4" disabled>다음</button>
				</c:when>
				<c:otherwise>
					<button type = "button" class = "btn btn-success fs-4" onclick="movePage(${nowPage+1});" >다음</button>
				</c:otherwise>
			</c:choose>
		
		</div>
		</c:when>
			<c:otherwise>
			<div id="emptyArea">등록된 글이 없습니다.</div>
			</c:otherwise>
		</c:choose>
	
	</section>

	</div>
		<jsp:include page="../footer.jsp" />
</body>
</html>
