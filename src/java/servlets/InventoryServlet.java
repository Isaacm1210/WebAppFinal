
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
import models.Category;
import models.Item;
import models.User;
import services.CategoryService;
import services.ItemService;
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
            CategoryService cs = new CategoryService();
            
        try {
            User user = us.getUser(email);
            List<Item> items = user.getItemList();
            List<Category> categories = cs.getAll();
            
            session.setAttribute("user", user);
            session.setAttribute("items", items);
            session.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UserService us = new UserService();
        ItemService is = new ItemService();
        CategoryService cs = new CategoryService();
        
        String action = request.getParameter("action");
        
        String email = (String) session.getAttribute("email");
        List<Item> items = (List<Item>) session.getAttribute("items");
        
        int categoryID = Integer.parseInt(request.getParameter("category"));
        String itemName = request.getParameter("itemName");
        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
        int itemID = items.size() + 1;
        
        try {
            User user = us.getUser(email);
            
            if(action != null && action.equals("Add")){
            Category category = cs.getCategory(categoryID);
            
            is.addItem(itemID, itemName, itemPrice, category, user);

            }
          items = user.getItemList();
          session.setAttribute("items", items);
    
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        
        
        
        
        
        
    }



}
