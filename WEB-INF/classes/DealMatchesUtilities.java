import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DealMatchesUtilities")

public class DealMatchesUtilities extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

        HashMap<String,Product> selectedproducts=new HashMap<String,Product>();
		try
		{
            pw.print("<div id='content'>");
            pw.print("<div class='post'>");
            pw.print("<h2 class='title'>");
            pw.print("<a href='/Tutorial_1'>Welcome to Best Deal</a></h2>");
            
            pw.print("<div class='entry'>");
            pw.print("<img src='images/site/img08.jpg'style='width: 600px; display: block; margin-left: auto; margin-right: auto' />");
            pw.print("<br> <br>");
            pw.print("<h1 style = 'text-align: center'>Get the latest deals and more.</h1>");
            pw.print("<br> <br>");
            pw.print("<h2 style = 'text-align: center'>We beat our competitors in all aspects. Price-Match Guaranteed</h2>");
            pw.print("<br> <br>");
		
			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

            HashMap<String,Product> productmap=MySqlDataStoreUtilities.getData();
			int i = 0;
			for(Map.Entry<String, Product> entry : productmap.entrySet())
			{
                if(selectedproducts.size()<2 && !selectedproducts.containsKey(entry.getKey()))
                {	
                    BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\DealMatches.txt")));
                    line=reader.readLine().toLowerCase();	
                    
                    if(line==null)
                    {
                        pw.print("<div class='panel panel-default'>");
                        pw.print("<div class='panel-body'>");
                        pw.print("<h4 style = 'text-align: center'>No Offers Found</h4>");
                        pw.print("</div>");
                        pw.print("</div>");
                        break;
                    }	
                    else
                    {	
                        do 
                        {	
                            if(line.contains(entry.getKey()))
                            {
                                pw.print("<div class='panel panel-default'>");
                                pw.print("<div class='panel-body'>");
                                pw.print("<h4 style = 'text-align: center'>"+line+"</h4>");
                                pw.print("</div>");
                                pw.print("</div>");

                                selectedproducts.put(entry.getKey(),entry.getValue());
                                break;
                            } 
                        } 
                        while((line = reader.readLine()) != null);
                    }
                }
			}
        }
        catch(Exception e)
        {
            pw.print("<div class='panel panel-default'>");
            pw.print("<div class='panel-body'>");
            pw.print("<h4 style = 'text-align: center'>No Offers Found</h4>");
            pw.print("</div>");
            pw.print("</div>");
        }
		pw.print("</div>");
		pw.print("</div>");
		pw.print("<div class='post'>");
		pw.print("<h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Deal Matches</a>");
		pw.print("</h2>");
        pw.print("<div class='entry'>");
        
		if(selectedproducts.size()==0)
		{
            pw.print("<br> <br>");
		    pw.print("<div class='panel panel-default'>");
            pw.print("<div class='panel-body'>");
            pw.print("<h4 style = 'text-align: center'>No Deals Found</h4>");
            pw.print("</div>");
            pw.print("</div>");	
		}
		else
		{
            pw.print("<table id='bestseller'>");
            pw.print("<tr>");
            for(Map.Entry<String, Product> entry : selectedproducts.entrySet())
            {
                pw.print("<td><div id='shop_item'><h3>"+entry.getValue().getproductName()+"</h3>");
                pw.print("<strong>"+entry.getValue().getproductPrice()+"$</strong>");
                pw.print("<ul>");
                pw.print("<li id='item'><img src='images/"+entry.getValue().getproductType()+"s/"+entry.getValue().getproductImage()+"' alt='' />");
                pw.print("</li><li>");

                pw.print("<form action='Cart' method='post'>");
                pw.print("<input type='submit' class='btnbuy' value='Buy Now'>");
                pw.print("<input type='hidden' name='name' value='"+entry.getValue().getID()+"'>");
                pw.print("<input type='hidden' name='type' value='"+entry.getValue().getproductType()+"s'>");
                pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getproductManufacturer()+"'>");
                pw.print("<input type='hidden' name='access' value=''>");
                pw.print("</form></li><li>");

                pw.print("<form action='WriteReview' method='post'><input type='submit' class='btnreview' value='WriteReview'>");
                pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
                pw.print("<input type='hidden' name='type' value='"+entry.getValue().getproductType()+"s'>");
                pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getproductManufacturer()+"'>");
                pw.print("<input type='hidden' name='price' value='"+entry.getValue().getproductPrice()+"'>");
                pw.print("</form></li>");

                pw.print("<li>");
                pw.print("<form action='ViewReview' method='post'><input type='submit' class='btnreview' value='ViewReview'>");
                pw.print("<input type='hidden' name='name' value='"+entry.getKey()+"'>");
                pw.print("<input type='hidden' name='type' value='"+entry.getValue().getproductType()+"s'>");
                pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getproductManufacturer()+"'>");
                pw.print("<input type='hidden' name='price' value='"+entry.getValue().getproductPrice()+"'>");
                pw.print("</form></li></ul></div></td>");
            }
            pw.print("</tr></table>");
		}
		pw.print("</div></div></div>");
	}
}
