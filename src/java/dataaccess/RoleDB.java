
package dataaccess;

import javax.persistence.EntityManager;
import models.Role;

/**
 *
 * @author mhame
 */
public class RoleDB {
    
    public Role getRole(int roleID) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Role role = em.find(Role.class, roleID);
            return role;
        }
        finally{
            em.close();
        }
    }
}
