package by.rasiel.club.model;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserXML extends DefaultHandler {

	List<Rating> ratings;

	private String login;
	private String role;
	private Integer score;

	String thisElement = "";

	public void startDocument() throws SAXException {
		ratings = new ArrayList<Rating>();
	}

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		thisElement = qName;
		if (thisElement.equals("rating")) {
			login = atts.getValue("login");
			role = atts.getValue("role");
			score = Integer.parseInt(atts.getValue("score"));
		}
	}

	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		thisElement = qName;
		if (thisElement.equals("rating")) {
			ratings.add(new Rating(login, role, score));
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	public void endDocument() {
	}

	public List<Rating> getRatings() {
		return ratings;
	}
}