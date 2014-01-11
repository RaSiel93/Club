package by.rasiel.club.model.enums;

public enum Drinks {
	MAHITO, VODKA, COLA, LIMON, TEKILA, LIKER;

	public String toString() {
		switch (this) {
		case MAHITO:
			return "Махито";
		case VODKA:
			return "Водка";
		case COLA:
			return "Coca-Cola";
		case LIMON:
			return "Лимон";
		case TEKILA:
			return "Текила";
		case LIKER:
			return "Ликер";
		default:
			return "Напиток";
		}
	}
}
