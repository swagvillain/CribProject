public class Main {

    public static void main(String[] args) {
        playCrib();
    }

    public static void playCrib(){
        Player p1 = new Player();
        Player p2 = new Player();
        CribbageDeck deck = new CribbageDeck();
        CribbageBoard board = new CribbageBoard();

        //determine who deals first via cutting or random
        determineDealer(p1, p2);

        //do a hand, then swap dealer
        //repeat

    }

    public static void playRound(){

    }

    public static Player determineDealer(Player p1, Player p2){

    }
}
