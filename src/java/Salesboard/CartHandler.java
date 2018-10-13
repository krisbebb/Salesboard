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
public class CartHandler implements Handler
{
   //It needs a no argument constructor so the front controller 
   //can instantiate it using reflection
CartHandler()
   { }
 
 @Override
   public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception
      //Create instance of data access class (Just like in the first assignment)
   {
      if(req.getMethod().equalsIgnoreCase("GET"))
      {
           System.out.println("WE are in cartHandler GET");
           Connection conn = getConnection(false); 
              HttpSession session = req.getSession();
           String name = (String)session.getAttribute("sessionuser");
           try {
            int id = Integer.parseInt(req.getParameter("itemId"));
            System.out.println("id is: " + id);
            System.out.println("sessionuser parameter: " + name);
            
            
           }
        finally {
            conn.close();
          }  
           return "/userCart.jsp";
           
      }
      else if(req.getMethod().equalsIgnoreCase("POST"))
      {
                System.out.println("WE are in cartHandler POST");
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



