<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Application settings</title>
    <jsp:include page="../partials/meta.jsp"/>
</head>
<body>
<img src="../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionContainer.user}"/>
<jsp:include page="../partials/navigation/backToAdminMenu.jsp"/>
<p><b>Application settings</b></p>
<br>
<p>Data from CSV files:</p>
<p>Total Number of Funds: <b>${fundCount}</b></p>
<p>Total Number of Currencies: <b>${currencyCount}</b></p>
<br>
<form action="../admin/settings" method="post">
    <button type="submit">Press to reload bossa csv files...</button>
</form>
<br>
<br>
<br>
<jsp:include page="../partials/footer.jsp"/>
</body>
</html>

