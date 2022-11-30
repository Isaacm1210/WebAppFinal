
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.User;

/**
 *
 * @author mhame
 */
public class UserDB {
    
    public List<User> getAll() throws Exception{
        return null;
    }
    
    public User getUser(String email) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, email);
            return user;
        }
        finally{
            em.close();
        }

    }
    
    public void addUser() throws Exception{
        
    }
    
    public void deleteUser() throws Exception{
        
    }
    
    public void updateUser() throws Exception{
        
    }
}
