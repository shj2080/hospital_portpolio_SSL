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
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel="stylesheet" type="text/css" href="style/board.css">
<script>const post_no = "${param.post_no}";</script>
</head>
<body>
<!-- header.jsp 불러오기 -->
	<jsp:include page="header.jsp" />
	
		<div id = "contentWrap">
			<%-- 컨텐츠 표시 영역 시작 --%>
			<section>
				<div class="row">
				
				<!-- 테이블 시작 -->
					<table class="table table-striped"  id = "boardViewBox">
					
					<!-- 헤더 부분 시작 -->
						<thead>
							<tr>
								<th colspan="3" class="text-center" style="background-color: #eeeeee;">게시글 비밀번호 체크</th>
							</tr>
						</thead>
					<!-- 헤더 부분 끝 -->

					<!-- tbody 부분 시작 -->
						<tbody>
							<tr>
								<td colspan="3">
									게시글 수정 또는 삭제를 위해 작성 시 입력한 비밀번호를 입력하세요.
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<input type="password" id = "post_pwd" name = "post_pwd"/>
								</td>
							</tr>
						</tbody>
					<!-- tbody 부분 끝 -->
				</table>
			<!-- 테이블 끝 -->
				
			<div class="text-center">
				<a href="userBoard.do" class="btn btn-primary fs-4">목록</a>
				<button type="button" id = "modifyDeleteBtn" class = "btn btn-secondary fs-4">확인</button>
			</div>
		</div>
			</section>
			<%-- 컨텐츠 표시 영역 끝 --%>
		</div>
	<jsp:include page="footer.jsp" />
</body>
</html>