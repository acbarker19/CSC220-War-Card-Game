/*
 * Amanda Hegidus
 * 21 February 2018
 * CSC 220
http://raider.mountunion.edu/csc/CSC220/Spring2018/

Project Description: Create a working card game using the Card class subclass (HegidusCard), displaying multiple cards, 
use MouseListener and/or MouseMotionListener, use a random number, display a congratulations message if the user wins the game,
have the user able to view game rules, and have the user able to restart/start a new game.
The game is Game of Elevens Solitaire.
Date: 19 March 2018
Course: CSC 220
Organization: this is the panel that will be displayed when MainFrame in demoMenusAndCardLayout package. this panel will display
9 HegidusCards, setting their boolean used to true, has a button to display the game rules of Elevens Solitaire in a JOptionPane,
and has a button that restarts the game/starts a new game.
Enhancement: boolean of whether a card has been used/displayed, dragging cards

* extends the Card class from helperCards package. this program uses all aspects of cards and adds a boolean for whether or not a 
card has been used/displayed already (true for already used, false for unused).
 */
package stuHegidus;

import helperCards.Card;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HegidusCard extends Card {

    private boolean used;
    private static final Font FONT = new Font("Serif", Font.BOLD, 22);
    private String suit;
    private int value;
    private int over;
    private int down;
    private BufferedImage image;
    private boolean cardmoving;

    public HegidusCard() {
        super();
        used = false;
        cardmoving = false;
    }

    public HegidusCard(String suit, int value) {
        super(suit, value);
        this.suit = suit;
        this.value = value;
        used = false;
        cardmoving = false;
    }

    public HegidusCard(String suit, int value, int over, int down, BufferedImage image) {
        super(suit, value, over, down, image);
        this.suit = suit;
        this.value = value;
        this.over = over;
        this.down = down;
        this.image = image;
        used = false;
        cardmoving = false;
    }

    private BufferedImage getImage(String fileString) {
        //returns the image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(fileString));
//            System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) {
            System.out.println("**error when trying to load image: " + fileString);
            //System.out.println(ex);
        }
        return image;
    }

    public boolean getMoving() {
        return cardmoving;
    }

    public void setMoving(boolean moving) {
        this.cardmoving = moving;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean containsPoint(int x, int y) {
        int cardOver = getOver() - image.getWidth() / 2;
        int cardDown = getDown() - image.getHeight() / 2;
        if (x > cardOver && x < cardOver + image.getWidth() && y > cardOver && y < cardDown + image.getHeight()) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        String suitname = "";
        if (null == this.getSuit()) {
            suitname = "Spades";
        } else //will use this to print out Hearts instead of H
        {
            switch (this.getSuit()) {
                case "C":
                    suitname = "Clubs";
                    break;
                case "D":
                    suitname = "Diamonds";
                    break;
                case "H":
                    suitname = "Hearts";
                    break;
                default:
                    suitname = "Spades";
                    break;
            }
        }
        //will use this to print out King instead of 13
        String valuename = "";
        switch (this.getValue()) {
            case 1:
                valuename = "Ace";
                break;
            case 2:
                valuename = "Two";
                break;
            case 3:
                valuename = "Three";
                break;
            case 4:
                valuename = "Four";
                break;
            case 5:
                valuename = "Five";
                break;
            case 6:
                valuename = "Six";
                break;
            case 7:
                valuename = "Seven";
                break;
            case 8:
                valuename = "Eight";
                break;
            case 9:
                valuename = "Nine";
                break;
            case 10:
                valuename = "Ten";
                break;
            case 11:
                valuename = "Jack";
                break;
            case 12:
                valuename = "Queen";
                break;
            default:
                valuename = "King";
                break;
        }

        return ("   " + valuename + " of " + suitname);
    }

}
