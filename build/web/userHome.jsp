<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show User</title>
    </head>
    <body>
        <h1> The customer is ${userBean.username} </h1>
           <form action = "showUser" method = "GET">
         First Name: <input type = "text" name = "username">
         <input type = "submit" value = "Submit" />
      </form> 
        <jsp:useBean id="userBean" class="Salesboard.userBean" scope="request"/>
        <jsp:getProperty name="userBean" property="username" />
        <jsp:getProperty name="userBean" property="age" />
        <jsp:getProperty name="userBean" property="address" />

        

    </body>
</html>
