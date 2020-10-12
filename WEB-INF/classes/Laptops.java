import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Laptops")

public class Laptops extends HttpServlet {

	/* Laptops Page Displays all the laptops and their Information in Best Deal*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the laptops type whether it is microsft or Dell or Microsoft then add products to hashmap*/

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, LaptopType> hm = new HashMap<String, LaptopType>();
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.laptops);
			name = "";
		}
		else
		{
			if(CategoryName.equals("Apple"))
			{
				for(Map.Entry<String,LaptopType> entry : SaxParserDataStore.laptops.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Apple")) 
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Apple";
			}
			else if(CategoryName.equals("Dell"))
			{
				for(Map.Entry<String,LaptopType> entry : SaxParserDataStore.laptops.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Dell"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Dell";
			}
			else if(CategoryName.equals("Microsoft"))
			{
				for(Map.Entry<String,LaptopType> entry : SaxParserDataStore.laptops.entrySet())
				{ 
			      if(entry.getValue().getRetailer().equals("Microsoft"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Mircosoft";
			}
		}
		

		/* Header, Left Navigation Bar are Printed.

		All the laptops and Tv information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Laptops</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, LaptopType> entry : hm.entrySet()){
			LaptopType laptop = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			
			pw.print("<h3>"+laptop.getName()+"</h3>");
			pw.print("<strong>$"+laptop.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/laptops/"+laptop.getImage()+"' alt='' /></li>");
			
			pw.print("<form name ='ViewItem' action='ViewItem' method='post'>");
			pw.print("<li><input type='hidden' name='name' value='"+laptop.getName()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='View Item'></form></li>");

			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");

			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='" + laptop.getName() + "'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+laptop.getPrice()+"'>"+
					"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
					
			
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='" + laptop.getName() + "'>"+
					"<input type='hidden' name='type' value='laptops'>"+
					"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+laptop.getPrice()+"'>"+
					"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	}
}
