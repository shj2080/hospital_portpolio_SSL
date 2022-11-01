<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel = "stylesheet" type="text/css" href="style/init/ul_listStyle_none.css">
<link rel = "stylesheet" type="text/css" href="style/introduce/introduce.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	 <!-- 컨텐츠를 묶는 div -->
	 <div id = "contentWrap">
	 <%-- 좌측 메뉴 영역 시작 --%>
        <aside>
              <jsp:include page="introduceSideBar.jsp" />
        </aside>
     <%-- 좌측 메뉴 영역 끝 --%>
     
     <%-- 컨텐츠 표시 영역 시작 --%>
        <section>
            <jsp:include page="${showPage}" />
        </section>
     </div>
     <%-- 컨텐츠 표시 영역 끝 --%>
     <jsp:include page="../footer.jsp" />
</body>
</html>