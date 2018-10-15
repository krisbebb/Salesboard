<%-- 
    Document   : userDetails
    Created on : 13/10/2018, 1:59:17 PM
    Author     : kris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
        <m:header/>
        <m:nav/>
        <div class="container">
        <h1> Edit User ${userBean.username} </h1>
          
       <form  action='userDetails.jsp' method="post">
           <div class = "form-group">
            Age: <input type="text" name="age" value="${userBean.age}">
            </div>
            <div class = "form-group">
            Name: <input type="text" name="address" value="${userBean.name}">
            </div>
            <div class = "form-group">
            Address: <input type="text" name="address" value="${userBean.address}">
            </div>
            <input type="hidden" name="username" value="${userBean.username}" />
            <button class="btn btn-primary" name="action" type="submit" value="edit">
                Update User</button>
            <button class="btn btn-primary" name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
        </form>
    </div>
            <m:footer/>