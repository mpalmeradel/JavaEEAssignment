/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mpalm
 */
public class SessionObjects implements Serializable {
    
    private String          nextPage;
    
    private Map             cart;
    private String[]        selectedBooks;
    private List            books;
    private String[]        remove;
    
    private HashMap         isbnQuantity;
    
    // all args constructor
    public SessionObjects(
            String nextPage,
            Map cart,
            String[] selectedBooks,
            List books,
            String[] remove,
            HashMap isbnQuantity
    )    
    {
        this.nextPage = nextPage;
        this.cart = cart;
        this.selectedBooks = selectedBooks;
        this.books = books;
        this.remove = remove;
        this.isbnQuantity = isbnQuantity;
    }
    
    // getters and setters
    
    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Map getCart() {
        return cart;
    }

    public void setCart(Map cart) {
        this.cart = cart;
    }

    public String[] getSelectedBooks() {
        return selectedBooks;
    }

    public void setSelectedBooks(String[] selectedBooks) {
        this.selectedBooks = selectedBooks;
    }

    public List getBooks() {
        return books;
    }

    public void setBooks(List books) {
        this.books = books;
    }

    public String[] getRemove() {
        return remove;
    }

    public void setRemove(String[] remove) {
        this.remove = remove;
    }

    public HashMap getIsbnQuantity() {
        return isbnQuantity;
    }

    public void setIsbnQuantity(HashMap isbnQuantity) {
        this.isbnQuantity = isbnQuantity;
    }

    
}
