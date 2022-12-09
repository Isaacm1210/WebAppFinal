
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author mhame
 */
public class ItemDB{
    
    public List<Item> getAll(String owner) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            User user = em.find(User.class, owner);
            return user.getItemList();
        }
        finally{
            em.close();
        }
        
    }
        
    public Item getItem(int itemID) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Item item = em.find(Item.class, itemID);
            return item;  
        }
        finally{
            em.close();
        }
        
    }
    
    public void addItem(Item item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User user = item.getOwner();
            user.getItemList().add(item);
            Category category = item.getCategory();
            category.getItemList().add(item);
            trans.begin();
            em.persist(item);
            em.merge(user);
            em.merge(category);
            trans.commit();     
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void deleteItem(Item item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User owner = item.getOwner();
            owner.getItemList().remove(item);
            
            Category category = item.getCategory();
            category.getItemList().remove(item);
            
            trans.begin();
            em.remove(em.merge(item));
            em.merge(owner);
            em.merge(category);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void updateItem(Item item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(item);
            trans.commit();     
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
            
        
    }
    
    public void deleteAll(List<Item> items, User owner){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            for(int i = 0; !items.isEmpty(); i++ ){
                Item item = items.get(i);
                
                owner.getItemList().remove(item);
                
                Category category = item.getCategory();
                category.getItemList().remove(item);
            
                trans.begin();
                em.remove(em.merge(item));
                em.merge(owner);
                em.merge(category);
                trans.commit();
            }
            
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
}
