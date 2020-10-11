import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			User user=utility.getUser();
			pw.print("<table class='gridtable' style = 'width: 100%'>");
			if(user.getId() != null)
			{
				pw.print("<tr>");
				pw.print("<th style = 'text-align: center;'> User ID</th>");
				pw.print("<td>" +user.getId()+ "</td>");
				pw.print("</tr>");
			}			
			pw.print("<tr>");
			pw.print("<th style = 'text-align: center;'> User Name</th>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<th style = 'text-align: center;'> User Type</th>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr></table>");
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			
			try
			{     
				orderPayments = MySqlDataStoreUtilities.selectOrder();
			}
			catch(Exception e)
			{
		
			}
			

			if(user.getUsertype().equals("retailer"))
			{
				displayRetailerTable(orderPayments, pw);
			}
			else if(user.getUsertype().equals("manager"))
			{
				displayManagerTable(orderPayments, pw);
			}
			else if(user.getUsertype().equals("customer"))
			{
				displayCustomerTable(orderPayments, pw, user);
			}
			
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}

	void displayCustomerTable(HashMap<Integer, ArrayList<OrderPayment>> orderPayments, PrintWriter pw, User user)
	{
		int size=0;
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
		{
			for(OrderPayment od:entry.getValue())	
			if(od.getUserName().equals(user.getName()))
			size= size+1;
		}
				
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'>Order Details</h3><br>");
			pw.print("<table class='gridtable'><tr><th></th>");
			pw.print("<th style = 'text-align: center;'>OrderId</th>");
			pw.print("<th style = 'text-align: center;'>Order Date</th>");
			pw.print("<th style = 'text-align: center;'>UserName</th>");
			pw.print("<th style = 'text-align: center;'>Item</th>");
			pw.print("<th style = 'text-align: center;'>Price</th>");
			pw.print("<th style = 'text-align: center;'>Shipping Mode</th>");
			pw.print("<th style = 'text-align: center;'>Pickup Location</th>");
			pw.print("<th style = 'text-align: center;'>Action</th></tr>");
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
			{
				for(OrderPayment oi:entry.getValue())	
				if(oi.getUserName().equals(user.getName())) 
				{
					pw.print("<form method='get' action='ViewOrder'>");
					pw.print("<tr>");			
					pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
					pw.print("<td>"+oi.getOrderId()+"</td>");
					pw.print("<td>"+oi.getOrderDate()+"</td>");
					pw.print("<td>"+oi.getUserName()+"</td>");
					pw.print("<td>"+oi.getOrderName()+"</td>");
					pw.print("<td>$ "+oi.getNetTotal()+"</td>");
					pw.print("<td>"+oi.getMode()+"</td>");
					if(!oi.getLocation().equals(""))
						pw.print("<td>"+oi.getLocation()+"</td>");
					else
					pw.print("<td> - </td>");
					pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
					pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
					pw.print("</tr>");
					pw.print("</form>");
				}
			
			}
			
			pw.print("</table>");
		}
		else
		{
			pw.print("<h4 style='color:red; text-align:center;'>There are no orders to display.</h4>");
		}

		pw.print("</table>");		
		pw.print("</h2></div></div></div>");		
	}

	void displayRetailerTable(HashMap<Integer, ArrayList<OrderPayment>> orderPayments, PrintWriter pw)
	{
		displayAllSalesmen(pw);
		displayAllCustomers(pw);
		int size=0;
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
		{
			for(OrderPayment od:entry.getValue())	
				size= size+1;
		}

				
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'>All Recent Orders</h3><br>");
			pw.print("<table class='gridtable'><tr><th></th>");
			pw.print("<th style = 'text-align: center;'>OrderId</th>");
			pw.print("<th style = 'text-align: center;'>Order Date</th>");
			pw.print("<th style = 'text-align: center;'>UserName</th>");
			pw.print("<th style = 'text-align: center;'>Item</th>");
			pw.print("<th style = 'text-align: center;'>Price</th>");
			pw.print("<th style = 'text-align: center;'>Shipping Mode</th>");
			pw.print("<th style = 'text-align: center;'>Pickup Location</th>");
			pw.print("<th style = 'text-align: center;'>Action</th></tr>");
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
			{
				for(OrderPayment oi:entry.getValue())	
				{
					pw.print("<form method='get' action='ViewOrder'>");
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
					pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
					pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
					pw.print("</tr>");
					pw.print("</form>");
				}
			
			}
			
			pw.print("</table>");
		}
		else
		{
			pw.print("<h4 style='color:red; text-align:center;'>There are no orders to display.</h4>");
		}

		pw.print("</table>");		
		pw.print("</h2></div></div></div>");		
	}

	void displayManagerTable(HashMap<Integer, ArrayList<OrderPayment>> orderPayments, PrintWriter pw)
	{
		displayAllCustomers(pw);
		int size=0;
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
		{
			for(OrderPayment od:entry.getValue())	
				size= size+1;
		}
				
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'>All Recent Orders</h3><br>");
			pw.print("<table class='gridtable'><tr><th></th>");
			pw.print("<th style = 'text-align: center;'>OrderId</th>");
			pw.print("<th style = 'text-align: center;'>Order Date</th>");
			pw.print("<th style = 'text-align: center;'>UserName</th>");
			pw.print("<th style = 'text-align: center;'>Item</th>");
			pw.print("<th style = 'text-align: center;'>Price</th>");
			pw.print("<th style = 'text-align: center;'>Shipping Mode</th>");
			pw.print("<th style = 'text-align: center;'>Pickup Location</th>");
			pw.print("<th style = 'text-align: center;'>Action</th></tr>");
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
			{
				for(OrderPayment oi:entry.getValue())	
				{
					pw.print("<form method='get' action='ViewOrder'>");
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
					pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
					pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
					pw.print("</tr>");
					pw.print("</form>");
				}
			
			}
			
			pw.print("</table>");
		}
		else
		{
			pw.print("<h4 style='color:red; text-align:center;'>There are no orders to display.</h4>");
		}

		pw.print("</table>");		
		pw.print("</h2></div></div></div>");		
	}

	void displayAllSalesmen(PrintWriter pw)
	{
		HashMap<String, User> usersHM = new HashMap<String, User>();
		
		try
		{      
			usersHM = MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
	
		}

		int size = 0;
		for(Map.Entry<String, User> entry : usersHM.entrySet())
		{
			User temp = entry.getValue();
			if(temp.getUsertype().equals("manager"))
				size++;
		}

		if( size > 0)
		{
			pw.print("<br><h3 style = 'text-align: center;'>All Managers</h3><br>");
			pw.print("<table class='gridtable' style = 'width:100%;'><tr><th></th>");
			pw.print("<th style = 'text-align: center;'>Sr. No.</th>");
			pw.print("<th style = 'text-align: center;'>Name.</th>");
			pw.print("<th style = 'text-align: center;'>Action</th></tr>");
			int i = 0;
			for(Map.Entry<String, User> entry : usersHM.entrySet())
			{
				User temp = entry.getValue();
				if(temp.getUsertype().equals("manager"))
				{
					i++;
					pw.print("<form method='get' action='ViewOrder'>");
					pw.print("<tr>");			
					pw.print("<td><input type='radio' name='managerName' value='"+temp.getName()+"'></td>");			
					pw.print("<td>"+i+"</td>");
					pw.print("<td>"+temp.getName()+"</td>");
					pw.print("<td><input type='submit' name='remove' value='Remove Manager' class='btnbuy'></td></tr>");
					pw.print("</form>");
					
				}
			}
			pw.print("</table>");
		}
	}

	void displayAllCustomers(PrintWriter pw)
	{
		HashMap<String, User> usersHM = new HashMap<String, User>();
		
		try
		{    
			usersHM = MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
	
		}

		int size = 0;
		for(Map.Entry<String, User> entry : usersHM.entrySet())
		{
			User temp = entry.getValue();
			if(temp.getUsertype().equals("customer"))
				size++;
		}

		if( size > 0)
		{
			pw.print("<br><h3 style = 'text-align: center;'>All Customers</h3><br>");
			pw.print("<table class='gridtable' style = 'width:100%;'><tr><th></th>");
			pw.print("<th style = 'text-align: center;'>Sr. No.</th>");
			pw.print("<th style = 'text-align: center;'>Name.</th>");
			pw.print("<th style = 'text-align: center;'>Action</th></tr>");
			int i = 0;
			for(Map.Entry<String, User> entry : usersHM.entrySet())
			{
				User temp = entry.getValue();
				if(temp.getUsertype().equals("customer"))
				{
					i++;
					pw.print("<form method='get' action='ViewOrder'>");
					pw.print("<tr>");			
					pw.print("<td><input type='radio' name='customerName' value='"+temp.getName()+"'></td>");			
					pw.print("<td>"+i+"</td>");
					pw.print("<td>"+temp.getName()+"</td>");
					pw.print("<td><input type='submit' name='remove' value='Delete Customer' class='btnbuy'></td></tr>");
					pw.print("</form>");
					
				}
			}
			pw.print("</table>");
		}
	}
}
