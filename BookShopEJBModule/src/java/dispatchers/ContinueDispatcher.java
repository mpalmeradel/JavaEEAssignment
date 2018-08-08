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
 * ContinueDispatcher
 * 
 * Sets next page to titles.jsp
 * 
 * @author Matt Palmer
 */
public class ContinueDispatcher implements IDispatcher {

    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em) {
        sessionObjects.setNextPage("/jsp/titles.jsp");
        return sessionObjects;
    }
    
}
