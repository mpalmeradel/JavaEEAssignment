/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dispatchers;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SessionObjects;

/**
 * CheckOutDispatcher
 * 
 * In its current form, simply indicates that then next page is to be
 * the checkout page
 * 
 * @author Matt Palmer
 * 
 */
public class CheckoutDispatcher implements IDispatcher {

    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em) {
        String nextPage = "/jsp/checkout.jsp";
        sessionObjects.setNextPage(nextPage);
        return sessionObjects;
    }
    
}
