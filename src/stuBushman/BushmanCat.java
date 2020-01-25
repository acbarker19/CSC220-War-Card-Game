/*
 * Made by Nick Bushman
 * CSC 220 Spring 2018
 * class that is a sub class to the Card class
 */
package stuBushman;

import helperCards.Card;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Nick
 */
public class BushmanCat extends Card{
    public static final int WIDTH = 71;
    public static final int HEIGHT = 96;

    
    
    public BushmanCat(Card card){
   super(card.getSuit(), card.getValue(), card.getOver(), 
           card.getDown(),  card.getImage()); 
    }
   
  
    //example from Dr. Klayders MyPanel_3_Card method
    public boolean containsMousePoint(int x, int y)
    {   
        int cardOver = getOver() - WIDTH / 2;
        int cardDown = getDown() - HEIGHT / 2;
        
        if (  x > cardOver && x < cardOver + WIDTH
           && y > cardDown && y < cardDown + HEIGHT)
        {
            return true;
        }else
        {
            return false;
        }
    }
    public int getTotal(){
        int total = getValue();
        
        return total;
       
    }


}

