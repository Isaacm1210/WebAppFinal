<%-- 
    Document   : Home
    Created on : 28-Nov-2022, 2:27:58 PM
    Author     : mhame
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>This is the Home Page</h1>
        <h3><b> Welcome ${user.firstName}</b></h3>
        
        <a href="Login">Logout</a>
        <br>
        <br>
        <c:if test="${view eq 'false'}">
            <form action="user" method="post">
                <input type="hidden" name="action" value="viewInfo">
                <input type="submit" value="Veiw User Info">
            </form>
        </c:if>
        
        <c:if test="${view eq 'true'}">
            <table border='1' >
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last name</th>
                    <th>password</th>
                </tr>
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.password}</td>
                </tr>
            </table>
                <a href="user">Close</a>
        </c:if>
        
    </body>
</html>
