public class CribbageBoard {
    private int player1Score;
    private int player2Score;
    private int currentTotal;
    private int currentStarter;

    public CribbageBoard() {
        player1Score = 0;
        player2Score = 0;
        currentTotal = 0;
        currentStarter = 1;
    }

    public void addPoints(int player, int points) {
        if (player == 1) {
            player1Score += points;
        } else {
            player2Score += points;
        }
        checkForWin();
    }

    public void addCardValue(int cardValue) {
        currentTotal += cardValue;
        if (currentTotal == 31) {
            addPoints(currentStarter, 2);
            resetCount();
        } else if (currentTotal > 31) {
            resetCount();
            switchStarter();
        }
    }

    private void switchStarter() {
        currentStarter = currentStarter == 1 ? 2 : 1;
    }

    private void resetCount() {
        currentTotal = 0;
    }

    private void checkForWin() {
        if (player1Score >= 121 || player2Score >= 121) {
            System.out.println("Game Over");
            System.out.println("Player 1 Score: " + player1Score);
            System.out.println("Player 2 Score: " + player2Score);
        }
    }

    public static void main(String[] args) {
        CribbageBoard board = new CribbageBoard();

    }
}