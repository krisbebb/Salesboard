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
public class LoginHandler implements Handler
{
   //It needs a no argument constructor so the front controller 
   //can instantiate it using reflection
LoginHandler()
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
         //with the values for the item.
        Connection conn = getConnection(false);
        try {
             HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            session.setAttribute("sessionuser", name);
            System.out.println("sessionuser: " + session.getAttribute("sessionuser"));
            System.out.println("request parameter username: " + name);

         PreparedStatement sellerItems = conn.prepareStatement("select * from items " + 
                    "where seller = ?");
            sellerItems.setString(1, name);
            ResultSet rs = sellerItems.executeQuery();
            List<itemBean> sellerList = new ArrayList<>();
            while (rs.next()) {
                System.out.println("Printing result...");
                int id = rs.getInt("id");
                String seller = rs.getString("seller");
                String item = rs.getString("item");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
               
                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                
                      sellerList.add(itemB);
                System.out.println("\tID: " + itemB.getId() +
                        ", seller: " + itemB.getSeller() + 
                       ", item: " + itemB.getItem() +
                        ", description: " + itemB.getDescription() + 
                        ", quantity: " + itemB.getQuantity() +
                        ", price: " + itemB.getPrice());
            }
              req.setAttribute("sellerList", sellerList);
        }
        finally {
            conn.close();
          }  
          return "/sellerReport.jsp";
      }
      else if(req.getMethod().equalsIgnoreCase("POST"))
      {
         //Obtain request parameters which will be the new values for
         //item being edited
         //Use the data access class to update the item being edited.
         //send a redirect to the client for the next page in the app (eg. a report page).
         //return null so the front controller knows that a redirect has been sent
         //and doesnt try to forward the request to a view.
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



