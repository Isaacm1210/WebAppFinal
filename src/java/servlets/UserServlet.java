/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author mhame
 */
public class UserServlet extends HttpServlet {

    


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        UserService us = new UserService();
        String action = request.getParameter("action");
        
        try {
            User user = us.getUser(email);
            session.setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (action != null && action.equals("close")) {
          session.setAttribute("view", "close");  
        }
        
        if(action != null && action.equals("viewInfo")){
            session.setAttribute("view", "view");
        }
        
        if(action != null && action.equals("edit")) {
            session.setAttribute("view", "edit");
        }
        
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response); 
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UserService us = new UserService();
        
        String email = (String) session.getAttribute("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        
        try {
            User user = us.getUser(email);
            
            if(action != null && action.equals("Edit Info")){
                
                if(firstName.equals("") || lastName.equals("") || password.equals("")){
                    session.setAttribute("editError", "All fields must be filled");
                }else{
                boolean active = user.getActive();
                Role role = user.getRole();
                
                us.updateUser(email, firstName, lastName, active, password, role);
                }
            } 
                
            if(action != null && action.equals("Deactivate")){
                boolean active = false;
                Role role = user.getRole();
                password = user.getPassword();
                
                us.updateUser(email, firstName, lastName, active, password, role);
                response.sendRedirect("Login");
            }
                
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
        if(action != "Deactivate"){
        try {
            User user = us.getUser(email);
            user = us.getUser(email);
            session.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response); 
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
