<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Application settings</title>
</head>
<body>
<tags:appMode  appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToAdminMenu.jsp"/>
<p><b>Application settings</b></p>
<br>
<p>Data from CSV files:</p>
<p>Total Number of Funds: <b>${fundCount}</b></p>
<p>Total Number of Currencies: <b>${currencyCount}</b></p>
<br>
<form action="../adminview/appsettings" method="post">
    <button type="submit">Press to reload data model csv files...</button>
</form>
</br>
<br>
</br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>

