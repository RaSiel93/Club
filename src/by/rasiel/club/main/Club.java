package by.rasiel.club.main;

import javafx.application.Application;
import javafx.stage.Stage;
import by.rasiel.club.model.enums.Frames;
import by.rasiel.club.view.controllers.GeneralController;

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
		frameController.changeFrame(Frames.AUTH);
	}
}
