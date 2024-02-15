
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;

/**
 *
 * @author Isaac Mhamed
 */
public class CategoryDB {
    
    public List<Category> getAll(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;
        }
        finally{
        em.close();
        }
        
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
    
    public void addCategory(Category category){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.persist(category);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
        
    }
    
    public void updateCategory(Category category){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(category);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
}
