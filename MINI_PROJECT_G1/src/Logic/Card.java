package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    private String suit;  // To store the suit of the card
    private String value; // To store the value of the card
    private String imgName;
    static List<Card> deck;

    // Default constructor
    public Card() {
    }

    // Parameterized constructor - 1
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    
    // Parameterized constructor - 2
    public Card(String suit, String value , String imgName) {
        this.suit = suit;
        this.value = value;
        this.imgName = imgName;
    }

    // Getter for suit
    public String getSuit() {
        return suit;
    }

    //Getter for Value
    public String getValue() {
        return value;
    }
    
    //Getter for ImgName
    public String getImgName(){
        return imgName + ".png";
    }
 
    //Setter for Suit 
    public void setSuit(String suit){
        this.suit = suit;
    }
    
    //Setter for Value 
    public void setValue(String value){
        this.value = value;
    }

    // Initialize the deck (create an empty deck)
    private static void initializeDeck() {
        deck = new ArrayList<>();
    }

    // Method to create a full deck of 52 cards
    public static List<Card> createDeck() {
        initializeDeck();  // Initialize the deck

        // Define possible suits and values
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[][] imgs = {
            {"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK"},  // Hearts images
            {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK"},  // Diamonds images
            {"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"},  // Spades images
            {"CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK"}   // Clubs images
        };

        
        for(int i=0 ; i<suits.length ; i++){
            for(int j=0 ; j<values.length ; j++){
                deck.add(new Card(suits[i] , values[j] , imgs[i][j]));
            }
        }
        return deck;  // Return the populated deck
    }
    
    public static List<Card> randomCards(int n){
        Collections.shuffle(deck);
        List<Card> randomDeck = deck.subList(0, n);
        return randomDeck;
    }

    
    //Printing the deck   
    public static void printDeck() {
        for (Card card : deck) {
            System.out.println(card.getValue() + " of " + card.getSuit() + " image : " + card.imgName);
        }
    }
}
