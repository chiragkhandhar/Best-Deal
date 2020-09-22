import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParserDataStore extends DefaultHandler {
    TVType tv;
    SSType soundsystem;
    PhoneType phone;
    Accessory accessory;
    static HashMap<String,TVType> tvs;
    static HashMap<String,SSType> soundsystems;
    static HashMap<String,PhoneType> phones;
    static HashMap<String,Accessory> accessories;
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    tvs = new HashMap<String, TVType>();
	soundsystems=new  HashMap<String, SSType>();
	phones=new HashMap<String, PhoneType>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}
	
	// when xml start element is parsed store the id into respective hashmap for TV, soundsystems etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("TV")) 
		{
			currentElement="TV";
			tv = new TVType();
            tv.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("phone"))
		{
			currentElement="phone";
			phone = new PhoneType();
            phone.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("soundsystem"))
		{
			currentElement="soundsystem";
			soundsystem= new SSType();
            soundsystem.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("TV"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for TV,soundsystems etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("TV")) {
			tvs.put(tv.getId(),tv);
			return;
        }
 
        if (element.equals("phone")) {	
			phones.put(phone.getId(),phone);
			return;
        }
        if (element.equals("soundsystem")) {	  
			soundsystems.put(soundsystem.getId(),soundsystem);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("TV")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("TV")) {
			tv.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("TV"))
				tv.setImage(elementValueRead);
        	if(currentElement.equals("soundsystem"))
				soundsystem.setImage(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("TV"))
				tv.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("soundsystem"))
				soundsystem.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("TV"))
				tv.setCondition(elementValueRead);
        	if(currentElement.equals("soundsystem"))
				soundsystem.setCondition(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("TV"))
				tv.setRetailer(elementValueRead);
        	if(currentElement.equals("soundsystem"))
				soundsystem.setRetailer(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("TV"))
				tv.setName(elementValueRead);
        	if(currentElement.equals("soundsystem"))
				soundsystem.setName(elementValueRead);
            if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("TV"))
				tv.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("soundsystem"))
				soundsystem.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
        }

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Tutorial_1\\ProductCatalog.xml");
    } 
}
