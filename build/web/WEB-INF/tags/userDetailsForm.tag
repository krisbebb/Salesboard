<%-- 
    Document   : userDetailsForm
    Created on : 16/10/2018, 11:02:38 PM
    Author     : kris
--%>
<%@ attribute name="userBean" required="true" rtexprvalue="true" type="Salesboard.userBean"%>


       <form  action='userDetails.jsp' method="post">
           <div class = "form-group my-3">
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