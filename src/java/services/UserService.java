
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
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public User getUser(String email)throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
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
    
    public void updateUser(String email, String firstName, String lastName, boolean active, String password, Role role)throws Exception{
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setActive(active);
        user.setRole(role);
        
        userDB.updateUser(user);
    }
    
    
}
