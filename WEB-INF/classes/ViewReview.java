import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try
        {           
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);

            if(!utility.isLoggedin())
            {
                HttpSession session = request.getSession(true);				
                session.setAttribute("login_msg", "Please Login to view Review");
                response.sendRedirect("Login");
                return;
            }

            String productName = request.getParameter("name");		 
            HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
            String userName = "";
            String reviewRating = "";
            String reviewDate;
            String reviewText = "";	
            String price = "";
            String city ="";
        
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Reviews</a>");
            pw.print("</h2><div class='entry'>");
        
            // if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
            if(hm == null)
            {
                pw.println("<h2>Mongo Db server is not up and running</h2>");
            }
            else
            {
                if(!hm.containsKey(productName))
                    pw.println("<h2>There are no reviews for this product.</h2>");
                else
                {
                    pw.print("<table class='gridtable'  style = 'width: 100%'>");

                    pw.print("<tr><td> Product Name </td>");
                    pw.print("<th style = 'text-align: center;'>" + productName + "</th></tr>");

                    pw.print("<tr><td> Price </td>");
                    for (Review r : hm.get(productName)) 
                    {
                        price = r.getPrice();
                        break;
                    }
                    pw.print("<th style = 'text-align: center;'>$ " + price + "</th></tr>");

                    pw.print("</table><br>");

                    pw.print("<table class='gridtable'  style = 'width: 100%'>");
                    pw.print("<tr><th style = 'text-align: center;'> Rating </th><th style = 'text-align: center;'>Reviewer</th><th style = 'text-align: center;'> Review </th><th style = 'text-align: center;'>Review Date</th><th style = 'text-align: center;'> Retailer City</th></tr>");
                    for (Review r : hm.get(productName)) 
                    {	
                        pw.print("<tr>");

                        reviewRating = r.getReviewRating().toString();
                        pw.print("<td>" + reviewRating + "</td>");
                        
                        userName = r.getUserName();
                        pw.print("<td>" + userName + "</td>");

                        reviewText = r.getReviewText();
                        pw.print("<td>" + reviewText + "</td>");
                        
                        reviewDate = r.getReviewDate().toString();
                        pw.print("<td>" + reviewDate + "</td>");

                        city = r.getRetailerCity();
                        pw.print("<td>" + city + "</td>");
 
                        pw.print("</tr>");
                    }
                    pw.println("</table>");	
                }
            }	       
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
