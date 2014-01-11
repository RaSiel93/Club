package by.rasiel.club.model.profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.rasiel.club.model.enums.Roles;

public class ListProfile {
	List<Profile> profiles;

	public ListProfile() {
		profiles = new ArrayList<Profile>();
	}

	public void loadProfiles() {
		profiles = FileController.getInstance().load();
	}

	public void saveProfiles() {
		FileController.getInstance().save(profiles);
	}

	public void addScoreInProfile(String login, Roles role, Integer score) {
		switch (role) {
		case SECURITY:
			getProfile(login).setScorePerSecurity(score);
			break;
		case WAITER:
			getProfile(login).setScorePerWaiter(score);
			break;
		case BARMAN:
			getProfile(login).setScorePerBarman(score);
			break;
		}
		saveProfiles();
	}

	public void addProfile(String login) {
		for (Profile profile : profiles) {
			if (profile.getLogin().equals(login)) {
				return;
			}
		}

		Profile profile = new Profile(login);
		profiles.add(profile);
		saveProfiles();
	}

	public List<Rating> getListRating() {
		List<Rating> ratings = new ArrayList<Rating>();
		for (Profile profile : profiles) {
			ratings.add(new Rating(profile.getLogin(), profile.getScore()));
		}
		Collections.sort(ratings);
		return ratings;
	}

	private Profile getProfile(String login) {
		for (Profile profile : profiles) {
			if (profile.getLogin().equals(login)) {
				return profile;
			}
		}

		return null;
	}

}
