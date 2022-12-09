
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
import services.RoleService;
import services.UserService;


public class AccountsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        
        String adminEmail = (String) session.getAttribute("email");
        String manageAccEmail = request.getParameter("accEmail");
        String action = request.getParameter("manageAcc");
        
        
        try {
            User admin = us.getUser(adminEmail);
            User manageUser = us.getUser(manageAccEmail);
            
            session.setAttribute("admin", admin);
            session.setAttribute("manageUser", manageUser);
            
            if(action != null && action.equals("manageAcc")){
            
                
                
            } 
        } catch (Exception ex) {
            Logger.getLogger(AccountsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        RoleService rs = new RoleService();
        
        String email = request.getParameter("accEmail");
        
        User user = (User) session.getAttribute("manageUser");
        String action = request.getParameter("action");
        
        try {
            if(action != null && action.equals("save")){
                String firstName = request.getParameter("accFirst");
                String lastName = request.getParameter("accLast");
                String password = request.getParameter("accPassword");
                int roleId = Integer.parseInt(request.getParameter("accRoleID"));
                boolean active = user.getActive();

                Role role = rs.getRole(roleId);
                us.updateUser(email, firstName, lastName, active, password, role);
            }
            
            if(action != null && action.equals("deactivate")){
                boolean active = false;
                us.updateUser(email, user.getFirstName(), user.getLastName(), active, user.getPassword(), user.getRole());
            }
            
            if(action != null && action.equals("reactivate")){
                boolean active = true;
                us.updateUser(email, user.getFirstName(), user.getLastName(), active, user.getPassword(), user.getRole());
            }
            
        } catch (Exception ex) {
                Logger.getLogger(AccountsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            if(action != null && action.equals("deleteUser")){
                us.deleteUser(email);
                response.sendRedirect("admin");
            }
            else{
            user = us.getUser(email);
            session.setAttribute("manageUser", user);
            getServletContext().getRequestDispatcher("/WEB-INF/accounts.jsp").forward(request, response);
            }
        } catch (Exception ex) {
                Logger.getLogger(AccountsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
