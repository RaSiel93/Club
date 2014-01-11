package by.rasiel.club.model.enums;

public enum Hearts {
	GOOD, BAD;

	public static Hearts generateHeart(double probable) {
		if (Math.random() > probable) {
			return Hearts.GOOD;
		} else {
			return Hearts.BAD;
		}
	}
}
