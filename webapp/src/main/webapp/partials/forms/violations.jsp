<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<div>
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
</div>
</body>
</html>