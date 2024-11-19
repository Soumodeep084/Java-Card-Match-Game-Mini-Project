package GUI;

import Logic.Card;  // Existing logic class
import Logic.User;  // Existing User class
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePage extends javax.swing.JFrame {

    private JLabel[] cardLabels;
    private HashSet<Integer> blinkSet;
    private String name = null;
    int blinkCheck = 3, currentBlinkIndex = -1, level = 1, totalCards = 8, score = 0;
    private final URL backSideCard = getClass().getResource("/Images/Other/Front.png");
    Timer timer;
    int secondsLeft = 10;
    List<Card> randomCard;
    Random random = new Random();

    public GamePage() {
        initComponents();
        User.initializeUserData();
        initialDeck();
    }

    public void sendGamePageName(String name) {
        this.name = name;
    }

    private void initialDeck() {
        levelLabel.setText("0" + level);
        Card.createDeck(); // Create Deck of Cards
        initializeCardLabels(); // Initalize the labels with Cards Logo
        startCountDown(); // Start the CountDown timer
    }

    private void initializeCardLabels() {
        submitGuess.setEnabled(false);
        cardLabels = new JLabel[totalCards];
        randomCard = Card.randomCards(totalCards);

        for (int i = 0; i < totalCards; i++) {
            Card card = randomCard.get(i);
            JPanel cardP = new JPanel(new java.awt.GridBagLayout());  // Use GridBagLayout for centering
            cardLabels[i] = new JLabel();

            cardLabels[i].setSize(115, 150);  // Set the size to match the card image size
            cardLabels[i].setIcon(new ImageIcon(getClass().getResource("/Images/Card/" + card.getImgName())));
            cardLabels[i].setHorizontalAlignment(JLabel.CENTER);  // Ensure the image is centered within the JLabel
            cardLabels[i].setVerticalAlignment(JLabel.BOTTOM);

            cardP.setSize(130, 160);  // Set size of JPanel
            cardP.add(cardLabels[i], new java.awt.GridBagConstraints());  // Add JLabel to JPanel with centered positioning

            cardPanel.add(cardP);  // Add the JPanel containing the centered JLabel to the cardPanel
        }

        cardPanel.revalidate();  // Refresh panel to display added cards
        cardPanel.repaint();     // Force panel to repaint
    }

    private void startCountDown() {
        if (timer != null) {
            timer.stop();
            secondsLeft = 10;
        }
        updateTimerLabel(secondsLeft);
        timer = new Timer(1000, (ActionEvent e) -> {
            secondsLeft--;
            updateTimerLabel(secondsLeft);

            if (secondsLeft <= 0) {
                timer.stop();
                timerLabel.setText("Cards Flipped . All the Best!!!");
                flipCardsToBack();
            }
        });
        timer.start();
    }

    private void stopCountDown() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void updateTimerLabel(int secondsLeft) {
        timerLabel.setText("Time Left: " + secondsLeft + " seconds to flip the Cards");
    }

    private void flipCardsToBack() {
        submitGuess.setEnabled(true);
        for (JLabel cardLabel : cardLabels) {
            cardLabel.setIcon(new ImageIcon(backSideCard));  // Change to card back image
        }
        blinkSet = new HashSet<>();  // Initialize the Set Cards for blinking Check 
        blinkCards();
    }

    private void blinkCards() {
        scoreLabel.setText("" + score);  // Set Score Label
        if (blinkCheck == 0) {
            checkNextLevel();
            return;
        }

        currentBlinkIndex = getNextCardIndex();  // Get the next card to blink
        if (currentBlinkIndex != -1) {
            JPanel parentPanel = (JPanel) cardLabels[currentBlinkIndex].getParent();
            parentPanel.setBackground(Color.yellow);  // Highlight the card
        }
    }

//  Method to get the next card index for blinking
    private int getNextCardIndex() {
        int randomNumber;
        do {
            randomNumber = random.nextInt(totalCards);  // Get a random card index
        } while (blinkSet.contains(randomNumber));  // Ensure it's not already blinked
        blinkSet.add(randomNumber);  // Add to set of blinked cards
        blinkCheck--;  // Decrement remaining blink count
        return randomNumber;  // Return the index for the next blinking card
    }

//  Next Level Check 
    private void checkNextLevel() {
        if (level > 3) {
            endGame();
            return;
        }
        int checkScore = level == 1 ? 5 : 8;
        if (Integer.parseInt(scoreLabel.getText()) >= checkScore) {
            resetGameForNextLevel();
        } else {
            endGame();
        }
    }

//  Reset Level 
    private void resetGameForNextLevel() {
        cardPanel.removeAll();  // Clear previous cards
        blinkSet.clear();  // Clear blink set
        User.setData(new User(name, score, level));  // Update user data
        String message = "Level " + level + " completed. Welcome to Level " + (level + 1) + ".";
        int showDialog = JOptionPane.showConfirmDialog(
                rootPane,
                message,
                "Level Completed",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (showDialog == JOptionPane.YES_OPTION) {
            level++;  // Increment level
            score = 0;
            blinkCheck = (level == 2 ? 4 : 5);  // Adjust blink check for the next level
            totalCards = 10;  // Increase total cards
            initialDeck();  // Reinitialize the deck
        } else {
            endGame();  // End the game if user declines
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ComboSuit = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        ComboValues = new javax.swing.JComboBox<>();
        submitGuess = new javax.swing.JButton();
        timerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 0));
        setLocation(new java.awt.Point(100, 80));
        setPreferredSize(new java.awt.Dimension(1030, 550));
        setSize(new java.awt.Dimension(1030, 550));

        cardPanel.setBackground(new java.awt.Color(204, 255, 255));
        cardPanel.setAlignmentX(10.0F);
        cardPanel.setAlignmentY(10.0F);
        cardPanel.setPreferredSize(new java.awt.Dimension(810, 400));
        cardPanel.setLayout(new java.awt.GridLayout(2, 5, 1, 1));

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 272));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("SCORE :-");

        scoreLabel.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(102, 0, 102));
        scoreLabel.setText("0");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText(" Level :-");

        levelLabel.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        levelLabel.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoreLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 255, 153));

        jLabel4.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel4.setText("Guess the Suit :");

        ComboSuit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ComboSuit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diamonds", "Clubs", "Spades", "Hearts" }));

        jLabel5.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel5.setText("Guess the Value :");

        ComboValues.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ComboValues.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Jack" }));

        submitGuess.setBackground(new java.awt.Color(255, 204, 255));
        submitGuess.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        submitGuess.setText("SUBMIT GUESS");
        submitGuess.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 255), 2, true));
        submitGuess.setFocusPainted(false);
        submitGuess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitGuessActionPerformed(evt);
            }
        });

        timerLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboSuit, 0, 142, Short.MAX_VALUE)
                    .addComponent(ComboValues, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addComponent(submitGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboSuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(timerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(submitGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitGuessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitGuessActionPerformed

        JPanel parentPanel = (JPanel) cardLabels[currentBlinkIndex].getParent();
        parentPanel.setBackground(new Color(204, 255, 255));
        String suit = (String) ComboSuit.getSelectedItem();
        String value = (String) ComboValues.getSelectedItem();
        Card currentCard = randomCard.get(currentBlinkIndex);  // Get the card currently blinking
        if (suit.equalsIgnoreCase(currentCard.getSuit())) {
            score += 1;
        } else {
            score -= 1;
        }

        if (value.equalsIgnoreCase(currentCard.getValue())) {
            score += 2;
        } else {
            score -= 1;
        }
        blinkCards();
    }//GEN-LAST:event_submitGuessActionPerformed

    private void endGame() {
        User.setData(new User(name, score, level));  // Save final user data

        // Show a game over message
        JOptionPane.showMessageDialog(
                this,
                "Game Over! Your score is: " + score,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Dispose the current game frame
        this.dispose();

        // Transition to the leaderboard page
        LeaderBoard leaderboard = new LeaderBoard();  // Assuming you have a LeaderBoard class
        leaderboard.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GamePage().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboSuit;
    private javax.swing.JComboBox<String> ComboValues;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton submitGuess;
    private javax.swing.JLabel timerLabel;
    // End of variables declaration//GEN-END:variables
}
