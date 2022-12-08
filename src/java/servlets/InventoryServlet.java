
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
            ItemService is = new ItemService();
            String action = request.getParameter("action");
            
        try {
            
        
            if(action != null && action.equals("Edit")){
                session.setAttribute("itemEdit", "true");
                
                int editCatID = Integer.parseInt(request.getParameter("itemCat"));
                Category editCat = cs.getCategory(editCatID);
 

                String itemID = request.getParameter("itemID");
                String itemN = request.getParameter("itemN");
                double itemP = Double.parseDouble(request.getParameter("itemP"));
                
                request.setAttribute("itemPrice", itemP);
                request.setAttribute("editCat", editCat);
                request.setAttribute("editItemName", itemN);
                request.setAttribute("editItemID", itemID);
            }
            
            if(action != null && action.equals("cancel")){
                session.setAttribute("itemEdit", "false");
            }
  
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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

        String itemName = request.getParameter("itemName");
        int itemID = items.size() + 1;

        try {
            User user = us.getUser(email);
            
            switch (action){
                case "Delete":
                    itemID = Integer.parseInt(request.getParameter("deleteItemID"));
                    is.deleteItem(itemID);
                    break;
                    
                    
                case "Add":
                    if(itemName.equals("") || itemName == null){
                        request.setAttribute("message", "All Fields Must be filled.");
                        
                    }else{
                        int categoryID = Integer.parseInt(request.getParameter("category"));
                        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
                        Category category = cs.getCategory(categoryID);
                        is.addItem(itemID, itemName, itemPrice, category, user);
                    }
                    break;
                    
                    
                case "Save":
                    if(itemName.equals("") || itemName == null){
                        request.setAttribute("message", "All Fields Must be filled.");
                        
                    }else{
                        int categoryID = Integer.parseInt(request.getParameter("category"));
                        double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
                        itemID = Integer.parseInt(request.getParameter("editItemID"));
                        int OcatID = Integer.parseInt(request.getParameter("oCatID"));
                    
                        Category category = cs.getCategory(categoryID);
                        Category origCategory = cs.getCategory(OcatID);

                        is.updateItem(itemID, itemName, itemPrice, category, user);

                        Item item = is.getItem(itemID);
                        if(category != origCategory){

                            origCategory.getItemList().remove(item);
                            category.getItemList().add(item);
                        }
                    }
            }

        }catch (NumberFormatException ex){
            request.setAttribute("message", "All Fields Must be filled.");
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "Error");
        }
        
        
        
        try {
            session.setAttribute("itemEdit", "false");
            User user = us.getUser(email);
            items = user.getItemList();
            session.setAttribute("items", items);
            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);       
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
