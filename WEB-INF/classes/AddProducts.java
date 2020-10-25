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

@WebServlet("/AddProducts")

public class AddProducts extends HttpServlet {
	private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        User user = utility.getUser();

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);				
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }

        displayAccount(request, response);

        if(user.getUsertype().equals("retailer"))
        {
            displayForm(pw);
        }

        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");	
	}

	/* Display Account Details of the Customer (Name and Usertype) */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Update Inventory Items</a>");
			pw.print("</h2><div class='entry'>");
			User user = utility.getUser();
			pw.print("<table class='gridtable' style = 'width: 100%'>");			
			pw.print("<tr>");
			pw.print("<th style = 'text-align: center;'> User Name</th>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<th style = 'text-align: center;'> User Type</th>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
            pw.print("</tr></table>");
		}
		catch(Exception e)
		{
		}		
    }
    
    void displayForm(PrintWriter pw)
    {
        pw.print("<form name ='AddProducts' action='AddProducts' method='post'>");
        pw.print("<div class='entry'>");

        pw.print("<h3>Add Product ID and Quantity</h3><br>");
        pw.print("<table style = \"width: 70%;\">");

        pw.print("<tr>");
        pw.print("<th>Product ID</th>");
        pw.print("<td><input type='text' name='productID' required = 'true' style = 'margin-left: 1rem;width: 70%;'></td></tr>");

        
        pw.print("<tr>");
        pw.print("<th>Number of Items</th>");
        pw.print("<td><input type='number' name='quantity' required = 'true' style = 'margin-left: 1rem;width: 70%;'></td></tr>");

        pw.print("<tr><td colspan = 2>");
        pw.print("<input type='submit' name='submit' class='btnbuy' value = 'Add Items' style = 'width: 100%; margin-top: 1rem;'>");
        pw.print("</td></tr>");

        pw.print("</table>");
        pw.print("</div></form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        User user = utility.getUser();

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);				
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }

        displayAccount(request, response);

        if(user.getUsertype().equals("retailer"))
        {
            processForm(request, response, pw);
        }

        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");	
    }
    
    void processForm(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
    {
            int flag = 1;
            String productID = request.getParameter("productID");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            try
            {
                MySqlDataStoreUtilities.setStock(productID, quantity, flag); // (productID, quantity, flag) // flag => 0: Sell Opeation 1: Stockup Operation
                pw.print("<br/><h3>Successfully updated number of items of " + productID + " to "+ quantity +" items.</h3><br/>");
            }
            catch(Exception e){

            }
            pw.print("<form method='get' action='AddProducts'>");
			pw.print("<input type='submit' class='btnbuy' value = 'Add Items to Inventory'v></input><br/>");
			pw.print("</form>");
    }
}




