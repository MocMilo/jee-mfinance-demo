<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<p><b>Investment indicators:</b></p>
<br>
<p>Enter name of investment: </p>
<form method="post" action="../userview/validation">
    <input type="hidden" value="IND" name="strategy"/>
    <p>Investment name:<input type="text" name="investmentName" value="${investmentName}" required/></p>
    <jsp:include page="/partials/addFavourite.jsp"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>
