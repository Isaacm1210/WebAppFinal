
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
import services.AccountService;
import services.RoleService;
import services.UserService;


public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            session.invalidate();
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            UserService us = new UserService();
            RoleService rs = new RoleService();
            
            if(action.equals("Register")){
                session.setAttribute("enter", "register");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            
            if(action.equals("registerUser")){
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                int roleID = 2;
                
                if(email.equals("") || firstName.equals("") || lastName.equals("") || password.equals("")){
                    request.setAttribute("message", "Please fill out all Fields");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
                else{
                  try {
                    Role role = rs.getRole(roleID);
                    us.addUser(email, firstName, lastName, password, role);
                } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }  
                  request.setAttribute("message", "You have been successfully been Registered. Please login");
                  session.setAttribute("enter", "login");
                  getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }

            }
            
            if(action.equals("Login")){
                session.setAttribute("enter", "login");
                
                AccountService as = new AccountService();
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                
                User user = as.login(email, password);
                
                if(user == null){
                    request.setAttribute("message", "Password/User-name is incorrect");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
                else if(user.getActive() == false){
                    request.setAttribute("message", "Your account has been Deactivated");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
                else{
                    session.setAttribute("email", email);
                    response.sendRedirect("user");
                }
            }    
        }
    }
