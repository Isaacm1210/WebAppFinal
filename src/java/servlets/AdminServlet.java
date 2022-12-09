
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
import models.Role;
import models.User;
import services.CategoryService;
import services.RoleService;
import services.UserService;


public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        CategoryService cs = new CategoryService();
        
        String adminEmail = (String) session.getAttribute("email");
        String action = request.getParameter("action");
        
        try{
             if(action != null && action.equals("allAccounts")){
                session.setAttribute("viewAcc", "true");

             }
                
             if(action != null && action.equals("allCategories")){
                session.setAttribute("viewCat", "true");
                
             }
                
             if(action != null && action.equals("closeAcc")){
                session.setAttribute("viewAcc", "fasle");
                
             }
                
             if(action != null && action.equals("closeCat")){
                session.setAttribute("viewCat", "fasle");
                
             }
             
             
        }catch(Exception ex){
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        try {
            List<Category> catList = cs.getAll();
            session.setAttribute("catList", catList);
                
            List<User> userList = us.getAllUsers();
            session.setAttribute("userList", userList);
            
            User user = us.getUser(adminEmail);
            session.setAttribute("admin", user);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService us = new UserService();
        CategoryService cs = new CategoryService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        
        
        
        try {
            
            List<Category> catList = cs.getAll();

            if(action != null && action.equals("addUser")){
                String addEmail = request.getParameter("addEmail");
                String addFname = request.getParameter("firstName");
                String addLname = request.getParameter("lastName");
                String addPassword = request.getParameter("password");
                int roleID = Integer.parseInt(request.getParameter("roleId"));
                
                if(addEmail.equals("") || addFname.equals("") || addLname.equals("") || addPassword.equals("")){
                  
                }else{
                    
                    Role role = rs.getRole(roleID);
                    us.addUser(addEmail, addFname, addLname, addPassword, role);
                }
            }
                    
            if(action != null && action.equals("addCat")){   
                String catName = request.getParameter("catName");
                int catId = catList.size() + 1;
                
                cs.addCategory(catId, catName);
            }
          
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            List<User> userList = us.getAllUsers();
            List<Category> catList = cs.getAll();
            session.setAttribute("catList", catList);
            session.setAttribute("userList", userList);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }




}
