package by.rasiel.club.model;

import javafx.event.EventHandler;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Order extends Circle {
	public Order(double x, double y) {
		super(x, y, 10);
		setEffect(new Lighting());
		setFill(Color.GOLD);
		setAction();

	}

	private void setAction() {
		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setCenterX(event.getX());
				setCenterY(event.getY());
			}
		});
	}
}
