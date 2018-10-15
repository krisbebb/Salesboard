<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>

        <m:header/>
        <m:nav/>
        <h1> The customer is ${userBean.username} </h1>
          
        <jsp:useBean id="userBean" class="Salesboard.userBean" scope="request"/>
        <jsp:getProperty name="userBean" property="username" />
        <jsp:getProperty name="userBean" property="age" />
        <jsp:getProperty name="userBean" property="address" />


        <m:footer/>