
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Item;
import models.User;
import services.UserService;

/**
 *
 * @author mhame
 */
public class InventoryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            UserService us = new UserService();
            
        try {
            User user = us.getUser(email);
            List<Item> items = user.getItemList();
            
            session.setAttribute("items", items);
            session.setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }



}
