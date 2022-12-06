<%-- 
    Document   : Home
    Created on : 28-Nov-2022, 2:27:58 PM
    Author     : mhame
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>This is the Home Page</h1>
        <h3><b> Welcome ${message}</b></h3>
        
        <a href="Login">Logout</a>
        <br>
        <br>
        <a href="user?action=viewInfo">View Account Information</a>
        
    </body>
</html>
