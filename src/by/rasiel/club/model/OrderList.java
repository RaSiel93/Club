package by.rasiel.club.model;

import java.util.ArrayList;
import java.util.List;

import by.rasiel.club.model.enums.Drinks;

import javafx.scene.text.Text;

public class OrderList {

	List<Text> listOrder;
	private int lvl;
	
	public OrderList(Text... textes) {
		listOrder = new ArrayList<Text>();
		for(Text text : textes){
			listOrder.add(text);
		}
		lvl = 0;
	}

	public void addIngredient(List<Drinks> ingredients){
		for(Drinks drink : ingredients){
			listOrder.get(lvl).setText(drink.toString());
			listOrder.get(lvl).setOpacity(1);
			lvl++;
		}
	}
	
	public void clear(){
		for(Text text : listOrder){
			text.setOpacity(0);
		}
		lvl = 0;
	}
}
