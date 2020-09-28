import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Math;
import java.time.LocalDate;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String street = request.getParameter("street");		
		String apt = request.getParameter("apt");		
		String city = request.getParameter("city");		
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String userAddress = street + ", " + apt + ", " + city + ", " + state + ", " + zip;
		String creditCardNo=request.getParameter("creditCardNo");
		String mode = request.getParameter("mode");
		String location = request.getParameter("location");

		if(mode.equals("delivery"))
			location = "";

		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
		{
			int min = 100000;
			int max = 999999;
			
			int orderId =  (int)(Math.random()*(max-min+1)+min);  
			//int orderId=utility.getOrderPaymentSize()+1; 

			String orderDate = LocalDate.now().toString();

			//iterate through each order
			for (OrderItem oi : utility.getCustomerOrders())
			{
				utility.storePayment(orderId, oi.getName(), oi.getPrice(), userAddress, creditCardNo, mode, location, orderDate);
			}

			//remove the order details from cart after processing			
			OrdersHashMap.orders.remove(utility.username());	

			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order Confirmation</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2>Your order is placed! </h2><br>");
			pw.print("<h3>Your tracking id is <span style = 'font-weight:bold;'>&nbsp;"+(orderId) + "</span></h3><br>");
			pw.print("<h4 style = 'text-align: center;'> Use this ID to manage your orders.");
			if(mode.equals("delivery"))
				pw.print("<h3>Your order would be delivered on "+LocalDate.now().plusDays(14)+"</h3>");
			else
				pw.print("<h3>Your order would be ready for pickup on "+LocalDate.now().plusDays(14)+"</h3>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
