/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import dispatchers.AddToCartDispatcher;
import dispatchers.CheckoutDispatcher;
import dispatchers.ContinueDispatcher;
import dispatchers.HomeDispatcher;
import dispatchers.IDispatcher;
import dispatchers.TitleDispatcher;
import dispatchers.UpdateCartDispatcher;
import dispatchers.ViewCartDispatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;
import model.SessionObjects;

/**
 *
 * @author mpalm
 */
@Stateless
public class EBJ_SessionBean implements EBJ_SessionBeanRemote {
    @PersistenceContext(unitName = "BookShopEJBModulePU")
    private EntityManager em;

    private final HashMap actions = new HashMap();
    
    @Override
    public Object action(Object sessionObjects, String actionKey) {
        
        SessionObjects s = (SessionObjects) sessionObjects;
        
        actions.put("titles", new TitleDispatcher());
        actions.put("add_to_cart", new AddToCartDispatcher());
        actions.put("checkout", new CheckoutDispatcher());
        actions.put("continue", new ContinueDispatcher());
        actions.put("home", new HomeDispatcher());
        actions.put("update_cart", new UpdateCartDispatcher());
        actions.put("view_cart", new ViewCartDispatcher());
        
        IDispatcher dispatcher = (IDispatcher) actions.get(actionKey);
        
        return dispatcher.execute(s, em);
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
