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
        
        <a href="Login">Logout</a>
        <br>
        <br>
        
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
                        
                        <td><a href="<c:url value="/inventory"><c:param name="action" value="Edit"/>
                                   <c:param name="itemID" value="${item.itemId}"/>
                                   <c:param name="itemN" value="${item.itemName}"/>
                                   <c:param name="itemP" value="${item.price}"/>
                                   <c:param name="itemCat" value="${item.category.categoryId}"/>
                               </c:url>">Edit</a></td>
 
                        
                    </tr>
                </c:forEach>
            </table>
        </c:if>
            
            <br>
            <c:if test="${itemEdit ne 'true'}">
                <b>Add an Item</b>

                <form action="inventory" method="post">
                    Category: <select name="category">
                        <c:forEach var="Category" items="${categories}">
                            <option value="${Category.categoryId}">${Category.categoryName}</option>
                        </c:forEach>
                    </select>
                    <br>
                    Item Name: <input type="text" name="itemName">
                    <br>
                    Item Price: <input type="number" step="0.01" min="0" name="itemPrice">
                    <br>
                    <input type="submit" name="action" value="Add">
                </form>
            </c:if>
                
            <c:if test="${itemEdit eq 'true'}">
                <b>Edit Item "${editItemName}"</b>
                <br>

                <form action="inventory" method="post">
                    Category: <select name="category">
                        <option selected="selected" value="${editCat.categoryId}">${editCat.categoryName}</option>
                        <c:forEach var="Category" items="${categories}">
                            <option value="${Category.categoryId}">${Category.categoryName}</option>
                        </c:forEach>
                    </select>
                    <br>
                    Item Name: <input type="text" name="itemName" value="${editItemName}">
                    <br>
                    Item Price: <input type="number" step="0.01" min="0" name="itemPrice" value="${itemPrice}">
                    <br>
                    <input type="hidden" name="oCatID" value="${editCat.categoryId}">
                    <input type="hidden" name="editItemID" value="${editItemID}">
                    <input type="submit" name="action" value="Save">
                </form>
                
                <form action="inventory" method="post">
                    <input type="hidden" name="deleteItemID" value="${editItemID}">
                    <input type="submit" name="action" value="Delete">
                </form>
                <a href="inventory?action=cancel">Cancel</a>
            </c:if>
            <br>
            <b style="color:red;">${message}</b>
    </body>
</html>
