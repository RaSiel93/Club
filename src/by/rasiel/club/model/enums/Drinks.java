package by.rasiel.club.model.enums;

public enum Drinks {
	MAHITO, VODKA, COLA, LIMON, TEKILA, LIKER;

	public String toString() {
		switch (this) {
		case MAHITO:
			return "������";
		case VODKA:
			return "�����";
		case COLA:
			return "Coca-Cola";
		case LIMON:
			return "�����";
		case TEKILA:
			return "������";
		case LIKER:
			return "�����";
		default:
			return "�������";
		}
	}
}
