<%-- 
    Document   : userDetails
    Created on : 13/10/2018, 1:59:17 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Cart</title>
    </head>
    <body>
        <h1>Shopping Cart for ${sessionScope["sessionuser"]} </h1>
         
       <table>
        <tr>
      <TH>Id</th>
      <TH>Seller</th>
      <TH>Item</th>
      <TH>Description</th>
      <TH>Quantity</th>
      <TH>Price</th>
      <th>Action</th>
        </tr>
      <c:forEach items="${cartList}" var="current">
        <tr>
            <td><c:out value="${current.id}" /></td>
            <td><c:out value="${current.seller}" /></td>
            <td><c:out value="${current.item}" /></td>
            <td><c:out value="${current.description}" /></td>
            <td><c:out value="${current.quantity}" /></td>
            <td><c:out value="${current.price}" /></td>
            <td>
            <form action="removeItem" method="POST">
                <input type="hidden" name="listItem" value="${current.id}" />
                <input type="submit" value="remove" name="action">
               </td>
            </form>
        </tr>
      </c:forEach>
    </table>
    <h2> Total for cart is ${sessionScope["totalPrice"]} </h2>
    <form action="checkout" method="POST">
       <input type="hidden" name="itemId" value="${current.id}" />
       <input type="submit" value="Checkout" name="action">
    
    
       <input type="hidden" name="itemId" value="${current.id}" />
       <input type="submit" value="Clear" name="action">
    </form>
       <jsp:include page="./nav.jsp" />

    </body>
</html>
