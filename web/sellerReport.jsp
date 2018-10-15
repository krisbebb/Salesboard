<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home </title>
    </head>
    <body>
        <m:nav/>
        <h1>Home Page for ${sessionScope["sessionuser"]} </h1>
        <h2> Seller Report </h2>
        <table>
        <tr>
      <TH>Id</th>
      <TH>Item</th>
      <TH>Description</th>
      <TH>Quantity</th>
      <TH>Price</th>
      <th>Actions</th>
        </tr>
      <c:forEach items="${sellerList}" var="current">
        <tr>
            <td><c:out value="${current.id}" /></td>
            <td><c:out value="${current.item}" /></td>
            <td><c:out value="${current.description}" /></td>
            <td><c:out value="${current.quantity}" /></td>
            <td><c:out value="${current.price}" /></td>
            <td><form action="editItem" method="GET">
                <input type="hidden" name="itemId" value="${current.id}" />
                <input type="submit" value="edit" name="edit">
           
                <input type="submit" value="delete" name="delete"></td>
            </form>
                </td>
        </tr>
      </c:forEach>
    </table>

        <form action="editItem" method="GET">
               
                <input type="submit" value="add item" name="add"></td>
            </form>
        
        <h2>Customer Report </h2>
         <table>
        <tr>
      <TH>Buyer</th>
      <TH>Total Spent</th>
     
        </tr>
      <c:forEach items="${buyerList}" var="current">
        <tr>
            <td><c:out value="${current.buyer}" /></td>
            <td><c:out value="${current.total_spent}" /></td>
           
        </tr>
      </c:forEach>
    </table>

        


    </body>
</html>
