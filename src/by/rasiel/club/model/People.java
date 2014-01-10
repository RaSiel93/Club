package by.rasiel.club.model;

import by.rasiel.club.view.models.GameFrame;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class People extends Circle {
	private Heart heart;

	SequentialTransition moveAnimation;

	public People(double x, double y, Heart heart) {
		super(x, y, 16);
		this.heart = heart;
		setFill(getColor(heart));
		setEffect(new Lighting());
	}

	public void initAction() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				click();
				stop();
			}

			private void click() {
				setVisible(false);
				if (isGoodHeart()) {
					GameFrame.setCountError(1);
					GameFrame.setCountScore(-100);
				} else {
					GameFrame.setCountDone(1);
					GameFrame.setCountScore(10);
				}
			}
		});
	}

	private Paint getColor(Heart heart) {
		if (isGoodHeart()) {
			return Color.web("1c89f4");
		}
		return Color.RED;
	}

	public boolean isGoodHeart() {
		if (heart == Heart.GOOD) {
			return true;
		}
		return false;
	}

	public void start(int dance_width, double speed) {

		TranslateTransition move = new TranslateTransition(
				Duration.seconds(100 / speed));
		move.setFromX(0);
		move.setToX(-this.getCenterX() + dance_width);

		FadeTransition fade = new FadeTransition(Duration.millis(2000));
		fade.setFromValue(1.0f);
		fade.setToValue(0.0f);

		Animation onFinished = new Transition() {
			{
				setCycleDuration(Duration.millis(0.1));
				setCycleCount(1);
			}

			protected void interpolate(double frac) {
				finish();
				setVisible(false);
			}

			private void finish() {
				if (isGoodHeart()) {
					GameFrame.setCountScore(10);
				} else {
					GameFrame.setCountError(1);
					GameFrame.setCountScore(-30);
				}
			}
		};

		moveAnimation = new SequentialTransition(this, move, onFinished);
		moveAnimation.play();
	}

	public void stop() {
		moveAnimation.stop();
	}

	public void move() {
		int toward = 1;
		if (Math.random() > 0.5) {
			toward = -1;
		}

		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.setX(this.getCenterX());
		moveTo.setY(this.getCenterY());

		QuadCurveTo quadCurveTo = new QuadCurveTo();
		quadCurveTo.setX(this.getCenterX() + 20 * toward);
		quadCurveTo.setY(this.getCenterY());
		quadCurveTo.setControlX(this.getCenterX() + 20 * toward);
		quadCurveTo.setControlY(this.getCenterY() - 60);

		path.getElements().add(moveTo);
		path.getElements().add(quadCurveTo);

		final PathTransition transition = generatePathTransition(this, path);
		transition.play();
	}

	private PathTransition generatePathTransition(final Shape shape,
			final Path path) {
		final PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.seconds(1.0));
		pathTransition.setDelay(Duration.seconds(Math.random() * 2));
		pathTransition.setPath(path);
		pathTransition.setNode(shape);
		pathTransition
				.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(Timeline.INDEFINITE);
		pathTransition.setAutoReverse(true);
		return pathTransition;
	}

	public void seats() {
		Path path = new Path();

		MoveTo moveTo = new MoveTo();
		moveTo.setX(this.getCenterX());
		moveTo.setY(this.getCenterY());

		QuadCurveTo quadCurveTo = new QuadCurveTo();
		quadCurveTo.setX(this.getCenterX() - 94);
		quadCurveTo.setY(this.getCenterY());
		quadCurveTo.setControlX(this.getCenterX() - 50);
		quadCurveTo.setControlY(this.getCenterY() - 50);

		path.getElements().add(moveTo);
		path.getElements().add(quadCurveTo);

		final PathTransition transition = generatePathTransition(this, path);
		transition.setCycleCount(1);
		transition.setOnFinished(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				setCenterX(getCenterX() + getTranslateX());
				setTranslateX(0);
			}
		});
		transition.play();
	}
}
