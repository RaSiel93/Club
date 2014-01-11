package by.rasiel.club.view.controllers;

import java.util.List;

import by.rasiel.club.model.enums.Frames;
import by.rasiel.club.model.enums.Roles;
import by.rasiel.club.model.profile.ListProfile;
import by.rasiel.club.model.profile.Rating;
import by.rasiel.club.view.models.Authorization;
import by.rasiel.club.view.models.GameBarman;
import by.rasiel.club.view.models.GameFrame;
import by.rasiel.club.view.models.GameOver;
import by.rasiel.club.view.models.GameSecurity;
import by.rasiel.club.view.models.GameWaiter;
import by.rasiel.club.view.models.Menu;
import by.rasiel.club.view.models.ChoseRole;
import by.rasiel.club.view.models.RatingFrame;
import javafx.stage.Stage;

public class GeneralController {
	private final String LOGIN_DEFAULT = "Аноним";

	private Stage currentFrame;

	private ListProfile listProfile;

	private Menu menuFrame;
	private Authorization authFrame;
	private ChoseRole personFrame;

	private String login = LOGIN_DEFAULT;

	private Integer error;
	private Integer done;
	private Integer score;

	public GeneralController(Stage frame) {
		currentFrame = frame;
		listProfile = new ListProfile();
		listProfile.loadProfiles();
	}

	public List<Rating> getListRating() {
		return listProfile.getListRating();
	}

	public void initFrames() {
		menuFrame = new Menu();
		authFrame = new Authorization();
		personFrame = new ChoseRole();
	}

	public void changeFrame(Frames ratingFrame) {
		currentFrame.hide();
		currentFrame = getFrame(ratingFrame);
		currentFrame.show();
	}

	private Stage getFrame(Frames choice) {
		switch (choice) {
		case MENU:
			return menuFrame;
		case AUTH:
			return authFrame;
		case RATING:
			return new RatingFrame();
		case PERSON:
			return personFrame;
		case GAME_SECURITY:
			return new GameSecurity();
		case GAME_OVER:
			return new GameOver();
		case GAME_WAITER:
			return new GameWaiter();
		case GAME_BARMAN:
			return new GameBarman();
		default:
			return null;
		}
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void addRating(int error, int done, Roles role, int score) {
		this.error = error;
		this.done = done;
		this.score = score;
		listProfile.addScoreInProfile(login, role, score);
	}

	public Integer getError() {
		return error;
	}

	public Integer getDone() {
		return done;
	}

	public Integer getScore() {
		return score;
	}

	public void smallDone() {
		((GameFrame) currentFrame).smallDone();
	}

	public void done() {
		((GameFrame) currentFrame).done();
	}

	public void error() {
		((GameFrame) currentFrame).error();
	}

	public boolean isGame() {
		return ((GameBarman) currentFrame).isGame();
	}

	public void addProfile(String login) {
		listProfile.addProfile(login);
	}
}
