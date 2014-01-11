package by.rasiel.club.view.models;

public class GameOver extends GeneralFrame{
	
	static final String TITLE = GeneralFrame.TITLE + " - " + "Итоги игры";
	static final String PATH = GeneralFrame.PATH + "game_over.fxml";

	public GameOver() {
		super(TITLE, PATH);
	}
}
