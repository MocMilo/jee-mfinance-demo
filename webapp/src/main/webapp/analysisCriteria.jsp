<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<body>
<img src="resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="partials/navigation/backToUserMenu.jsp"/>
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
