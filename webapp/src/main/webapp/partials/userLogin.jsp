<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form method="post" action="/logout">
    <p>logged user: ${user.login} <button type="submit">logout</button></p>
</form>
</body>
</html>