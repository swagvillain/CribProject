//package Player;

//import Card.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	private boolean isDealer = false;
	private String name;
	
	private int score;
	
	private Random rand = new Random();
	
	
	private static ArrayList<Card> deck = new ArrayList<>();
	
	//private static ArrayList<Card> cribHand = new ArrayList<>();
	
	private ArrayList<Card> hand = new ArrayList<>();

	public void receiveCard(Card card){
		hand.add(card);
	}

	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public Player(String name) {
		this.name = name;
		this.isDealer = false;
		this.score = 0;
	}
	
	public void deal(Player opponent) {
		if(isDealer) {
			for(int i = 0; i < 6; i++) {
				int r = rand.nextInt(52);
				int s = rand.nextInt(52);
				opponent.hand.add(deck.get(r));
				deck.remove(r);
				this.hand.add(deck.get(rand.nextInt(52)));
				deck.remove(s);
			}
		}
		else {
			System.out.println("You are not the dealer");
		}
	}
	
//	public ArrayList<CribbageCard> cutDeck(int choice) {
//		ArrayList<CribbageCard> cut1 = new ArrayList<>();
//		ArrayList<CribbageCard> cut2 = new ArrayList<>();
//		ArrayList<CribbageCard> shuffle = shuffle();
//
//		for(int i = 0; i < shuffle.size()/2; i++) {
//			cut1.add(shuffle.get(i));
//		}
//		for(int i = 52; i > shuffle.size()/2; i--) {
//			cut2.add(shuffle.get(i));
//		}
//
//		if(choice == 1) {
//			return cut1;
//		}
//		else if(choice == 2){
//			return cut2;
//		}
//
//		else {
//			return cut1;
//		}
//
//	}
	
	public void setDealer() {
		this.isDealer = true;
	}

	public void toggleDealer(){
		this.isDealer = !this.isDealer;
	}
	
	public boolean getDealer() {
		return this.isDealer;
	}

	public String getName(){
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int points) {
		score += points;
	}
	
	public void discardCard(Card c) {
		this.hand.remove(c);
		Main.crib.add(c);
	}
	
	public Card playCard(Card c) {
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).equals(c)) {
				return hand.get(i);
			}
		}
		System.out.println("This card is not in your hand");
		return null;
	}
	
//	public ArrayList<Card> getCribHand(){
//		if(isDealer == true) {
//			return Player.cribHand;
//		}
//		System.out.println("You are not the dealer this round");
//		return null;
//	}
}