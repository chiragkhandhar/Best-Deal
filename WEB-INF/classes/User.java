import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;



/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable{
	private String userID;
	private String name;
	private String password;
	private String usertype;
	
	public User(String userID, String name, String password, String usertype) {
		this.userID = userID;
		this.name=name;
		this.password=password;
		this.usertype=usertype;
	}

	public String getId() {
		return userID;
	}

	public void setId(String userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
}
