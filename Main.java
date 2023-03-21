import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        playCrib();
    }

    public static void playCrib(){
        Player p1 = new Player("Human");
        Player p2 = new Player("Computer");
        CribbageDeck deck = new CribbageDeck();
        CribbageBoard board = new CribbageBoard();

        //determine who deals first via cutting or random
        determineDealer(p1, p2);
        System.out.println(determineDealer(p1, p2).getName()+" is dealer");

        //do a hand, then swap dealer
        //repeat

    }

    public static void playRound(){

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
