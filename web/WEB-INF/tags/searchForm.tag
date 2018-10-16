<%-- 
    Document   : searchForm
    Created on : 16/10/2018, 10:08:20 PM
    Author     : kris
--%>

    <form class="form-inline" action="searchQuery" method = "POST">
    <div class="form-group">
        <label for="searchform" >Search Items:</label>
        <input type="text" class="form-control mx-3 my-3" name="query">
        <button class="btn btn-primary" name="action" type="submit" value="search">
        Submit</button>
    </div>
    </form>