<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <m:header title="Add Item"/>
    <m:nav sessionBean="${sessionScope.sessionBean}"/>
    <div class="container">
        <h1>Add Item</h1>
        <m:addItemForm/>
    </div>
    <m:footer/>