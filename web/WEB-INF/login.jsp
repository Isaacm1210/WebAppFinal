

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory Login</title>
    </head>
    <body>
        <h1>HOME nVentory</h1>

        <!--Login Form-->
        <c:if test="${enter ne 'register'}">
            <h2>Please Login</h2>
            <b>${message}</b>
        <form action="Login" method="post">
            Email: <input type="text" name="email"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Login" name="action">
        </form>
        
        <form action="Login" method="post">
            <input type="submit" value="Register" name="action">
        </form>
        <br>
        </c:if>
        
        
        <!--Register Form-->
        <c:if test="${enter eq 'register'}">
            <h2>Please Register your account</h2>
            <b>${message}</b>
        <form action="Login" method="post">
            Email: <input type="text" name="email">
            <br>
            First Name: <input type="text" name="firstName">
            <br>
            Last Name: <input type="text" name="lastName">
            <br>
            Password: <input type="password" name="password">
            <br>
            <input type="hidden" name="action" value="registerUser">
            <input type="submit" value="Register User">
        </form>
        <a href="Login">Return to Login</a>
        </c:if>
        
    </body>
</html>
