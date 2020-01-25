/*
    Zachriah Brown CSC 220 2/20/2018
    Sub class of helperCards.Card
 */
package stuBrown;

import helperCards.Card;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author brownzt2021
 */
public class BrownCard extends Card{
    
    private Boolean faceUp, dragging;
    
    public BrownCard(Card temp, Boolean fu){
        super(temp.getSuit(), temp.getValue(), temp.getOver(), temp.getDown(), temp.getImage());
        faceUp = fu;
        dragging = false;
    }
    
    @Override
    public void drawCard(Graphics g, JPanel panel){
        g.setColor(Color.BLACK);
        BufferedImage image = super.getImage();
        if (!faceUp){image = loadImage("src/cards/faceDown.gif");}
        if (getImage() == null){
            g.drawString(getValue()+" of "+getSuit(), getOver(), getDown());
        }
        else{
            g.drawImage(image, getOver(), getDown(), getImage().getWidth(), getImage().getHeight(), panel);
        }
    }
    
    private BufferedImage loadImage(String fileString){
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
        } 
        catch (IOException ex) {
            System.out.println("Error when trying to load image");
        }
        return image;
    }
    
    public void setFaceUp(Boolean fu){faceUp = fu;}
    public Boolean getFaceUp(){return faceUp;}
    public Boolean containsPoint(Integer x, Integer y){
        Boolean answer = false;
        if (x >= getOver() && x <= getOver()+71 && y >= getDown() && y <= getDown()+96){
            answer = true;
        }
        return answer;
    }
    public Boolean getDragging(){return dragging;}
    public void setDragging(Boolean d){dragging = d;}
    public Integer getBottom(){return getDown()+96;}
    public Integer getRight(){return getOver()+71;}
    public Boolean isBlack(){
        Boolean answer = false;
        if (getSuit().equals("C") || getSuit().equals("S")){answer = true;}
        return answer;
    }
    
}
