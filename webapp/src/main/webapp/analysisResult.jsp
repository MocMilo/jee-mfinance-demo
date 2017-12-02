<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>Investment Revenue Result</b></title>
</head>
<body>
<img src="resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="partials/navigation/backToUserMenu.jsp"/>
<p><b> Analysis: Result</b></p>
<br>
<c:set var="analysisResult" value="${analysisResult}" scope="request"/>
<c:choose>
    <c:when test="${analysisResult.strategy=='IVR'}">
        <jsp:include page="partials/forms/ivrResultForm.jsp"/>
    </c:when>
    <c:when test="${analysisResult.strategy=='IND'}">
        <%--<tags:indResult analysisResult="${analysisResult}"/>--%>
        <jsp:include page="partials/forms/indResultForm.jsp"/>
    </c:when>
</c:choose>
<jsp:include page="partials/footer.jsp"/>
</body>
</html>
