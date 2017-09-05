<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>Investment Revenue Result</b></title>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p><b> Analysis: Investment Revenue Result</b></p>
<br>
<tags:analysisResult revenueWrapper="${contentWrapper}"/>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>