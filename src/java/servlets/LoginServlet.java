
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            
            session.setAttribute("enter", null);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
        
            if(action.equals("Register")){
                session.setAttribute("enter", "register");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
                else{
                    getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                }

            }    
        }
    }
