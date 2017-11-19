<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Menu</title>
    <jsp:include page="../../partials/meta.jsp" />
</head>
<body>
<img src="../../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<hr>
<p><b>Menu</b></p>
<a href="../userview/favouritesmenu">Favourites</a>
<br>
<p>Choose your analysis:</p>
<a href="../userview/analysisCriteria.jsp?strategy=IVR">Investment revenue</a><br>
<a href="../userview/analysisCriteria.jsp?strategy=IND">Investment indicators</a><br>

<br>
<%--TODO--%>
<a href="../userview/investments">Database info</a><br>
<a href="../userview/chart.jsp">Charts comparator</a><br>
<br>
<jsp:include page="../../partials/footer.jsp" />
</body>
</html>
