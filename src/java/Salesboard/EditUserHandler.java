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
import javax.servlet.http.*;



/**
 *
 * @author kris
 */
public class EditUserHandler implements Handler
{
   //It needs a no argument constructor so the front controller 
   //can instantiate it using reflection
EditUserHandler()
   { }
 
 @Override
   public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception
      //Create instance of data access class (Just like in the first assignment)
    {
        HttpSession session = req.getSession();
        String dbConn = (String)session.getAttribute("dbConn");
        if(req.getMethod().equalsIgnoreCase("GET"))
        {
            System.out.println("WE are in editUserHandler GET");
            String name = (String)session.getAttribute("sessionuser");
            Connection conn = getConnection(false, dbConn);
            try {
                System.out.println(name);
                PreparedStatement selectUser = conn.prepareStatement("select * from users " +
                    "where username = ?");
                selectUser.setString(1, name);
                ResultSet rs = selectUser.executeQuery();
                while (rs.next()) {
                    System.out.println("Printing result...");
                    String uname = rs.getString("username");
                    String fullName = rs.getString("name");
                    int age = rs.getInt("age");
                    String address = rs.getString("address");
                    userBean user = new userBean(uname, fullName, age, address);
                    req.setAttribute("userBean", user);
                    System.out.println("\tUsername: " + uname +
                          ", name: " + ", age: " + age +
                            ", address: " + address);
                }
            }
            finally {
            conn.close();
             }  
             return "/userDetails.jsp";
        }
        else if(req.getMethod().equalsIgnoreCase("POST"))
        {
            System.out.println("WE are in editUserHandler POST");
            String name = (String)session.getAttribute("sessionuser");
            Connection conn = getConnection(false, dbConn);
            String action = req.getParameter("action");
            try {
                if (action.equals("edit")) {
                    int age = Integer.parseInt(req.getParameter("age"));
                    String address = req.getParameter("address");
                    String fullname = req.getParameter("name");
                    PreparedStatement editUser = conn.prepareStatement("update users " +
                    "  SET name = ?, age = ?, address = ? " + 
                             "where username = ?");
                    editUser.setString(1, fullname);
                    editUser.setInt(2, age);
                    editUser.setString(3, address);
                    editUser.setString(4, name);
                    editUser.executeUpdate();
                    System.out.println("executing update...");
                    userBean sessionBean = new userBean(name,fullname, age, address);
                    session.setAttribute("sessionBean", sessionBean);
                }
            }
            finally {
             conn.close();
            }  
            return "allItemsReport";
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



