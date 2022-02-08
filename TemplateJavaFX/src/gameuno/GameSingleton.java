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
public class GameSingleton {
	
	static GameSingleton databaseInstance;
	static ArrayList<Card> LstCards = new ArrayList<Card>();
	public static ArrayList<Integer> LstGamers = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Card>> LstHands = new ArrayList<ArrayList<Card>>();
	static ArrayList<Card> DrawCard = new ArrayList<Card>();
	public static Card ReturnedCard = new Card();
	public ArrayList<String> LstAccesHand = new ArrayList<String>();
	
	
	
	public static GameSingleton getInstance() {
		if(databaseInstance == null) {
			databaseInstance = new GameSingleton();
			
		}
		return databaseInstance;
	}
	
	
	/**
	 * fonction qui lance la partie
	 *  elle charge le jeu et les mains
	 */
	public static void startGame() {
		GameSingleton.loadCards();
		GameSingleton.loadHand();
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
		int count = 0;
		for(int gamer : LstGamers) {
			count++;
			ArrayList<Card> Hand = new ArrayList<Card>();
			
			for(int i = 0; i < 7; i++) {
				// récupère une carte de manière aléatoire, l'affecte à un joueur et la supprime de la pioche
				Random rand = new Random();
				int randCard = rand.nextInt(DrawCard.size());
				Card card = DrawCard.get(randCard);
				Hand.add(card);
				DrawCard.remove(randCard);
			}
			LstHands.add(DrawCard);
		}
		System.out.println("COUNT : " + count);
		
	}	
	
	public int FindPseudo(String pseudo) {
		int nb = LstAccesHand.size();
		System.out.println("nb : " + nb);
;		for(int i = 0; i < nb; i++) {
			System.out.println("patate");
			if(pseudo == LstAccesHand.get(i)) {
				return i;
			}
			System.out.println(LstAccesHand.get(i));
//			return i;
		}
		return -1;
	}
}
