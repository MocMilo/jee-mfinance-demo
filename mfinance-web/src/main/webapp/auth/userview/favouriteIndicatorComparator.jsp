<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script>
        $(function () {
            $(document).ready(function () {
                $("#hideBtn").click(function () {
                    $(".Favourite").toggle();
                });
            });
            $(document).ready(function () {
                $("#editBtn").click(function () {
                    $(".editFavourite").toggle();
                });
            });
        });
    </script>
</head>
<body>
<tags:appMode appMode="${applicationScope.appMode}"/>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../../partials/backToUserMenu.jsp"/>
<p><b>Favourite Indicator Comparators </b></p>
</br>
<p><b>Investment Revenue:</b>
</p>
<div class="Favourite" style="background-color: #e0e0e0">
    <c:forEach var="contentWrapper" items="${contentWrappers}">
        <tags:indicatorResult contentWrapper="${contentWrapper}"/>
    </c:forEach>
</div>

<br>
<jsp:include page="../../partials/footer.jsp"/>
</body>
</html>
