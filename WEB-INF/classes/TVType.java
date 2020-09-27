import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TVType")


/* 
	TVType class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	TVType class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class TVType extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String description;
	private String retailer;
	private String condition;
	private double discount;
	HashMap<String,String> accessories;
	public TVType(String name, double price, String image, String retailer,String condition,double discount){
		this.name=name;
		this.price=price;
		this.image=image;
		this.description = description;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
        this.accessories=new HashMap<String,String>();
	}
	
    HashMap<String,String> getAccessories() {
		return accessories;
		}

	public TVType(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
