<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="user" type="com.infoshareacademy.web.model.user.User" required="true" %>
<form method="post" action="/logout">
    <p>logged user: ${user.login} <button type="submit">logout</button></p>
</form>
