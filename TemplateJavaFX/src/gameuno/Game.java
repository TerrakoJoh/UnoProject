package gameuno;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private ArrayList<Card> LstCards = new ArrayList<Card>();
	private ArrayList<String> LstGamers = new ArrayList<String>();
	private ArrayList<ArrayList<Card>> LstHands = new ArrayList<ArrayList<Card>>();
	
	public Game() {
		loadCards();
		LstGamers.add("Patate");
		LstGamers.add("R�mi");
		LstGamers.add("Melvyn");
		LstGamers.add("Alexandre");
		loadHand();
	}
	
	public void loadCards() {
		EnumColor colors[] = EnumColor.values();
		EnumSymbol symbols[] = EnumSymbol.values();
		for(EnumColor color : colors) {
			for (EnumSymbol symbol : symbols) {
				switch(color.name()) {
				case "BLACK":
					if(symbol.getNumVal() > 20) {
						LstCards.add(new Card(color, symbol));
						LstCards.add(new Card(color, symbol));
						LstCards.add(new Card(color, symbol));
						LstCards.add(new Card(color, symbol));
					}
					break;
					
				default:
					if(symbol.getNumVal() < 20) {
						if(symbol.getNumVal() != 0) {
							LstCards.add(new Card(color, symbol));
							LstCards.add(new Card(color, symbol));
						}else {
							LstCards.add(new Card(color, symbol));
						}
					}
					break;
				}
			}
		}
		
//		for(Card card : LstCards) {
//			System.out.println(card.PrintCard());
//		}
	}
	
	public void loadHand() {
		ArrayList<Card> DrawCard = new ArrayList<Card>();
		DrawCard = LstCards;
		
		for(String gamer : LstGamers) {
			ArrayList<Card> Hand = new ArrayList<Card>();
			
			for(int i = 0; i < 7; i++) {
				Random rand = new Random();
				int randCard = rand.nextInt(DrawCard.size());
				Card card = DrawCard.get(randCard);
				Hand.add(card);
//				card.PrintCard();
//				DrawCard.get(randCard).PrintCard();
				DrawCard.remove(randCard);
			}
			LstHands.add(DrawCard);
			
			
			System.out.println(gamer);
			for(Card c : Hand) {
				System.out.println(c.PrintCard());
			}
		}
		System.out.println("Pioche");
		for(Card c : DrawCard) {
			System.out.println(c.PrintCard());
		}
	}
}