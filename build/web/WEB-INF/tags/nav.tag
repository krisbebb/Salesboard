<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <a href="./sellerReport">Home</a>
    <a href="./allItemsReport">Salesboard</a>
    <a href="./userDetails.jsp">Edit User Details</a>
    <a href="../login.jsp">Logout</a>
   
    <c:if test="${sessionScope.adminUser == sessionScope.sessionuser}">
        <a href="./admin.jsp">Admin</a>
</c:if>
