package by.rasiel.club.model.profile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileController {

	private static FileController fileController = null;

	public static FileController getInstance() {
		if (fileController == null) {
			fileController = new FileController();
		}
		return fileController;
	}

	public List<Profile> load() {
		List<Profile> profiles = new ArrayList<Profile>();

		try {
			Document root = getRootDocument("profiles.xml");

			root.getDocumentElement().normalize();

			NodeList listProfile = root.getElementsByTagName("profile");

			for (int i = 0; i < listProfile.getLength(); i++) {
				Element personXML = (Element) listProfile.item(i);
				
				Element loginXML = (Element) personXML.getElementsByTagName(
						"login").item(0);
				String login = ((Node) loginXML).getChildNodes().item(0)
						.getNodeValue().trim();

				Element scoreXML = (Element) personXML.getElementsByTagName("score").item(0);
				Element securityXML = (Element) scoreXML.getElementsByTagName(
						"security").item(0);
				int scorePerSecurity = Integer.parseInt(((Node) securityXML).getChildNodes().item(0)
						.getNodeValue().trim());
				Element waiterXML = (Element) scoreXML.getElementsByTagName(
						"waiter").item(0);
				int scorePerWaiter = Integer.parseInt(((Node) waiterXML).getChildNodes().item(0)
						.getNodeValue().trim());
				Element barmanXML = (Element) scoreXML.getElementsByTagName(
						"barman").item(0);
				int scorePerBarman = Integer.parseInt(((Node) barmanXML).getChildNodes().item(0)
						.getNodeValue().trim());

				profiles.add(new Profile(login, scorePerSecurity,
						scorePerWaiter, scorePerBarman));
			}
		} catch (Exception t) {
			t.printStackTrace();
		}

		return profiles;
	}

	public void save(List<Profile> profiles) {
		Document root = createDocument(profiles);
		saveInFile(root);
	}

	private Document createDocument(List<Profile> profiles) {
		Document root = getRootDocument(null);

		Element profilesXML = root.createElement("profiles");
		root.appendChild(profilesXML);

		for (Profile profile : profiles) {
			Element loginXML = root.createElement("login");
			loginXML.appendChild(root.createTextNode(profile.getLogin()));

			Element scoreFerSecurityXML = root.createElement("security");
			scoreFerSecurityXML.appendChild(root.createTextNode(String
					.valueOf(profile.getScorePerSecurity())));

			Element scoreFerWaiterXML = root.createElement("waiter");
			scoreFerWaiterXML.appendChild(root.createTextNode(String
					.valueOf(profile.getScorePerWaiter())));

			Element scoreFerBarmanXML = root.createElement("barman");
			scoreFerBarmanXML.appendChild(root.createTextNode(String
					.valueOf(profile.getScorePerBarman())));

			Element scoreXML = root.createElement("score");
			scoreXML.appendChild(scoreFerSecurityXML);
			scoreXML.appendChild(scoreFerWaiterXML);
			scoreXML.appendChild(scoreFerBarmanXML);

			Element profileXML = root.createElement("profile");
			profileXML.appendChild(loginXML);
			profileXML.appendChild(scoreXML);
			profilesXML.appendChild(profileXML);
		}

		return root;
	}

	private Document getRootDocument(String path) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory
					.newInstance().newDocumentBuilder();
			if (path == null) {
				return documentBuilder.newDocument();
			} else {
				return documentBuilder.parse(new File(path));
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void saveInFile(Document root) {
		try {
			TransformerFactory
					.newInstance()
					.newTransformer()
					.transform(
							new DOMSource(root),
							new StreamResult(new FileOutputStream(
									".//profiles.xml")));
		} catch (FileNotFoundException | TransformerException
				| TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

}
