package controller;

import business.EBJ_SessionBeanRemote;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.json.Json;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Book;
import model.CartItem;
import model.SessionObjects;


import utility.AdmitBookStoreDAO;


public class FrontController extends HttpServlet {
    @PersistenceContext(unitName = "BookShopPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
  
    InitialContext ctx;
    EBJ_SessionBeanRemote br;
    

  //Initialize global variables
  public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config);
            
            ctx = new InitialContext();
            br = (EBJ_SessionBeanRemote)ctx.lookup("ActionBean");
            
        } catch (NamingException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

   //Process the HTTP Get request
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.err.println("doGet()");
    doPost(request,response);

  }

  //Process the HTTP Post request
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setContentType("text/html");
     String next_view = "no view ";
     String requestedAction = request.getParameter("action");
     if (requestedAction == null)
         requestedAction = "titles";
     
     SessionObjects s = this.storeSessionObjectsInNewObject(request);
     s = (SessionObjects)br.action(s, requestedAction);
     next_view = this.retrieveSessionObjectsFromObject(s, request);
     
     dispatch(request, response, next_view);
     
  }
  private void dispatch(HttpServletRequest request, HttpServletResponse response,String page)throws ServletException,
      IOException {
     RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page) ;
     dispatcher.forward (request, response);
  }

  //Get Servlet information
  public String getServletInfo() {
    return "controller.FrontController Information";
  }

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    // Matt Palmer's additional methods for merit component
    
    // Store session objects into a 'SessionObjects' object (that's a mouthful)
    
    SessionObjects storeSessionObjectsInNewObject(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        
        String nextPage = "";
        Map cart = (Map)session.getAttribute("cart");
        String[] selectedBooks = request.getParameterValues("add");
        List books = (List)session.getAttribute("books");
        String[] remove = request.getParameterValues("remove");
        HashMap iq = new HashMap();
        
        if (books != null)
            for(Object o : books) {
                Book b = (Book)o;
                if(request.getParameter(b.getIsbn()) != null) {
                    iq.put(b.getIsbn(), Integer.parseInt(request.getParameter(b.getIsbn())));
                }
            }
        
        return new SessionObjects(nextPage, cart, selectedBooks, books, remove, iq);
        
    }
    
    // Retrieve session objects from a 'SessionObjects' and place into session
    // returns a string containing the next page name
    String retrieveSessionObjectsFromObject(SessionObjects sessionObjects, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        
        session.setAttribute("cart", sessionObjects.getCart());
        session.setAttribute("books", sessionObjects.getBooks());
        
        return sessionObjects.getNextPage();
    }
  
}
