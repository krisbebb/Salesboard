<%-- 
    Document   : userDetails
    Created on : 13/10/2018, 1:59:17 PM
    Author     : kris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
        <<m:header title="Edit Details"/>
        <m:nav sessionBean="${sessionScope["sessionBean"]}"/>
        <div class="container">
        <h1> Edit User ${userBean.username} </h1>
         <m:userDetailsForm userBean="${userBean}"/>
        </div>
        <m:footer/>