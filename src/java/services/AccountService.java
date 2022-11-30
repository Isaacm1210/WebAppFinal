
package services;

import dataaccess.UserDB;
import models.User;

public class AccountService {
    
    public User login(String email, String password){
        UserDB userDB = new UserDB();
        
        try{
            User user = userDB.getUser(email);
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        catch(Exception e){
            
        }
        return null;
    }
}
