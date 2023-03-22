import java.util.Collections;
import java.util.ArrayList;

public class Card {

    private static Card [] theCards = new Card[52];

    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

        public int count(){
            return Math.min(ordinal()+1, 10);
        }

        public String toShortString(){
            if(ordinal()==0)
                return "A";
            else if(ordinal() < 10)
                return String.valueOf(ordinal() + 1);
            else if(ordinal() == 10)
                return "J";
            else if(ordinal() == 11)
                return "Q";
            else if(ordinal() == 12)
                return "K";
            else return "ERROR";
        }
    }

    public enum Suit {
        SPADES, DIAMONDS, CLUBS, HEARTS;

        public String toShortString(){
            if(ordinal()==0)
                return "S";
            if(ordinal()==1)
                return "D";
            if(ordinal()==2)
                return "C";
            if(ordinal()==3)
                return "H";
            else
                return "ERROR";
        }
    }

    private int cardID;

    private Card(Rank r, Suit s){
        switch (r){
            case ACE:   ;
            case TWO:   ;
            case THREE: ;
            case FOUR:  ;
            case FIVE:  ;
            case SIX:   ;
            case SEVEN: ;
            case EIGHT: ;
            case NINE:  ;
                cardID = r.count();
                break;
            case TEN:
                cardID = 9;
            case JACK:
                cardID = 10;
                break;
            case QUEEN:
                cardID = 11;
                break;
            case KING:
                cardID = 12;
        }

        switch(s){
            case CLUBS:
                break;
            case DIAMONDS:
                cardID = cardID + 13;
                break;
            case HEARTS:
                cardID = cardID + 26;
                break;
            case SPADES:
                cardID = cardID + 39;
        }
    }

    private Card(int i){
        cardID = i;
    }

    public Rank getRank(){
        switch(cardID%13){
            case 0: return Rank.ACE;
            case 1: return Rank.TWO;
            case 2: return Rank.THREE;
            case 3: return Rank.FOUR;
            case 4: return Rank.FIVE;
            case 5: return Rank.SIX;
            case 6: return Rank.SEVEN;
            case 7: return Rank.EIGHT;
            case 8: return Rank.NINE;
            case 9: return Rank.TEN;
            case 10: return Rank.JACK;
            case 11: return Rank.QUEEN;
            case 12: return Rank.KING;
        }
        return Rank.ACE;
    }

    public Suit getSuit(){
        switch (cardID/13){
            case 0: return Suit.CLUBS;
            case 1: return Suit.DIAMONDS;
            case 2: return Suit.HEARTS;
            case 3: return Suit.SPADES;
        }
        return Suit.SPADES;
    }

    public int getCribCount(){
        return this.getRank().count();
    }

    @Override
    public String toString(){
        String rank = "", suit = "";

        if(cardID%13==0){
            rank = "A";
        }else if(cardID%13<9){
            rank = Integer.toString(this.getRank().count());
        }else if(cardID%13 == 9){
            rank = "T";
        }else if(cardID%13 == 10){
            rank = "J";
        }else if(cardID%13 == 11){
            rank = "Q";
        }else
            rank = "K";

        switch(cardID/13){
            case 0:
                suit = "\u2663";
                break;
            case 1:
                suit = "\u2662";
                break;
            case 2:
                suit = "\u2661";
                break;
            case 3:
                suit = "\u2660";
        }

        return rank+suit;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Card)
            return equals((Card)o);
        else
            return false;
    }

    public boolean equals(Card c){
        return(this.cardID == c.cardID);
    }

    public static Card getCard(Rank r, Suit s){
        int cardNumber = 0;

        switch (r){
            case ACE:   ;
            case TWO:   ;
            case THREE: ;
            case FOUR:  ;
            case FIVE:  ;
            case SIX:   ;
            case SEVEN: ;
            case EIGHT: ;
            case NINE:  ;
                cardNumber = r.count();
                break;
            case TEN:
                cardNumber = 9;
                break;
            case JACK:
                cardNumber = 10;
                break;
            case QUEEN:
                cardNumber = 11;
                break;
            case KING:
                cardNumber = 12;
        }

        switch(s){
            case CLUBS:
                break;
            case DIAMONDS:
                cardNumber = cardNumber + 13;
                break;
            case HEARTS:
                cardNumber = cardNumber + 26;
                break;
            case SPADES:
                cardNumber = cardNumber + 39;
        }
        if(theCards[cardNumber] == null) {
            Card newCard = new Card(r, s);
        }
        return new Card(cardNumber);
    }

    public static Card getCard(int i){
        if (theCards[i] == null){
            theCards[i] = new Card(i);
        }
        return new Card(i);
    }
}

 class CribbageDeck {

    private ArrayList<Card> cards;

    public CribbageDeck() {
        cards = new ArrayList<Card>();
        for(int i = 0; i<52; i++){
            cards.add(Card.getCard(i));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void deal() {
        for(int i = 0; i < 6; i++){
            Main.p1.receiveCard(cards.get(i));
            Main.p2.receiveCard(cards.get(i+6));
        }
    }

    public Card cutCardTurnedUp(){
        return(cards.get(25));
    }
}

 class CribbageHand {
     private ArrayList<Card> cards;

     public CribbageHand() {
         cards = new ArrayList<Card>();
     }

     public void add(Card card) {
         cards.add(card);
     }

     public int score() {
         int score = 0;

         // check for pairs of face cards
         ArrayList<Card> faceCards = new ArrayList<Card>();
         for (Card card : cards) {
             if (card.getCribCount() == 10) {
                 faceCards.add(card);
             }
         }
         if (faceCards.size() >= 2 && faceCards.get(0).equals(faceCards.get(1))) {
             score += 2;
         }


         // check for runs of three or more cards


             int playLength = 1;
             Collections.sort(cards, (c1, c2) -> c1.getCribCount() - c2.getCribCount());//to sort the card

             for (int i = 0; i < cards.size() - 1; i++) {//If two adjacent cards are consecutive, we increment the runLength variable. If runLength is higher than or equal to 3, its value is added to the final score. If the cards are not sequential, runLength is reset to 1.
                 if (cards.get(i).getCribCount() == cards.get(i + 1).getCribCount() - 1) {
                     playLength++;
                     if (playLength >= 3) {
                         score += playLength;
                     }
                 } else {
                     playLength = 1;
                 }// playLength variable - keep track of the length of the current run
             }

             return score;
         }
     }






////package Card;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class Card implements Comparable{
//    public static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
//    public static final String[] SUITS = {"C", "D", "H", "S"};
//
//    private String rank;
//    private String suit;
//
//    public Card(String rank, String suit) {
//        //checking validity and returns invalid message if its not valid
//        if (!"A23456789TJQK".contains(rank) || !"CDHS".contains(suit)) {
//            throw new IllegalArgumentException("Invalid rank or suit");
//        }
//        this.rank = rank;//assigns rank the value
//        this.suit = suit;//assigns suit the value
//    }
//
//    public String toString() {
//        return rank + suit;
//    }
//
//    public int getValue() {
//        switch (rank) {
//            case "A":
//                return 1;
//            case "J":
//            case "Q":
//            case "K":
//                return 10;
//            default:
//                return Integer.valueOf(rank);
//        }
//    }
//
//    public String getRank(){
//        return this.rank;
//    }
//
//    public String getSuit(){
//        return this.suit;
//    }
//
//    private static boolean isValidRank(String rank) {
//        for (String validRank : RANKS) {
//            if (validRank.equals(rank)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean isValidSuit(String suit) {
//        for (String validSuit : SUITS) {
//            if (validSuit.equals(suit)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
////    @Override
////    public int compareTo(Card otherCard){
////        if(this.getValue()<10 || otherCard.getValue()<10){
////            return this.getValue()-otherCard.getValue();
////        }else if(!this.rank.equals("Q") && !otherCard.rank.equals("Q")){
////            return this.rank.compareTo(otherCard.rank);
////        }else if(this.rank.equals("Q") && otherCard.rank.equals("K")){
////            return -1;}
////        else if(this.rank.equals("Q") && otherCard.rank.equals("Q")){
////            return 0;
////        }else{
////            return 1;
////        }
////    }
//
//    @Override
//    public int compareTo(Object o) {
//        return 0;
//    }
//}
//
// class CribbageDeck {
//    private List<Card> cards;
//
//    public CribbageDeck() {
//        cards = new ArrayList<Card>();
//        for (String rank : Card.RANKS) {
//            for (String suit : Card.SUITS) {
//                cards.add(new Card(rank, suit));
//            }
//        }
//    }
//
//    public void shuffle() {
//        Collections.shuffle(cards);
//    }
//
//    public Card deal() {
//        return cards.remove(0);
//    }
//}
//
// class CribbageHand {
//     private List<Card> cards;
//
//     public CribbageHand() {
//         cards = new ArrayList<Card>();
//     }
//
//     public void addCard(Card card) {
//         cards.add(card);
//     }
//
//     public int score() {
//         int score = 0;
//
//         // check for pairs of face cards
//         List<Card> faceCards = new ArrayList<Card>();
//         for (Card card : cards) {
//             if (card.getValue() == 10) {
//                 faceCards.add(card);
//             }
//         }
//         if (faceCards.size() >= 2 && faceCards.get(0).equals(faceCards.get(1))) {
//             score += 2;
//         }
//
//
//         // check for runs of three or more cards
//
//
//             int playLength = 1;
//             Collections.sort(cards, (c1, c2) -> c1.getValue() - c2.getValue());//to sort the card
//
//             for (int i = 0; i < cards.size() - 1; i++) {//If two adjacent cards are consecutive, we increment the runLength variable. If runLength is higher than or equal to 3, its value is added to the final score. If the cards are not sequential, runLength is reset to 1.
//                 if (cards.get(i).getValue() == cards.get(i + 1).getValue() - 1) {
//                     playLength++;
//                     if (playLength >= 3) {
//                         score += playLength;
//                     }
//                 } else {
//                     playLength = 1;
//                 }// playLength variable - keep track of the length of the current run
//             }
//
//             return score;
//         }
//     }
//
//
