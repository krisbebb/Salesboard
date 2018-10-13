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
      if(req.getMethod().equalsIgnoreCase("GET"))
      {
      
         //Query data access class for item to be edited
         //attach item to request
         //return path to view (JSP page) (eg. return “./editView.jsp”; 
         //In this case the editView.jsp would use the item
         //attached to the request to write out a form prepopulated
         //with the values for the item.Connection conn = getConnection(false);
          //         PreparedStatement sellerItems = conn.prepareStatement("select * from items " + 
//                    "where seller = ?");
//            sellerItems.setString(1, name);
//            ResultSet rs = sellerItems.executeQuery();
//            List<itemBean> sellerList = new ArrayList<>();
//            while (rs.next()) {
//                System.out.println("Printing result...");
//                int id = rs.getInt("id");
//                String seller = rs.getString("seller");
//                String item = rs.getString("item");
//                String description = rs.getString("description");
//                int quantity = rs.getInt("quantity");
//                int price = rs.getInt("price");
//               
//                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
//                
//                      sellerList.add(itemB);
//                System.out.println("\tID: " + itemB.getId() +
//                        ", seller: " + itemB.getSeller() + 
//                       ", item: " + itemB.getItem() +
//                        ", description: " + itemB.getDescription() + 
//                        ", quantity: " + itemB.getQuantity() +
//                        ", price: " + itemB.getPrice());
//            }
//              req.setAttribute("sellerList", sellerList);
          
           System.out.println("WE are in itemHandler GET");
           Connection conn = getConnection(false); 
              HttpSession session = req.getSession();
           String name = (String)session.getAttribute("sessionuser");
           try {
          
            
            String action =(String)req.getParameter("action");
          
            if (req.getParameter("edit")!=null) {
                action = "edit";
            } else if (req.getParameter("add")!=null) {
                action = "add";
            } else if (req.getParameter("delete")!=null) {
                action = "delete";
            }
            System.out.println("action is: " + action);
            System.out.println("sessionuser parameter: " + name);
            
            if (action == "edit") {
//                String item = req.getParameter("item");
//                String description = req.getParameter("description");  
//                int quantity = Integer.parseInt(req.getParameter("quantity"));  
//                int price = Integer.parseInt(req.getParameter("price"));
//                
//                PreparedStatement editItem = conn.prepareStatement("update items "
//                    + "set item=?, description=?, quantity=?, price=?"
//                    + "where id = ?");
//            editItem.setString(1, item);
//            editItem.setString(2, description);
//            editItem.setInt(3, quantity);
//            editItem.setInt(4, price);
                // redirect to edit item details?
                int id = Integer.parseInt(req.getParameter("itemId"));
                System.out.println("ItemId is: " + id);
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
            }
                return "/editItem.jsp";
            }
            
            if (action == "add"){
                // redirect to edit item details?
                return "/addItem.jsp";
            }
            
            if (action == "delete"){
                int id = Integer.parseInt(req.getParameter("itemId"));
                System.out.println("Deleting item... " + id);
                 PreparedStatement deleteItem = conn.prepareStatement("delete from items "
                    + "where id = ?");
            deleteItem.setInt(1, id);
            deleteItem.executeUpdate();
            }
            
             if (action.equals("view")){
                 int id = Integer.parseInt(req.getParameter("itemId"));
                System.out.println("ItemId is: " + id);
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
           System.out.println("WE are in itemHandler POST");
         //Obtain request parameters which will be the new values for
         //item being edited
         //Use the data access class to update the item being edited.
         //send a redirect to the client for the next page in the app (eg. a report page).
         //return null so the front controller knows that a redirect has been sent
         //and doesnt try to forward the request to a view.
            Connection conn = getConnection(false); 
             HttpSession session = req.getSession();
            String name = (String)session.getAttribute("sessionuser");
           
//        RequestDispatcher dispatch = req.getRequestDispatcher("sellerReport?username=" + name);
//        dispatch.forward(req, resp);
            
            
            String action = req.getParameter("action");
            String item = req.getParameter("item");
            String seller = name;
            String description = req.getParameter("description");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int price = Integer.parseInt(req.getParameter("price"));
            System.out.println("item, seller, description, quantity, price" + item + seller
            + description + quantity + price );
            
            System.out.println("action is: " + action);
            if (action.equals("add")) {
                PreparedStatement addItem = conn.prepareStatement("insert into items "
                        + "(seller, item, description, quantity, price) values "
                        + "(?,?,?,?,?)");
            addItem.setString(1, seller);
            addItem.setString(2, item);
            addItem.setString(3, description);
            addItem.setInt(4, quantity);
            addItem.setInt(5, price);
            
            
            addItem.executeUpdate();
                System.out.println("executing add...");
            } else if (action.equals("edit")) {
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
                System.out.println("executing update...");
            }
            
            return "login?username=" + name;
   }
         return null;
   
   }
    private Connection getConnection(boolean createDatabase) throws SQLException {
    checkDriverLoaded();
    String attributes = "";
    if (createDatabase) {
        attributes = ";create=true";
    }
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/salesboard" + attributes);
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



