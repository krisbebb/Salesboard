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
import java.util.Enumeration;
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
       // get all items

           reportAllItems(req, resp);
           return "/salesBoard.jsp";
      }
      else if(req.getMethod().equalsIgnoreCase("POST"))
      {
         String action = req.getParameter("action");
          // search
          if (action.equals("search")){
              String view = searchFilter(req, resp);
              return view;
          }
          reportAllItems(req, resp);
              return "/salesBoard.jsp";
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
    private String searchFilter(HttpServletRequest req, HttpServletResponse resp) throws Exception {
         HttpSession session = req.getSession();
       String dbConn = (String)session.getAttribute("dbConn");
        Connection conn = getConnection(false, dbConn);
        String query = req.getParameter("query");
        try {
            PreparedStatement searchItems = conn.prepareStatement("select * from items " +
                "where lower(item) like lower('%' || ? || '%')"
                    + "or lower(description) like lower('%' || ? || '%')");
            searchItems.setString(1, query);
            searchItems.setString(2, query);
            ResultSet rs = searchItems.executeQuery();
            List<itemBean> itemList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String seller = rs.getString("seller");
                String item = rs.getString("item");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                      itemList.add(itemB);
            }
              req.setAttribute("itemList", itemList);
        } finally {
            conn.close();
        }
        return "/salesBoard.jsp";
    }

        private void reportAllItems(HttpServletRequest req,HttpServletResponse resp) throws Exception {
            HttpSession session = req.getSession();
             String dbConn = (String)session.getAttribute("dbConn"); 
            Connection conn = getConnection(false, dbConn);
        try {
            String name = (String) req.getParameter("username");
            PreparedStatement allItems = conn.prepareStatement("select * from items ");
            ResultSet rs = allItems.executeQuery();
            List<itemBean> itemList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String seller = rs.getString("seller");
                String item = rs.getString("item");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("price");
                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                      itemList.add(itemB);
              req.setAttribute("itemList", itemList);
            }
        } finally {
            conn.close();
        }  
    }
}



