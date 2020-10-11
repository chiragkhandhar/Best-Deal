import java.io.IOException;
import java.io.*;

public class StoreLocation implements Serializable
{
    private String storeID, street, city, state, zip;

    public StoreLocation(String storeID, String street, String city, String state, String zip){
        this.storeID = storeID;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStoreID(){
        return storeID;
    }

    public void setStoreID(String storeID){
        this.storeID = storeID;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getZip(){
        return zip;
    }

    public void setZip(String zip){
        this.zip = zip;
    }

    
}