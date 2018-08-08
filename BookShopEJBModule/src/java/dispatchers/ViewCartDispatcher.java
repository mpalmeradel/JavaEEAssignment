/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dispatchers;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SessionObjects;

/**
 * ViewCartDispatcher
 * 
 * @author Matt Palmer
 * 
 * 
 */
public class ViewCartDispatcher implements IDispatcher {

    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em) {
        sessionObjects.setNextPage("/jsp/cart.jsp");
        Map cart = sessionObjects.getCart();
        if(cart == null) {
             sessionObjects.setNextPage("/jsp/titles.jsp");
        }
        return sessionObjects;
    }
    
}
