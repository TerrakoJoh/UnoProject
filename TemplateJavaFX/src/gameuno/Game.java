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
		LstGamers.add("Rémi");
		LstGamers.add("Melvyn");
		LstGamers.add("Alexandre");
		loadHand();
	}
	
	public void loadCards() { // chargement du jeu de cartes : total de 108 cartes
		EnumColor colors[] = EnumColor.values();
		EnumSymbol symbols[] = EnumSymbol.values();
		for(EnumColor color : colors) {
			for (EnumSymbol symbol : symbols) {
				switch(color.name()) { // chaque enum symbole correspond à une valeur numérique
				case "BLACK": // créer les cartes +4 et changmnt couleur
					if(symbol.getNumVal() > 20) {
						LstCards.add(new Card(color, symbol));
						LstCards.add(new Card(color, symbol));
						LstCards.add(new Card(color, symbol));
						LstCards.add(new Card(color, symbol));
					}
					break;
					
				default: // créer les cartes classiques 0 à 9, +2, interdit jouer et chagmnt sens pour chaque couleurs
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
	
	public void loadHand() { // distribution des mains, composé de 7 cartes pour chaque joueur
		ArrayList<Card> DrawCard = new ArrayList<Card>();
		DrawCard = LstCards;
		
		for(String gamer : LstGamers) {
			ArrayList<Card> Hand = new ArrayList<Card>();
			
			for(int i = 0; i < 7; i++) {
				// récupère une carte de manière aléatoire, l'affecte à un joueur et la supprime de la pioche
				Random rand = new Random();
				int randCard = rand.nextInt(DrawCard.size());
				Card card = DrawCard.get(randCard);
				Hand.add(card);
//				card.PrintCard();
//				DrawCard.get(randCard).PrintCard();
				DrawCard.remove(randCard);
			}
			LstHands.add(DrawCard);
			
//			System.out.println(gamer);
//			for(Card c : Hand) {
//				System.out.println(c.PrintCard());
//			}
		}
//		System.out.println("Pioche");
//		for(Card c : DrawCard) {
//			System.out.println(c.PrintCard());
//		}
	}
}