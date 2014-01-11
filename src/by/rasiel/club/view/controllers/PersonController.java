package by.rasiel.club.view.controllers;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.enums.Frames;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PersonController {
	private static final GeneralController controller = Club.getController();

	@FXML
	protected void buttonSecurity(ActionEvent event) {
		controller.changeFrame(Frames.GAME_SECURITY);
	}
	
	@FXML
	protected void buttonBarman(ActionEvent event) {
		controller.changeFrame(Frames.GAME_BARMAN);
	}

	@FXML
	protected void buttonWaiter(ActionEvent event) {
		controller.changeFrame(Frames.GAME_WAITER);
	}

	@FXML
	protected void buttonBackToMenu(ActionEvent event) {
		controller.changeFrame(Frames.MENU);
	}
}