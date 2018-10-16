<%-- 
    Document   : itemsReport
    Created on : 16/10/2018, 10:07:36 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="itemList" required="true" rtexprvalue="true" type="java.util.List<Salesboard.itemBean>"%>

<table class="table">
    <tr>
      <TH>Id</th>
      <TH>Seller</th>
      <TH>Item</th>
      <TH>Description</th>
      <TH>Quantity</th>
      <TH>Price</th>
      <th>Action</th>
      <th></th>
    </tr>
    <c:forEach items="${itemList}" var="current">
    <tr>
        <td><c:out value="${current.id}" /></td>
        <td><c:out value="${current.seller}" /></td>
        <td><c:out value="${current.item}" /></td>
        <td><c:out value="${current.description}" /></td>
        <td><c:out value="${current.quantity}" /></td>
        <td><c:out value="${current.price}" /></td>
        <c:choose>
            <c:when test = "${sessionScope.adminUser == sessionScope.sessionuser}">
                <td> 
                <form class = "form-inline " action="viewItem" method="GET">
                 <input type="hidden" name="itemId" value="${current.id}" />
                 <input class="btn btn-primary" type="submit" value="view" name="action">
                </td>
                <td> 
                </form>
                <form class = "form-inline " action="editItem" method="POST">
                  <input type="hidden" name="itemId" value="${current.id}" />
                  <input class="btn btn-primary" type="submit" value="delete" name="action">
                 </form>
                </td>
            </c:when>
            <c:otherwise>
            <td>
            <form action="viewItem" method="GET">
            <div class="form-group">
                <input type="hidden" name="itemId" value="${current.id}" />
                <input class="btn btn-primary" type="submit" value="view" name="action">
            </div>
            </form>
            </td>
            </c:otherwise>
        </c:choose>
        </tr>
    </c:forEach>
</table>