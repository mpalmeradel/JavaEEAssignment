/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dispatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Book;
import model.SessionObjects;
import model.Tbooks;

/**
 * TitleDispatcher
 * @author Matt Palmer
 * 
 * 
 */
public class TitleDispatcher implements IDispatcher {

    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em) {

        List<Tbooks> tbooks = em.createQuery(
            "SELECT b FROM Tbooks b").getResultList();
        
        List<Book> books = new ArrayList<Book>();
        for(Tbooks b : tbooks ) {
            books.add(new Book(b.getIsbn(), b.getTitle(), b.getAuthor(), b.getPrice())); 
        }
        
        sessionObjects.setNextPage("/jsp/titles.jsp");
        sessionObjects.setBooks(books);
        
        return sessionObjects;
    }

   
    
}
