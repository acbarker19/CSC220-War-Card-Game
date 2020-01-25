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

* code copied from MultiCard class in helperCards package but changes the use of Card class to using HegidusCard.
 */
package stuHegidus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HegidusMultiCard {

    private HegidusCard[] list;

    public HegidusMultiCard() {
        fillCardList();
    }

    private void fillCardList() {
        //adds all cards from a standard deck of cards to list. note there are no jokers.
        list = new HegidusCard[52];
        list[0] = new HegidusCard("C", 1, 100, 100, getImage("src/cards/" + "ac" + ".gif"));
        list[1] = new HegidusCard("C", 2, 100, 100, getImage("src/cards/" + "2c" + ".gif"));
        list[2] = new HegidusCard("C", 3, 100, 100, getImage("src/cards/" + "3c" + ".gif"));
        list[3] = new HegidusCard("C", 4, 100, 100, getImage("src/cards/" + "4c" + ".gif"));
        list[4] = new HegidusCard("C", 5, 100, 100, getImage("src/cards/" + "5c" + ".gif"));
        list[5] = new HegidusCard("C", 6, 100, 100, getImage("src/cards/" + "6c" + ".gif"));
        list[6] = new HegidusCard("C", 7, 100, 100, getImage("src/cards/" + "7c" + ".gif"));
        list[7] = new HegidusCard("C", 8, 100, 100, getImage("src/cards/" + "8c" + ".gif"));
        list[8] = new HegidusCard("C", 9, 100, 100, getImage("src/cards/" + "9c" + ".gif"));
        list[9] = new HegidusCard("C", 10, 100, 100, getImage("src/cards/" + "10c" + ".gif"));
        list[10] = new HegidusCard("C", 11, 100, 100, getImage("src/cards/" + "jc" + ".gif"));
        list[11] = new HegidusCard("C", 12, 100, 100, getImage("src/cards/" + "qc" + ".gif"));
        list[12] = new HegidusCard("C", 13, 100, 100, getImage("src/cards/" + "kc" + ".gif"));

        list[13] = new HegidusCard("D", 1, 100, 100, getImage("src/cards/" + "ad" + ".gif"));
        list[14] = new HegidusCard("D", 2, 100, 100, getImage("src/cards/" + "2d" + ".gif"));
        list[15] = new HegidusCard("D", 3, 100, 100, getImage("src/cards/" + "3d" + ".gif"));
        list[16] = new HegidusCard("D", 4, 100, 100, getImage("src/cards/" + "4d" + ".gif"));
        list[17] = new HegidusCard("D", 5, 100, 100, getImage("src/cards/" + "5d" + ".gif"));
        list[18] = new HegidusCard("D", 6, 100, 100, getImage("src/cards/" + "6d" + ".gif"));
        list[19] = new HegidusCard("D", 7, 100, 100, getImage("src/cards/" + "7d" + ".gif"));
        list[20] = new HegidusCard("D", 8, 100, 100, getImage("src/cards/" + "8d" + ".gif"));
        list[21] = new HegidusCard("D", 9, 100, 100, getImage("src/cards/" + "9d" + ".gif"));
        list[22] = new HegidusCard("D", 10, 100, 100, getImage("src/cards/" + "10d" + ".gif"));
        list[23] = new HegidusCard("D", 11, 100, 100, getImage("src/cards/" + "jd" + ".gif"));
        list[24] = new HegidusCard("D", 12, 100, 100, getImage("src/cards/" + "qd" + ".gif"));
        list[25] = new HegidusCard("D", 13, 100, 100, getImage("src/cards/" + "kd" + ".gif"));

        list[26] = new HegidusCard("H", 1, 100, 100, getImage("src/cards/" + "ah" + ".gif"));
        list[27] = new HegidusCard("H", 2, 100, 100, getImage("src/cards/" + "2h" + ".gif"));
        list[28] = new HegidusCard("H", 3, 100, 100, getImage("src/cards/" + "3h" + ".gif"));
        list[29] = new HegidusCard("H", 4, 100, 100, getImage("src/cards/" + "4h" + ".gif"));
        list[30] = new HegidusCard("H", 5, 100, 100, getImage("src/cards/" + "5h" + ".gif"));
        list[31] = new HegidusCard("H", 6, 100, 100, getImage("src/cards/" + "6h" + ".gif"));
        list[32] = new HegidusCard("H", 7, 100, 100, getImage("src/cards/" + "7h" + ".gif"));
        list[33] = new HegidusCard("H", 8, 100, 100, getImage("src/cards/" + "8h" + ".gif"));
        list[34] = new HegidusCard("H", 9, 100, 100, getImage("src/cards/" + "9h" + ".gif"));
        list[35] = new HegidusCard("H", 10, 100, 100, getImage("src/cards/" + "10h" + ".gif"));
        list[36] = new HegidusCard("H", 11, 100, 100, getImage("src/cards/" + "jh" + ".gif"));
        list[37] = new HegidusCard("H", 12, 100, 100, getImage("src/cards/" + "qh" + ".gif"));
        list[38] = new HegidusCard("H", 13, 100, 100, getImage("src/cards/" + "kh" + ".gif"));

        list[39] = new HegidusCard("S", 1, 100, 100, getImage("src/cards/" + "as" + ".gif"));
        list[40] = new HegidusCard("S", 2, 100, 100, getImage("src/cards/" + "2s" + ".gif"));
        list[41] = new HegidusCard("S", 3, 100, 100, getImage("src/cards/" + "3s" + ".gif"));
        list[42] = new HegidusCard("S", 4, 100, 100, getImage("src/cards/" + "4s" + ".gif"));
        list[43] = new HegidusCard("S", 5, 100, 100, getImage("src/cards/" + "5s" + ".gif"));
        list[44] = new HegidusCard("S", 6, 100, 100, getImage("src/cards/" + "6s" + ".gif"));
        list[45] = new HegidusCard("S", 7, 100, 100, getImage("src/cards/" + "7s" + ".gif"));
        list[46] = new HegidusCard("S", 8, 100, 100, getImage("src/cards/" + "8s" + ".gif"));
        list[47] = new HegidusCard("S", 9, 100, 100, getImage("src/cards/" + "9s" + ".gif"));
        list[48] = new HegidusCard("S", 10, 100, 100, getImage("src/cards/" + "10s" + ".gif"));
        list[49] = new HegidusCard("S", 11, 100, 100, getImage("src/cards/" + "js" + ".gif"));
        list[50] = new HegidusCard("S", 12, 100, 100, getImage("src/cards/" + "qs" + ".gif"));
        list[51] = new HegidusCard("S", 13, 100, 100, getImage("src/cards/" + "ks" + ".gif"));

    }

    //NOTE: this is a PRIVATE method
    //  (it only needs to be called in the fillCardList method)
    private BufferedImage getImage(String fileString) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(fileString));
