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

@WebServlet("/ViewItem")
public class ViewItem extends HttpServlet 
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    response.setContentType("text/html");
    PrintWriter pw = response.getWriter();

    /* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/
    Utilities utility = new Utilities(request, pw);
    String name = request.getParameter("name");
    String type = request.getParameter("type");
    String maker = request.getParameter("maker");
    String access = request.getParameter("access");
   
    displayItem(request, response);
  }

  /* displayItem Function shows the products that users has bought, these products will be displayed with Total Amount.*/
  protected void displayItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    response.setContentType("text/html");
    PrintWriter pw = response.getWriter();
    Utilities utility = new Utilities(request, pw);
    Carousel carousel = new Carousel();
    LaptopType laptop = new LaptopType();
    TVType tv = new TVType();
    SSType soundsystem = new SSType();

    String name = request.getParameter("name");
    String type = request.getParameter("type");
    String maker = request.getParameter("maker");
    String image = "";
    String description = "";
    String retailer = "";
    String key = "";

    double price = 0, discount = 0;
    

    switch (type) 
    {
      case "tvs":
        for (Map.Entry<String, TVType> entry : SaxParserDataStore.tvs.entrySet()) {
          if (entry.getValue().getRetailer().equals(maker) && entry.getValue().getName().equals(name)) 
          {
            tv = entry.getValue();
            price = tv.getPrice();
            discount = tv.getDiscount();
            image = tv.getImage();
            description = tv.getDescription();
            retailer = tv.getRetailer();
            key = entry.getKey();
            break;
          }
        }
        break;

      case "soundsystems":
      for (Map.Entry<String, SSType> entry : SaxParserDataStore.soundsystems.entrySet()) {
        if (entry.getValue().getRetailer().equals(maker) && entry.getValue().getName().equals(name)) 
        {
          soundsystem = entry.getValue();
          price = soundsystem.getPrice();
          discount = soundsystem.getDiscount();
          image = soundsystem.getImage();
          description = soundsystem.getDescription();
          retailer = soundsystem.getRetailer();
          key = entry.getKey();
          break;
        }
      }
      break;

      case "laptops":
        for (Map.Entry<String, LaptopType> entry : SaxParserDataStore.laptops.entrySet()) {
          if (entry.getValue().getRetailer().equals(maker) && entry.getValue().getName().equals(name)) 
          {
            laptop = entry.getValue();
            price = laptop.getPrice();
            discount = laptop.getDiscount();
            image = laptop.getImage();
            description = laptop.getDescription();
            retailer = laptop.getRetailer();
            key = entry.getKey();
            break;
          }
        }
        break;

    }

    utility.printHtml("Header.html");
    utility.printHtml("LeftNavigationBar.html");

    pw.print("<div id='content'><div class='post'>");
    pw.print("<h2 class='title meta'><a style='font-size: 24px; text-align: center'>" + name + "</a></h2>");
    pw.print("<div class='entry'><table id='bestseller'>");

    pw.print("<td><div id='shop_item'>");

    pw.print("<h3>$ " + getNewPrice(price, discount) + "</h3>");
    pw.print("<strong>Discount: " + discount + " %</strong>");
    pw.print("<h3>Old Price: $ " + price + "</h3><ul>");
    pw.print("<li id='item'><img src='images/" + type +"/" + image +"' alt='' /></li>");
    pw.print("<li><p style = 'text-align: center;'>" + description + "</p></li>");

    pw.print(
      "<li><form method='post' action='Cart'>" +
      "<input type='hidden' name='name' value='" + key +"'>" +
      "<input type='hidden' name='type' value='" + type +"'>" +
      "<input type='hidden' name='maker' value='" + retailer +"'>" +
      "<input type='hidden' name='access' value=''>" +
      "<input type='submit' class='btnbuy' value='Add to Cart'></form></li>");

    pw.print(
      "<li><form method='post' action='WriteReview'>" +
      "<input type='hidden' name='name' value='" + name +"'>" +
      "<input type='hidden' name='type' value='laptops'>" +
      "<input type='hidden' name='maker' value='" + retailer +"'>" +
      "<input type='hidden' name='access' value=''>" +
	  "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
	  
    pw.print(
      "<li><form method='post' action='ViewReview'>" +
      "<input type='hidden' name='name' value='" + name +"'>" +
      "<input type='hidden' name='type' value='laptops'>" +
      "<input type='hidden' name='maker' value='" + retailer +"'>" +
      "<input type='hidden' name='access' value=''>" +
	  "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
	  
    pw.print("</ul></div></td>");
    //pw.print(carousel.carouselfeature(utility));
    pw.print("</table></div></div></div>");
    utility.printHtml("Footer.html");
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
  {
    response.setContentType("text/html");
    PrintWriter pw = response.getWriter();
    Utilities utility = new Utilities(request, pw);
    displayItem(request, response);
  }

  protected double getNewPrice(double original, double discount) {
    return  Math.round((original * (100 - discount)) / 100);
  }
}
