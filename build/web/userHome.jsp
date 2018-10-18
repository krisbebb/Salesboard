<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <m:header title="Home"/>
        <m:nav sessionBean="${sessionScope.sessionBean}"/>
        <div class="container">
        <h1>Home Page for ${sessionScope["sessionuser"]} </h1>
        <hr>
        <br>
        <h2> My Items for Sale </h2>
        <m:sellerList sellerList="${sellerList}"/>
        <hr>
        <br>
        <h2>My Customers </h2>
        <m:buyerList buyerList="${buyerList}"/>
        </div>
        <m:footer/>

