<%-- 
    Document   : editUser
    Created on : 11/10/2018, 5:09:18 PM
    Author     : kris
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<m:header/>
         <m:nav/>
        <h1>Add Item</h1>
        
        <form action='editItem' method="post">
            item: <input type="text" name="item">
            description: <input type="text" name="description">
            quantity:  <input type="text" name="quantity">
            price:  <input type="text" name="price">
            <button class="btn btn-primary" name="action" type="submit" value="add">
                Add Item</button>
            <button class="btn btn-primary" name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
        </form>
        <m:footer/>