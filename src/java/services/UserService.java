
package services;

import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

/**
 *
 * @author mhame
 */
public class UserService {
    
    public List<User> getAllUsers() throws Exception{
        
        return null;
    }
    
    public User getUser()throws Exception{
        
        return null;
    }
    
    public void addUser(String email, String firstName, String lastName, String password, Role role)throws Exception{
        UserDB userDB = new UserDB();
        Boolean active = true;
        User user = new User(email, active, firstName, lastName, password);
        user.setRole(role);
        userDB.addUser(user);
        
    }
    
    public void deleteUser()throws Exception{
        
    }
    
    public void updateUser()throws Exception{
        
    }
    
    
}
