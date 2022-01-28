package gameuno;

import java.util.ArrayList;

public class Game {
	private ArrayList<Card> LstCards = new ArrayList<Card>();
	private ArrayList<String> LstGamers = new ArrayList<String>();
	
	public Game() {
		loadCards();
	}
	
	public void loadCards() {
		EnumColor colors[] = EnumColor.values();
		EnumSymbol symbols[] = EnumSymbol.values();
		for(EnumColor color : colors) {
			for (EnumSymbol symbol : symbols) {
				symbol.getNumVal();
			}
				
		}
		
		//this.LstCards.add(1)
	}
}