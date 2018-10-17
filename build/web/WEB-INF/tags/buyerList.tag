<%-- 
    Document   : buyerList
    Created on : 16/10/2018, 11:06:26 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="buyerList" required="true" rtexprvalue="true" type="java.util.List<Salesboard.sellerBean>"%>
    
<table class="table">
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