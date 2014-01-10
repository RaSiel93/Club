package by.rasiel.club.view.controllers;

import java.io.IOException;
import java.util.List;

import by.rasiel.club.model.ListRating;
import by.rasiel.club.model.Rating;
import by.rasiel.club.view.models.AuthFrame;
import by.rasiel.club.view.models.GameBarmanFrame;
import by.rasiel.club.view.models.GameOverFrame;
import by.rasiel.club.view.models.GameSecurityFrame;
import by.rasiel.club.view.models.GameWaiterFrame;
import by.rasiel.club.view.models.GeneralFrame;
import by.rasiel.club.view.models.MenuFrame;
import by.rasiel.club.view.models.PersonFrame;
import by.rasiel.club.view.models.RatingFrame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GeneralController {
	private final String LOGIN_DEFAULT = "Аноним";

	private Stage currentFrame;
	private GameBarmanFrame barFrame;

	private ListRating listRating;

	private MenuFrame menuFrame;
	private AuthFrame authFrame;
	private PersonFrame personFrame;

	private String login = LOGIN_DEFAULT;
	private Integer error;
	private Integer done;
	private String role;
	private Integer score;

	public GeneralController(Stage frame) {
		currentFrame = frame;
		listRating = new ListRating();
		listRating.loadRating();
	}

	public List<Rating> getListRating() {
		return listRating.getListRating();
	}

	public void initFrames() {
		menuFrame = new MenuFrame();
		authFrame = new AuthFrame();
		personFrame = new PersonFrame();
	}

	public void changeFrame(Frames ratingFrame) {
		currentFrame.hide();
		currentFrame = getFrame(ratingFrame);
		currentFrame.show();
	}
	
	private Stage getFrame(Frames choice) {
		switch (choice) {
		case MENU_FRAME:
			return menuFrame;
		case AUTH_FRAME:
			return authFrame;
		case RATING_FRAME:
			return new RatingFrame();
		case PERSON_FRAME:
			return personFrame;
		case GAME_SECURITY_FRAME:
			return new GameSecurityFrame();
		case GAME_OVER_FRAME:
			return new GameOverFrame();
		case GAME_WAITER_FRAME:
			return new GameWaiterFrame();
		case GAME_BARMAN_FRAME:
			return new GameBarmanFrame();
		default:
			return null;
		}
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void addRating(Integer error, Integer done, String role,
			Integer score) {
		this.error = error;
		this.done = done;
		this.role = role;
		this.score = score;
		listRating.addRating(new Rating(login, role, score));
	}

	public Integer getError() {
		return error;
	}

	public Integer getDone() {
		return done;
	}

	public String getRole() {
		return role;
	}

	public Integer getScore() {
		return score;
	}

	public void smallDone() {
		((GameBarmanFrame)currentFrame).smallDone();
	}
	public void done() {
		((GameBarmanFrame)currentFrame).done();
	}
	public void error() {
		((GameBarmanFrame)currentFrame).error();
	}

	public boolean isGame() {
		return ((GameBarmanFrame)currentFrame).isGame();
	}
}
