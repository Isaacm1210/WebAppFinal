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
        <title>${user.firstName}'s Information</title>
    </head>
    <body>

        <h1><b> Welcome ${user.firstName}</b></h1>
        <b>${message}</b>
        <a href="Login">Logout</a>
        <br>
        <br>
        <c:if test="${view eq 'close' || view eq null}">
            <a href="user?action=viewInfo">View User Info</a>
        </c:if>
        
        <c:if test="${view eq 'view'}">
            <table border='1' >
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last name</th>
                    <th>Role</th>
                </tr>
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.roleName}</td>
                    <td><a href="user?action=edit">Edit</a></td>
                </tr>
            </table>
                <a href="user?action=close">Close</a>
        </c:if>
                
        <c:if test="${view eq 'edit'}">
            <h3>Edit Your Information</h3>

            <form action="user" method="post">
                Email: ${user.email}
                <input type="hidden" name="email" value="${user.email}">
                <br>
                First Name: <input type="text" name="firstName" value="${user.firstName}">
                <br>
                Last Name: <input type="text" name="lastName" value="${user.lastName}">
                <br>
                Password: <input type="password" name="password">
                <br>
                <input type="submit" value="Edit Info" name="action">
            </form>
             <a href="user?action=close">Close</a>
        </c:if>
        
    </body>
</html>
