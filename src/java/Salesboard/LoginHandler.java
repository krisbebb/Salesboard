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
import javax.servlet.ServletContext;
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
       HttpSession session = req.getSession();
       String dbConn = (String)session.getAttribute("dbConn");
      if(req.getMethod().equalsIgnoreCase("GET"))
      {
           System.out.println("WE are in loginHandler GET");
           
//           HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            if (name == null || name.isEmpty()){
                req.setAttribute("message", "Please enter a value");
                System.out.println("we have no username");
//                RequestDispatcher dispatcher = req.getRequestDispatcher("login");
//       dispatcher.forward(req, resp);
            return "/login.jsp";
            }
            session.setAttribute("sessionuser", name);
           
            
            System.out.println("sessionuser: " + session.getAttribute("sessionuser"));
            System.out.println("request parameter username: " + name);

           boolean userExists = checkForUser(req, resp);
           if (!userExists) {
               System.out.println("!userExists is true ie user does NOT exist");
              return "userDetails.jsp";
           }
           // get buyer report
            sellerReport(req, resp);
        
              // get buyer report 
              
              buyerReport(req, resp);
              
       
          return "/userHome.jsp";
      }
      else if(req.getMethod().equalsIgnoreCase("POST"))
      {
//            HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            session.setAttribute("sessionuser", name);
//           
//  ServletContext context = getServletContext();
//            String adminUser = context.getInitParameter("adminUser");
           
            
            System.out.println("sessionuser: " + session.getAttribute("sessionuser"));
            System.out.println("request parameter username: " + name);

           boolean userExists = checkForUser(req, resp);
           if (!userExists) {
               System.out.println("!userExists is true ie user does NOT exist");
              return "userDetails.jsp";
           }
           // get buyer report
            sellerReport(req, resp);
        
              // get buyer report 
              
              buyerReport(req, resp);
              
       
          return "/userHome.jsp";
 
       
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
    private void sellerReport(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        HttpSession session = req.getSession();
       String dbConn = (String)session.getAttribute("dbConn");
        Connection conn = getConnection(false, dbConn);
//          HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            session.setAttribute("sessionuser", name);
            System.out.println("sessionuser: " + session.getAttribute("sessionuser"));
            System.out.println("request parameter username: " + name);
        try {
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
    }
    
    private void buyerReport(HttpServletRequest req, HttpServletResponse resp) throws Exception{
         HttpSession session = req.getSession();
       String dbConn = (String)session.getAttribute("dbConn");
        Connection conn = getConnection(false, dbConn);
//          HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            session.setAttribute("sessionuser", name);
            System.out.println("sessionuser: " + session.getAttribute("sessionuser"));
            System.out.println("request parameter username: " + name);
        try {
            session.setAttribute("sessionuser", name);
         PreparedStatement buyerItems = conn.prepareStatement("select * from sellers " + 
                    "where seller = ?");
            buyerItems.setString(1, name);
            ResultSet rsb = buyerItems.executeQuery();
            List<sellerBean> buyerList = new ArrayList<>();
            while (rsb.next()) {
                System.out.println("Printing result...");
                String buyer = rsb.getString("buyer");
                int total_spent = rsb.getInt("total_spent");
                sellerBean sellerB = new sellerBean(name, buyer, total_spent);
                buyerList.add(sellerB);
                System.out.println("\tID: " + sellerB.getSeller() +
                        ", seller: " + sellerB.getBuyer() + 
                       ", item: " + sellerB.getTotal_spent());
            }
              req.setAttribute("buyerList", buyerList);
    }  finally {
            conn.close();
          }  
        
    }
    private boolean checkForUser(HttpServletRequest req, HttpServletResponse resp) throws Exception{
          HttpSession session = req.getSession();
       String dbConn = (String)session.getAttribute("dbConn");
        Connection conn = getConnection(false, dbConn);
//          HttpSession session = req.getSession();
            String name = (String) req.getParameter("username");
            session.setAttribute("sessionuser", name);
            System.out.println("sessionuser: " + session.getAttribute("sessionuser"));
            System.out.println("request parameter username: " + name);
        try {
            session.setAttribute("sessionuser", name);
         PreparedStatement userQuery = conn.prepareStatement("select * from users " + 
                    "where username = ?");
            userQuery.setString(1, name);
            ResultSet rsb = userQuery.executeQuery();
//            List<sellerBean> buyerList = new ArrayList<>();
            if (rsb.next()) {
                System.out.println("User exists.");
                conn.close();
                return true;
//                String buyer = rsb.getString("buyer");
//                int total_spent = rsb.getInt("total_spent");
//                sellerBean sellerB = new sellerBean(name, buyer, total_spent);
//                buyerList.add(sellerB);
//                System.out.println("\tID: " + sellerB.getSeller() +
//                        ", seller: " + sellerB.getBuyer() + 
//                       ", item: " + sellerB.getTotal_spent());
            } else {
                System.out.println("User does NOT exist");
                PreparedStatement insertName = conn.prepareStatement("insert into users "+ 
                        "(username) values (?)");
                insertName.setString(1, name);
                insertName.executeUpdate();
                System.out.println("executing name insert...");
//                session.setAttribute("totalPrice", 0);
                   conn.close();
            return false;
            }
//              req.setAttribute("buyerList", buyerList);
    }  finally {
            conn.close();
           
          }  
        
    }
}


