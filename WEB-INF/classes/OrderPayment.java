import java.io.IOException;
import java.io.*;

public class OrderPayment implements Serializable
{
	private String userID;
	private String userName;
	private String userAddress;
	private String creditCardNo;
	private int orderId;
	private String productID;
	private String orderName;
	private String category;
	private String orderDate;
	private String shipDate;
	private double orderPrice;
	private int quantity;
	private double discount;
	private double shippingCost;
	private double netTotal;	
	private String mode;
	private String storeID;
	private String location;
	
	
	public OrderPayment(String userID, String userName, String userAddress, String creditCardNo, int orderId, String productID, String orderName, String category, String orderDate, String shipDate, double orderPrice, int quantity, double discount, double shippingCost, double netTotal, String mode, String storeID, String location )
	{
		this.userID = userID;
		this.userName = userName;
		this.userAddress = userAddress;
		this.creditCardNo = creditCardNo;
		this.orderId = orderId;	
		this.productID = productID;	
		this.orderName = orderName;
		this.category = category;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.orderPrice = orderPrice;
		this.quantity = quantity;
		this.discount = discount;		
		this.shippingCost = shippingCost;
		this.netTotal = netTotal;
		this.mode = mode;
		this.storeID = storeID;
		this.location = location;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderID) {
		this.orderId = orderID;
	}

	public String getProductId() {
		return productID;
	}

	public void setProductId(String productID) {
		this.productID = productID;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public double getDiscount(){
		return discount;
	}

	public void setDiscount(double discount){
		this.discount = discount;
	}

	public double getShippingCost(){
		return shippingCost;
	}

	public void setShippingCost(double shippingCost){
		this.shippingCost = shippingCost;
	}

	public double getNetTotal(){
		return netTotal;
	}

	public void setNetTotal(double netTotal){
		this.netTotal = netTotal;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
