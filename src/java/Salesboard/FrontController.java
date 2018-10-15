/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salesboard;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author kris
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet FrontController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FrontController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

      //This map maps paths to names of java classes (fully qualified)
  //used to handle requests to those paths
  private Map<String, String> pathsToHandlers = new HashMap<String, String>();
 
  @Override
  public void init()
  {
     //Here we define which java classes handle which requests
        pathsToHandlers.put("/userDetails.jsp", "Salesboard.EditUserHandler");
//        pathsToHandlers.put("/showUser", "Salesboard.ShowUserHandler");
        pathsToHandlers.put("/login", "Salesboard.LoginHandler");
        pathsToHandlers.put("/allItemsReport", "Salesboard.AllItemsHandler");
        pathsToHandlers.put("/sellerReport", "Salesboard.SellerHandler");
        pathsToHandlers.put("/editItem", "Salesboard.ItemHandler");
        pathsToHandlers.put("/viewItem", "Salesboard.ItemHandler");
        pathsToHandlers.put("/buyItem", "Salesboard.CartHandler");
        pathsToHandlers.put("/removeItem", "Salesboard.CartHandler");
        pathsToHandlers.put("/checkout", "Salesboard.CartHandler");
//        pathsToHandlers.put("userHome.jsp", "Salesboard.userHandler");
//     pathsToHandlers.put("/movieDetails", "Salesboard.MovieDetails");
     
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
  {
      try {
          //We want processRequest to be run for both GET and POST requests
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
  {
      try {
          //We want processRequest to be run for both GET and POST requests
          processRequest(request, response);
      } catch (Exception ex) {
          Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
  {
    //1. Get path info from request.
    //2. Get the name of the class that is the handler for this request from  pathsToHandlers
    //3. Use the getHandlerInstance method (provided above) to get an instance of this class.
    //4. Call handleRequest on this instance to perform the handlers logic.
    //When the handler finishes handling the request it will do one of two things:
    // a. Return the path to a view (jsp page) to display the data to the user. The front controller should create a RequestDispatcher for this path and use it to forward the request to the jsp.
    // b. Return null to indicate to the front controller that a redirect has been sent to the client by the handler. In this case the front controller should take no further action.
    String path = request.getPathInfo();
    String handlerClass = pathsToHandlers.get(path);
    
      System.out.println("request path is :"+ path + " handerClass is: " + handlerClass);
      Handler handler = getHandlerInstance(handlerClass);
      String viewPath = handler.handleRequest(request, response);
      
      
        System.out.println("viewPath is: " + viewPath + "\n");
      if (viewPath != null) {
        RequestDispatcher rd=request.getRequestDispatcher(viewPath); 
        
      
        rd.forward(request, response);
      }
      System.out.println("We have null and got here");
//      this.getServletContext().getRequestDispatcher("/view.jsp");
     
      
  }
private Handler getHandlerInstance(String handlerClassName) throws ServletException
  {
    try
    {
      //Get the Class object for handlerClassName
      Class handlerClass = Class.forName(handlerClassName);

      //check that the handlerClass is actually a Handler)
      if(Handler.class.isAssignableFrom(handlerClass))
      {
        return (Handler) handlerClass.newInstance();
      }
      else
      {
        throw new ServletException("Class " + handlerClassName + " is not a Handler");
      }
    }
    catch(ClassNotFoundException classEx)
    {
      //This happens if the class cannot be found when Class.forName(String) is called
      throw new ServletException("Class " + handlerClassName + " does not exist", classEx);
    }
    catch(InstantiationException instanceEx)
    {
      //This happens if the constructor of the class throws an exception when handlerClass.newInstance() is called or if the constructor does not exist.
      throw new ServletException("An exception occurred trying to instantiate " + handlerClassName, instanceEx);
    }
    catch(IllegalAccessException illegalEx)
    {
      //This happens if the class cannot be accessed. Eg. If it was not declared public
      throw new ServletException("An exception occurred trying to instantiate " + handlerClassName, illegalEx);
    }
  }

}
