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
<img src="../../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<hr>
<br>
<p><b>Administrator panel menu</b></p>
<a href="../adminview/appsettings">Main Container Management</a><br>
<a href="../adminview/usermanagement">User Management</a><br>
<a href="../adminview/userstatistics">Statistics</a><br>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>