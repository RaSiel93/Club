package by.rasiel.club.view.models;

import java.util.ArrayList;
import java.util.List;

import by.rasiel.club.model.People;
import by.rasiel.club.model.enums.Hearts;
import by.rasiel.club.model.enums.Roles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameSecurity extends GameFrame {

	static final String TITLE = GameFrame.TITLE + "охранника";
	static final Roles ROLE = Roles.SECURITY;

	Group root;
	Group peoples;

	Rectangle danceArea;
	Rectangle streetArea;

	Timeline timer;

	private List<People> listPeople;

	public GameSecurity() {
		super(TITLE, ROLE);
		initContent();
		update();
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
		initDanceArea();
		initStreetArea();
		initTimer();

		HBox hBox = new HBox();
		hBox.getChildren().addAll(danceArea, streetArea);
		gamePanel = new Group();
		gamePanel.getChildren().addAll(hBox);
	}

	private void initStreetArea() {
		streetArea = new Rectangle();
		streetArea.setWidth(500);
		streetArea.setHeight(300);
		streetArea.setFill(Color.DARKOLIVEGREEN);
	}

	private void initDanceArea() {

		danceArea = new Rectangle();
		danceArea.setWidth(100);
		danceArea.setHeight(300);
		danceArea.setFill(Color.GRAY);
	}

	private void initTimer() {
		timer = new Timeline();
		timer = new Timeline(new KeyFrame(Duration.seconds(0.3),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						generatePeople();
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	private void generatePeople() {
		final People people = new People(600 + Math.random() * 30,
				40 + Math.random() * 250, Hearts.generateHeart(0.4));

		peoples.getChildren().add(people);
		people.start(100, 30);
		people.initAction();
		listPeople.add(people);
	}

	@Override
	void startGame() {
		timer.play();
	}

	@Override
	void stopGame() {
		timer.stop();
		stopPeoples();
	}

	private void stopPeoples() {
		for (People people : listPeople) {
			people.stop();
		}
	}

	@Override
	public void smallDone() {
		GameFrame.setCountScore(10);
	}

	@Override
	public void done() {
		GameFrame.setCountDone(1);
		GameFrame.setCountScore(100);
	}

	@Override
	public void error() {
		GameFrame.setCountError(1);
		GameFrame.setCountScore(-100);
	}
}
