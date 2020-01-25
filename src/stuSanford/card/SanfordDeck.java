/*
Name: Jacob Sanford
Date: 3/27/2018
Description: A class that is a ArrayList of SanfordCard that has features to allow the cards to draw with proper cordinates, 
    and generate new list on need.
*/
package stuSanford.card;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javax.swing.JPanel;


public class SanfordDeck{
    private ArrayList <SanfordCard> deck;
    private SanfordMultiCard multiCard;
    private Integer score;
    private Random ranGen;
    private SanfordCard gameCard;
    public SanfordDeck(JPanel panel){
        deck = new ArrayList ();
        multiCard = new SanfordMultiCard();
        ranGen = new Random();
        score = 0;
        gameCard = new SanfordCard();
        generateCardList();
    }
    //Score
    /**
     * Sets score
     * @param s 
     */
    public void setScore(int s){
        score = s;
    }
    /**
     * Returns score
     * @return 
     */
    public int getScore(){
        return score;
    }
    
    //Card actions
    /**
    * Generates 3 cards in an array List
    */
    public void generateCardList(){
        for(int i = 0; i < 3; i++){
            deck.add(multiCard.getSanfordCard());
        }
        placeDownCards();
    }
    /**
    Puts three new cards in the ArrayList and replaces the old ones
    */
    public void replaceCardList(){
        for(int i = 0; i < 3; i++){
            deck.set(i, multiCard.getSanfordCard());
        }
        placeDownCards();
    }
    /**
    Sets Cordinates for the cards in the arraylist and generates a gamecard
    */
    private void placeDownCards(){
        int xCord = 20;
        for(int i = 0; i < deck.size(); i++)
        {
            deck.get(i).setCardLocation(xCord, 70);
            xCord += 100;
        }//end of for(i)
        int index = ranGen.nextInt(3);
        gameCard = new SanfordCard(deck.get(index).getSuit(), deck.get(index).getValue(), 120, 180, deck.get(index).getImage());
    }//end of placeDownCards 
    /**
     * Draws cards
     * @param g
     * @param panel 
     */
    public void drawCards(Graphics g, JPanel panel){
        g.fillRect(118,178,gameCard.getWidth()+6,gameCard.getHeight()+6);
        gameCard.drawCard(g, panel);
        for(int x = 0; x < deck.size(); x++){  
            deck.get(x).drawCard(g, panel);
        }//end of for
    }
    /**
     * Checks to see if a card is clicked on and then checks to see if it matches one of the main card.
     * If it matches the main card, the user gets a point added.
     * @param x
     * @param y 
     */
    public void checkIfCardMatch(int x, int y){
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).checkIsOnCard(x, y)){
                if(deck.get(i).getSuit().equals(gameCard.getSuit()) && deck.get(i).getValue() == (gameCard.getValue())){
                    score++;
                }//end of if
                else{
                    score--;
                }//end of else
                replaceCardList();
            }//end of if
         }//end of for
    }//end of checkIfCardMain
}//end of SanfordDeck



