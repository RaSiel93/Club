package by.rasiel.club.view.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.rasiel.club.model.People;
import by.rasiel.club.model.enums.Hearts;
import by.rasiel.club.model.enums.Roles;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class GameBarman extends GameFrame {

	static final String PATH = GeneralFrame.PATH + "barman.fxml";
	static final String TITLE = GameFrame.TITLE + "бармена";
	static final Roles ROLE = Roles.BARMAN;

	Group root;
	Group peoples;

	boolean game;

	private List<People> listPeople;

	public GameBarman() {
		super(TITLE, ROLE);
		initContent();
		update();
		game = false;
	}

	public void initContent() {
		peoples = new Group();
		listPeople = new ArrayList<People>();

		initInfoPanel();
		initButtonPanel();
		initGamePanel();

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(infoPanel);
		borderPane.setBottom(buttonPanel);
		borderPane.setCenter(gamePanel);
		borderPane.setId("borderPane");

		Group root = new Group();
		root.getChildren().add(borderPane);
		root.getChildren().add(peoples);

		Scene scene = new Scene(root);

		scene.getStylesheets().add("by/rasiel/club/view/style.css");
		scene.setFill(Color.GREY);
		setScene(scene);
	}

	@Override
	void initGamePanel() {
		initPeople();

		Parent node = null;

		try {
			node = FXMLLoader.load(getClass().getResource(PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gamePanel = new Group();
		gamePanel.getChildren().addAll(node);
	}

	private void initPeople() {
		for (int i = 0; i < 6; i++) {
			generatePeople(65 + i * 94, 280);
		}
	}

	private People generatePeople(int x, int y) {
		final People people = new People(x, y, Hearts.generateHeart(0.2));

		listPeople.add(people);
		peoples.getChildren().add(people);
		return people;
	}

	private void generateNewPeople() {
		People people = generatePeople(65 + 6 * 94, 280);
		people.seats();
	}

	@Override
	void startGame() {
		game = true;
	}

	private void playPeople() {
		for (People people : listPeople) {
			people.seats();
		}
	}

	@Override
	void stopGame() {
		game = false;
	}

	public void smallDone() {
		GameFrame.setCountScore(30);
	}

	public void done() {
		GameFrame.setCountDone(1);
		GameFrame.setCountScore(200);
		playPeople();
		generateNewPeople();
	}

	public void error() {
		GameFrame.setCountError(1);
		GameFrame.setCountScore(-50);
	}

	public boolean isGame() {
		return game;
	}
}
