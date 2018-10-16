<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<m:header/>
         <m:nav/>
         <div class="container">
        <h1> Salesboard</h1>
        <form class="form-inline" action="searchQuery" method = "POST">
            <div class="form-group">
             <label for="searchform" >Search Items:</label>
             <input type="text" class="form-control mx-3 my-3" name="query">
             <button class="btn btn-primary" name="action" type="submit" value="search">
                Submit</button>
            </div>
        </form>
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
</div>
        <m:footer/>
