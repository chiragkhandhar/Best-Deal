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
import com.google.gson.Gson;

@WebServlet("/Inventory")

public class Inventory extends HttpServlet {
	private String error_msg;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
            ArrayList<Product> productList = MySqlDataStoreUtilities.getInventory();
            
            String productListJson = new Gson().toJson(productList);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(productListJson);

		} 
		catch (Exception ex) 
		{
            System.out.println(ex.getMessage());
        }

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
			pw.print("<a style='font-size: 24px;'>Inventory</a></h2>");
			pw.print("<div class='entry'>");
			User user=utility.getUser();
			pw.print("<table class='gridtable' style = 'width: 100%'>");			
			pw.print("<tr>");
			pw.print("<th style = 'text-align: center;'> User Name</th>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<th style = 'text-align: center;'> User Type</th>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
            pw.print("</tr></table>");
            
            
			ArrayList<Product> productList = new ArrayList<Product>();
			ArrayList<Product> discountedProductList = new ArrayList<Product>();
			
			try
			{     
				productList = MySqlDataStoreUtilities.getInventory();
				discountedProductList = MySqlDataStoreUtilities.getDiscountedProducts();
			}
			catch(Exception e)
			{
		
			}
			

			if(user.getUsertype().equals("retailer"))
			{
				displayInventoryTable(productList, pw);
				displayDiscountedTable(discountedProductList, pw);
			}
		
            pw.print("</div></div></div>");
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}

	

	void displayInventoryTable(ArrayList<Product> productList, PrintWriter pw)
	{
		int size = productList.size();
	
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'> Product Inventory</h3><br/>");
			pw.print("<div style = \"display: flex; flex-direction: row;\">");
			pw.print("<form method='get' action='AddProducts'>");
			pw.print("<input type='submit' class='btnbuy' value = 'Update Inventory Items'></input>");
			pw.print("</form>");
			pw.print("<button id='btnGetChartData' class='btnbuy'>View Chart</button>");
			pw.print("</div><br/>");
			pw.println("<div id='chart_div'></div><br/>");
			pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
			pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
			pw.print("<table class='gridtable'><tr>");
			pw.print("<th style = 'text-align: center;'>No.</th>");
			pw.print("<th style = 'text-align: center;'>Product ID</th>");
			pw.print("<th style = 'text-align: center;'>Product Name</th>");
			pw.print("<th style = 'text-align: center;'>Product Price</th>");
			pw.print("<th style = 'text-align: center;'>On Sale</th>");
			pw.print("<th style = 'text-align: center;'>Items Available</th></tr>");
        
            int count = 1;
            for(Product temp : productList)	
            {
               
                pw.print("<tr>");					
				pw.print("<td>"+count+"</td>");
				pw.print("<td>"+temp.getID()+"</td>");
                pw.print("<td>"+temp.getproductName()+"</td>");
				pw.print("<td> $ "+temp.getproductPrice()+"</td>");
				if(temp.getstock() > 0)
					pw.print("<td>"+"Available"+"</td>");
				else
					pw.print("<td style = \"background-color: red; color:white; font-weight:bold;\">"+"Out of Stock"+"</td>");
                pw.print("<td style = \"font-weight:bold;\">"+temp.getstock()+"</td>");
                pw.print("</tr>"); 
                count++;
            }
			pw.print("</table>");
		}
		else
		{
			pw.print("<h4 style='color:red; text-align:center;'>There are no product to display.</h4>");
		}

		pw.print("</table>");		
	}

	void displayDiscountedTable(ArrayList<Product> discountedProductList, PrintWriter pw)
	{
		int size = discountedProductList.size();

				
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'> Discounted Products</h3><br>");
			pw.print("<table class='gridtable' style = \"width: 100%;\"><tr>");
			pw.print("<th style = 'text-align: center;'>No.</th>");
			pw.print("<th style = 'text-align: center;'>Product Name</th>");
			pw.print("<th style = 'text-align: center;'>Actual Price</th>");
			pw.print("<th style = 'text-align: center;'>Discount/Rebate</th>");
			pw.print("<th style = 'text-align: center;'>Marked-down Price</th></tr>");
        
            int count = 1;
            for(Product temp : discountedProductList)	
            {
               
                pw.print("<tr>");					
                pw.print("<td>"+count+"</td>");
                pw.print("<td>"+temp.getproductName()+"</td>");
				pw.print("<td> $ "+temp.getproductPrice()+"</td>");
				pw.print("<td>"+temp.getproductDiscount()+" %</td>");
                pw.print("<td style = \"font-weight:bold;\"> $ "+getNewPrice(temp.getproductPrice(), temp.getproductDiscount())+"</td>");
                pw.print("</tr>"); 
                count++;
            }
			pw.print("</table>");
		}
		else
		{
			pw.print("<h4 style='color:red; text-align:center;'>There are no product to display.</h4>");
		}

		pw.print("</table>");		
	}

	double getNewPrice(double original, double discount) {
		return  Math.round((original * (100 - discount)) / 100);
	  }

	
}




