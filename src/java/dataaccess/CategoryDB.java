
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Category;

/**
 *
 * @author mhame
 */
public class CategoryDB {
    
    public List<Category> getAll(){
        return null;
    }
    
    public Category getCatecory(int category_ID) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
        Category category = em.find(Category.class, category_ID);
        return category;
        }
        finally {
            em.close();
        }
    }
    
    public void addCategory(){
    
    }
    
    public void updateCategory(){
        
    }
    
}
