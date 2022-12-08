
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
    
    
        
}
