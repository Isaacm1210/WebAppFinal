
package services;

import dataaccess.RoleDB;
import models.Role;

/**
 *
 * @author mhame
 */
public class RoleService {
    
    public Role getRole(int roleID) throws Exception{
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(roleID);
        
        return role;
    }
    
}
