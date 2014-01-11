package by.rasiel.club.view.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.Glass;
import by.rasiel.club.model.OrderList;
import by.rasiel.club.model.enums.Drinks;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BarmanController implements Initializable {
	private static final GeneralController controller = Club.getController();

	@FXML private Rectangle lvl1;
	@FXML private Rectangle lvl2;
	@FXML private Rectangle lvl3;
	@FXML private Rectangle lvl4;
	@FXML private Rectangle lvl5;

	@FXML private Text text1;
	@FXML private Text text2;
	@FXML private Text text3;
	@FXML private Text text4;
	@FXML private Text text5;

	@FXML
	protected void btnMahito(ActionEvent event) {
		if (isGame()) {
			addIngredient(Drinks.MAHITO);
		}
	}

	@FXML
	protected void btnVodka(ActionEvent event) {
		if (isGame()) {
			addIngredient(Drinks.VODKA);
		}
	}

	@FXML
	protected void btnCola(ActionEvent event) {
		if (isGame()) {
			addIngredient(Drinks.COLA);
		}
	}

	@FXML
	protected void btnLimon(ActionEvent event) {
		if (isGame()) {
			addIngredient(Drinks.LIMON);
		}
	}

	@FXML
	protected void btnTekila(ActionEvent event) {
		if (isGame()) {
			addIngredient(Drinks.TEKILA);
		}
	}

	@FXML
	protected void btnLiker(ActionEvent event) {
		if (isGame()) {
			addIngredient(Drinks.LIKER);
		}
	}

	private void addIngredient(Drinks drink) {
		if (checkIngredient(drink)) {
			glass.addIngredient(drink);
			controller.smallDone();
			level++;
			if (checkComplite()) {
				complite();
			}
		} else {
			error();
		}
	}

	private boolean checkComplite() {
		if (ingredients.size() == level) {
			return true;
		}
		return false;
	}

	private boolean checkIngredient(Drinks drink) {
		if (ingredients.get(level) == drink) {
			return true;
		}
		return false;
	}

	Glass glass;
	OrderList order;
	List<Drinks> ingredients;
	int level;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		glass = new Glass(lvl1, lvl2, lvl3, lvl4, lvl5);
		order = new OrderList(text1, text2, text3, text4, text5);
		ingredients = new ArrayList<Drinks>();
		generateOrderList();
		level = 0;
	}

	private void generateOrderList() {
		ingredients.clear();
		order.clear();
		int countIngredients = 3 + (int) (Math.random() * 3);
		for (int i = 0; i < countIngredients; i++) {
			ingredients.add(Drinks.values()[(int) (Math.random() * 6)]);
		}
		order.addIngredient(ingredients);
	}

	private void complite() {
		glass.clear();
		generateOrderList();
		controller.done();
		level = 0;
	}

	private void error() {
		controller.error();
	}

	private boolean isGame() {
		return controller.isGame();
	}
}