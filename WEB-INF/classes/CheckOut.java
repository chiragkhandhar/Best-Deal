import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request,pw);
			ArrayList<StoreLocation> locations = new ArrayList<StoreLocation>();
			
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 

			//get the order product details	on clicking submit the form will be passed to submitorder page	
			User user = utility.getUser();			
			String userName = session.getAttribute("username").toString();
			String orderTotal = request.getParameter("orderTotal");
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<form name ='CheckOut' action='Payment' method='post'>");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<table  class='gridtable' style = 'width: 100%'>");
			pw.print("<tr><td style = 'text-align: center;'>Customer ID</td><th>" + user.getId() + "</th></tr>");
			pw.print("<tr><td style = 'text-align: center;'>Customer Name</td><th>" + userName + "</th></tr>");
			pw.print("</table>");

			pw.print("<br>");
			pw.print("<table  class='gridtable' style = 'width: 100%'>");
			pw.print("<tr><th style = 'text-align: center;'>Item</th><th style = 'text-align: center;'> Price</th></tr>");
			// for each order iterate and display the order name price
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<tr><td>" + oi.getName()+"</td><td>" + oi.getNetTotal() + "</td></tr>");
				pw.print("<input type='hidden' name='orderPrice' value='$ "+oi.getPrice()+"'>");
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			}

			pw.print("<tr><th style = 'text-align: center;'>Total Amount</th><td>$ " + orderTotal + "</td></tr>");
			pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
			pw.print("</table><br>");

			pw.print("<h3>Shipping Details</h3><br>");
			pw.print("<table>");

			pw.print("<tr>");
			pw.print("<th>Street Address</th>");
			pw.print("<td><input type='text' name='street' required = 'true' style = 'margin-left: 1rem;'></td></tr>");

			pw.print("<tr>");
			pw.print("<th>Apt/Suit</th>");
			pw.print("<td><input type='text' name='apt' required = 'true' style = 'margin-left: 1rem;'></td></tr>");
		
			pw.print("<tr>");
			pw.print("<th>City</th>");
			pw.print("<td><input type='text' name='city' required = 'true' style = 'margin-left: 1rem;'></td></tr>");

			pw.print("<tr>");
			pw.print("<th>State</th>");
			pw.print("<td><input type='text' name='state' required = 'true' style = 'margin-left: 1rem;'></td></tr>");

			pw.print("<tr>");
			pw.print("<th>Zip</th>");
			pw.print("<td><input type='text' name='zip' required = 'true' style = 'margin-left: 1rem;'></td></tr>");

			pw.print("<tr>");
			pw.print("<th>Shipping Mode</th>");
			pw.print("<td><input type='radio' id = 'delivery' name ='mode' value = 'delivery' required = 'true' style = 'margin-left: 1rem;'> <label for='delivery'>Delivery</label><br></td>");
			pw.print("<td><input type='radio' id = 'pickup' name ='mode' value = 'pickup' required = 'true' > <label for='pickup'>Pickup</label><br></td>");
			pw.print("</tr>");

			try{
				locations = MySqlDataStoreUtilities.getLocations();
			}
			catch(Exception e){

			}
			
			pw.print("<tr>");
			pw.print("<td colspan='2'>");
			pw.print("<label>Select Pickup location(if pickup is selected): </label> ");
			pw.print("<select name = 'locationDetails' class='input' >");
			for(StoreLocation location : locations)
			{
				String storeID = location.getStoreID();
				String temp = location.getStreet() + ", " + location.getCity() + ", " + location.getState() + ", " + location.getZip();
				pw.print("<option value = '" + storeID + "&" +  temp + "'>" + temp + "</option>");
			}
			pw.print("</td>");
			pw.print("</tr>");
			pw.print("<tr><label>Note: Additional $3 per item would be charged if shipping mode is <b>Delivery</b> </label> </tr>");
			pw.print("</table>");
		

			pw.print("<h3>Payment Details</h3><br>");
			pw.print("<table>");
			pw.print("<tr>");
			pw.print("<th>Credit/Debit Card</th>");
			pw.print("<td><input type='text' name='creditCardNo' required = 'true' style = 'margin-left: 1rem;'> </td></tr>");

			pw.print("<tr'><td colspan='2'>");
			pw.print("<input type='submit' name='submit' class='btnbuy' value = 'Place Order' style = 'width: 100%; margin-top: 1rem;'>");
			pw.print("</td></tr></table>");

			pw.print("</form>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
