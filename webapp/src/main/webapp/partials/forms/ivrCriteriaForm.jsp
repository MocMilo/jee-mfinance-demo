<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<form method="post" action="../userview/validation">
    <input type="hidden" value="IVR" name="strategy"/>
    <p><b>Investment Revenue form:</b></p>
    <p>1. Investment name (eg."CHF"):</p>
    <p><input type="text" name="investmentName" id="inputForm" value="${investmentName}"/></p>
    <p>2. Invested capital (eg."1000"):</p>
    <p><input type="text" pattern="[0-9]*" name="capital" value="${capital}"/></p>
    <p>3. Date of buy (eg."2015-01-05"):</p>
    <p><input type="text" class="datePicker" name="buyDate" value="${buyDate}"/></p>
    <p>4. Date of sell (eg."2017-01-25"):</p>
    <p><input type="text" class="datePicker" name="sellDate" value="${sellDate}"/></p>
    <jsp:include page="/partials/addFavourite.jsp"></jsp:include>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>
</body>
</html>
