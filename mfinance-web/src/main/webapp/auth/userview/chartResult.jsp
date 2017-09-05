<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chart</title>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p><b>Chart comparison</b></p>
<br>
<p><img src="../userview/chartA" /></p>
<p><img src="../userview/chartB" /></p>

<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>