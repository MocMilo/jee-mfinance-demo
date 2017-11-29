<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Menu</title>
    <jsp:include page="partials/meta.jsp" />
</head>
<body>
<img src="resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<hr>
<p><b>Menu</b></p>
<form action="/favourites" method="post">
    <button type="submit" name="your_name" value="your_value" class="btn-link">My Favourites</button>
</form>
<br>
<a href="/investments">Database info</a><br>
<br>
<p>Choose your analysis:</p>
<a href="analysisCriteria.jsp?strategy=IVR">Investment revenue</a><br>
<a href="analysisCriteria.jsp?strategy=IND">Investment indicators</a><br>
<br>
<br>
<jsp:include page="partials/footer.jsp" />
</body>
</html>
