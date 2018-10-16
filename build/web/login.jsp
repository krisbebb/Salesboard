<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<m:header/>
<div class="container">
       <c:remove var="sessionuser"/>
       <c:remove var="adminUser"/>
            
       <c:set var="adminUser" value="${initParam['adminUser']}" scope="session" />
       <c:set var="dbConn" value="${initParam['dbConn']}" scope="session" />
     
        <h1> Salesboard </h1>
        <h2> Enter Username </h2>
        
        <form class="form-inline" action = "./app/login" method = "GET">
            <div class="form-group form-inline mx-2">
            <label for="usernameInput" class="form-control-plaintext mx-2">Username: </label>
            </div>
            <input type="text" class="form-control" name = "username" value="George">
            <input class="btn btn-primary mx-2 " type = "submit" value = "Submit" />
            </div>

         </div>
      </form> 
        </div>
           <m:footer/>
    