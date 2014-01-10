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

public class RatingController implements Initializable {
	private static final GeneralController controller = Club.getController();

	@FXML
	protected BorderPane bp;

	@FXML
	protected void buttonBackToMenu(ActionEvent event) {
		controller.changeFrame(Frames.MENU_FRAME);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Rating> data = FXCollections.observableArrayList(controller.getListRating());

		TableColumn numberCol = new TableColumn("#");
        numberCol.setMinWidth(20);
        numberCol.setCellValueFactory(new Callback<CellDataFeatures<Rating, Rating>, ObservableValue<Rating>>() {
            @Override public ObservableValue<Rating> call(CellDataFeatures<Rating, Rating> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });

        numberCol.setCellFactory(new Callback<TableColumn<Rating, Rating>, TableCell<Rating, Rating>>() {
            @Override public TableCell<Rating, Rating> call(TableColumn<Rating, Rating> param) {
                return new TableCell<Rating, Rating>() {
                    @Override protected void updateItem(Rating item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(1 + this.getTableRow().getIndex()+"");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        numberCol.setResizable(false);
        numberCol.setSortable(false);
		
		TableColumn loginCol = new TableColumn("���������");
		loginCol.setMinWidth(114);
		loginCol.setCellValueFactory(new PropertyValueFactory<Rating, String>(
				"login"));
		loginCol.setResizable(false);
		loginCol.setSortable(false);

		TableColumn roleCol = new TableColumn("����");
		roleCol.setMinWidth(80);
		roleCol.setCellValueFactory(new PropertyValueFactory<Rating, String>(
				"role"));
		roleCol.setResizable(false);
		roleCol.setSortable(false);

		TableColumn<Rating, Integer> scoreCol = new TableColumn("������� ����");
		scoreCol.setMinWidth(90);
		scoreCol.setCellValueFactory(new PropertyValueFactory<Rating, Integer>(
				"score"));
		scoreCol.setResizable(false);
        scoreCol.setSortable(false);

		TableView<Rating> table = new TableView<Rating>();
		table.setItems(data);
		table.getColumns().addAll(numberCol, loginCol, roleCol, scoreCol);
		
		bp.setCenter(table);
	}
}