<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><b>Investment Revenue</b></title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datePicker").datepicker({
                dateFormat: "yy-mm-dd",
                changeMonth: true,
                changeYear: true,
            }).val()
        });
    </script>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p><b> Analysis: Investment Revenue</b></p>
<br>
<form method="post" action="../userview/investmentrevenue">
    <p><b>form:</b></p>
    <p>1. Investment name:</p>
    <p><input type="text" name="investmentName" id="inputForm" value="${investmentName}"/></p>
    <p>2. Invested capital:</p>
    <p><input type="text" pattern="[0-9]*" name="capital" value="${capital}"/></p>
    <p>3. Date of buy:</p>
    <p><input type="text" class="datePicker" name="buyDate" value="${buyDate}"/></p>
    <p>4. Date of sell:</p>
    <p><input type="text" class="datePicker" name="sellDate" value="${sellDate}"/></p>
    <jsp:include page="/partials/addFavourite.jsp"></jsp:include>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>
<c:choose>
    <c:when test="${violations.size() >= 1}">
        <p style="color: red">Number of violations: ${violations.size()}</p>
        <ul>
            <c:forEach var="violation" items="${violations}">
                <li style="color: red">${violation.message}</li>
            </c:forEach>
        </ul>
    </c:when>
</c:choose>

<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>