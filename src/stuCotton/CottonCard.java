/* Erin Cotton
 * March 27,2018
 * CSC 220
 * Description: This is a specific class which extends the class Card.
 */ 
package stuCotton;

import helperCards.Card;
import helperCards.MultiCard;
import helpers.Utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



/**
 *
 * @author cottonem2020
 */
public class CottonCard extends Card{
    

    
    public CottonCard(Card temp, Boolean fu){
        super(temp.getSuit(), temp.getValue(), temp.getOver(), temp.getDown(), temp.getImage());

    }
    
    @Override
    
// This is used to draw a card.
    public void drawCard(Graphics g, JPanel panel){
        g.setColor(Color.BLACK);
        BufferedImage image = getImage();
        if (getImage() == null){
            g.drawString(getValue()+" of "+getSuit(), getOver(), getDown());
        }
        else{
            g.drawImage(image, getOver(), getDown(), getImage().getWidth(), getImage().getHeight(), panel);
        }
    }
    
// This loads the buffered, face down image. If it can't load it prints an error.
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
    
    
}
    

