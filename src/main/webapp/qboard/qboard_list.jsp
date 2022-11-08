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
</head>

<body>
	<jsp:include page="../header.jsp" />
	 
	 <div id = "contentWrap">
	<section>
		
		<div id = "listForm">
		 <h2>병원문의 게시판</h2>
			 <c:choose>
				 <c:when test="${userID != null}">
				     <h4>
					 	<a href="qboardWriteForm.qna" class="btn"><span class="fs-2">문의하기</span></a>
				     </h4>
				 </c:when>
				 <c:otherwise>
					 <h4>
					 	<a href="login.do" class="btn"><span class=" fs-2">문의하기</span></a>	 
					 </h4>	 
				 </c:otherwise>
			 </c:choose>
	 	<c:if test="${articleList != null and 0 < pageInfo.listCount}">
	 	<c:set var="nowPage" value="${pageInfo.page}"/>
	 	<table border="1" id="first-table"  class="table table-hover">
			<tr id="tr_top">
				<td>번호</td>
				<td>작성자ID</td>
				<td>제목</td>
				<td>날짜</td>
				<td>조회수</td>
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
		<c:when test="${articleList != null and 0 < pageInfo.listCount}">
		<div id="pageList">
			<c:choose>
				<c:when test="${nowPage <= 1 }">
					[이전]&nbsp;
				</c:when>
				<c:otherwise>
					<a href="qboardList.qna?page=${nowPage-1 }">[이전]</a>&nbsp;
				</c:otherwise>
			</c:choose>
	
			<c:forEach var = "p" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1">
				<c:choose>
					<c:when test="${p == nowPage }"> <%--현재페이지[1]가 시작페이지[1]와 같으면 링크x ex:[1]-click링크X--%>
						[${p}]
					</c:when>
					<c:otherwise>
						<a href="qboardList.qna?page=${p}">[${p}]</a>&nbsp; <%--그게아니면 ex:[2]-click링크O -> [2]페이지의 리스트 출력--%>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<c:choose>
				<c:when test="${nowPage >= pageInfo.maxPage }"> <%--현재페이지가 마지막 페이지일경우 링크X [다음] --%>
					[다음]
				</c:when>
				<c:otherwise>
					<a href="qboardList.qna?page=${nowPage + 1}">[다음]</a>  <%--마지막이아닐 경우 [다음]에 링크 --%>
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
