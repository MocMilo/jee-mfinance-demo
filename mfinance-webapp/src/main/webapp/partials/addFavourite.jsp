<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
         $('#isFavourite').click(function() {
            $('.customNameField')[this.checked ? "show" : "hide"]();
        });
    });
</script>
</head>
<body>

<p><input type="checkbox" name="isFavourite" id="isFavourite"/> add to favourites
    <input type="text" class="customNameField" name="userCustomName" width="300" hidden
           placeholder="my analysis custom name"/></p>
</body>
</html>

