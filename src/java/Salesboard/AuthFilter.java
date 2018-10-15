/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salesboard;

import java.io.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 * @author kris
 */
public class AuthFilter implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            String sessionUser = (String)session.getAttribute("sessionuser");
            System.out.println("Sessionuser is " + sessionUser);
            String requestUser = (String)req.getParameter("username");
            System.out.println("Requested user is: " + requestUser);
            System.out.println("Filter: checking sessionuser");
           if (sessionUser.equals(requestUser) == false){
               System.out.println("Filter: not logged on as this user: ");
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
           }
        } catch (Exception e) {
                System.out.println(e.getMessage()); 
        }
   //This line passes the request to the next filter in the chain (or the destination servlet if this is the last filter in the chain)
        chain.doFilter(request, response);

   //This line will be run after the servlet has been run
   //System.out.println("Post-processing");
    }

//    /**
//     * Destroy method for this filter
//     */
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
