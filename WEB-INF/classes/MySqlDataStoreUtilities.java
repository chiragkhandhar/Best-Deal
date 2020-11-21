import java.sql.*;
import java.util.*;
                	
public class MySqlDataStoreUtilities
{
    static Connection conn = null;

    public static void getConnection()
    {
        try
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","IllinoisTech2021@");							
        }
        catch(Exception e)
        {
        
        }
    }

    public static void deleteOrder(int orderId,String orderName)
    {
        try
        {
            
            getConnection();
            String deleteOrderQuery ="Delete from CustomerOrder where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1,orderId);
            pst.setString(2,orderName);
            pst.executeUpdate();
        }
        catch(Exception e)
        {
                
        }
    }

    public static void deleteUser(String name)
    {
        try
        {
            getConnection();
            String deleteUserQuery ="Delete from Registration where username=?";
            PreparedStatement pst = conn.prepareStatement(deleteUserQuery);
            pst.setString(1,name);
            pst.executeUpdate();
        }
        catch(Exception e)
        {            
        }
    }

    public static void insertProduct(String type, String ID, String name, double price, String image, String manufactorer, String condition, double discount, String description)
    {
        // System.out.println(type + " | " + ID + " | " + name + " | " + price + " | " + image + " | " + manufactorer + " | " + condition + " | " + discount + " | " + description);
        try
        {        
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO productDetails(productType, ID, productName, productPrice, productImage, productManufacturer, productCondition, productDiscount, description)"
            + "VALUES (?,?,?,?,?,?,?,?,?);";	
                
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            pst.setString(1,type);
            pst.setString(2,ID);
            pst.setString(3,name);
            pst.setDouble(4,price);
            pst.setString(5,image);
            pst.setString(6,manufactorer);
            pst.setString(7,condition);
            pst.setDouble(8,discount);
            pst.setString(9,description);
            pst.execute();
        }
        catch(Exception e)
        {
        
        }		
    }

    public static void insertTransaction()
    {
        String[] users = {"chirag", "nishant", "akash", "dhiraj", "ninad", "harsh", "satyaveer", "chris", "jose", "jean"};
        String [] user_IDS = {"1602341465", "1602462193", "1602465920", "1602465931", "1602465967", "1602465977", "1602465951", "1602466072", "1602466080", "1602466090"};
        
        String [] creditcardno = {"653235654","4654135461","56556556445", "64654654464", "6464545612"};
        String [] orderDate = {"2020-20-08", "2020-21-08","2020-22-08","2020-23-08","2020-24-08"};
        String [] exp_DD = {"2020-07-09", "2020-08-09","2020-09-09","2020-10-09","2020-11-09"};
        String [] act_DD = {"2020-05-09", "2020-09-09","2020-09-09","2020-09-09","2020-12-09"};
        String [] zipArr = {"60616", "425401", "411004", "484645", "564565"};
       
        String [] productIDs = {"al1", "al2", "al3", "amz1", "amz2", "amz3", "ap1", "ap2", "ap3", "dl1"};
        String [] productNames = {"Apple Macbook Air", "Apple Macbook Pro", "Apple Macbook Pro 16", "Amazon - Echo Dot (3rd Gen) - Smart Speaker with Alexa - Charcoal", "Amazon - Echo Studio Smart Speaker with Alexa - Charcoal", "Amazon - Echo Dot Kids Edition Smart Speaker with Alexa - Rainbow", "Apple iPhone 11", "Apple iPhone 11 Pro", "Apple iPhone 11 Pro Max", "Dell Inspiron 6546"};
        String [] categories = {"laptop", "laptop","laptop", "va", "va", "va", "phone", "phone", "phone", "laptop"};
        String [] manuf = {"Apple", "Apple", "Apple", "Amazon", "Amazon","Amazon","Apple", "Apple", "Apple", "Dell"};

        String [] modeArr = {"delivery", "pickup"};
        String [] statusArr = {"disputed", "approved"};
        String [] returnedArr = {"Yes", "No"};

        int indexMin = 0, ageMin = 18, commonMin = 0, modeMin = 0;
        int indexMax = 9, ageMax = 30, commonMax = 4, modeMax = 1;
        int min = 100000;
        int max = 999999;

        try
        {        
            getConnection();

            
                int nameIndex = (int)(Math.random()*(indexMax-indexMin+1)+indexMin);  
                int productIndex = (int)(Math.random()*(indexMax-indexMin+1)+indexMin);  	
                int ageIndex = (int)(Math.random()*(ageMax-ageMin+1)+ageMin);  	
                int orderId =  (int)(Math.random()*(max-min+1)+min); 
                int commonIndex =  (int)(Math.random()*(commonMax-commonMin+1)+commonMin);  
                int rand_rating = (int)(Math.random()*(5-1+1)+1);
                int modeIndex =  (int)(Math.random()*(modeMax-modeMin+1)+modeMin);  
                int statusIndex =  (int)(Math.random()*(modeMax-modeMin+1)+modeMin);  
                int returnedIndex =  (int)(Math.random()*(modeMax-modeMin+1)+modeMin);  
                int onTimeIndex =  (int)(Math.random()*(modeMax-modeMin+1)+modeMin);  
                
                String query = "INSERT INTO transactions(loginID,customerName, customerAge,creditCardNo,OrderID,orderDate,exp_deliveryDate,act_deliveryDate,productID,productName,category,manufacturer,rating,del_trackingID,mode,del_ZIP,status,returned,onTime)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
                
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1,user_IDS[nameIndex]);
                pst.setString(2,users[nameIndex]);
                pst.setInt(3,ageIndex);
                pst.setString(4,creditcardno[commonIndex]);
                pst.setInt(5,orderId);
                pst.setString(6,orderDate[commonIndex]);
                pst.setString(7,exp_DD[commonIndex]);
                pst.setString(8,act_DD[commonIndex]);
                pst.setString(9,productIDs[productIndex]);
                pst.setString(10,productNames[productIndex]);
                pst.setString(11,categories[productIndex]);
                pst.setString(12,manuf[productIndex]);
                pst.setInt(13,rand_rating);
                pst.setString(14,"" + orderId);
                pst.setString(15,zipArr[commonIndex]);
                pst.setString(16,modeArr[modeIndex]);
                pst.setString(17,statusArr[statusIndex]);
                pst.setString(18,returnedArr[returnedIndex]);
                pst.setString(19,returnedArr[onTimeIndex]);
                pst.execute();            
        }
        catch(Exception e)
        {
        
        }		
    }

    public static HashMap<String,TVType> getTVs(){
        HashMap<String,TVType> tvs = new HashMap<String,TVType>();            
        try
        {			
            getConnection();
            String selectOrderQuery ="select * from productDetails where productType = 'tv'";			
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();	
           
            while(rs.next())
            {
                TVType tv = new TVType(rs.getString("ID"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("description"));
                tvs.put(tv.getId(), tv);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return tvs;
    }


    public static void insertOrder(String userID, String userName, String userAddress, String creditCardNo, int orderId, String productID, String orderName, String category, String orderDate, String shipDate, double orderPrice, int quantity, double discount, double shippingCost, double netTotal, String mode, String storeID, String location)
    {
        try
        {        
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO CustomerOrder(OrderId, userID, userName, orderName, productID, category, quantity, discount, shippingCost, netTotal, orderPrice, userAddress, creditCardNo, mode, storeID, location, orderDate, shipDate)"
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";	
                
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1,orderId);
            pst.setString(2,userID);
            pst.setString(3,userName);
            pst.setString(4,orderName);
            pst.setString(5,productID);
            pst.setString(6,category);
            pst.setInt(7,quantity);
            pst.setDouble(8,discount);
            pst.setDouble(9,shippingCost);
            pst.setDouble(10,netTotal);
            pst.setDouble(11,orderPrice);
            pst.setString(12,userAddress);
            pst.setString(13,creditCardNo);
            pst.setString(14,mode);
            pst.setString(15,storeID);
            pst.setString(16,location);
            pst.setString(17,orderDate);
            pst.setString(18,shipDate);
            pst.execute();
        }
        catch(Exception e)
        {
        
        }		
    }

    public static int getStock(String ID)
    {
        int stock = 0; 
        try
        {
            getConnection();
            String getStockQuery = "SELECT Stock FROM productDetails WHERE ID = ?";	
                    
            PreparedStatement pst = conn.prepareStatement(getStockQuery);
            pst.setString(1,ID);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                stock = rs.getInt("Stock");
            }			
        }
        catch(Exception e)
        {

        }

        return stock;
    }

    // (productID, quantity, flag) // flag => 0: Sell Opeation 1: Stockup Operation
    public static void setStock(String ID, int quantity, int flag)
    {
        try
        {
            getConnection();
            int stock = getStock(ID);
            int newStock = stock - quantity;
            if(stock > newStock || flag == 1)
            {
                String setStockQuery = "UPDATE productDetails SET Stock = ? WHERE ID = ?";	 
                PreparedStatement pst = conn.prepareStatement(setStockQuery);
                
                if (flag == 0)
                    pst.setInt(1, newStock );
                else if (flag == 1)
                    pst.setInt(1, quantity );
                pst.setString(2,ID);
                pst.executeUpdate();
            }   
            else
            {
                System.out.println("Not enough quantity available");
            }
        }
        catch(Exception e)
        {

        }     
    }

    public static ArrayList<Product> getInventory()
    {	
       ArrayList<Product> productList = new ArrayList<Product>();
       try
        {			
            getConnection();
            String selectInventoryQuery ="select * from productDetails";			
            PreparedStatement pst = conn.prepareStatement(selectInventoryQuery);
            ResultSet rs = pst.executeQuery();	
            while(rs.next())
            {
                Product temp = new Product(rs.getString("productType"), rs.getString("ID"), rs.getInt("Stock"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("description"));
                productList.add(temp);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return productList;
    }

    
    public static ArrayList<Product> getDiscountedProducts()
    {	
       ArrayList<Product> productList = new ArrayList<Product>();
       try
        {			
            getConnection();
            String selectInventoryQuery ="select * from productDetails WHERE productDiscount > 0";			
            PreparedStatement pst = conn.prepareStatement(selectInventoryQuery);
            ResultSet rs = pst.executeQuery();	
            while(rs.next())
            {
                Product temp = new Product(rs.getString("productType"), rs.getString("ID"), rs.getInt("Stock"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("description"));
                productList.add(temp);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return productList;
    }

    public static ArrayList<Product> getSalesList()
    {	
       ArrayList<Product> productList = new ArrayList<Product>();
       try
        {			
            getConnection();
            String selectInventoryQuery ="SELECT orderName, productID, netTotal, COUNT(OrderId) AS ItemSold FROM  customerOrder GROUP BY orderName";			
            PreparedStatement pst = conn.prepareStatement(selectInventoryQuery);
            ResultSet rs = pst.executeQuery();	
            while(rs.next())
            {
                Product temp = new Product("", rs.getString("productID"), rs.getInt("ItemSold"), rs.getString("orderName"), rs.getDouble("netTotal"), "", "", "", 0, "");
                productList.add(temp);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return productList;
    }

    public static ArrayList<Product> getDailySalesList()
    {	
       ArrayList<Product> productList = new ArrayList<Product>();
       try
        {			
            getConnection();
            String selectInventoryQuery ="SELECT orderDate, SUM(netTotal) AS Revenue, COUNT(OrderId) AS ItemSold FROM  customerOrder GROUP BY orderDate";			
            PreparedStatement pst = conn.prepareStatement(selectInventoryQuery);
            ResultSet rs = pst.executeQuery();	
            while(rs.next())
            {
                Product temp = new Product("", "", rs.getInt("ItemSold"), rs.getString("orderDate"), rs.getDouble("Revenue"), "", "", "", 0, "");
                productList.add(temp);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return productList;
    }

    public static ArrayList<StoreLocation> getLocations()
    {	
       ArrayList<StoreLocation> locations = new ArrayList<StoreLocation>();            
        try
        {			
            getConnection();
            String selectOrderQuery ="select * from locations";			
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();	
           
            while(rs.next())
            {
                StoreLocation location = new StoreLocation(rs.getString("storeID"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zip"));
                locations.add(location);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return locations;
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
    {	
        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
            
        try
        {			
            getConnection();
            //select the table 
            String selectOrderQuery ="select * from CustomerOrder";			
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();	
            ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
            while(rs.next())
            {
                if(!orderPayments.containsKey(rs.getInt("OrderId")))
                {	
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
                System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

                //add to orderpayment hashmap
                OrderPayment order = new OrderPayment(rs.getString("userID"), rs.getString("userName"), rs.getString("userAddress"), rs.getString("creditCardNo"), rs.getInt("OrderId"), rs.getString("productID"), rs.getString("orderName"), rs.getString("category"), rs.getString("orderDate"), rs.getString("shipDate"), rs.getDouble("orderPrice"), rs.getInt("quantity"), rs.getFloat("discount"), rs.getFloat("shippingCost"), rs.getFloat("netTotal"), rs.getString("mode"), rs.getString("storeID"), rs.getString("location"));
                listOrderPayment.add(order);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return orderPayments;
    }

    public static void insertUser(String userID, String username, String password, String usertype)
    {
        try
        {	
            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO registration(userID, username, password, usertype) "
            + "VALUES (?,?,?,?);";	
                    
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1,userID);
            pst.setString(2,username);
            pst.setString(3,password);            
            pst.setString(4,usertype);
            pst.execute();
        }
        catch(Exception e)
        {
        
        }	
    }

    public static HashMap<String,User> selectUser()
    {	
        HashMap<String,User> hm=new HashMap<String,User>();
        try 
        {
            getConnection();
            Statement stmt=conn.createStatement();
            String selectCustomerQuery="select * from  Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while(rs.next())
            {	
                User user = new User(rs.getString("userID"), rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        }
        catch(Exception e)
        {
        }
        return hm;			
    }
    
    public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productDetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
            {	Product p = new Product(rs.getString("productType"), rs.getString("ID"), rs.getInt("Stock"), rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productImage"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"), rs.getString("description"));
                hm.put(rs.getString("productName"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}

}	