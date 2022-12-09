<%-- 
    Document   : admin
    Created on : 8-Dec-2022, 2:29:53 PM
    Author     : mhame
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory Admin Page</title>
    </head>
    <body>
        <h1>Hello ${admin.firstName}</h1>
        
        <a href="Login">Logout ${admin.firstName}</a>
        <br>
        <br>
        <br>
        
        <c:if test="${viewAcc ne 'true'}">
            <a href="admin?action=allAccounts">View All Accounts</a>
        </c:if>
        
        <br>
        <c:if test="${viewAcc eq 'true'}">
            <a href="admin?action=closeAcc">Close</a>
            <table border="1">
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>Is Active</th>
                </tr>
                <c:forEach var="User" items="${userList}">
                <tr>
                    <td>${User.email}</td>
                    <td>${User.firstName}</td>
                    <td>${User.lastName}</td>
                    <td>${User.password}</td>
                    <td>${User.role.roleName}</td>
                    <td>${User.active}</td>
                    <td><a href="<c:url value="/accounts">
                               <c:param name="action" value="manageAcc"/>
                               <c:param name="accEmail" value="${User.email}"/>
                           </c:url>">Manage</a></td>
                </tr>
                </c:forEach>
            </table> 
            <br>
            <h3>Add a new User</h3>
            <form action="admin" method="post">
                Email:<input type="text" name="addEmail">
                <br>
                First Name:<input type="text" name="firstName">
                <br>
                Last Name:<input type="text" name="lastName">
                <br>
                Password: <input type="password" name="password">
                <br>
                Role:<select name="roleId">
                    <option value="2" selected="selected">Regular User</option>
                    <option value="1">System Admin</option>
                </select>
                <br>
                <input type="hidden" name="action" value="addUser">
                <input type="submit" value="Add New User">
            </form>
            
            
            
        </c:if>
        <br>
        <br>
        <b>${message}</b>
        <br>
        <c:if test="${viewCat ne 'true'}">
            <a href="admin?action=allCategories">Manage all Categories</a>
        </c:if>
        <br>
        
        
        <c:if test="${viewCat eq 'true'}">
            <a href="admin?action=closeCat">Close</a>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Id#</th>
                </tr>
                <c:forEach var="Category" items="${catList}">
                <tr>
                    <td>${Category.categoryName}</td>
                    <td>${Category.categoryId}</td>
                    <td>Manage</td>
                </tr>
                </c:forEach>
            </table>

            <form action="admin" method="post">
                Category Name:<input type="text" name="catName" >
                <input type="hidden" name="action" value="addCat">
                <input type="submit" value="Add Category">     
            </form>
        </c:if>  
    </body>
</html>
