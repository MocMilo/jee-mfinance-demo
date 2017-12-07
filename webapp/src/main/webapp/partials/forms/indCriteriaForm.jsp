<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body>
<p><b>Investment indicators:</b></p>
<br>
<p>Enter name of investment: </p>
<form method="post" action="/validation">
    <input type="hidden" value="IND" name="strategy"/>
    <p>Investment name (eg."CHF"):<input type="text" name="investmentName" value="${criteriaForm.investmentName}" required/></p>
    <jsp:include page="/partials/addFavourite.jsp"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>
