/* 
 * Faith Haiss
 * February 21st, 2018
 * CSC 220
 *
 * Class used for the cards in the game. Is a sub class of the Card class.
 */

package stuHaiss;

import helperCards.Card;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * Begin HaissCard
 */
public class HaissCard extends Card{
    
    private Image FaceDownImage;
    private Boolean IsFaceDown;
    
    //These are to fix errors I had
    private Font myFont;
    private BufferedImage image;
    private int value;
    private int over;
    private int down;
    private String suit;
    
    //Begin constructors
    
    public HaissCard(){
    }

    public HaissCard(
        String suit,
        int value)
    {
       super(suit, value);
       FaceDownImage = getImage("src/cards/"+"faceDown"+".gif");
       IsFaceDown = false;
    }
    
    public HaissCard(
        String suit,
        int value,
        int over, 
        int down,
        BufferedImage image)
    {
        super(suit, value, over, down, image);
        FaceDownImage = getImage("src/cards/"+"faceDown"+".gif");
        IsFaceDown = false;
    }
    
    //End constructors
    
    //Sets faceDown value for cards
    public void setFaceDown(boolean faceDown){
        IsFaceDown = faceDown;
    }
    
    //NOTE: This is a PRIVATE method
    //It only needs to be called in the fillCardList method
    private BufferedImage getImage(String fileString)
    {
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
            //System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) 
        {
            System.out.println("*********************** error when trying to load image: "+fileString);
            //System.out.println(ex);
        }
        return image;
    }
    
    //Draws the card
    public void drawCard(Graphics g, JPanel panel)
    {
        g.setColor(Color.red);
        g.setFont(myFont);
        
        if (image == null)
        {
            g.drawString(value+" of "+suit, over, down);
        }else
        {
            g.drawImage(image, over - image.getWidth()/2, down - image.getHeight()/2, image.getWidth(), image.getHeight(), panel); 
        }
        
        if(IsFaceDown == true){
            drawFaceDownCard(g, panel);
        }
        else{
            super.drawCard(g, panel);
        }
    }
    
    //Draws the cards facedown
    public void drawFaceDownCard(Graphics g, JPanel panel)
    {
        g.setColor(Color.red);
        //g.setFont(myFont);
        
        if (getImage() == null)
        {
            g.drawString(getValue()+" of "+getSuit(), getOver(), getDown());
        }else
        {
            g.drawImage(FaceDownImage, getOver() - getImage().getWidth()/2, getDown()
                    - getImage().getHeight()/2, getImage().getWidth(), getImage().getHeight(), panel); 
        }
        
    }
    
}