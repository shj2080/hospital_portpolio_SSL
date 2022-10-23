<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- 반응형 웹을 위한 기본 태그 부분 -->
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>it's 병원 - 마이페이지</title>
</head>
<body>
    <div id = "warpper">
        <jsp:include page="header.jsp" />
        <%-- 컨텐츠 표시 영역 시작 --%>
        <section>
            <div>
                마이페이지
            </div>
        </section>
        <%-- 컨텐츠 표시 영역 끝 --%>
        <jsp:include page="footer.jsp" />
    </div>
</body>
</html>
