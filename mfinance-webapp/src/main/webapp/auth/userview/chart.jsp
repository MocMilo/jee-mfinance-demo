<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><b>Quotiations Comparison Charts</b></title>
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
<img src="../../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/navigation/backToUserMenu.jsp"/>
<p><b>Chart</b></p>
<br>

<p>Enter comparison data</p>

<form method="post" action="../userview/chart">
    <p><b>Form:</b></p>
    <p>Investment A<input type="text" name="investmentNameA" value="${investmentNameA}" required/></p>
    <p>Investment B<input type="text" name="investmentNameB" value="${investmentNameB}" required/></p>
    <p>Start Date:<input type="text" class="datePicker" name="startDate" value="${startDate}" required/></p>
    <p>End Date:<input type="text" class="datePicker" name="endDate" value="${endDate}" required/></p>
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
<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
