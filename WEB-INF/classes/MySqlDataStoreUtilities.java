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


    public static void insertOrder(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, String mode, String location, String orderDate)
    {
        try
        {        
            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO CustomerOrder(OrderId, UserName, OrderName, OrderPrice, userAddress, creditCardNo, mode, location, orderDate)"
            + "VALUES (?,?,?,?,?,?,?,?,?);";	
                
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1,orderId);
            pst.setString(2,userName);
            pst.setString(3,orderName);
            pst.setDouble(4,orderPrice);
            pst.setString(5,userAddress);
            pst.setString(6,creditCardNo);
            pst.setString(7,mode);
            pst.setString(8,location);
            pst.setString(9,orderDate);
            System.out.println(orderId + " | " + userName + " | " + orderName + " | " + orderPrice + " | " + userAddress + " | " + creditCardNo + " | " + mode + " | " + " | " + location + " | " + orderDate);
            System.out.println("Inside insertOrder | pst object Set.");
            pst.execute();
        }
        catch(Exception e)
        {
        
        }		
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
                OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"), rs.getString("mode"), rs.getString("location"), rs.getString("orderDate"));
                listOrderPayment.add(order);		
            }					
        }
        catch(Exception e)
        {
            
        }
        return orderPayments;
    }

    public static void insertUser(String username,String password,String repassword,String usertype)
    {
        try
        {	
            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO registration(username,password,repassword,usertype) "
            + "VALUES (?,?,?,?);";	
                    
            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,repassword);
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
            {	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
                    hm.put(rs.getString("username"), user);
            }
        }
        catch(Exception e)
        {
        }
        return hm;			
    }	
}	