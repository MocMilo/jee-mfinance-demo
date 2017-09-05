<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Administrator Mode</title>
    <jsp:include page="../../partials/meta.jsp"/>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<hr>
<br>
<p><b>Administrator panel menu</b></p>
<c:choose>
    <c:when test="${applicationScope.appMode.slave}">
        Main Container Management - DISABLED<br>
    </c:when>
    <c:otherwise>
        <a href="../adminview/appsettings">Main Container Management</a><br>
    </c:otherwise>
</c:choose>
<a href="../adminview/emailsender">Task Agent Service</a><br>
<a href="../adminview/usermanagement">User Management</a><br>
<a href="../adminview/userstatistics">Statistics</a><br>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>