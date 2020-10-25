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

@WebServlet("/Sales")

public class Sales extends HttpServlet 
{
	private String error_msg;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
            ArrayList<Product> productList = MySqlDataStoreUtilities.getSalesList();
            
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
			pw.print("<a style='font-size: 24px;'>Sales Report</a>");
			pw.print("</h2><div class='entry'>");
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
            ArrayList<Product> dailySalesList = new ArrayList<Product>();
			
			try
			{     
                productList = MySqlDataStoreUtilities.getSalesList();
                dailySalesList = MySqlDataStoreUtilities.getDailySalesList();
			}
			catch(Exception e)
			{
		
			}
			

			if(user.getUsertype().equals("retailer"))
			{
                displaySalesTable(productList, pw);
                displayDailySalesTable(dailySalesList, pw);
			}
		
            pw.print("</div></div></div>");
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}

	void displaySalesTable(ArrayList<Product> productList, PrintWriter pw)
	{
		int size = productList.size();

				
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'> Sales Table</h3><br>");
			pw.print("<button id='btnGetChartData2' class='btnbuy'>View Chart</button>");
			pw.println("<div id='chart_div'></div><br/>");
			pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
			pw.println("<script type='text/javascript' src='DataVisualization.js'></script>");
			
			pw.print("<table class='gridtable' style = \"width: 100%;\"><tr>");
			pw.print("<th style = 'text-align: center;'>No.</th>");
			pw.print("<th style = 'text-align: center;'>Product Name</th>");
			pw.print("<th style = 'text-align: center;'>Sale Price </th>");
			pw.print("<th style = 'text-align: center;'>No. of items sold</th>");
			pw.print("<th style = 'text-align: center;'>Net Revenue</th></tr>");
        
            int count = 1;
            for(Product temp : productList)	
            {
               
                pw.print("<tr>");					
                pw.print("<td>"+count+"</td>");
                pw.print("<td >"+temp.getproductName()+"</td>");
                pw.print("<td> $ "+getNewPrice(temp.getproductPrice(), temp.getproductDiscount())+"</td>");
				pw.print("<td> "+temp.getstock()+"</td>");
                pw.print("<td >$ "+getRevenue(temp.getstock(), getNewPrice(temp.getproductPrice(), temp.getproductDiscount()))+"</td>");
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
    
    void displayDailySalesTable(ArrayList<Product> productList, PrintWriter pw)
	{
		int size = productList.size();

				
		if(size>0)
		{	
			pw.print("<br><h3 style = 'text-align: center;'>Daily Sales Table</h3><br>");
			pw.print("<table class='gridtable' style = \"width: 100%;\"><tr>");
			pw.print("<th style = 'text-align: center;'>No.</th>");
			pw.print("<th style = 'text-align: center;'>Date</th>");
			pw.print("<th style = 'text-align: center;'>No. of items sold</th>");
			pw.print("<th style = 'text-align: center;'>Net Revenue</th></tr>");
        
            int count = 1;
            for(Product temp : productList)	
            {
               
                pw.print("<tr>");					
                pw.print("<td>"+count+"</td>");
                pw.print("<td >"+temp.getproductName()+"</td>");
				pw.print("<td> "+temp.getstock()+"</td>");
                pw.print("<td >$ " + temp.getproductPrice()+"</td>");
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

    double getRevenue(int quantity, double rate) {
		return quantity * rate;
	  }

	
}




