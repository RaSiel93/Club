package by.rasiel.club.view.controllers;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.enums.Frames;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {
	private static final GeneralController controller = Club.getController();

	@FXML
	protected void buttonStartGame(ActionEvent event) {
		controller.changeFrame(Frames.PERSON);
	}

	@FXML
	protected void buttonRating(ActionEvent event) {
		controller.changeFrame(Frames.RATING);
	}
	
	@FXML
	protected void buttonExit(ActionEvent event) {
		System.exit(1);
	}
}