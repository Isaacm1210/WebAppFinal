
package services;

import dataaccess.ItemDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author mhame
 */
public class ItemService {
    
    public List<Item> getAllItems(){
        return null;
    }
    
    public Item getItem(int itemID){
        return null;
    }
    
    public void addItem(int itemID, String itemName, double price, Category category, User owner) throws Exception{
        ItemDB itemDB = new ItemDB();
        Item item = new Item(itemID, itemName, price);
        item.setCategory(category);
        item.setOwner(owner);
        
        itemDB.addItem(item);
    }
    
    public void deleteItem(int itemID){
        
    }
    
    public void updateItem(int itemID, String itemName, double price, Category category, User owner){
        
    }
            
            
}
