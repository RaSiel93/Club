package by.rasiel.club.model;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javafx.util.Callback;

public class ListRating {
	private List<Rating> listRating;

	public ListRating() {
		listRating = new ArrayList<Rating>();
	}

	public void loadRating() {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			SAXParserXML saxParser = new SAXParserXML();

			File file = new File(".\\rating.xml");

			parser.parse(file, saxParser);
			listRating = saxParser.getRatings();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void saveRating() {
		try {
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = f.newDocumentBuilder();
			Document doc = (Document) builder.newDocument();

			Element ratingsXML = doc.createElement("ratings");
			doc.appendChild(ratingsXML);

			for (Rating rating : listRating) {
				Element ratingXML = doc.createElement("rating");
				ratingsXML.appendChild(ratingXML);
				ratingXML.setAttribute("login",
						String.valueOf(rating.getLogin()));
				ratingXML
						.setAttribute("role", String.valueOf(rating.getRole()));
				ratingXML.setAttribute("score",
						String.valueOf(rating.getScore()));
			}
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.transform(new DOMSource(doc), new StreamResult(
					new FileOutputStream(".//rating.xml")));
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException | TransformerException e) {
			e.printStackTrace();
		}
	}

	public void addRating(Rating rating) {
		listRating.add(rating);
		saveRating();
	}

	public List<Rating> getListRating() {
		Collections.sort(listRating);
		return listRating;
	}
}
