import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class test {
  public static void main(String[] args) {
    String line = "";
    String splitBy = ",";
    try {
      //parsing a CSV file into BufferedReader class constructor  
      BufferedReader br = new BufferedReader(new FileReader("F:\\MS\\SEM3\\EWA\\assignment4_transactions.csv"));
      while ((line = br.readLine()) != null)
      //returns a Boolean value  
      {
        String[] row = line.split(splitBy);
        //use comma as separator  
        System.out.println("CREATE ("+getC(row[1])+")-[:ORDERS {orderID:'"+row[4]+"', customerName: '"+row[1]+"', rating: '"+row[12]+"', zip: '"+row[14]+"', status: '"+row[16]+"', onTime:'"+row[18]+"', returned: '"+row[17]+"'  }]->("+getP(row[9])+")");
      }
    }
    catch(IOException e) {
      e.printStackTrace();
    }
  }

  public static String getC(String name)
  {
      switch ((name)) {
        case "harsh":
        return "c1";

        case "ninad":
        return "c2";

        case "chirag":
        return "c3";

        case "chris":
        return "c4";

        case "nishant":
        return "c5";

        case "akash":
        return "c6";

        case "satyaveer":
        return "c7";

        case "dhiraj":
        return "c8";

        case "jose":
        return "c9";

        case "jean":
        return "c10";
      }
      return "na";
  }

  public static String getP(String name)
  {
      switch ((name)) {
        case "Apple Macbook Air":
        return "p1";

        case "Apple Macbook Pro":
        return "p2";

        case "Apple Macbook Pro 16":
        return "p3";

        case "Amazon - Echo Dot (3rd Gen) - Smart Speaker with Alexa - Charcoal":
        return "p4";

        case "Amazon - Echo Studio Smart Speaker with Alexa - Charcoal":
        return "p5";

        case "Amazon - Echo Dot Kids Edition Smart Speaker with Alexa - Rainbow":
        return "p6";

        case "Apple iPhone 11":
        return "p7";

        case "Apple iPhone 11 Pro":
        return "p8";

        case "Apple iPhone 11 Pro Max":
        return "p9";

        case "Dell Inspiron 6546":
        return "p10";
      }
      return "na";
  }

  public static void generateProduct(){
    String [] productIDs = {"al1", "al2", "al3", "amz1", "amz2", "amz3", "ap1", "ap2", "ap3", "dl1"};
    String [] productNames = {"Apple Macbook Air", "Apple Macbook Pro", "Apple Macbook Pro 16", "Amazon - Echo Dot (3rd Gen) - Smart Speaker with Alexa - Charcoal", "Amazon - Echo Studio Smart Speaker with Alexa - Charcoal", "Amazon - Echo Dot Kids Edition Smart Speaker with Alexa - Rainbow", "Apple iPhone 11", "Apple iPhone 11 Pro", "Apple iPhone 11 Pro Max", "Dell Inspiron 6546"};
    String [] categories = {"laptop", "laptop","laptop", "va", "va", "va", "phone", "phone", "phone", "laptop"};
    
    for(int i = 0; i< 10; i++){
        System.out.println("CREATE (p"+ (i+1) + ":Product {name:'"+productNames[i]+"', id: '"+productIDs[i]+"', category:'"+categories[i]+"'})");
    }
  }
}

