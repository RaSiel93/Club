package by.rasiel.club.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rating implements Comparable<Rating>{

	private final SimpleStringProperty login;
	private final SimpleStringProperty role;
	private final SimpleIntegerProperty score;
	
	public Rating(String login, String role,
			Integer score) {
		super();
		this.login = new SimpleStringProperty(login);
		this.role = new SimpleStringProperty(role);
		this.score = new SimpleIntegerProperty(score);
	}

	public String getLogin() {
		return login.get();
	}

	public String getRole() {
		return role.get();
	}

	public Integer getScore() {
		return score.get();
	}

	@Override
	public int compareTo(Rating rating) {
		return rating.score.getValue() - this.score.getValue();
	}	
	
}
