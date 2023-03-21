//package Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CribbageCard {
    public static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    public static final String[] SUITS = {"C", "D", "H", "S"};

    private String rank;
    private String suit;

    public CribbageCard(String rank, String suit) {
        //checking validity and returns invalid message if its not valid
        if (!"A23456789TJQK".contains(rank) || !"CDHS".contains(suit)) {
            throw new IllegalArgumentException("Invalid rank or suit");
        }
        this.rank = rank;//assigns rank the value
        this.suit = suit;//assigns suit the value
    }

    public String toString() {
        return rank + suit;
    }

    public int getValue() {
        switch (rank) {
            case "A":
                return 1;
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.valueOf(rank);
        }
    }

    public String getRank(){
        return this.rank;
    }

    public String getSuit(){
        return this.suit;
    }

    private static boolean isValidRank(String rank) {
        for (String validRank : RANKS) {
            if (validRank.equals(rank)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidSuit(String suit) {
        for (String validSuit : SUITS) {
            if (validSuit.equals(suit)) {
                return true;
            }
        }
        return false;
    }
}

 class CribbageDeck {
    private List<CribbageCard> cards;

    public CribbageDeck() {
        cards = new ArrayList<CribbageCard>();
        for (String rank : CribbageCard.RANKS) {
            for (String suit : CribbageCard.SUITS) {
                cards.add(new CribbageCard(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public CribbageCard deal() {
        return cards.remove(0);
    }
}

 class CribbageHand {
     private List<CribbageCard> cards;

     public CribbageHand() {
         cards = new ArrayList<CribbageCard>();
     }

     public void addCard(CribbageCard card) {
         cards.add(card);
     }

     public int score() {
         int score = 0;

         // check for pairs of face cards
         List<CribbageCard> faceCards = new ArrayList<CribbageCard>();
         for (CribbageCard card : cards) {
             if (card.getValue() == 10) {
                 faceCards.add(card);
             }
         }
         if (faceCards.size() >= 2 && faceCards.get(0).equals(faceCards.get(1))) {
             score += 2;
         }


         // check for runs of three or more cards
         public int scoreRun(List<CribbageCard> cards) {
             int score = 0;
             int playLength = 1;
             Collections.sort(cards, (c1, c2) -> c1.getValue() - c2.getValue());//to sort the card

             for (int i = 0; i < cards.size() - 1; i++) {//If two adjacent cards are consecutive, we increment the runLength variable. If runLength is higher than or equal to 3, its value is added to the final score. If the cards are not sequential, runLength is reset to 1.
                 if (cards.get(i).getValue() == cards.get(i + 1).getValue() - 1) {
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
 }


