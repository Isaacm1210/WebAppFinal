
package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * @author mhame
 */
public class CategoryService {
    
    public List<Category> getAll(){
        CategoryDB catDB = new CategoryDB();
        
        List<Category> categories = catDB.getAll();
        return categories;
    }
    
    public Category getCategory(int categoryID) throws Exception{
        CategoryDB catDB = new CategoryDB();
        Category category = catDB.getCatecory(categoryID);
        
        return category;
    }
    
    public void addCategory(int categoryID, String categoryName)throws Exception{
        CategoryDB catDB = new CategoryDB();
        Category category = new Category(categoryID, categoryName);
        
        catDB.addCategory(category);
        
    }
    
    public void updateCategory(int categoryID, String categoryName) throws Exception{
        CategoryDB catDB = new CategoryDB();
        Category category = catDB.getCatecory(categoryID);
        
        category.setCategoryName(categoryName);
        
        catDB.updateCategory(category);
    }
    
    
        
}
