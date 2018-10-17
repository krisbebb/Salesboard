<%-- 
    Document   : editItemForm
    Created on : 16/10/2018, 9:28:53 PM
    Author     : kris
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>

<%-- any content can be specified here e.g.: --%>

        <form class="form-inline" action='editItem' method="post">
            <div class="form-group">
            item: <input type="text" name="item">
            description: <input type="text" name="description">
            quantity:  <input type="text" name="quantity">
            price:  <input type="text" name="price">
            </div>
            <div class="form-group">
            <button class="btn btn-primary mx-2 my-3" name="action" type="submit" value="add">
                Add Item</button>
            <button class="btn btn-primary mx-2 my-3" name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
            </div>
        </form>