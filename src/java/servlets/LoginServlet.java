
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
        
        
        
        
    }


}
