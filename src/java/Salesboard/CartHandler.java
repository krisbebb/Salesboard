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
import static java.util.Collections.list;
import java.util.Enumeration;
import java.util.Iterator;
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
        HttpSession session = req.getSession();
        String dbConn = (String)session.getAttribute("dbConn");
        if(req.getMethod().equalsIgnoreCase("GET"))
        {
        return "/userCart.jsp";
        }
        else if(req.getMethod().equalsIgnoreCase("POST"))
        {
            String action = (String)req.getParameter("action");
            if (session.getAttribute("totalPrice") == null) {
                session.setAttribute("totalPrice", 0);
            }
            if ((action.equals("Checkout"))){
                checkout(req,resp);
                return "allItemsReport";
            }
            if (action.equals("buy")){
                String view = addItemToCart(req,resp);
                return view;
            }
            if (action.equals("remove")){
                removeCartItem(req, resp);
                return "/userCart.jsp";
            }
            if (action.equals("Clear")) {
                session.setAttribute("totalPrice", 0);
                session.setAttribute("cartList", null);
                return "/userCart.jsp";
            }
             if (session.getAttribute("cartList") == null){
                return "/userCart.jsp";
            }
        }  return null;
    }
   
    private String addItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HttpSession session = req.getSession();
        String dbConn = (String)session.getAttribute("dbConn");
        Connection conn = getConnection(false, dbConn); 
        try{
            String name = (String)session.getAttribute("sessionuser");
            int totalPrice = (int)session.getAttribute("totalPrice");  
            if (req.getParameter("itemId") == null){
                return null;
            }
            int id = Integer.parseInt(req.getParameter("itemId"));
            int itemQty = Integer.parseInt(req.getParameter("itemQty"));
            PreparedStatement cartItem = conn.prepareStatement("select * from items " +
                    "where id = ?");
            cartItem.setInt(1, id);
            ResultSet rs = cartItem.executeQuery();
            List<itemBean> tempList = new ArrayList<>();
            while (rs.next()) {
                String seller = rs.getString("seller");
                String item = rs.getString("item");
                String description = rs.getString("description");
                int quantity = itemQty;
                int price = rs.getInt("price");
                itemBean itemB = new itemBean(id, seller, item, description,quantity, price);
                totalPrice += itemB.getQuantity() * itemB.getPrice();
                session.setAttribute("totalPrice", totalPrice);
                tempList.add(itemB);
            }
            if (session.getAttribute("cartList") == null){
                session.setAttribute("cartlist", tempList);
            } else {
                List<itemBean> cartList = new ArrayList<>();
                cartList = (List)session.getAttribute("cartList");
                session.setAttribute("cartList", tempList.addAll(cartList));
            }
              session.setAttribute("cartList", tempList);
        }  finally {
            conn.close();
        }  
        return "/userCart.jsp";
    }
    
    private String checkout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HttpSession session = req.getSession();
        String dbConn = (String)session.getAttribute("dbConn");
        String name = (String)session.getAttribute("sessionuser");
        Connection conn = getConnection(false, dbConn); 
        List<itemBean> cartList = new ArrayList<>();
        cartList = (List)session.getAttribute("cartList");
        int totalSpent= 0;
        while (cartList.isEmpty() == false){
            int sellerTotal = 0;
            itemBean itemB = new itemBean();
            // get cartlist item 0
            itemB.setId(cartList.get(0).getId());
            itemB.setPrice(cartList.get(0).getPrice());
            itemB.setQuantity(cartList.remove(0).getQuantity());
            // get data from items
            try {
                conn = getConnection(false, dbConn); 
                PreparedStatement getQty = conn.prepareStatement("select * from items " +
                    "where id = ?");
                getQty.setInt(1, itemB.getId());
                // get items qty, subtract cart qty from items qty
                // get item price, seller
                // increment seller total by price
                ResultSet rs = getQty.executeQuery();
                while (rs.next()) {
                    int qty = rs.getInt("quantity");
                    String seller = rs.getString("seller");
                    int price = rs.getInt("price");
                    int newQty = qty - itemB.getQuantity();
                    sellerTotal += price * itemB.getQuantity();
                    // update items table
                    PreparedStatement editItem = conn.prepareStatement("update items " +
                        "  SET quantity = ? " + 
                        "where id = ?");
                    editItem.setInt(1, newQty);
                    editItem.setInt(2, itemB.getId());
                    editItem.executeUpdate();
                    conn = getConnection(false, dbConn); 
                    PreparedStatement getTotal = conn.prepareStatement("select * from sellers " +
                        "where buyer = ? and seller=?");
                    getTotal.setString(1, name);
                    getTotal.setString(2, seller);
                    ResultSet rsb = getTotal.executeQuery();
                    while (rsb.next()) {
                        totalSpent = rsb.getInt("total_spent");
                    }
                    // add cart totalPrice
                    // update sellers total_spent
                    // if record exists
                    PreparedStatement checkBuyer = conn.prepareStatement ("select * from sellers " + 
                        "where seller = ? and buyer = ?");
                    checkBuyer.setString(1, seller);
                    checkBuyer.setString(2, name);
                    ResultSet existsBuyer = checkBuyer.executeQuery();
                    while (existsBuyer.next()){
                        PreparedStatement editSeller = conn.prepareStatement("update sellers " +
                             "  SET total_spent = ? " + 
                             "where buyer = ? and seller = ?");
                        editSeller.setInt(1, totalSpent + sellerTotal);
                        editSeller.setString(2, name);
                        editSeller.setString(3, seller);
                        editSeller.executeUpdate();
                        session.setAttribute("totalPrice", 0);
                        return "/userCart.jsp";
                    }
                    PreparedStatement insertBuyer = conn.prepareStatement("insert into sellers "+ 
                            "values (?, ?, ?)");
                    insertBuyer.setString(1, seller);
                    insertBuyer.setString(2, name);
                    insertBuyer.setInt(3, sellerTotal);
                    insertBuyer.executeUpdate();
                    session.setAttribute("totalPrice", 0);
                }
                // get buyers total_spent
            } finally {
                conn.close();
            }
            // else
            // insert record for seller, with buyer and total price
        }  return "/userCart.jsp";
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

    private void removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int listItem = Integer.parseInt(req.getParameter("listItem"));
        HttpSession session = req.getSession();
        int totalPrice = (int)session.getAttribute("totalPrice");
        List<itemBean> tempList = new ArrayList<>();
        tempList = (List)session.getAttribute("cartList");
        int i = 0;
        if (tempList == null) {
            System.out.println("cartlist is null");
            System.out.println("clearing totalPrice");
            totalPrice = 0;
        }
        for (Iterator<itemBean> iter = tempList.listIterator(); 
        iter.hasNext(); ) 
        {
            itemBean a = iter.next();
            if (a.getId() == listItem) {
                System.out.println("list position is " + i);
                System.out.println("found!");
                // subtract quantity * total from cartTotal
                totalPrice -= a.getQuantity() * a.getPrice();
                session.setAttribute("totalPrice", totalPrice);
                iter.remove();
            }
        }
    }
}


