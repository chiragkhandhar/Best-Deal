import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/ViewOrder")

public class ViewOrder extends HttpServlet {
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=utility.username();
		String usertype = utility.usertype();

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");

		/*check if the order button is clicked 
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order 
		if order button is clicked user will be directed to this same servlet and user has given order number 
		then this page shows all the order details*/
	
		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter Order ID<input name='orderId' type='text' style = 'margin-left: 1rem;'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy' style = 'margin-left: 1rem;'></td></tr></table>");
		}

		//hashmap gets all the order details from file 

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
	
		try
		{				      
			orderPayments = MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{
		}
		

		/*if order button is clicked that is user provided a order number to view order 
		order details will be fetched and displayed in  a table 
		Also user will get an button to cancel the order */

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			if (request.getParameter("orderId") != null && request.getParameter("orderId") != "" )
			{	
				int orderId=Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				try
				{
					orderPayments = MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
				int size=0;
			

				/*get the order size and check if there exist an order with given order number 
				if there is no order present give a message no order stored with this id */

				if(orderPayments.get(orderId)!=null)
				{
					for(OrderPayment od:orderPayments.get(orderId))	
						if(od.getUserName().equals(username))
							size= orderPayments.get(orderId).size();
				}
				// display the orders if there exist order with order id
				if(size>0)
				{	
					pw.print("<table  class='gridtable' style = 'width: 100%;'><th></th>");
					pw.print("<th style = 'text-align: center;'>OrderId</th>");
					pw.print("<th style = 'text-align: center;'>Order Date</th>");
					pw.print("<th style = 'text-align: center;'>UserName</th>");
					pw.print("<th style = 'text-align: center;'>Item</th>");
					pw.print("<th style = 'text-align: center;'>Price</th>");
					pw.print("<th style = 'text-align: center;'>Shipping Mode</th>");
					pw.print("<th style = 'text-align: center;'>Pickup Location</th>");
					pw.print("<th style = 'text-align: center;'>Action</th></tr>");
					for (OrderPayment oi : orderPayments.get(orderId)) 
					{
						pw.print("<tr>");			
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
						pw.print("<td>"+oi.getOrderId()+"</td>");
						pw.print("<td>"+oi.getOrderDate()+"</td>");
						pw.print("<td>"+oi.getUserName()+"</td>");
						pw.print("<td>"+oi.getOrderName()+"</td>");
						pw.print("<td>$ "+oi.getOrderPrice()+"</td>");
						pw.print("<td>"+oi.getMode()+"</td>");
						if(!oi.getLocation().equals(""))
							pw.print("<td>"+oi.getLocation()+"</td>");
						else
						pw.print("<td> - </td>");
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");
					
					}
					pw.print("</table>");
				}
				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			}else
				
			{
				pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");	
			}
		}

		// if the retailer presses Remove Manager button 
		if(usertype.equals("retailer") && request.getParameter("managerName") != null && request.getParameter("remove").equals("Remove Manager") )
		{
			String managerName = request.getParameter("managerName");

			HashMap<String, User> usersHM = new HashMap<String, User>();
			
			try
			{      
				usersHM = MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
		
			}

			for(Map.Entry<String, User> entry : usersHM.entrySet())
			{
				User temp = entry.getValue();
				if(temp.getUsertype().equals("manager") && temp.getName().equals(managerName))
				{
					MySqlDataStoreUtilities.deleteUser(managerName);
					usersHM.remove(entry.getKey());
					break;
				}
			}	
			response.sendRedirect("Account");
		}

		// if the retailer or manager presses Delete Customer button 
		if((usertype.equals("retailer") || usertype.equals("manager")) && request.getParameter("customerName") != null && request.getParameter("remove").equals("Delete Customer") )
		{
			String customerName = request.getParameter("customerName");
			HashMap<String, User> usersHM = new HashMap<String, User>();
			
			try
			{     
				usersHM = MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
		
			}

			for(Map.Entry<String, User> entry : usersHM.entrySet())
			{
				User temp = entry.getValue();
				if(temp.getUsertype().equals("customer") && temp.getName().equals(customerName))
				{
					MySqlDataStoreUtilities.deleteUser(customerName);
					usersHM.remove(entry.getKey());
					break;
				}
			}
			response.sendRedirect("Account");
		}


		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder"))
		{
			if(request.getParameter("orderName") != null)
			{
				String orderName=request.getParameter("orderName");
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
				ArrayList<OrderPayment> ListOrderPayment =new ArrayList<OrderPayment>();
				//get the order from file
				try
				{      
					orderPayments = MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
			
				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (OrderPayment oi : orderPayments.get(orderId)) 
				{
					if(oi.getOrderName().equals(orderName) && (oi.getUserName().equals(username) || usertype.equals("retailer") || usertype.equals("manager") ))
					{
						MySqlDataStoreUtilities.deleteOrder(orderId,orderName);
						ListOrderPayment.add(oi);
						pw.print("<h4 style='color:red'>Your item  <span style='color:black'>"+ orderName +"</span> has been cancelled</h4>");								
					}
				}
				//remove all the orders from hashmap that exist in cancel list
				orderPayments.get(orderId).removeAll(ListOrderPayment);
				if(orderPayments.get(orderId).size()==0)
				{
						orderPayments.remove(orderId);
				}
			}
			else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

}


