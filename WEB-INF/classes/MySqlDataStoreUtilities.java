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
}	