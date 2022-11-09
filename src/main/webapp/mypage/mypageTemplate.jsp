<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%-- JSTL 사용을 위한 선언 부분 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹을 위한 기본 태그 부분 -->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>it's 병원 - 마이페이지</title>
<link rel="stylesheet" type="text/css" href="style/initStyle.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="style/header.css">
<link rel="stylesheet" type="text/css" href="style/body.css">
<link rel="stylesheet" type="text/css" href="style/footer.css">
<link rel = "stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/mypageTemplateStyle.css">


<style>
/* box-sizing를 변경한 적이 있는 경우 이 스타일 사용 */
	.selector-for-some-widget {
	  box-sizing: content-box;
	}
</style>
</head>
<body>
	<script type="text/javascript" src = "bootstrap/js/bootstrap.bundle.min.js"></script>
     <jsp:include page="../header.jsp" />

		
	 <!-- 내부 메뉴바와 컨텐츠를 묶는 div -->
	 <!-- <div id = "contentWrap"> -->
     <%-- 컨텐츠 표시 영역 시작 --%>
        <section id = "contentWrap">
        	<%-- 좌측 메뉴 영역 시작 --%>
	        <aside>
	        	  <c:if test="${userType eq 'N'}">
	              	<jsp:include page="mypageSideBar.jsp" />
	              </c:if>
	              <c:if test="${userType eq 'M'}">
	              	<jsp:include page="admin/mypageSideBar.jsp" />
	              </c:if>
	        </aside>
	    	<%-- 좌측 메뉴 영역 끝 --%>
	    	<div class = "contentPageZone container">
                <jsp:include page="${showPage}" />
            </div>
        </section>
     <!-- </div> -->
     <%-- 컨텐츠 표시 영역 끝 --%>
     <jsp:include page="../footer.jsp" />
</body>
</html>
