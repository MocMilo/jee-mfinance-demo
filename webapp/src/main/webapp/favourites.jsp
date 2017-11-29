<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>Favourites</b></title>
</head>
<body>
<img src="resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="partials/navigation/backToUserMenu.jsp"/>
<p><b> User Favourites</b></p>
<br>

<c:forEach var="analysisResult" items="${analysisResults}">
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
</c:forEach>

<jsp:include page="partials/footer.jsp"/>
</body>
</html>
