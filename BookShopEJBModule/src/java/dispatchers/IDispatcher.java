/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dispatchers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SessionObjects;


/**
 * IDispatcher
 * 
 * Simple interface for dispatchers
 * @author Matt Palmer
 * 
 * 
 */
public interface IDispatcher {
    public SessionObjects execute(SessionObjects sessionObjects, EntityManager em);
    
}
