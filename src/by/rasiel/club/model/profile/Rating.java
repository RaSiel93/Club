package by.rasiel.club.model.profile;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rating implements Comparable<Rating>{

	private final SimpleStringProperty login;
	private final SimpleIntegerProperty score;
	
	public Rating(String login, Integer score) {
		super();
		this.login = new SimpleStringProperty(login);
		this.score = new SimpleIntegerProperty(score);
	}

	public String getLogin() {
		return login.get();
	}

	public Integer getScore() {
		return score.get();
	}

	@Override
	public int compareTo(Rating rating) {
		return rating.score.getValue() - this.score.getValue();
	}	
	
}
