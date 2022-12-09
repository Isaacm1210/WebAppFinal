<%-- 
    Document   : categories
    Created on : 9-Dec-2022, 1:55:28 PM
    Author     : mhame
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Category</title>
    </head>
    <body>
        <h1>Hello ${admin.firstName}</h1>
        
        <a href="Login">Logout ${admin.firstName}</a>
        <br>
        <br>
        <a href="admin">Return to Admin Home page</a>
        <br>
        <br>
        <table border="1">
            <tr>
                <th>Category Name</th>
                <th>Category ID</th>
            </tr>
            <tr>
                <td>${manageCat.categoryName}</td>
                <td>${manageCat.categoryId}</td>
            </tr>
        </table>
        <br>
        <b>Change Category Name</b>
        <form action="category" method="post">
            New Category Name: <input type="text" name="newCatName">
            <input type="hidden" name="catID" value="${manageCat.categoryId}">
            <input type="submit" name="action" value="Save">
        </form>
            
            <br>
            <br>
            <b>All Items in the "${manageCat.categoryName}" Category</b>
            <table border="1">
                <tr>
                    <th>Item ID</th>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Owner</th>
                </tr>
                <c:forEach items="${catItemList}" var="Item">
                <tr>
                    <td>${Item.itemId}</td>
                    <td>${Item.itemName}</td>
                    <td>${Item.price}</td>
                    <td>${Item.owner.email}</td>
                </c:forEach>    
                </tr>
            </table>
            
            
            
    </body>
</html>
