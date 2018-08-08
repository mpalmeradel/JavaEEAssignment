/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tags;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import model.Book;
import model.CartItem;

/**
 * DisplayCart
 * 
 * Displays the cart in a table
 * 
 * @author Matt Palmer
 * 
 * 
 */
public class DisplayCart extends TagSupport {
    // Begin tag processing
    public int doStartTag() throws JspException
    {
        HttpSession session = pageContext.getSession();
        
        try {
            JspWriter out = pageContext.getOut();
            
            out.print("<table width=\"100%\" border=\"1\" cellspacing=\"0\" cellpadding=\"3\" height=\"98\">");
            out.print("<tr> ");
            out.print("<td width=\"13%\" bgcolor=\"#CCCCCC\"> ");
            out.print("<div align=\"center\"><font face=\"Arial, Helvetica, sans-serif\" size=\"3\">ISBN</font></div>");
            out.print("</td>");
            out.print("<td width=\"34%\" bgcolor=\"#CCCCCC\"> ");
            out.print("<div align=\"center\"><font face=\"Arial, Helvetica, sans-serif\" size=\"3\">Title</font></div>");
            out.print("</td>");
            out.print("<td width=\"13%\" bgcolor=\"#CCCCCC\"> ");
            out.print("<div align=\"center\"><font face=\"Arial, Helvetica, sans-serif\" size=\"3\">Price/unit</font></div>");
            out.print("</td>");
            out.print("<td width=\"9%\" bgcolor=\"#CCCCCC\"> ");
            out.print("<div align=\"center\"><font face=\"Arial, Helvetica, sans-serif\" size=\"3\">Quantity</font></div>");
            out.print("</td>");
            out.print("<td width=\"20%\" bgcolor=\"#CCCCCC\"> ");
            out.print("<div align=\"center\"><font face=\"Arial, Helvetica, sans-serif\" size=\"3\">Subtotal</font></div>");
            out.print("</td>");
            out.print("<td width=\"11%\" bgcolor=\"#CCCCCC\"> ");
            out.print("<div align=\"center\"><font face=\"Arial, Helvetica, sans-serif\" size=\"3\">Remove</font></div>");
            out.print("</td>");
            out.print("</tr>");
            
            
            Map items = (Map)session.getAttribute("cart");
            Set entries = items.entrySet();
            Iterator iter = entries.iterator();
            double totalCostOfOrder = 0.00;
            Book book = null;
            CartItem item = null;
            while(iter.hasNext()) {
                Map.Entry entry = (Map.Entry)iter.next();
                String isbn = (String)entry.getKey();
                item = (CartItem)entry.getValue();
                book = item.getBook();
                String title = book.getTitle();
                String price = book.getDollarPrice();
                Integer quantity = item.getQuantity();
                double cost =  item.getOrderCost();
                String dollarCost = item.getDollarOrderCost();
                totalCostOfOrder +=cost;
                
                out.print("<tr bgcolor=\"#F0F0F0\">");
                out.print("<td width=\"13%\" height=\"5\">" + isbn + "</td>");
                out.print("<td width=\"34%\" height=\"5\">" + title + "</td>");
                out.print("<td width=\"13%\" height=\"5\">" + price + "</td>");
                out.print("<td width=\"9%\" height=\"5\"> ");
                out.print("<input type=\"text\" name=" + isbn +
                        " size=\"2\" value=" +
                        quantity.toString() +  " maxlength=\"4\">");
                out.print("</td>");
                out.print("<td width=\"20%\" height=\"5\">");
                out.print("<div align=\"right\">" + dollarCost + "</div>");
                out.print("</td>");
                out.print("<td width=\"11%\" height=\"5\">");
                out.print("<div align=\"center\">");
                out.print("<input type=\"checkbox\" name=\"remove\" value=" +  isbn + ">");
                out.print("</div>");
                out.print("</td>");
                out.print("</tr>");
                            
                } // end while  
            DecimalFormat dollars = new DecimalFormat("0.00");
            String totalOrderInDollars =("ORDER TOTAL   " +"$" + dollars.format (totalCostOfOrder));   
        
            out.print("<tr bgcolor=\"#E4E4E4\">");
            out.print("<td width=\"13%\" height=\"2\" bordercolor=\"#CCCCCC\">");
            out.print("<input type=\"submit\" name=\"Submit\" value=\"Update Cart\">");
            out.print("</td>");
            out.print("<td width=\"34%\" height=\"2\" bordercolor=\"#CCCCCC\">&nbsp; </td>");
            out.print("<td width=\"13%\" height=\"2\" bordercolor=\"#CCCCCC\">&nbsp; </td>");
            out.print("<td width=\"9%\" height=\"2\" bordercolor=\"#CCCCCC\">&nbsp; </td>");
            out.print("<td width=\"20%\" height=\"2\" bordercolor=\"#CCCCCC\">");
            out.print("<div align=\"right\"><b>" + totalOrderInDollars + "</b></div>");
            out.print("</td>");
            out.print("<td width=\"11%\" height=\"2\" bordercolor=\"#CCCCCC\">&nbsp;</td>");
            out.print("</tr>");
            
            out.print("</table>");
        
        }
        catch(IOException e) {
            throw new JspException(e.getMessage() );
        }
        
        return SKIP_BODY;
    }
}