//            System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) {
            System.out.println("** error when trying to load image: " + fileString + "**");
            //System.out.println(ex);
        }
        return image;
    }

    public HegidusCard getCardAtIndex(int index) {
        //returns card at given index
        if (index < 0 || index >= list.length) {
            System.out.println("error in MultiCard.getCardAtIndex -- index out of range -- " + index);
            System.out.println("   (setting index to 0)");
            index = 0;
        }

        return list[index];
    }

    public int getIndexOfCard(String theSuit, int theValue) {
        //returns index of the card with the suit and value passed
        for (int i = 0; i < list.length; i++) {
            if (list[i].getSuit().equals(theSuit) && list[i].getValue() == theValue) {
                return i;
            }
        }
        return -999;
    }

    public int getTotalValue() {
        //sums all the values of the "deck"
        int total = 0;
        for (int i = 0; i < list.length; i++) {
//            System.out.println("i "+i);
            total += list[i].getValue();
        }
        return total;
    }

    public String toString() {
        String result = "HegidusMultiCard has " + list.length + " cards\n";
        for (int i = 0; i < list.length; i++) {
            //System.out.println(i+".  "+list[i].toString());
            result += "list[" + i + "]     " + list[i].toString() + "\n";
        }
        return result;
    }
//main method for testing:
/*    public static void main(String args[]) {
        System.out.println("test code for MultiCard class");
        HegidusMultiCard temp = new HegidusMultiCard();
        System.out.println("temp.toString()       " + temp.toString());
        System.out.println("\n\n\n");
        System.out.println("temp.getTotalValue()      " + temp.getTotalValue());
        System.out.println("\n\n\n");
        System.out.println("temp.getIndexOfCard(\"hearts\", 1)      " + temp.getIndexOfCard("hearts", 1));
        System.out.println("\n\n\n");
        System.out.println("temp.getIndexOfCard(\"hearts\", 123)      " + temp.getIndexOfCard("hearts", 123));

    }*/
}
