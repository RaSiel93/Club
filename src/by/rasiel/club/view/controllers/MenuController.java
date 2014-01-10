package by.rasiel.club.view.controllers;

import by.rasiel.club.main.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MenuController {
	private static final GeneralController controller = Club.getController();

	@FXML
	protected void buttonStartGame(ActionEvent event) {
		controller.changeFrame(Frames.PERSON_FRAME);
	}

	@FXML
	protected void buttonRating(ActionEvent event) {
		controller.changeFrame(Frames.RATING_FRAME);
	}
	
	@FXML
	protected void buttonExit(ActionEvent event) {
		System.exit(1);
	}
}