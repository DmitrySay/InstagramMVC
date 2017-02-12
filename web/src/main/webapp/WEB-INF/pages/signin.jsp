<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sign In</title>
    <link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/css/signin.css" rel="stylesheet">
    <link href="${contextPath}/css/style.css" rel="stylesheet">
    <link href="${contextPath}/css/favicon.ico" rel="icon">
</head>

<body>


<tiles:insertAttribute name="header"/>

<div class="container">

    <form class="form-signin" method="post" action="j_spring_security_check">
        <h2 class="form-signin-heading">Please sign in</h2>
        <h5 style="color: darkred">${error}</h5>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="inputEmail" name="j_username" class="form-control" placeholder="Email address" required
               autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="j_password" class="form-control" placeholder="Password"
               required>
        <div class="checkbox">
            <label><input type="checkbox" name="remember_me">Remember me</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
    </form>

</div> <!-- /container -->


<tiles:insertAttribute name="footer"/>

</body>

</html>
