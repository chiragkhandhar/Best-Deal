import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Product")

public class Product extends HttpServlet{
	private String productType;
	private String ID;
	private int Stock;
	private String productName;
	private double productPrice;
	private String productImage;
	private String productManufacturer;
	private String productCondition;
	private double productDiscount;
	private String description;	

	
	public Product(String productType, String ID, int Stock, String productName,  double productPrice, String productImage, String productManufacturer, String productCondition, double productDiscount, String description)
	{
		this.productType = productType;
		this.ID = ID;
		this.Stock = Stock;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productImage = productImage;
		this.productManufacturer = productManufacturer;
		this.productCondition = productCondition;
		this.productDiscount = productDiscount;
		this.description = description;	
	}
	
	public Product(){
		
	}

	public String getproductType() {
		return productType;
	}
	public void setproductType(String productType) {
		this.productType = productType;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}

	public int getstock(){
		return Stock;
	}

	public void setstock(int Stock){
		this.Stock = Stock;
	}
	
	public String getproductName() {
		return productName;
	}
	public void setproductName(String productName) {
		this.productName = productName;
	}
	public double getproductPrice() {
		return productPrice;
	}
	
	public void setproductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getproductManufacturer() {
		return productManufacturer;
	}
	public void setproductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}

	public String getproductCondition() {
		return productCondition;
	}

	public void setproductCondition(String productCondition) {
		this.productCondition = productCondition;
	}

	public double getproductDiscount() {
		return productDiscount;
	}

	public void setproductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getproductImage() {
		return productImage;
	}
	public void setproductImage(String productImage) {
		this.productImage = productImage;
	}
}
