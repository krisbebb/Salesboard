<%-- 
    Document   : userDetails
    Created on : 13/10/2018, 1:59:17 PM
    Author     : kris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Cart</title>
    </head>
    <body>
        <h1>Shopping Cart for ${sessionScope["sessionuser"]} </h1>
         
       <form action='userDetails.jsp' method="post">
            Age: <input type="text" name="age" value="${userBean.age}">
            Address <input type="text" name="address" value="${userBean.address}">
            <input type="hidden" name="username" value="${userBean.username}" />
            <button name="action" type="submit" value="edit">
                Update User</button>
            <button name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
        </form>
       <jsp:include page="./nav.jsp" />

    </body>
</html>
