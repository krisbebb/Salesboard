<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show User</title>
    </head>
    <body>
       <c:remove var="sessionuser"/>
       <c:remove var="adminUser"/>
            
       <c:set var="adminUser" value="${initParam['adminUser']}" scope="session" />
     
        <h1> Please enter username to login or create account </h1>
           <form action = "./app/login" method = "GET">
         First Name: <input type = "text" name = "username">
         <input type = "submit" value = "Submit" />
      </form> 
       
    </body>
</html>
