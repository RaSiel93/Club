package by.rasiel.club.view.controllers;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.ListRating;
import by.rasiel.club.model.Rating;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class GameOverController implements Initializable {
	private static final GeneralController controller = Club.getController();

	@FXML protected Text error;
	@FXML protected Text done;
	@FXML protected Text role;
	@FXML protected Text score;

	@FXML
	protected void buttonRating(ActionEvent event) {
		controller.changeFrame(Frames.RATING_FRAME);
	}

	@FXML
	protected void buttonBackToMenu(ActionEvent event) {
		controller.changeFrame(Frames.MENU_FRAME);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		error.setText(controller.getError().toString());
		done.setText(controller.getDone().toString());
		role.setText(controller.getRole());
		score.setText(controller.getScore().toString());
	}
}