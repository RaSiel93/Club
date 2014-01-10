package by.rasiel.club.view.models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class GeneralFrame extends Stage{	
	static final String PATH = "../frames/";
	static final String TITLE = "Клуб";
	
	Parent node;

	public GeneralFrame(){
		setResizable(false);
	}
	
	public GeneralFrame(String title) {
		setTitle(title);
	}
	
	public GeneralFrame(String title, String path) {
		try {
			node = FXMLLoader.load(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle(title);
		setScene(new Scene(node));
	}

	public abstract void initContent();
}
