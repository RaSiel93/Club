package by.rasiel.club.view.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.enums.Frames;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class GameOverController implements Initializable {
	private static final GeneralController controller = Club.getController();

	@FXML protected Text error;
	@FXML protected Text done;
	@FXML protected Text score;

	@FXML
	protected void buttonRating(ActionEvent event) {
		controller.changeFrame(Frames.RATING);
	}

	@FXML
	protected void buttonBackToMenu(ActionEvent event) {
		controller.changeFrame(Frames.MENU);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		error.setText(controller.getError().toString());
		done.setText(controller.getDone().toString());
		score.setText(controller.getScore().toString());
	}
}