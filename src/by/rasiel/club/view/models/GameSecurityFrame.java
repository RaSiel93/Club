package by.rasiel.club.view.models;

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

public class GameSecurityFrame extends GameFrame {

	static final String TITLE = GameFrame.TITLE + "охранника";
	static final String ROLE = "Охранник";

	Parent node;
	Group root;
	Group peoples;

	Rectangle danceArea;
	Rectangle streetArea;

	Timeline timer;

	private List<People> listPeople;
	
	public GameSecurityFrame() {
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
				40 + Math.random() * 250, Heart.generateHeart(0.4));

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
		for(People people : listPeople){
			people.stop();
		}
	}
}
