package by.rasiel.club.view.models;

import java.io.IOException;

import brickbreaker.Config;
import brickbreaker.Level;
import brickbreaker.LevelData;
import brickbreaker.Splash;
import by.rasiel.club.view.controllers.GeneralController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RatingFrame extends GeneralFrame {

	static final String TITLE = GeneralFrame.TITLE + " - " + "Рейтинг";
	static final String PATH = GeneralFrame.PATH + "rating.fxml";
	
	Parent node;

	public RatingFrame() {
		super(TITLE, PATH);
	}

	public void initContent() {
	}
}
