package by.rasiel.club.view.models;

import java.awt.MouseInfo;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import brickbreaker.Config;
import brickbreaker.Level;
import brickbreaker.LevelData;
import brickbreaker.Splash;
import by.rasiel.club.model.Heart;
import by.rasiel.club.model.Order;
import by.rasiel.club.model.People;
import by.rasiel.club.view.controllers.GeneralController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameBarmanFrame extends GameFrame {

	static final String PATH = GeneralFrame.PATH + "barman.fxml";

	static final String TITLE = GameFrame.TITLE + "бармена";
	static final String ROLE = "Бармен";

	Parent node;
	Group root;
	Group peoples;

	boolean game;

	Timeline timer;
	private List<People> listPeople;

	public GameBarmanFrame() {
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
		final People people = new People(x, y, Heart.generateHeart(0.2));

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
		GameFrame.setCountScore(100);
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
