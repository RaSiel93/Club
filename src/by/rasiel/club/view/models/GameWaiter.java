package by.rasiel.club.view.models;

import java.util.ArrayList;
import java.util.List;

import by.rasiel.club.model.OrderForWaiter;
import by.rasiel.club.model.People;
import by.rasiel.club.model.enums.Hearts;
import by.rasiel.club.model.enums.Roles;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GameWaiter extends GameFrame {

	static final String TITLE = GameFrame.TITLE + "официанта";
	static final Roles ROLE = Roles.WAITER;

	Group root;
	Group peoples;

	Rectangle danceArea;
	Rectangle chilloutArea;
	Rectangle barArea;

	Timeline timer;
	private List<People> listPeople;

	private OrderForWaiter order;

	public GameWaiter() {
		super(TITLE, ROLE);
		initContent();
		update();
	}

	public void initContent() {
		peoples = new Group();
		listPeople = new ArrayList<People>();
		initPeople();

		initInfoPanel();
		initButtonPanel();
		initGamePanel();
		initTimer();
		playPeople();

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

	private void initTimer() {
		timer = new Timeline();
		timer = new Timeline(new KeyFrame(Duration.seconds(0.1),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if (order != null) {
							for (People people : listPeople) {
								Circle circle = new Circle(people.getCenterX()
										+ people.getTranslateX(), people
										.getCenterY() + people.getTranslateY(),
										10);
								if (order.intersects(circle.getBoundsInLocal())) {
									order.setVisible(false);
									order = null;
									error();
									break;
								}
							}
						}
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	@Override
	void initGamePanel() {
		initBarArea();
		initDanceArea();
		initChilloutArea();

		HBox hBox = new HBox();
		hBox.getChildren().addAll(barArea, danceArea, chilloutArea);
		gamePanel = new Group();
		gamePanel.getChildren().addAll(hBox);
	}

	private void initAction() {
		barArea.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (order != null) {
					order.setVisible(false);
					order = null;
				}
				order = new OrderForWaiter(event.getX(), event.getY() + 20);
				peoples.getChildren().add(order);
			}
		});
		chilloutArea.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (order != null && order.getCenterX() > 500) {
					order.setVisible(false);
					order = null;
					done();
				}
			}
		});
	}

	private void initBarArea() {
		barArea = new Rectangle();
		barArea.setWidth(100);
		barArea.setHeight(300);
		barArea.setFill(Color.DARKBLUE);
	}

	private void initDanceArea() {
		danceArea = new Rectangle();
		danceArea.setWidth(400);
		danceArea.setHeight(300);
		danceArea.setFill(Color.GRAY);
	}

	private void initChilloutArea() {
		chilloutArea = new Rectangle();
		chilloutArea.setWidth(100);
		chilloutArea.setHeight(300);
		chilloutArea.setFill(Color.DARKORCHID);
	}

	private void initPeople() {
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 4; y++) {
				generatePeople(x, y);
			}
		}
	}

	private void generatePeople(int x, int y) {
		final People people = new People(130 + x * 400 / 5 + Math.random()
				* 400 / 5 / 4, 60 + y * 300 / 4 + Math.random() * 300 / 4 / 4,
				Hearts.generateHeart(0.2));

		listPeople.add(people);
		peoples.getChildren().add(people);
	}

	@Override
	void startGame() {
		initAction();
		timer.play();
	}

	private void playPeople() {
		for (People people : listPeople) {
			people.move();
		}
	}

	@Override
	void stopGame() {
		timer.stop();
	}

	@Override
	public void smallDone() {
		GameFrame.setCountScore(10);
	}

	@Override
	public void done() {
		GameFrame.setCountDone(1);
		GameFrame.setCountScore(400);
	}

	@Override
	public void error() {
		GameFrame.setCountError(1);
		GameFrame.setCountScore(-50);
	}
}
