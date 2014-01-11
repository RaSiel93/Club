package by.rasiel.club.model.profile;

public class Profile {

	private String login;

	private int scorePerSecurity;
	private int scorePerWaiter;
	private int scorePerBarman;

	Profile(String login) {
		this.login = login;
		scorePerSecurity = 0;
		scorePerWaiter = 0;
		scorePerBarman = 0;
	}

	public Profile(String login, int scorePerSecurity, int scorePerWaiter,
			int scorePerBarman) {
		super();
		this.login = login;
		this.scorePerSecurity = scorePerSecurity;
		this.scorePerWaiter = scorePerWaiter;
		this.scorePerBarman = scorePerBarman;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getScorePerSecurity() {
		return scorePerSecurity;
	}

	public void setScorePerSecurity(int scorePerSecurity) {
		if (this.scorePerSecurity < scorePerSecurity) {
			this.scorePerSecurity = scorePerSecurity;
		}
	}

	public int getScorePerWaiter() {
		return scorePerWaiter;
	}

	public void setScorePerWaiter(int scorePerWaiter) {
		if (this.scorePerWaiter < scorePerWaiter) {
			this.scorePerWaiter = scorePerWaiter;
		}
	}

	public int getScorePerBarman() {
		return scorePerBarman;
	}

	public void setScorePerBarman(int scorePerBarman) {
		if (this.scorePerBarman < scorePerBarman) {
			this.scorePerBarman = scorePerBarman;
		}
	}

	public int getScore() {
		return getScorePerSecurity() + getScorePerWaiter()
				+ getScorePerBarman();
	}
}
