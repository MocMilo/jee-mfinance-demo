<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>mfinance - logowanie</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="150451143938-rmal4qgn03753krup0vc30jmf3n69fkk.apps.googleusercontent.com">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>
<body>
<img src="../../resources/icons/banner_top.jpg">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
            <span class="glyphicon glyphicon-list"></span>
        </button>
    </div>
</nav>
<br>
<P align="middle"><img src="resources/icons/banner_welcome.jpg"></P>
<br>
<div style="text-align: center">
    <div style="width: 50%; font-size:18px; margin: 0 auto; text-align: left"><p align="middle">login in with Google+</p></div>
</div>
<div class="container">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <div class="g-signin2" data-onsuccess="onSignIn"></div>
    </div>
</div>
<footer class="footer">
    <div class="container">
        <p class="navbar-text">mfinance</p>
    </div>
</footer>
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        console.log('id_token: ' + googleUser.getAuthResponse().id_token);

        //do not post all above info to the server because that is not secure.
        //just send the id_token

        var redirectUrl = 'authentication';

        //using jquery to post data dynamically
        var form = $('<form action="' + redirectUrl + '" method="post">' +
            '<input type="text" name="id_token" value="' +
            googleUser.getAuthResponse().id_token + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    }
</script>
</body>
</html>
