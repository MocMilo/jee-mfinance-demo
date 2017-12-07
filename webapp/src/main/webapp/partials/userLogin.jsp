<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<form method="post" action="/logout">
    <p>logged user: ${sessionContainer.user} <button type="submit">logout</button></p>
</form>
</body>
</html>