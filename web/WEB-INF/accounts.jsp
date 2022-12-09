<%-- 
    Document   : accounts
    Created on : 9-Dec-2022, 10:26:06 AM
    Author     : mhame
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage All Accounts</title>
    </head>
    <body>
        <h1>Welcome to the Account Manage Page</h1>
        <b>Logged in as "${admin.firstName}"</b>
        <br>
        <a href="Login">Logout ${admin.firstName}</a>
        <br>
        <br>
        <a href="admin">Return to Admin Home Page</a>
        <br>
        <br>
        <b>Manage ${manageUser.firstName}'s Account</b>
        <form action="accounts" method="post">
            Email: <input type="text" name="accEmail" value="${manageUser.email}"> 
            <br>
            First Name:<input type="text" name="accFirst" value="${manageUser.firstName}"> 
            <br>
            Last Name:<input type="text" name="accLast" value="${manageUser.lastName}"> 
            <br>
            Password:<input type="text" name="accPassword" value="${manageUser.password}"> 
            <br>
            Role:<select name="accRoleID">
                <option value="${manageUser.role.roleId}">${manageUser.role.roleName}</option>
                    <c:if test="${manageUser.role.roleId eq 1}"> 
                        <option value="2">reqular user</option>
                    </c:if>
                    <c:if test="${manageUser.role.roleId eq 2}"> 
                        <option value="1">system admin</option>
                    </c:if>
                 </select>
            <br>
            Is Active: <b>${manageUser.active}</b>
            <br>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Save Changes">
        </form>
            <br>
            <br>
            
        <c:if test="${manageUser.active eq false}">
            <form action="accounts" method="post">
                <input type="hidden" name="accEmail" value="${manageUser.email}">
                <input type="hidden" name="action" value="reactivate">
                <input type="submit" value="Reactivate User">
            </form>
        </c:if>
            
        <c:if test="${manageUser.active eq true}">  
            <form action="accounts" method="post">
                <input type="hidden" name="accEmail" value="${manageUser.email}">
                <input type="hidden" name="action" value="deactivate">
                <input type="submit" value="Deactivate User">
            </form>
        </c:if>  
            
            <br>
            <br>
            <b style="color: red">The button Below will DELETE ${manageUser.firstName}'s entire account!</b>
            <br>
            <b style="color: red">And all Items in their inventory!</b>
            <br>
            <form action="accounts" method="post">
                <input type="hidden" name="action" value="deleteUser">
                <input type="hidden" name="accEmail" value="${manageUser.email}">
                <input type="submit" value="Delete User">
            </form>
                
                
                
                <table>
                    <tr>
                        <th></th>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
    </body>
</html>
