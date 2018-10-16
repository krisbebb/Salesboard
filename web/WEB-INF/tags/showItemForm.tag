<%-- 
    Document   : showItemForm
    Created on : 16/10/2018, 10:48:05 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="itemBean" required="true" rtexprvalue="true" type="Salesboard.itemBean"%>

 <table class="table">
            <tr>
                <th>Id</th>
                <th>Item</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>
                <th></th>
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
                <c:choose>
                    <c:when test = "${sessionScope.adminUser == sessionScope.sessionuser}">
                    <td>
                    <form action="buyItem" method="POST">
                    <div class="form-group">
                    <input class="form-control" type="number" value="1" name="itemQty">  </div> </td>
                    <td><div class="form-group">
                    <input type="hidden" name="itemId" value="${itemBean.id}" />
                    <input class="btn btn-primary" type="submit" value="buy" name="action"> </div>  </form> </td>
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
                    <form action="buyItem" method="POST">
                    <div class="form-group">
                    <input class="form-control" type="number" value="1" name="itemQty">  </div> </td>
                    <td><div class="form-group">
                    <input type="hidden" name="itemId" value="${itemBean.id}" />
                    <input class="btn btn-primary" type="submit" value="buy" name="action"> </div>  </form> </td>
                     </td>
                    </c:otherwise>
                </c:choose>
        </tr>
        </table>