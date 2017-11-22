<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>User Statistics</title>
</head>
<body>
<img src="../../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/navigation/backToAdminMenu.jsp"/>
<p><b>User Statistics</b></p>
<br>
<tags:invRevCritTable allInvRevCrit="${allInvRevCrit}"/>
<br>
<br>
<c:if test="${!applicationScope.appMode.slave}">
    <p><b>User activity report (csv):</b></p>
    <p><a href="../adminview/userstatistics/csv">Download form slave API</a></p>
</c:if>
<br>
<form method="post" action="../adminview/userstatistics">
    <c:if test="${applicationScope.appMode.slave}">
        <p>
            <button type="submit">Update db from master application</button>
        </p>
    </c:if>
</form>
<br>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
