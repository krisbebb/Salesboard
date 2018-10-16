<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
    <m:header title="SalesBoard"/>
    <m:nav/>
    <div class="container">
        <h1> Salesboard</h1>
        <m:searchForm/>
        <m:itemsReport itemList="${itemList}"/>
    </div>
    <m:footer/>
