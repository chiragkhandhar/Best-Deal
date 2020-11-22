import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class ProductRecommenderUtility
{	
	static Connection conn = null;
    static String message;
	
	public static String getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bestdeal","root","IllinoisTech2021@");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}

    public HashMap<String,String> readOutputFile()
    {
		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
        try 
        {
            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\Tutorial_1\\matrixFactorizationBasedRecommendations.csv")));
            while ((line = br.readLine()) != null) 
            {
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }
			
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
		}
		
		return prodRecmMap;
	}
	
    public static Product getProduct(String product)
    {
        System.out.println("-----------Inside getProduct()----------");
        System.out.println("Requested Product = " + product);
        
        Product prodObj = new Product();

        try 
		{
            ArrayList<Product> productList = MySqlDataStoreUtilities.getInventory();
            for(Product temp : productList)
            {
                if(temp.getproductName().contains(product))
                {
                    prodObj = temp;
                    break;
                }
            }

		} 
		catch (Exception ex) 
		{
            System.out.println(ex.getMessage());
        }
        System.out.println("Product Found = " + prodObj.getproductName());
        System.out.println("------------------------------------");
        return prodObj;
	}
}