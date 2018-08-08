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
 * DisplayCartSmall
 * 
 * Displays the cart in a small table
 * 
 * @author Matt Palmer
 * 
 * 
 */
public class DisplayCartSmall extends TagSupport {
    
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
    
        try {
            JspWriter out = pageContext.getOut();
    
            out.print("<table width=\"38%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\" height=\"53\" align=\"left\">");
            out.print("<tr> ");
            out.print("<td bgcolor=\"#E2E2E2\"> ");
            out.print("<div align=\"left\"><font face=\"Arial, Helvetica," + 
                    "sans-serif\"><font face=\"Times New Roman, Times, serif\" " + 
                    " size=\"3\"><b><font face=\"Arial, Helvetica, sans-serif\" " + 
                    "size=\"2\">Items \n in your Shopping Cart</font></b>" +
                    "</font></font></div>");
            out.print("</td>");
            out.print("</tr>");
            
            Map items = (Map)session.getAttribute("cart");
            if(items !=null) {
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
                out.print("<td height=\"13\" bgcolor=\"#E8FFE8\">" + item + "</td>");
                out.print("</tr>");
                
                } // end while
                DecimalFormat dollars = new DecimalFormat("0.00");
                String totalOrderInDollars =(dollars.format (totalCostOfOrder));
                
                out.print("<tr>");
                out.print("<td height=\"13\" bgcolor=\"#CCCCCC\">");
                out.print("<div align=\"right\"><font face=\"Times New Roman,"
                        + "Times, serif\"><b><font size=\"2\" face=\"Arial," + 
                        "Helvetica, sans-serif\">Order \n" +
                        "Total: " + totalOrderInDollars + "</font></b></font></div>");
                out.print("</td>");
                out.print("</tr>");
                
               }// end if
            else {
                out.print("<tr>");
                out.print("<td height=\"13\">No Items in Cart</td>");
                out.print("</tr>");
            }
            out.print("</table>");
            
        } catch (IOException e) {
            throw new JspException(e.getMessage() );
        }
        
        return SKIP_BODY;
    }
}
