<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="sessionBean" required="true" rtexprvalue="true" type="Salesboard.userBean"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
<ul class="nav">
    <li class="nav-item">
    <a class="nav-link" href="./sellerReport">Home</a>
    </li>
    <li class="nav-item">
    <a class="nav-link" href="./allItemsReport">Salesboard</a>
    </li>
    <li class="nav-item">
    <a class="nav-link" href="./userDetails">Edit User Details</a>
    </li>
    <li class="nav-item">
    <a class="nav-link" href="./buyItem">Shopping Cart</a>
    </li>
    <li class="nav-item">
    <a class="nav-link" href="../login.jsp">Logout</a>
    </li>
   
    <c:if test="${sessionScope.adminUser == sessionScope.sessionuser}">
        <li class="nav-item">
        <a class="nav-link" href="#">Admin</a>
        </li>
    </c:if>
</ul>

 ${sessionBean.name}

 ${sessionBean.address}
    
      
</nav>