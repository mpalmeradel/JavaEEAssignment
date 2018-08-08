/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tags;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import model.Book;
import model.CartItem;

/**
 * BookSelection
 * 
 * Tag displaying selected books in a table
 * 
 * @author Matt Palmer
 * 
 * 
 */
public class BookSelection extends TagSupport {
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        try {
            JspWriter out = pageContext.getOut();
            
            out.print("<table width=\"93%\" border=\"2\" cellspacing=\"0\" cellpadding=\"1\" bordercolor=\"#FFFFFF\">");
            out.print("<tr bgcolor=\"#CCCCCC\">");
            out.print("<td width=\"10%\"><b>ISBN</b></td>");
            out.print("<td width=\"37%\"><b>Title</b></td>");
            out.print("<td width=\"24%\"><b>Author</b></td>");
            out.print("<td width=\"13%\"><b>Price</b></td>");
            out.print("<td width=\"10%\"><b>Quantity</b></td>");
            out.print("<td width=\"6%\"> ");
            out.print("<div align=\"left\"><b>Add</b></div>");
            out.print("</td>");
            out.print("</tr>");
            
            List books = (List)session.getAttribute("books");
            Iterator iter = books.iterator();
            while(iter.hasNext() ) {

                Book book  = (Book) iter.next();
                String isbn = book.getIsbn();
        	String title = book.getTitle();
                String author = book.getAuthor();
                String price = book.getDollarPrice();
                
                out.print("<tr bgcolor=\"#F4F4F4\">");
                out.print("<td width=\"10%\">" + isbn + "</td>");
                out.print("<td width=\"37%\">" + title + "</td>");
                out.print("<td width=\"24%\">" + author + "</td>");
                out.print("<td width=\"13%\">" + price + "</td>");
                out.print("<td width=\"10%\">");
                out.print("<select name=" + isbn + " size=\"1\">");
                out.print("<option value=\"1\">1</option>");
                out.print("<option value=\"2\">2</option>");
                out.print("<option value=\"3\">3</option>");
                out.print("</select>");
                out.print("</td>");
                out.print("<td width=\"6%\"> ");
                out.print("<div align=\"center\">");
                out.print("<input type=\"checkbox\" name=\"add\" value=" + isbn + ">");
                out.print("</div>");
                out.print("</td>");
                out.print("</tr>");
            }
            
            out.print("<tr> ");
            out.print("<td width=\"10%\">");
            out.print("<input type=\"submit\" name=\"Details\" value=\"Add to Cart\">");
            out.print("</td>");
            out.print("<td width=\"37%\">&nbsp;</td>");
            out.print("<td width=\"24%\">&nbsp;</td>");
            out.print("<td width=\"13%\">&nbsp;</td>");
            out.print("<td width=\"10%\">&nbsp;</td>");
            out.print("<td width=\"6%\">&nbsp;</td>");
            out.print("</tr>");
            out.print("</table>");
            
        } catch (IOException e) {
            throw new JspException(e.getMessage() );
            }
        return SKIP_BODY;
        }
    }

