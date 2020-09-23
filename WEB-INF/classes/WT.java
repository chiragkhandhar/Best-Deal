import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WT")

public class WT extends HttpServlet {

	/* WT Page Displays all the Wearable Technologies and their Information in Best Deal*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the WT category is fitnesswatch or smartwatch or headphones or virtualreality or wearabletechnologies then add products to hashmap*/

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, WTType> hm = new HashMap<String, WTType>();
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.wts);
			name = "All Wearble Technologies";
		}
		else
		{
			if(CategoryName.equals("fitnesswatches"))
			{
				for(Map.Entry<String,WTType> entry : SaxParserDataStore.wts.entrySet())
				{
				  if(entry.getValue().getCategory().equals("fitnesswatch")) 
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Fitness Watches";
			}
			else if(CategoryName.equals("smartwatches"))
			{
				for(Map.Entry<String,WTType> entry : SaxParserDataStore.wts.entrySet())
				{
				  if(entry.getValue().getCategory().equals("smartwatch"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Smart Watches";
            }
            else if(CategoryName.equals("headphones"))
			{
				for(Map.Entry<String,WTType> entry : SaxParserDataStore.wts.entrySet())
				{
				  if(entry.getValue().getCategory().equals("headphone"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Headphones";
            }
            else if(CategoryName.equals("vr"))
			{
				for(Map.Entry<String,WTType> entry : SaxParserDataStore.wts.entrySet())
				{
				  if(entry.getValue().getCategory().equals("vr"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Virtual Realities";
            }
            else if(CategoryName.equals("pettrackers"))
			{
				for(Map.Entry<String,WTType> entry : SaxParserDataStore.wts.entrySet())
				{
				  if(entry.getValue().getCategory().equals("pettrackers"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Pet Trackers";
			}
			
		}
		

		/* Header, Left Navigation Bar are Printed.

		All the wts and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, WTType> entry : hm.entrySet()){
			WTType wt = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wt.getName()+"</h3>");
			pw.print("<strong>$"+wt.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/wts/"+wt.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
					"<input type='hidden' name='maker' value='"+wt.getCategory()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
					"<input type='hidden' name='maker' value='"+wt.getCategory()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wts'>"+
					"<input type='hidden' name='maker' value='"+wt.getCategory()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	}
}
