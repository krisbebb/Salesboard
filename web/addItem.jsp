<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item </title>
    </head>
    <body>
        <h1>Add Item</h1>
        
        <form action='editItem' method="post">
            item: <input type="text" name="item">
            description: <input type="text" name="description">
            quantity:  <input type="text" name="quantity">
            price:  <input type="text" name="price">
            <button name="action" type="submit" value="add">
                Add Item</button>
            <button name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
        </form>
</body>
</html>