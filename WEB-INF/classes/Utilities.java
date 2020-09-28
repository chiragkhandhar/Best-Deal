import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  isLoggedin Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}

	protected double getNewPrice(double original, double discount) {
		return  Math.round((original * (100 - discount)) / 100);
	  }
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("tvs")){
			TVType tv;
			tv = SaxParserDataStore.tvs.get(name);
			OrderItem orderitem = new OrderItem(tv.getName(), getNewPrice(tv.getPrice(), tv.getDiscount()), tv.getImage(), tv.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("soundsystems")){
			SSType soundsystem = null;
			soundsystem = SaxParserDataStore.soundsystems.get(name);
			OrderItem orderitem = new OrderItem(soundsystem.getName(), getNewPrice(soundsystem.getPrice(), soundsystem.getDiscount()), soundsystem.getImage(), soundsystem.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			PhoneType phone = null;
			phone = SaxParserDataStore.phones.get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), getNewPrice(phone.getPrice(), phone.getDiscount()), phone.getImage(), phone.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			LaptopType laptop = null;
			laptop = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), getNewPrice(laptop.getPrice(), laptop.getDiscount()), laptop.getImage(), laptop.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("vas")){
			VAType va = null;
			va = SaxParserDataStore.vas.get(name);
			OrderItem orderitem = new OrderItem(va.getName(), getNewPrice(va.getPrice(), va.getDiscount()), va.getImage(), va.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("wts")){
			WTType wt = null;
			wt = SaxParserDataStore.wts.get(name);
			OrderItem orderitem = new OrderItem(wt.getName(), getNewPrice(wt.getPrice(), wt.getDiscount()), wt.getImage(), wt.getCategory());
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
			Accessory accessory = SaxParserDataStore.accessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), getNewPrice(accessory.getPrice(), accessory.getDiscount()), accessory.getImage(), accessory.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	
	// store the payment details for orders
	public void storePayment(int orderId, String orderName, double orderPrice, String userAddress, String creditCardNo, String mode, String location)
	{
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			// get the payment details file 
			try
			{
				FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				orderPayments = (HashMap)objectInputStream.readObject();
			}
			catch(Exception e)
			{
			
			}
			if(orderPayments==null)
			{
				orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
			}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
			if(!orderPayments.containsKey(orderId)){	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(orderId, arr);
			}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(orderId, username(), orderName, orderPrice, userAddress, creditCardNo, mode, location);
		listOrderPayment.add(orderpayment);	
			
			// add order details into file

			try
			{	
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\PaymentDetails.txt"));
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            	objectOutputStream.writeObject(orderPayments);
				objectOutputStream.flush();
				objectOutputStream.close();       
				fileOutputStream.close();
			}
			catch(Exception e)
			{
				System.out.println("inside exception file not written properly");
			}	
	}

	
	/* getTVs Functions returns the Hashmap with all tvs in the store.*/

	public HashMap<String, TVType> getTVs(){
			HashMap<String, TVType> hm = new HashMap<String, TVType>();
			hm.putAll(SaxParserDataStore.tvs);
			return hm;
	}
	
	/* getSoundSystems Functions returns the  Hashmap with all SoundSystems in the store.*/

	public HashMap<String, SSType> getSoundSystems(){
			HashMap<String, SSType> hm = new HashMap<String, SSType>();
			hm.putAll(SaxParserDataStore.soundsystems);
			return hm;
	}
	
	/* getPhones Functions returns the Hashmap with all Phones in the store.*/

	public HashMap<String, PhoneType> getPhones(){
			HashMap<String, PhoneType> hm = new HashMap<String, PhoneType>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}

	/* getLaptops Functions returns the Hashmap with all Laptops in the store.*/

	public HashMap<String, LaptopType> getLaptops(){
		HashMap<String, LaptopType> hm = new HashMap<String, LaptopType>();
		hm.putAll(SaxParserDataStore.laptops);
		return hm;
	}
	/* getVAs Functions returns the Hashmap with all Laptops in the store.*/

	public HashMap<String, VAType> getVAs(){
		HashMap<String, VAType> hm = new HashMap<String, VAType>();
		hm.putAll(SaxParserDataStore.vas);
		return hm;
	}

	/* getWTs Functions returns the Hashmap with all Laptops in the store.*/

	public HashMap<String, WTType> getWTs(){
		HashMap<String, WTType> hm = new HashMap<String, WTType>();
		hm.putAll(SaxParserDataStore.wts);
		return hm;
	}
	
	/* getProducts Functions returns the Arraylist of tvs in the store.*/

	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, TVType> entry : getTVs().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProductsSoundSystem Functions returns the Arraylist of soundsystems in the store.*/

	public ArrayList<String> getProductsSoundSystem(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SSType> entry : getSoundSystems().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProductsPhones Functions returns the Arraylist of Phones in the store.*/

	public ArrayList<String> getProductsPhones(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, PhoneType> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsLaptops Functions returns the Arraylist of Laptops in the store.*/

	public ArrayList<String> getProductsLaptops(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, LaptopType> entry : getLaptops().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsVAs Functions returns the Arraylist of Voice Assistants in the store.*/

	public ArrayList<String> getProductsVAs(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VAType> entry : getVAs().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsWTs Functions returns the Arraylist of Voice Assistants in the store.*/

	public ArrayList<String> getProductsWTs(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, WTType> entry : getWTs().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	

}
