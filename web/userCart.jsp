<%-- 
    Document   : userDetails
    Created on : 13/10/2018, 1:59:17 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <m:header title="Shopping Cart"/>
    <m:nav sessionBean="${sessionScope["sessionBean"]}"/>
    <div class="container">
        <h1>Shopping Cart for ${sessionScope["sessionuser"]} </h1>
        <m:cartList itemList="${itemList}" cartList="${cartList}"/>
    </div>
   <m:footer/>