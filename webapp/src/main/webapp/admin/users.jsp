<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<img src="../resources/icons/banner_top.jpg">
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../partials/navigation/backToAdminMenu.jsp"/>
<p><b>User Management</b></p>
<tags:userTable allUsers="${allUsers}"/>
<jsp:include page="../partials/footer.jsp"/>
</body>
</html>
