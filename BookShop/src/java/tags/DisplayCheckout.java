/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tags;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import model.Book;
import model.CartItem;

/**
 * DisplayCheckout
 * 
 * Displays items to be checked out in a table
 * 
 * 
 * @author Matt Palmer
 * 
 * 
 */
public class DisplayCheckout extends TagSupport {
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        
        try {
            JspWriter out = pageContext.getOut();
            
            Map items = (Map)session.getAttribute("cart");
            Set entries = items.entrySet();
            Iterator iter = entries.iterator();
            double totalCostOfOrder = 0.00;
            Book book = null;
            CartItem item = null;

            while(iter.hasNext()) {
                Map.Entry entry = (Map.Entry)iter.next();
                item = (CartItem)entry.getValue();
                double cost = item.getOrderCost();
                totalCostOfOrder +=cost;
                
                out.print("<tr>");
                out.print("<td bgcolor=\"#F0F0F0\"> " + item + "</td>");
                out.print("</tr>");
                
                } // end while
            DecimalFormat dollars = new DecimalFormat("0.00");
            String totalOrderInDollars =(dollars.format (totalCostOfOrder));
            
            // places total in session
            session.setAttribute("totalOrderInDollars", totalOrderInDollars.toString());
        }
        catch (IOException e) {
            throw new JspException(e.getMessage() );
        }

        return SKIP_BODY;
    }
}
