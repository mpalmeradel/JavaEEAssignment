/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import model.SessionObjects;

/**
 *  
 * AddToCartDispatcher
 * 
 * This dispatcher adds an item(s) to cart from a list of selected books.
 * 
 *
 * @author Matt Palmer 
 */
public class AddToCartDispatcher implements IDispatcher {

    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em) {
        sessionObjects.setNextPage("/jsp/titles.jsp");
        Map cart = sessionObjects.getCart();
        String[] selectedBooks = sessionObjects.getSelectedBooks();

        if(cart == null) {
            cart = new HashMap();
            for(int i=0; i<selectedBooks.length; i++ ) {
                String isbn = selectedBooks[i];
                int quantity = (Integer)sessionObjects.getIsbnQuantity().get(isbn);
                Book book = this.getBookFromList(isbn, sessionObjects);
                CartItem item = new CartItem(book);
                item.setQuantity (quantity);
                cart.put(isbn,item);
            }  // end for
            sessionObjects.setCart(cart);
        }   // end if
        else {
            for(int i=0; i<selectedBooks.length; i++ ) {
                String isbn = selectedBooks[i];
                int quantity = (Integer)sessionObjects.getIsbnQuantity().get(isbn);
                
                if(cart.containsKey(isbn)) {
                    CartItem item = (CartItem)cart.get(isbn);
                    item.setQuantity (quantity);
                }    // end if
                else{
                    Book book = this.getBookFromList(isbn, sessionObjects);
                    CartItem item = new CartItem(book);
                    item.setQuantity (quantity);
                    cart.put(isbn,item);
                } // end else
            } // end for
        } //end else
        
        return sessionObjects;
    }
    
    private Book getBookFromList(String isbn, SessionObjects s) {
    List list = (List)s.getBooks();
    Book aBook = null;
    for(int i=0; i<list.size(); i++) {
      aBook = (Book)list.get(i);
      if(isbn.equals(aBook.getIsbn())){
        break;
      } // end if
    } // end for
    return aBook;
    } // end getBookFromList
    
    
    
}
