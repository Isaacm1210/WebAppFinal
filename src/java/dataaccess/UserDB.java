
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.Role;
import models.User;

/**
 *
 * @author mhamed
 */
public class UserDB {
    
    public List<User> getAll() throws Exception{ //gets all users
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
        return users;
        }
        finally{
            em.close();
        }
    }
    
    
    public User getUser(String email) throws Exception{ //gets ONE user based on Primary Key "email"
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, email);
            return user;
        }
        finally{
            em.close();
        }

    }
    
    
    public void addUser(User user) throws Exception{ //Adds a user to the Data Base
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            Role role = user.getRole();
            role.getUserList().add(user);
            trans.begin();
            em.persist(user);
            em.merge(role);
            trans.commit();
        }
        catch(Exception e){
            trans.rollback();
        }
        finally{
            em.close();
        }
    }
    
    
    public void deleteUser(User user) throws Exception{ //Removes a User from the data base
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        ItemDB itemDB = new ItemDB();
        
        try{
            List<Item> itemList = user.getItemList();
            itemDB.deleteAll(itemList, user);
            
            Role role = user.getRole();
            role.getUserList().remove(user);
            
            trans.begin();
            em.remove(em.merge(user));
            em.merge(role);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
            
    
    
    public void updateUser(User user) throws Exception{ //Updates Information of a User
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(user);
            trans.commit();
        }
        catch(Exception ex){
            trans.rollback();
        }
        finally{
            em.close();
        }
    }
    
}
