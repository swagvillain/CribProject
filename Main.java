import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Player p1 = new Player("Human");
    static Player p2 = new Player("Computer");
    static CribbageBoard board = new CribbageBoard();
    static CribbageHand crib = new CribbageHand();

    public static void main(String[] args) {
        playCrib();
    }

    public static void playCrib(){
        //determine who deals first via cutting or random
        System.out.println(determineDealer(p1, p2).getName()+" is dealer first");//determine

        playRound();

        //do a hand, then swap dealer
        //repeat

    }

    public static void playRound(){
        CribbageDeck deck = new CribbageDeck();

        deck.shuffle();
        deck.deal();
        havePlayersDiscardToCrib();
        Card cardTurnedUp = deck.cutCardTurnedUp();
        System.out.println("The card turned up is: " + cardTurnedUp);
        jackTurnedUpProtocol(cardTurnedUp);

        peggingPhase();
        countingPhase();

    }

    public static void countingPhase(){

    }

    public static void peggingPhase(){
        int peggingCount = 0;
        boolean p1CanGo = true;
        boolean p2CanGo = true;

        ArrayList<Card> p1Hand = p1.getHand();
        ArrayList<Card> p2Hand = p2.getHand();
        ArrayList<Card> playedCards = new ArrayList<>(5);

        System.out.println("\nIt's the pegging phase. The non-dealer goes first.");



        System.out.println("The count is at: " + peggingCount);
        if(p1.getDealer()){//add other parts to match in the while loop
            humanPegging(p1, p1Hand, peggingCount, playedCards);
        }
        while(peggingCount < 31 && (p1CanGo||p2CanGo)){
            int k = computerPegging(p2, p2Hand, peggingCount, playedCards);
            peggingCount += k;
            if(k==0)p2CanGo=false;

            int l = humanPegging(p1, p1Hand, peggingCount, playedCards);
            peggingCount += l;
            if(l==0)p1CanGo=false;
        }

        System.out.println("Thus concludes the pegging phase.");
    }

    private static int humanPegging(Player player, ArrayList<Card> hand, int pegCount, ArrayList<Card> playedCards){
        int cardToPlay;
        int cardVal;
        Scanner scanner = new Scanner(System.in);


        System.out.println("These are the played cards: ");
        System.out.println(playedCards);
        System.out.println("The pegging count is at "+pegCount);

        if(!playerCanGo(pegCount, hand)) {
            System.out.println("You can't go.");
            return 0;
        }

        System.out.println(hand);
        System.out.println("Your turn to peg. This is your hand. Which card would you like to play? Please enter a number 1-"+hand.size());
        cardToPlay = scanner.nextInt();
        System.out.println("You played "+hand.get(cardToPlay-1));
        cardVal = hand.get(cardToPlay-1).getCribCount();
        System.out.println("The count is at "+(pegCount+cardVal));
        playedCards.add(hand.get(cardToPlay-1));
        hand.remove(cardToPlay-1);



        return cardVal;
    }

    public static boolean playerCanGo(int count, ArrayList<Card> hand){
        boolean canGo = false;

        for (Card card : hand) {
            if (31 - count > card.getCribCount())
                canGo = true;
        }
        return canGo;
    }

    public static int computerPegging(Player player, ArrayList<Card> hand, int pegCount, ArrayList<Card> playedCards){

        if(!playerCanGo(pegCount, hand)) {
            System.out.println("Opponent can't go.");
            return 0;
        }

        int cardVal = hand.get(0).getCribCount();

        System.out.println("Your opponent played "+hand.get(0));
        playedCards.add(hand.get(0));
        hand.remove(0);

        return cardVal;
    }

    public static void havePlayersDiscardToCrib(){
        haveComputerDiscardToCrib();
        haveHumanDiscardToCrib();
    }

    public static void jackTurnedUpProtocol(Card card){
        if(card.getRank() != Card.Rank.JACK){
            return;
        }else{
            if(p1.getDealer()){
                board.addPoints(1, 2);
            }else{
                board.addPoints(2, 2);
            }
        }
    }

    public static void haveComputerDiscardToCrib(){
        ArrayList<Card> p2Hand = p2.getHand();
        Collections.shuffle(p2Hand);
        //System.out.println(p2Hand);
        p2.discardCard(p2Hand.get(1));
        p2.discardCard(p2Hand.get(2));
        //System.out.println(p2Hand);
    }

    public static void haveHumanDiscardToCrib(){

        Scanner scanner = new Scanner(System.in);
        int cardToDiscard;

        System.out.println("Here is your hand.");
        ArrayList<Card> p1Hand = p1.getHand();

        System.out.println(p1Hand);

        System.out.println("What is the first card that you would like to discard? Please enter a " +
                "number, 1-6");

        remindPlayerIfTheyAreTheDealer();

        cardToDiscard = scanner.nextInt();
        p1.discardCard(p1Hand.get(cardToDiscard-1));
        p1Hand = p1.getHand();
        System.out.println("Card discarded to crib. Here is your resulting hand.");
        System.out.println(p1Hand);

        System.out.println("What is the second card that you would like to discard? Please enter a " +
                "number, 1-5");

        remindPlayerIfTheyAreTheDealer();

        cardToDiscard = scanner.nextInt();
        p1.discardCard(p1Hand.get(cardToDiscard-1));
        p1Hand = p1.getHand();
        System.out.println("Card discarded to crib. Here is your resulting hand.");
        System.out.println(p1Hand);
    }

    public static Player determineDealer(Player p1, Player p2){
        ArrayList<Player> both = new ArrayList<>();
        both.add(p1);
        both.add(p2);
        Collections.shuffle(both);
        both.get(1).setDealer();
        return both.get(1);
    }

    public static void remindPlayerIfTheyAreTheDealer(){
        if(!p1.getDealer()) {
            System.out.println("(Remember, you are not the dealer; it is not your crib)");
        }else{
            System.out.println("(Remember, you are the dealer; it is your crib)");
        }
    }
}
