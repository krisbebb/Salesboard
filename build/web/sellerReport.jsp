<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Report </title>
    </head>
    <body>
        <h1>Seller report for ${sessionScope["sessionuser"]} </h1>
        
        <table>
        <tr>
      <TH>Id</th>
      <TH>Item</th>
      <TH>Description</th>
      <TH>Quantity</th>
      <TH>Price</th>
      <th>Edit</th>
        </tr>
      <c:forEach items="${sellerList}" var="current">
        <tr>
            <td><c:out value="${current.id}" /></td>
            <td><c:out value="${current.item}" /></td>
            <td><c:out value="${current.description}" /></td>
            <td><c:out value="${current.quantity}" /></td>
            <td><c:out value="${current.price}" /></td>
            <td><form action="editItem" method="POST">
                <input type="hidden" name="itemId" value="${current.id}" />
                <input type="submit" value="edit" name="edit"></td>
            </form>
        </tr>
      </c:forEach>
    </table>

        <p> add item </p>
        
      <jsp:include page="./nav.jsp" />

    </body>
</html>
