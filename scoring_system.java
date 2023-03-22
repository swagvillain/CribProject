//package Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import Card.*;

public class scoring_system {
    private static final int MAX_SCORE = 31;
    private static final int MAX_HAND_SCORE = 15;
    private static final int MAX_CRIB_SCORE = 29;

    // Score a hand of cards
    public static int score(List<Card> hand, Card cardTurnedUp) {
        int score = 0;

        // Check for 15s
        List<List<Card>> fifteenCombinations = combinationsForFifteens(hand, cardTurnedUp);
        for (List<Card> combo : fifteenCombinations) {
            if (sumValues(combo) == MAX_HAND_SCORE) {
                score += 2;
            }
        }

        // Check for pairs
        List<List<Card>> pairCombinations = combinationsForPairs(hand, cardTurnedUp);
        for (List<Card> combo : pairCombinations) {
            if (combo.size() == 2 && combo.get(0).getRank() == combo.get(1).getRank()) {
                score += 2;
            }
        }

//        // Check for runs
//        List<Card> allCards = new ArrayList<>(hand);
//        allCards.add(cardTurnedUp);
//        Collections.sort(allCards);
//        int runLength = 1;
//        for (int i = 1; i < allCards.size(); i++) {
//            if (allCards.get(i).getRank().getValue() == allCards.get(i - 1).getRank().getValue() + 1) {
//                runLength++;
//            } else {
//                if (runLength >= 3) {
//                    score += runLength;
//                }
//                runLength = 1;
//            }
//        }
//        if (runLength >= 3) {
//            score += runLength;
//        }

        // Check for flushes
        boolean isFlush = true;
        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getSuit() != hand.get(0).getSuit()) {
                isFlush = false;
                break;
            }
        }
        if (isFlush && cardTurnedUp.getSuit().equals(hand.get(0).getSuit())) {
            if (hand.size() == 4) {
                score += 4; // Four-card flush
            } else {
                score += 5; // Five-card flush
            }
        }

        return score;
    }

    // Score a crib of cards
    public static int scoreCrib(List<Card> crib, Card cardTurnedUp) {
        int score = 0;

        // Check for 15s
        List<List<Card>> fifteenCombinations = combinationsForFifteens(crib, cardTurnedUp);
        for (List<Card> combo : fifteenCombinations) {
            if (sumValues(combo) == MAX_CRIB_SCORE) {
                score += 2;
            }
        }

        // Check for pairs
        List<List<Card>> pairCombinations = combinationsForPairs(crib, cardTurnedUp);
        for (List<Card> combo : pairCombinations) {
            if (combo.size() == 2 && combo.get(0).getRank() == combo.get(1).getRank()) {
                score += 2;
            }
        }

//        // Check for runs
//        List<Card> allCards = new ArrayList<>(crib);
//        allCards.add(cardTurnedUp);
//        Collections.sort(allCards);
//        int runLength = 1;
//        for (int i = 1; i < allCards.size(); i++) {
//            if (allCards.get(i).getRank().getValue() == allCards.get(i - 1).getRank().getValue() + 1) {
//            runLength++;
//        } else{
//            if (runLength >= 3) {
//                score += runLength;
//            }
//            runLength = 1;
//        }
//    }
//        if(runLength >=3)
//
//    {
//        score += runLength;
//    }

    // Check for flushes
    boolean isFlush = true;
    for(
    int i = 1; i<crib.size();i++)

    {
        if (crib.get(i).getSuit() != crib.get(0).getSuit()) {
            isFlush = false;
            break;
        }
    }
    if(isFlush &&cardTurnedUp.getSuit()==crib.get(0).

    getSuit())

    {
        score += 5; // Five-card flush
    }

    return score;
}
    // Get all combinations of cards that add up to 15
    private static List<List<Card>> combinationsForFifteens(List<Card> hand, Card starter) {
        List<List<Card>> combinations = new ArrayList<>();
        List<Card> allCards = new ArrayList<>(hand);
        allCards.add(starter);
        int numCards = allCards.size();

        for (int i = 0; i < (1 << numCards); i++) {
            List<Card> combo = new ArrayList<>();
            int sum = 0;
            for (int j = 0; j < numCards; j++) {
                if ((i & (1 << j)) != 0) {
                    combo.add(allCards.get(j));
                    sum += allCards.get(j).getCribCount();
                }
            }
            if (sum == MAX_SCORE) {
                combinations.add(combo);
            }
        }

        return combinations;
    }

    // Get all pairs of cards
    private static List<List<Card>> combinationsForPairs(List<Card> hand, Card starter) {
        List<List<Card>> combinations = new ArrayList<>();
        List<Card> allCards = new ArrayList<>(hand);
        allCards.add(starter);

        for (int i = 0; i < allCards.size(); i++) {
            for (int j = i+1; j < allCards.size(); j++) {
                List<Card> combo = new ArrayList<>();
                combo.add(allCards.get(i));
                combo.add(allCards.get(j));
                combinations.add(combo);
            }
        }

        return combinations;
    }

    // Get the sum of the values of a list of cards
    private static int sumValues(List<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getCribCount();
        }
        return sum;
    }
}

