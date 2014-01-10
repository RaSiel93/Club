package by.rasiel.club.view.controllers;

import by.rasiel.club.main.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PersonController {
	private static final GeneralController controller = Club.getController();

	@FXML
	protected void buttonSecurity(ActionEvent event) {
		controller.changeFrame(Frames.GAME_SECURITY_FRAME);
	}
	
	@FXML
	protected void buttonBarman(ActionEvent event) {
		controller.changeFrame(Frames.GAME_BARMAN_FRAME);
	}

	@FXML
	protected void buttonWaiter(ActionEvent event) {
		controller.changeFrame(Frames.GAME_WAITER_FRAME);
	}

	@FXML
	protected void buttonBackToMenu(ActionEvent event) {
		controller.changeFrame(Frames.MENU_FRAME);
	}
}