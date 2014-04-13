package xml;

import java.util.ArrayList;

import helpers.bean.*;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/*
 * !-- XML READER CLASS --!
 * XMLReader is used to read data from a XML file.
 * Data needed to be read is usernames and passwords
 * existing in database
 */
public class XMLReader extends DefaultHandler {
	//Login details
	private String name;
	private Integer pts;
	
	//List of users
	private ArrayList<String> groupNames = new ArrayList<String>();
	private ArrayList<Integer> points = new ArrayList<Integer>();
	
	//Constructor of XML Reader class
	public XMLReader() {
		new DefaultHandler();
	}

	public void startElement(String ns,
			String local,
			String name,
			org.xml.sax.Attributes attributes)
					throws SAXException {
		if(name.equals("group")) {
			name = attributes.getValue("name").toString();
			pts = Integer.parseInt(attributes.getValue("points"));
		}
		
	}
	
	public void endElement(String ns, String local, String name2) throws SAXException {		
		if (name2.equals("group")){
			groupNames.add(name);
			points.add(pts);
		}
	}

	public ArrayList<String> getGroupNames() {
		return groupNames;
	}
	
	public ArrayList<Integer> getPoints() {
		return points;
	}
}