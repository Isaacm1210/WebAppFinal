<%-- 
    Document   : inventory
    Created on : 6-Dec-2022, 7:49:26 PM
    Author     : mhame
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${user.firstName}'s HOME nVentory</title>
    
    </head>
    <body>
        <h1>${user.firstName}'s Inventory</h1>
        
        <a href="user">Return to your Account</a>
        <br>
        <br>
        <c:if test="${items.size() lt 1}">
            <b>No items found. Please add an item</b>
        </c:if>
        
        <c:if test="${items.size() gt 0}">
            <table border="1">
                <tr>
                    <th>Category</th>
                    <th>Item Name</th>
                    <th>Item Price</th>
                </tr>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td>${item.category.categoryName}</td>
                        <td>${item.itemName}</td>
                        <td>${item.price}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
            
            <form action="inventory" method="post">
                
            </form>
    </body>
</html>
