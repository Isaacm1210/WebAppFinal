
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
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
    
    public void addItem() throws Exception{
        
    }
    
    public void deleteItem() throws Exception{
        
    }
    
    public void updateItem() throws Exception{
        
    }
}
