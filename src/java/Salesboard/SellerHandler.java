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
public class SellerHandler implements Handler
{
   //It needs a no argument constructor so the front controller 
   //can instantiate it using reflection
SellerHandler()
   { }
 
 @Override
   public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception
      //Create instance of data access class (Just like in the first assignment)
   {
       HttpSession session = req.getSession();
       String dbConn = (String)session.getAttribute("dbConn");
      
      if(req.getMethod().equalsIgnoreCase("GET"))
      {
        
          System.out.println("WE are in sellerHandler GET");
//           HttpSession session = req.getSession();
          String name = (String)session.getAttribute("sessionuser");
          Connection conn = getConnection(false, dbConn);
        try {
            
           
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
              
                PreparedStatement buyerItems = conn.prepareStatement("select * from sellers " + 
                    "where seller = ?");
            buyerItems.setString(1, name);
            ResultSet rsb = buyerItems.executeQuery();
            List<sellerBean> buyerList = new ArrayList<>();
            while (rsb.next()) {
                System.out.println("Printing result...");
             
                String buyer = rsb.getString("buyer");
                  int total_spent = rsb.getInt("total_spent");
               
                sellerBean sellerBean = new sellerBean(name, buyer, total_spent);
                
                      buyerList.add(sellerBean);
                System.out.println("\tSeller: " + sellerBean.getSeller() +
                        ", seller: " + sellerBean.getBuyer() + 
                       ", item: " + sellerBean.getTotal_spent());
            }
              req.setAttribute("buyerList", buyerList);
        }
        finally {
            conn.close();
          }  
      }
      else if(req.getMethod().equalsIgnoreCase("POST"))
      {
        
          System.out.println("WE are in sellerHandler POST");
      }
       return "/userHome.jsp";
   }

    
    private Connection getConnection(boolean createDatabase, String dbConn) throws SQLException {
    checkDriverLoaded();
    String attributes = "";
        System.out.println("DBCONN IS: " + dbConn);
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



