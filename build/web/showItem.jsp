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
        <h1>Item Details</h1>
        <table class="table">
            <tr>
                <th>Id</th>
                <th>Item</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
                
            </tr>
        <tr>
            <td><c:out value="${itemBean.id}" /></td>
            <td><c:out value="${itemBean.item}" /></td>
            <td><c:out value="${itemBean.description}" /></td>
            <td><c:out value="${itemBean.quantity}" /></td>
            <td><c:out value="${itemBean.price}" /></td>
        </tr>
        <tr>
            <td></td>
                <td></td>
                <td></td>
                <td><form action="buyItem" method="POST">
                <input type="number" value="1" name="itemQty">   </td>
                <td>
                <input type="hidden" name="itemId" value="${itemBean.id}" />
                <input class="btn btn-primary" type="submit" value="buy" name="action">   </form> </td>
           
                </td>
        </tr>
        </table>
                <m:footer/>
