<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Investment comparator</title>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p><b>Investments indicator comparator</b></p>
<br>
<p>Enter names of 2 investments to compare</p>
<form action="../userview/comparator" method="post">
    <p>Investment A<input type="text" name="investmentNameA" value="${investmentNameA}" required/></p>
    <p>Investment B<input type="text" name="investmentNameB" value="${investmentNameB}" required/></p>
    <jsp:include page="/partials/addFavourite.jsp"></jsp:include>
    <button type="submit">Submit</button>
</form>
<br>
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
