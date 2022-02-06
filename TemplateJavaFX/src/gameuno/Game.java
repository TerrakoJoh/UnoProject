package gameuno;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import common.Message;
import server.DatabaseSingleton;

/**
 * @author arnaud
 * classe qui permet la gestion du jeu
 */
public class Game {
	
	static Game databaseInstance;
	static ArrayList<Card> LstCards = new ArrayList<Card>();
	//private ArrayList<String> LstGamers = new ArrayList<String>();
	public static ArrayList<Integer> LstGamers = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Card>> LstHands = new ArrayList<ArrayList<Card>>();
	static ArrayList<Card> DrawCard = new ArrayList<Card>();
	public static Card ReturnedCard = new Card();
	public static ArrayList<String> LstAccesHand = new ArrayList<String>();
	
	
	
	public static Game getInstance() {
		if(databaseInstance == null) {
			databaseInstance = new Game();
		}
		return databaseInstance;
	}
	
	
	/**
	 * fonction qui lance la partie
	 *  elle charge le jeu et les mains
	 */
	public static void startGame() {
		Game.loadCards();
		Game.loadHand();
		// charge la première carte
		Random rand1 = new Random();
		int randCard1 = rand1.nextInt(DrawCard.size());
		ReturnedCard = DrawCard.get(randCard1);
		DrawCard.remove(randCard1);
	}
	
	
	/**
	 * fonction qui charge le jeu de cartes
	 */
	public static void loadCards() {
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
	}
	
	
	/**
	 * fonction qui charge les mains pour chaque joueur
	 */
	public static void loadHand() {
		DrawCard = LstCards;
		for(int gamer : LstGamers) {
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
		}
	}	
	
	public static int FindPseudo() {
		for(int i = 0; i < LstAccesHand.size(); i++) {
			return i;
		}
		return 0;
	}
}
