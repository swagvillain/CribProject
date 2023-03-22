import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static Player p1 = new Player("Human");
    static Player p2 = new Player("Computer");
    static CribbageBoard board = new CribbageBoard();

    public static void main(String[] args) {
        playCrib();
    }

    public static void playCrib(){
        //determine who deals first via cutting or random
        determineDealer(p1, p2);
        System.out.println(determineDealer(p1, p2).getName()+" is dealer first");

        playRound();

        //do a hand, then swap dealer
        //repeat

    }

    public static void playRound(){
        CribbageDeck deck = new CribbageDeck();
        deck.shuffle();
        deck.deal();



    }

    public static Player determineDealer(Player p1, Player p2){
        ArrayList<Player> both = new ArrayList<>();
        both.add(p1);
        both.add(p2);
        Collections.shuffle(both);
        both.get(1).setDealer();
        return both.get(1);

    }
}
