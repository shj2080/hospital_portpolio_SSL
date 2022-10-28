<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 마이페이지</title>
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel = "stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/mypageTemplateStyle.css">
</head>
<body>
    <div id = "warpper">
        <jsp:include page="../header.jsp" />

        <%-- 좌측 메뉴 영역 시작 --%>
        <aside>
            <div>
                <jsp:include page="mypageSideBar.jsp" />
            </div>
        </aside>
        <%-- 좌측 메뉴 영역 끝 --%>

        <%-- 컨텐츠 표시 영역 시작 --%>
        <section>
            <div>
                <jsp:include page="${showPage}" />
            </div>
        </section>
        
        <%-- 컨텐츠 표시 영역 끝 --%>
        <jsp:include page="../footer.jsp" />
    </div>
</body>
</html>
