package by.rasiel.club.view.models;

import java.io.IOException;

import brickbreaker.Main;
import brickbreaker.Main.MainFrame;
import by.rasiel.club.main.Club;
import by.rasiel.club.view.controllers.GeneralController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameOverFrame extends GeneralFrame{
	
	static final String TITLE = GeneralFrame.TITLE + " - " + "Итоги игры";
	static final String PATH = GeneralFrame.PATH + "game_over.fxml";

	Parent node;

	public GameOverFrame() {
		super(TITLE, PATH);
	}

	public void initContent() {
	}
}
