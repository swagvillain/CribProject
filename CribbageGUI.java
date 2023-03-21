//package Board;

import javax.swing.*;
import java.awt.*;

public class CribbageGUI extends JFrame {

    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JLabel cribLabel;
    private JLabel dealerLabel;
    private JLabel playerOneScoreLabel;
    private JLabel playerTwoScoreLabel;
    private JLabel cribScoreLabel;
    private JLabel dealerIndicatorLabel;
    private JPanel playerOnePanel;
    private JPanel playerTwoPanel;
    private JPanel cribPanel;
    private JPanel dealerPanel;
    private JPanel scorePanel;

    public CribbageGUI() {
        // Set up GUI components
        playerOneLabel = new JLabel("Player One");
        playerTwoLabel = new JLabel("Player Two");
        cribLabel = new JLabel("Crib");
        dealerLabel = new JLabel("Dealer: ");
        playerOneScoreLabel = new JLabel("0");
        playerTwoScoreLabel = new JLabel("0");
        cribScoreLabel = new JLabel("0");
        dealerIndicatorLabel = new JLabel();

        playerOnePanel = new JPanel();
        playerOnePanel.setLayout(new BoxLayout(playerOnePanel, BoxLayout.PAGE_AXIS));
        playerOnePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerOnePanel.add(playerOneLabel);
        playerOnePanel.add(playerOneScoreLabel);

        playerTwoPanel = new JPanel();
        playerTwoPanel.setLayout(new BoxLayout(playerTwoPanel, BoxLayout.PAGE_AXIS));
        playerTwoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        playerTwoPanel.add(playerTwoLabel);
        playerTwoPanel.add(playerTwoScoreLabel);

        cribPanel = new JPanel();
        cribPanel.setLayout(new BoxLayout(cribPanel, BoxLayout.PAGE_AXIS));
        cribPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cribPanel.add(cribLabel);
        cribPanel.add(cribScoreLabel);

        dealerPanel = new JPanel();
        dealerPanel.setLayout(new BoxLayout(dealerPanel, BoxLayout.PAGE_AXIS));
        dealerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        dealerPanel.add(dealerLabel);
        dealerPanel.add(dealerIndicatorLabel);

        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(1, 3));
        scorePanel.add(playerOnePanel);
        scorePanel.add(cribPanel);
        scorePanel.add(playerTwoPanel);

        // Set up JFrame
        setTitle("Cribbage Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // Add components to JFrame
        add(scorePanel, BorderLayout.CENTER);
        add(dealerPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        CribbageGUI gui = new CribbageGUI();
    }
}
