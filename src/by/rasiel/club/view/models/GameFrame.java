package by.rasiel.club.view.models;

import by.rasiel.club.main.Club;
import by.rasiel.club.model.enums.Frames;
import by.rasiel.club.model.enums.Roles;
import by.rasiel.club.view.controllers.GeneralController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class GameFrame extends GeneralFrame {

	static final String TITLE = GeneralFrame.TITLE + " - " + "Èãðà çà ";
	private static final GeneralController controller = Club.getController();

	Group infoPanel;
	Group buttonPanel;
	Group gamePanel;

	private Roles role;

	static Integer countError;
	static Integer countDone;
	static Integer countScore;
	private Integer countTime;

	private Text t_errorCaption;
	private static Text t_error;
	private Text t_doneCaption;
	private static Text t_done;
	private Text t_scoreCaption;
	private static Text t_score;
	private Text t_timeCaption;
	private Text t_time;

	private Button btn_start;
	private Button btn_back;

	private Timeline timer;

	public GameFrame(String title, Roles role) {
		super(title);
		this.role = role;

		setResizable(false);
		
		initStats();
		initText();
		initTimer();
	}

	private void initStats() {
		countError = 0;
		countDone = 0;
		countScore = 0;
		countTime = 60;
	}

	private void initText() {
		t_errorCaption = new Text();
		t_error = new Text();
		t_doneCaption = new Text();
		t_done = new Text();
		t_scoreCaption = new Text();
		t_score = new Text();
		t_timeCaption = new Text();
		t_time = new Text();
	}

	private void initTimer() {
		timer = new Timeline(new KeyFrame(Duration.seconds(1),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						goTime();
						if (timeDone()) {
							gameOver();
						}
					}

					private boolean timeDone() {
						return countTime <= 0;
					}

					private void goTime() {
						countTime--;
						updateTime();
					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	public static void setCountError(int value) {
		countError += value;
		updateError();
	}

	public static void setCountDone(int value) {
		countDone += value;
		updateDone();
	}

	public static void setCountScore(int value) {
		countScore += value;
		updateScore();
	}

	protected void initInfoPanel() {
		initFields();
		infoPanel = new Group();
		infoPanel.getChildren().addAll(getSpacing(20), t_errorCaption, t_error,
				t_doneCaption, t_done, t_scoreCaption, t_score, t_timeCaption,
				t_time);
	}

	private void initFields() {
		initField(t_errorCaption, t_error, 0, "ÎØÈÁÊÈ: ", Color.FIREBRICK);
		initField(t_doneCaption, t_done, 120, "ÏÐÀÂÈËÜÍÎ: ", Color.GREEN);
		initField(t_scoreCaption, t_score, 260, "ÊÎËÈ×ÅÑÒÂÎ Î×ÊÎÂ: ",
				Color.DARKBLUE);
		initField(t_timeCaption, t_time, 500, "ÂÐÅÌß: ", Color.BLACK);
	}

	protected void initButtonPanel() {
		initButtons();

		HBox hBox = getHBox();
		hBox.getChildren().addAll(btn_start, btn_back);

		buttonPanel = new Group();
		buttonPanel.getChildren().addAll(getSpacing(30), hBox);
	}

	private HBox getHBox() {
		HBox hBox = new HBox(10);
		hBox.setPrefWidth(600);
		hBox.setPrefHeight(30);
		hBox.setAlignment(Pos.CENTER);
		return hBox;
	}

	private void initButtons() {
		btn_start = new Button("Íà÷àòü èãðó");
		btn_start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				start();
				btn_start.setDisable(true);
			}
		});

		btn_back = new Button("Íàçàä â ìåíþ");
		btn_back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				stop();
				controller.changeFrame(Frames.MENU);
			}
		});
	}

	private Rectangle getSpacing(int height) {
		return new Rectangle(1, height);
	}
	
	private void initField(Text text1, Text text2, int translateX,
			String label, Color color) {
		Font f = new Font("Impact", 18);

		text1.setText(label);
		text1.setTextOrigin(VPos.TOP);
		text1.setFill(color);
		text1.setFont(f);
		text1.setTranslateX(translateX);

		text2.setTextOrigin(VPos.TOP);
		text2.setFill(color);
		text2.setFont(f);
		text2.setTranslateX(translateX + text1.getBoundsInLocal().getWidth());
	}

	public static void updateError() {
		t_error.setText(countError.toString());
	}

	public static void updateDone() {
		t_done.setText(countDone.toString());
	}

	public static void updateScore() {
		t_score.setText(countScore.toString());
	}

	public void updateTime() {
		t_time.setText(countTime.toString());
	}

	public void update() {
		updateError();
		updateDone();
		updateScore();
		updateTime();
	}

	abstract void initGamePanel();
	abstract void startGame();
	abstract void stopGame();
	public abstract void done();
	public abstract void smallDone();
	public abstract void error();
	
	
	public void start() {
		initStats();
		update();
		timer.play();
		startGame();
	}
	
	public void stop() {
		timer.stop();
		stopGame();
	}

	private void gameOver() {
		stop();
		controller.addRating(countError, countDone, role, countScore);
		controller.changeFrame(Frames.GAME_OVER);
	}
}
