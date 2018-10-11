/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Salesboard;

import javax.servlet.http.*;

/**
 *
 * @author kris
 */
public interface Handler {
     //Runs the business logic for the request and returns the view to display
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
