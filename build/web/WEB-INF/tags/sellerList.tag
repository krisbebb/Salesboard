<%-- 
    Document   : sellerList
    Created on : 16/10/2018, 11:06:12 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="sellerList" required="true" rtexprvalue="true" type="java.util.List<Salesboard.itemBean>"%>
  
    <table class="table">
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
            <input class="btn btn-primary" type="submit" value="edit" name="edit">
            <input class="btn btn-primary" type="submit" value="delete" name="delete"></td>
            </form>
        </td>
    </tr>
    </c:forEach>
    </table>
        <form action="createItem" method="GET">
            <input class="btn btn-primary" type="submit" value="add" name="add"></td>
        </form>