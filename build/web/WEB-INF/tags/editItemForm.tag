<%-- 
    Document   : editItemForm
    Created on : 16/10/2018, 9:46:39 PM
    Author     : kris
--%>

<%@ attribute name="itemBean" required="true" rtexprvalue="true" type="Salesboard.itemBean"%>

<%-- any content can be specified here e.g.: --%>
        <form action='editItem' method="post">
            <div class="form-group">
            item: <input type="text" name="item" value="${itemBean.item}">
            </div>
            <div class="form-group">
            description: <input type="text" name="description" value="${itemBean.description}">
            </div>
            <div class="form-group">
            quantity:  <input type="text" name="quantity" value="${itemBean.quantity}">
            </div>
            <div class="form-group">
            price:  <input type="text" name="price" value="${itemBean.price}">
            </div>
            <div class="form-group">
             <input type="hidden" name="itemId" value="${itemBean.id}" />
             </div>
             <div class="form-group">
            <button class="btn btn-primary" name="action" type="submit" value="edit">
                Edit Item</button>
            <button class="btn btn-primary" name="action"  type="submit" method="post" value="cancel">
                Cancel</button>
                 </div>
            </div>
        </form>