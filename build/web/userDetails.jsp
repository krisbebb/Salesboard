<%-- 
    Document   : userDetails
    Created on : 13/10/2018, 1:59:17 PM
    Author     : kris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        <m:nav/>
        <h1> Edit User ${userBean.username} </h1>
          
       <form action='userDetails.jsp' method="post">
            Age: <input type="text" name="age" value="${userBean.age}">
            Name: <input type="text" name="address" value="${userBean.name}">
            Address: <input type="text" name="address" value="${userBean.address}">
            <input type="hidden" name="username" value="${userBean.username}" />
            <button name="action" type="submit" value="edit">
                Update User</button>
            <button name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
        </form>
    

    </body>
</html>
