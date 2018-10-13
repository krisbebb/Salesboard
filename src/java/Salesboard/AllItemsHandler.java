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
public class AllItemsHandler implements Handler
{
   //It needs a no argument constructor so the front controller 
   //can instantiate it using reflection
AllItemsHandler()
   { }
 
 @Override
   public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception
      //Create instance of data access class (Just like in the first assignment)
   {
      if(req.getMethod().equalsIgnoreCase("GET"))
      {
           System.out.println("WE are in allItemsHandler GET");
       
        Connection conn = getConnection(false);
        try {
             HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            System.out.println(name);

            PreparedStatement allItems = conn.prepareStatement("select * from items ");
//            allItems.setString(1, name);
            ResultSet rs = allItems.executeQuery();
            List<itemBean> itemList = new ArrayList<>();
            while (rs.next()) {
                System.out.println("Printing result...");
                int id = rs.getInt("id");
                String seller = rs.getString("seller");
                String item = rs.getString("item");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
               
                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                
                      itemList.add(itemB);
                System.out.println("\tID: " + itemB.getId() +
                        ", seller: " + itemB.getSeller() + 
                       ", item: " + itemB.getItem() +
                        ", description: " + itemB.getDescription() + 
                        ", quantity: " + itemB.getQuantity() +
                        ", price: " + itemB.getPrice());
            }
              req.setAttribute("itemList", itemList);
            }
        finally {
            conn.close();
          }  
          return "/allItemsReport.jsp";
      }
      else if(req.getMethod().equalsIgnoreCase("POST"))
      {
               System.out.println("WE are in allItemsHandler POST");
         
       Connection conn = getConnection(false);
        try {
             HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            System.out.println(name);

            PreparedStatement allItems = conn.prepareStatement("select * from items ");
//            allItems.setString(1, name);
            ResultSet rs = allItems.executeQuery();
            List<itemBean> itemList = new ArrayList<>();
            while (rs.next()) {
                System.out.println("Printing result...");
                int id = rs.getInt("id");
                String seller = rs.getString("seller");
                String item = rs.getString("item");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
               
                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                
                      itemList.add(itemB);
                System.out.println("\tID: " + itemB.getId() +
                        ", seller: " + itemB.getSeller() + 
                       ", item: " + itemB.getItem() +
                        ", description: " + itemB.getDescription() + 
                        ", quantity: " + itemB.getQuantity() +
                        ", price: " + itemB.getPrice());
            }
              req.setAttribute("itemList", itemList);
            }
        finally {
            conn.close();
          }  
          return "/allItemsReport.jsp";
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



