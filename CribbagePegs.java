import java.util.Scanner;

public class CribbagePegs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int player1Score = 0;
        int player2Score = 0;

        // Simulate several rounds of play
        for (int round = 1; round <= 4; round++) {
            System.out.println("Round " + round + ":\n");

            // Simulate player 1's turn
            int player1Points = getPlayerPoints(scanner, 1);
            player1Score += player1Points;
            System.out.println("Player 1 scores " + player1Points + " points.\n"
                    + "Player 1's total score is now " + player1Score + ".\n");

            // Simulate player 2's turn
            int player2Points = getPlayerPoints(scanner, 2);
            player2Score += player2Points;
            System.out.println("Player 2 scores " + player2Points + " points.\n"
                    + "Player 2's total score is now " + player2Score + ".\n");
        }

        // Determine the winner
        if (player1Score > player2Score) {
            System.out.println("Player 1 wins!");
        } else if (player2Score > player1Score) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static int getPlayerPoints(Scanner scanner, int playerNum) {
        System.out.println("Player " + playerNum + ", enter your score for this turn:");
        int score = scanner.nextInt();
        while (score < 0 || score > 31) {
            System.out.println("Invalid score! Please enter a score between 0 and 31:");
            score = scanner.nextInt();
        }
        return score;
    }
}
