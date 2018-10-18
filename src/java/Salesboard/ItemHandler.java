/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salesboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;



/**
 *
 * @author kris
 */
public class ItemHandler implements Handler
{
    
   //It needs a no argument constructor so the front controller 
   //can instantiate it using reflection
  
ItemHandler()
   { }
 
 @Override
   public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception
      //Create instance of data access class (Just like in the first assignment)
    {
        HttpSession session = req.getSession();
        String dbConn = (String)session.getAttribute("dbConn");
        if(req.getMethod().equalsIgnoreCase("GET"))
        {
            Connection conn = getConnection(false, dbConn); 
            String name = (String)session.getAttribute("sessionuser");
            try {
                String action = req.getParameter("action");
                System.out.println("action: " + action);
                if (req.getParameter("edit")!=null) {
                    action = "edit";
                } else if (req.getParameter("add")!=null) {
                    action = "add";
                } else if (req.getParameter("delete")!=null) {
                    action = "delete";
                }
                if (action == "edit") {
                    int id = Integer.parseInt(req.getParameter("itemId"));
                    PreparedStatement getItem = conn.prepareStatement("select * from items "
                         + "where id = ?");
                    getItem.setInt(1, id);
                    ResultSet rs = getItem.executeQuery();
                    while (rs.next()) {
                        String seller = name;
                        String item = rs.getString("item");
                        String description = rs.getString("description");
                        int quantity = rs.getInt("quantity");
                        int price = rs.getInt("price");
                        itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                        req.setAttribute("itemBean", itemB);
                        // set to session here (maybe -- already in request
                    }   
                    return "/editItem.jsp";
                }
                if (action == "add"){
          
                    // create session bean for item (with no ID)
                    // return /editItem.jsp
                    // /editItem routine to check if exists (does item
                    // bean have id?) and insert if not, SELECT SCOPE_IDENTITY() as id(?);
                    // update if exists
                    System.out.println("item is " + req.getParameter("item"));
                    System.out.println("we got to add");
//                    resp.sendRedirect("editItem");
//                    return null;
                    return "/addItem.jsp";

                }
                if (action == "delete"){
                    int id = Integer.parseInt(req.getParameter("itemId"));
                    PreparedStatement deleteItem = conn.prepareStatement("delete from items "
                        + "where id = ?");
                    deleteItem.setInt(1, id);
                    deleteItem.executeUpdate();
                 }
                if (action.equals("view")){
                    // this section may not be needed
                    // showView accessed from all items screen view
                    
                    int id = Integer.parseInt(req.getParameter("itemId"));
                    PreparedStatement getItem = conn.prepareStatement("select * from items "
                         + "where id = ?");
                    getItem.setInt(1, id);
                    ResultSet rs = getItem.executeQuery();
                    while (rs.next()) {
                        String seller = name;
                        String item = rs.getString("item");
                        String description = rs.getString("description");
                        int quantity = rs.getInt("quantity");
                        int price = rs.getInt("price");
                        itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                        req.setAttribute("itemBean", itemB);
                        return "/showItem.jsp";
                    }
                }
            }
            finally {
              conn.close();
            }  
            return "login?username=" + name;
        }
        else if(req.getMethod().equalsIgnoreCase("POST"))
        {
            Connection conn = getConnection(false, dbConn); 
            String name = (String)session.getAttribute("sessionuser");
            String action = req.getParameter("action");
            System.out.println("action is: " + action);
            System.out.println("parameter add: " + req.getParameter("add"));
            if (req.getParameter("add")!=null) {
                    action = "add";
                }
            if (action.equals("delete")){
                int id = Integer.parseInt(req.getParameter("itemId"));
                PreparedStatement deleteItem = conn.prepareStatement("delete from items "
                    + "where id = ?");
                deleteItem.setInt(1, id);
                deleteItem.executeUpdate();
                return "allItemsReport";
            } else {
                 String item = req.getParameter("item");
                String seller = name;
                String description = req.getParameter("description");
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                int price = Integer.parseInt(req.getParameter("price"));
               
                if (action.equals("add")) {
                    
                    // make bean from req params (above)
                    itemBean itemB = new itemBean(0, seller, item, description,quantity, price);
                    req.setAttribute("itemBean", itemB);
                    // set session item bean
                    // redirect to editview (get)
                   
                    PreparedStatement addItem = conn.prepareStatement("insert into items "
                            + "(seller, item, description, quantity, price) values "
                            + "(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    addItem.setString(1, seller);
                    addItem.setString(2, item);
                    addItem.setString(3, description);
                    addItem.setInt(4, quantity);
                    addItem.setInt(5, price);
                    addItem.executeUpdate();
                    ResultSet rs = addItem.getGeneratedKeys();
                    while (rs.next()) {
                    long id = rs.getLong(1);
                    System.out.println("Inserted ID " + id); // display inserted record
                     req.setAttribute("itemBean.id", id);
                    }
//                    PreparedStatement getId = conn.prepareStatement("select @@IDENTITY");
//                    ResultSet rs = getId.executeQuery();
//                    while (rs.next()){
//                    int id =  rs.getInt("id");
//                        }
                   
                   return "/editItem.jsp";
            
                } else if (action.equals("edit")) {
                    
                    // move add here 
                    // if bean has id, then update record with that id -- fine
                    // if bean has no ID, add to db, set bean id
                    // clear session item bean
                    // redirect sellerreport
                    
                    if (description.isEmpty()){
                        int id = Integer.parseInt(req.getParameter("itemId"));
                        System.out.println("Empty description");
                        session.setAttribute("message", "Please enter a description");
                        resp.sendRedirect("editItem?itemId=" +id + "&edit=edit");
                        return null;
                    }
                    int id = Integer.parseInt(req.getParameter("itemId"));
                    PreparedStatement editItem = conn.prepareStatement("update items " +
                          "  SET item = ?, description = ?, quantity = ?, price = ? " + 
                         "where id = ?");
                    editItem.setString(1, item);
                    editItem.setString(2, description);
                    editItem.setInt(3, quantity);
                    editItem.setInt(4, price);
                    editItem.setInt(5, id);
                    editItem.executeUpdate();
                    resp.sendRedirect("sellerReport");
                    return null;
                } 
            }   
//            return "login?username=" + name;
        }
        return null;
    }
   
    private Connection getConnection(boolean createDatabase, String dbConn) throws SQLException {
    
        checkDriverLoaded();
    String attributes = "";
    
    if (createDatabase) {
        attributes = ";create=true";
    }
        Connection conn = DriverManager.getConnection(dbConn + attributes);
        return conn;
    }

    private void checkDriverLoaded() throws RuntimeException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        }
        catch(Exception ex) {
            throw new RuntimeException("An error occrred loading jdbc driver", ex);
        }
    }

}


