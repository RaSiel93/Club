package by.rasiel.club.view.controllers;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.enums.Frames;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AuthorizationController {
	private static final GeneralController controller = Club.getController();

	@FXML
	private Text actiontarget;
	
	@FXML
	private TextField loginField;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		String login;
		if ((login = loginField.getText()).length() > 0) {
			controller.setLogin(login);
			controller.addProfile(login);
			controller.changeFrame(Frames.MENU);
		} else {
			actiontarget.setText("Вы не ввели псевдоним");
		}
	}

}