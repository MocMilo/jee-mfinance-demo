<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>User Management details</b></title>
</head>
<body>
<img src="../../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>

<p><b>User Details</b></p>
<form method="post" action="../adminview/userdetails">
    <p>Id:<b>${contentWrapper.id}</b></p>
    <p><input name="id" value="${contentWrapper.id}" hidden/></p>
    <p>login: <b>${contentWrapper.login}</b></p>
<c:choose>
    <c:when test="${contentWrapper.admin}">
    <p>isAdmin: <input type="checkbox" name="isAdmin" value="${contentWrapper.admin}" checked/></p>
    </c:when>
    <c:otherwise>
    <p>isAdmin: <input type="checkbox" name="isAdmin" value="${contentWrapper.admin}"/></p>
    </c:otherwise>
</c:choose>
    <p>accout creation: ${contentWrapper.creationDateTime}</p>
    <p>last login: ${contentWrapper.lastLoginDateTime}</p>
    <p>last update: ${contentWrapper.lastUpdateDateTime}</p>
    <p>
        <button type="submit">Update!</button>
    </p>
</form>

<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
