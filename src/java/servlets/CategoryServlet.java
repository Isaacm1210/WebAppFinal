
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
import services.UserService;


public class CategoryServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        CategoryService cs = new CategoryService();
        
        String adminEmail = (String) session.getAttribute("email");
        int catID = Integer.parseInt(request.getParameter("catID"));
        
        try {
            User admin = us.getUser(adminEmail);
            session.setAttribute("admin", admin);
            
            Category category = cs.getCategory(catID);
            session.setAttribute("manageCat", category);
            
            List<Item> catItemList = category.getItemList();
            session.setAttribute("catItemList", catItemList);
            
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CategoryService cs = new CategoryService();
        
        String newCatName = request.getParameter("newCatName");
        int catID = Integer.parseInt(request.getParameter("catID"));
        String action = request.getParameter("action");
        
        try {
            if(action != null && action.equals("Save")){
                
            
                cs.updateCategory(catID, newCatName);
            }
        } catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        try {
            
            Category category = cs.getCategory(catID);
            session.setAttribute("manageCat", category);
            
            getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
        }catch (Exception ex) {
                Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


}
