/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dispatchers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import model.SessionObjects;

/**
 * UpdateCartDispatcher
 * 
 * Performs updates on cart
 * @author Matt Palmer
 * 
 * 
 */
public class UpdateCartDispatcher implements IDispatcher {
    

    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em) {
        
        
        Map cart = null;
        Book book = null;
        CartItem item = null;
        String isbn= null;
        
        sessionObjects.setNextPage("/jsp/cart.jsp");
        cart = sessionObjects.getCart();
        
        // remove selected cart items
        String[] booksToRemove =  sessionObjects.getRemove();
        if(booksToRemove !=null){
            for(int i=0; i<booksToRemove.length; i++) {
                cart.remove(booksToRemove[i]);
            }
        }
            
        Set entries = cart.entrySet();
        Iterator iter = entries.iterator();
        while(iter.hasNext()) {
		  Map.Entry entry = (Map.Entry)iter.next();
		  isbn = (String)entry.getKey();
		  item = (CartItem)entry.getValue();
                  
                  int quantity = (Integer)sessionObjects.getIsbnQuantity().get(isbn);
                  
                  item.updateQuantity(quantity);
        }
        return sessionObjects;   }
    
}
