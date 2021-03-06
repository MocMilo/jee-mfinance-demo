<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Analysis criteria</title>
    <jsp:include page="/partials/meta.jsp"/>
</head>
<body>
<img src="resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionContainer.user}"/>
<jsp:include page="partials/navigation/backToUserMenu.jsp"/>
<p><b>Analysis criteria</b></p>
<br>
<c:set var="criteriaForm" value="${criteriaForm}" scope="request"/>
<c:choose>
    <c:when test="${param.strategy=='IVR'}">
        <jsp:include page="partials/forms/ivrCriteriaForm.jsp"/>
    </c:when>
    <c:when test="${param.strategy=='IND'}">
        <jsp:include page="partials/forms/indCriteriaForm.jsp"/>
    </c:when>
</c:choose>
<c:set var="violations" value="${violations}" scope="request"/>
<li style="color: red">${errorMessage}</li>
<jsp:include page="partials/forms/violations.jsp"/>
<jsp:include page="partials/footer.jsp"/>
<br>
<br>
</body>
</html>

