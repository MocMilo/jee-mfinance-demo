<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Analysis result</title>
    <jsp:include page="/partials/meta.jsp"/>
</head>
<body>
<img src="resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionContainer.user}"/>
<jsp:include page="partials/navigation/backToUserMenu.jsp"/>
<p><b>Analysis result</b></p>
<br>
<c:set var="analysisResult" value="${analysisResult}" scope="request"/>
<c:choose>
    <c:when test="${analysisResult.strategy=='IVR'}">
        <jsp:include page="partials/forms/ivrResultForm.jsp"/>
    </c:when>
    <c:when test="${analysisResult.strategy=='IND'}">
        <jsp:include page="partials/forms/indResultForm.jsp"/>
    </c:when>
</c:choose>
<jsp:include page="partials/footer.jsp"/>
</body>
</html>
