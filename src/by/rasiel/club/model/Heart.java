package by.rasiel.club.model;

public enum Heart {
	GOOD, BAD;

	public static Heart generateHeart(double probable) {
		if (Math.random() > probable) {
			return Heart.GOOD;
		} else {
			return Heart.BAD;
		}
	}
}
