<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Menu</title>
    <jsp:include page="../../partials/meta.jsp" />
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<hr>
<p><b>Menu</b></p>
<a href="../userview/favouritesmenu">Favourites</a>
<br>
<p>Choose your investment analysis:</p>
<a href="../userview/investments">Database info</a><br>
<a href="../userview/investmentrevenue">Investment revenue</a><br>
<a href="../userview/comparator">Indicator comparator</a><br>
<a href="../userview/chart.jsp">Charts comparator</a><br>
<br>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>
