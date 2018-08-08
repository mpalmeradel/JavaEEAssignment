/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;


import model.Book;
import model.CartItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mpalm
 */
public class BookStoreJUnitTests {
    
    Book book;
    CartItem cartItem;
    
    public BookStoreJUnitTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        book = new Book();
        cartItem = new CartItem();
        
        book.setPrice(10.0d);
        
        cartItem.setBook(book);
        cartItem.setQuantity(2);
        
        
    }
    
    @After
    public void tearDown() {
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testingGetPrice() {
        //assertEquals(i, 2);
        
        assertEquals(10.0d, book.getPrice(), 0.01);
    };
    
    @Test
    public void testGetDollarPrice() {
        assertTrue(book.getDollarPrice().equals("$10.00"));
    }
    
    @Test
    public void testGetQuantity() {
        assertEquals(2, cartItem.getQuantity());
    }
    
    @Test
    public void testGetDollarOrderCost() {
        assertEquals("$20.00", cartItem.getDollarOrderCost());
    }
    
    
}
