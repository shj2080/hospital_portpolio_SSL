<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>율제병원 소개</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	 <!-- 컨텐츠를 묶는 div -->
	 <div class="container">
     
     <%-- 컨텐츠 표시 영역 시작 --%>
        <section>
            <jsp:include page="${showPage}" />
        </section>
     </div>
     <%-- 컨텐츠 표시 영역 끝 --%>
     <jsp:include page="../footer.jsp" />
</body>
</html>