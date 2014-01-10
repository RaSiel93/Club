package by.rasiel.club.main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import brickbreaker.Config;
import brickbreaker.Level;
import brickbreaker.LevelData;
import brickbreaker.Splash;
import by.rasiel.club.view.controllers.Frames;
import by.rasiel.club.view.controllers.GeneralController;
import by.rasiel.club.view.models.AuthFrame;
import by.rasiel.club.view.models.MenuFrame;

public class Club extends Application {

	private static GeneralController frameController;
	

	public static void main(String[] args) {
		launch(args);
	}

	public static GeneralController getController() {
		return frameController;
	}
	
	@Override
	public void start(Stage frame) {
		frameController = new GeneralController(frame);
		frameController.initFrames();
		frameController.changeFrame(Frames.AUTH_FRAME);
	}
}
