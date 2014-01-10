package by.rasiel.club.view.controllers.utils;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Glass {

	private List<Rectangle> listGlass;

	private int lvl;
	
	public Glass(Rectangle lvl1, Rectangle lvl2, Rectangle lvl3,
			Rectangle lvl4, Rectangle lvl5) {
		listGlass = new ArrayList<Rectangle>();
		listGlass.add(lvl1);
		listGlass.add(lvl2);
		listGlass.add(lvl3);
		listGlass.add(lvl4);
		listGlass.add(lvl5);
		lvl = 0;
	}

	public void addIngredient(Drink drink) {
		Color color = null;
		
		switch(drink){
		case MAHITO: 
			color = Color.web("#72cc00"); break;
		case VODKA: 
			color = Color.web("WHITE"); break;
		case COLA: 
			color = Color.web("#000000"); break;
		case LIMON: 
			color = Color.web("#fff733"); break;
		case TEKILA: 
			color = Color.web("#ff7a00"); break;
		case LIKER: 
			color = Color.web("#00ffe0"); break;
		}
		listGlass.get(lvl).setFill(color);
		listGlass.get(lvl).setOpacity(1);
		lvl++;
	}
	
	public void clear(){
		for(Rectangle glass : listGlass){
			glass.setOpacity(0);
		}
		lvl = 0;
	}
}
