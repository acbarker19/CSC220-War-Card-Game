/*
This class is a subclass of the Card class. It feature methods that are not
already included in the Card class.

Created By: Alec Barker
Due: 3/28/18
CSC 220
 */
package stuBarker;

import helperCards.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class subCard extends Card {
    
    private Boolean isFaceDown;
    private BufferedImage faceDownImage;
    private Integer WIDTH = 71;
    private Integer HEIGHT = 96;
    
    public subCard(Card theCard){
        super(theCard.getSuit(), theCard.getValue(), theCard.getOver(), theCard.getDown(), theCard.getImage());
        isFaceDown = true;
        
        faceDownImage = getImage("src/cards/"+"faceDown"+".gif");
    }

    //Draws an image of a card
    private BufferedImage getImage(String fileString)
    {
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
//            System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) 
        {
            System.out.println("*********************** error when trying to load image: "+fileString);
            //System.out.println(ex);
        }
        return image;
    }
    
    //Checks if the player is clicking inside the card
    public boolean containsMousePoint(int x, int y){
        int cardOver = getOver() - WIDTH/2;
        int cardDown = getDown() - HEIGHT/2;
        
        if(x > cardOver && x < cardOver + WIDTH && y > cardDown && y < cardDown + HEIGHT){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean getFaceDown(){
        return isFaceDown;
    }
    
    public void setFaceDown(Boolean faceDown){
        isFaceDown = faceDown;
    }
    
    public void turnCardOver(){
        isFaceDown = !isFaceDown;
        //System.out.println("isfacedown  " + isFaceDown);
    }
    
    //Draws an image of a card
    public void drawCard(Graphics g, JPanel panel){
        if(isFaceDown == true){
            //System.out.println("draw face down");
            if(faceDownImage != null){
            g.drawImage(faceDownImage, getOver() - faceDownImage.getWidth()/2, getDown() - faceDownImage.getHeight()/2,
                    faceDownImage.getWidth(), faceDownImage.getHeight(), panel);
            }
        }
        else{
            super.drawCard(g, panel);
            //System.out.println("draw face up");
        }
    }
    
}
