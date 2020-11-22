/* This code is used to implement carousel feature in Website. Carousels are used to implement slide show feature. This  code is used to display 
all the accessories related to a particular product. This java code is getting called from cart.java. The product which is added to cart, all the 
accessories realated to product will get displayed in the carousel.*/
  

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;			
			
			
public class Carousel
{

	public String showAccessories(String name, String type, String retailer, Utilities utility)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("");
		if(type.equals("tvs"))
		{
			HashMap<String, TVType> hm = new HashMap<String, TVType>();
			hm.putAll(SaxParserDataStore.tvs);
			
			String myCarousel = "accessories";

			TVType tv1 = hm.get(name);
			int k = 0; int size= hm.size();		
			sb.append("<div id='content'><div class='post'><h2 class='title meta'>");
			sb.append("<a style='font-size: 24px;'>"+tv1.getName()+" Accessories</a>");
			sb.append("</h2>");
			sb.append("<div class='container'>");
			sb.append("<div class='carousel slide' id='"+myCarousel+"' data-ride='carousel'>");
			sb.append("<div class='carousel-inner'>");
			for(Map.Entry<String, String> acc:tv1.getAccessories().entrySet())
			{
			
				Accessory accessory = SaxParserDataStore.accessories.get(acc.getValue());
				if (k == 0 )
				{				
					sb.append("<div class='item active'><div class='col-md-6' style = 'background-color: #ffffff;border :1px solid #cfd1d3'>");
				}
				else
				{
					sb.append("<div class='item'><div class='col-md-6' style = 'background-color: #ffffff ;border :1px solid #cfd1d3' >");
				}
				sb.append("<div id='shop_item'>");
				sb.append("<h3>"+accessory.getName()+"</h3>");
				sb.append("<strong>$ "+accessory.getPrice()+"</strong><ul>");
				sb.append("<li id='item'><img src='images/accessories/"+accessory.getImage()+"' alt='' /></li>");
				sb.append("<li><form method='post' action='Cart'>" +
						"<input type='hidden' name='name' value='"+acc.getValue()+"'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
				sb.append("</ul></div></div>");
				sb.append("</div>");
				k++;
			}
			sb.append("</div>");
			sb.append("<a class='left carousel-control' href='#"+myCarousel+"' data-slide='prev' style = 'width : 10% ;background-color:#D7e4ef; opacity :1'>"+
							"<span class='glyphicon glyphicon-chevron-left' style = 'color :black'></span>"+
							"<span class='sr-only'>Previous</span>"+
							"</a>");
			sb.append("<a class='right carousel-control' href='#"+myCarousel+"' data-slide='next' style = 'width : 10% ;background-color:#D7e4ef; opacity :1'>"+
					"<span class='glyphicon glyphicon-chevron-right' style = 'color :black'></span>"+
						"<span class='sr-only'>Next</span>"+
						"</a>");

		
			sb.append("</div>");	
			sb.append("</div></div>");
			sb.append("</div>");
		}
		
		return sb.toString();
	}
			
	public String carouselfeature(Utilities utility)
	{	
		ProductRecommenderUtility prodRecUtility = new ProductRecommenderUtility();

		HashMap<String, TVType> hm = new HashMap<String, TVType>();
		StringBuilder sb = new StringBuilder();
		String myCarousel = null;
			
		String name = null;
		String CategoryName = null;
		if(CategoryName == null)
		{
			hm.putAll(SaxParserDataStore.tvs);
			name = "";
		}

		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		prodRecmMap = prodRecUtility.readOutputFile();
		
		int l = 0;
		for (String user: prodRecmMap.keySet())
		{
			if (user.equals(utility.username()))
			{	
		        String products = prodRecmMap.get(user);
				products=products.replace("[","");
				products=products.replace("]","");
				products=products.replace("\"", " ");
				ArrayList<String> productsList = new ArrayList<String>(Arrays.asList(products.split(",")));

		        myCarousel = "myCarousel"+l;
					
				sb.append("<div id='content'><div class='post'><h2 class='title meta'>");
				sb.append("<a style='font-size: 24px;'>"+""+" Recommended Products</a>");
				sb.append("</h2>");

				sb.append("<div class='container'>");
				sb.append("<div class='carousel slide' id='"+myCarousel+"' data-ride='carousel'>");
				sb.append("<div class='carousel-inner'>");
						
				int k = 0;
				for(String prod : productsList)
				{	
					prod = prod.replace("'", "");
					Product prodObj = new Product();
					
					prodObj = prodRecUtility.getProduct(prod.trim());
					
					if (k == 0 )
						sb.append("<div class='item active'><div class='col-md-6' style = 'background-color: #58acfa;border :1px solid #cfd1d3'>");
					else
						sb.append("<div class='item'><div class='col-md-6' style = 'background-color: #58acfa ;border :1px solid #cfd1d3' >");

					sb.append("<div id='shop_item'>");
					sb.append("<h3>"+prodObj.getproductName()+"</h3>");
					sb.append("<strong>"+prodObj.getproductPrice()+"$</strong><ul>");
					sb.append("<li id='item'><img src='images/"+prodObj.getproductType()+"s/"+prodObj.getproductImage()+"' alt='' /></li>");
					
					sb.append("<li><form method='post' action='Cart'>" +
							"<input type='hidden' name='name' value='"+prodObj.getID()+"'>"+
							"<input type='hidden' name='type' value='"+prodObj.getproductType()+"s'>"+
							"<input type='hidden' name='maker' value='"+prodObj.getproductManufacturer()+"'>"+
							"<input type='hidden' name='access' value='"+" "+"'>"+
							"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
					
					sb.append("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+prodObj.getproductName()+"'>"+
							"<input type='hidden' name='type' value='"+prodObj.getproductType()+"s'>"+
							"<input type='hidden' name='maker' value='"+prodObj.getproductManufacturer()+"'>"+
							"<input type='hidden' name='access' value='"+" "+"'>"+
							"<input type='hidden' name='price' value='"+prodObj.getproductPrice()+"'>"+
							"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
					
					sb.append("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+prodObj.getproductName()+"'>"+
							"<input type='hidden' name='type' value='"+prodObj.getproductType()+"s'>"+
							"<input type='hidden' name='maker' value='"+prodObj.getproductManufacturer()+"'>"+
							"<input type='hidden' name='access' value='"+" "+"'>"+
							"<input type='submit' value='ViewReview' class='btnreview'></form></li>");

					sb.append("</ul></div></div>");
					sb.append("</div>");
					k++;
				}
			
				sb.append("</div>");
				sb.append("<a class='left carousel-control' href='#"+myCarousel+"' data-slide='prev' style = 'width : 10% ;background-color:#D7e4ef; opacity :1'>"+
						"<span class='glyphicon glyphicon-chevron-left' style = 'color :red'></span>"+
						"<span class='sr-only'>Previous</span>"+
						"</a>");
				sb.append("<a class='right carousel-control' href='#"+myCarousel+"' data-slide='next' style = 'width : 10% ;background-color:#D7e4ef; opacity :1'>"+
						"<span class='glyphicon glyphicon-chevron-right' style = 'color :red'></span>"+
							"<span class='sr-only'>Next</span>"+
							"</a>");

			
				sb.append("</div>");
				sb.append("</div></div>");
				sb.append("</div>");
				l++;
			}
		}
		return sb.toString();
	}
}
	