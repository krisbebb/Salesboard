<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
        <m:header title="Login"/>
        <div class="container">
            <c:remove var="sessionuser"/>
            
            <c:set var="adminUser" value="${initParam['adminUser']}" scope="session" />
            <c:set var="dbConn" value="${initParam['dbConn']}" scope="session" />
            <h1> Salesboard </h1>
            <h2> Enter Username </h2>
            <m:loginForm/>
            <div class="alert-danger" role="alert">
            ${message}
            
            </div>
        </div>
        <m:footer/>
    