import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Trending")
public class Trending extends HttpServlet 
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    response.setContentType("text/html");
    PrintWriter pw = response.getWriter();
    Utilities utility = new Utilities(request, pw);
  
    String type = request.getParameter("type");
   
    utility.printHtml("Header.html");
    utility.printHtml("LeftNavigationBar.html");
    displayTrending(request, response, type);
    utility.printHtml("Footer.html");
  }

  /* displayTrending Function shows the products that users has bought, these products will be displayed with Total Amount.*/
  protected void displayTrending(HttpServletRequest request, HttpServletResponse response, String type) throws ServletException, IOException 
  {
    PrintWriter pw = response.getWriter();
    Utilities utility = new Utilities(request, pw);
  
    switch (type) 
    {
      case "type1":
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Most Liked Products</a></h2>");
		pw.print("</div></div>");        
        break;

        case "type2":
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Top Locations</a></h2>");
        pw.print("</div></div>");
        break;

        case "type3":
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Best Selling</a></h2>");
        pw.print("</div></div>");
        break;
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    response.setContentType("text/html");
    PrintWriter pw = response.getWriter();
    Utilities utility = new Utilities(request, pw);
  
    String type = request.getParameter("type");
   
    utility.printHtml("Header.html");
    utility.printHtml("LeftNavigationBar.html");
    displayTrending(request, response, type);
    utility.printHtml("Footer.html");
  }

}
