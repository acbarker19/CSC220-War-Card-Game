/*CSC Project 2 editged by Berry Grant IV 
 This is the Sub Class for the Game
 It extends Card
 */
package stuGrant;

import helperCards.Card;
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
 * @author grantb
 */
public class MyCard extends Card     
        
{

    private boolean isFaceUp;
    private BufferedImage faceDownImage;
  
    
    public MyCard (){
        
        super();
        
        isFaceUp = false;
        
    
    }
    
    public MyCard(
            
        String suit,
        int value,
        int over, 
        int down,
        BufferedImage image)
    {
        super(suit,value,over,down, image);
        faceDownImage = getImage("src/cards/"+"faceDown"+".gif");
    
    }
    private BufferedImage getImage(String fileString)
    {
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
//            
        } catch (IOException ex) 
        {
            System.out.println("*********************** error when trying to load image: "+fileString);
            
        }
        return image;
    }
    
    public void flipCard(){
        
        isFaceUp = !isFaceUp;
        
        
    }
    
     public void drawMyCard(Graphics g, JPanel panel)
    {
        System.out.println("is Face Up  "+ isFaceUp);
        if(isFaceUp == true){
            g.setColor(Color.red);
            g.setFont(new Font("Serif", Font.BOLD, 22));

            if (getImage() == null)
            {
                g.drawString(getValue()+" of "+getSuit(), getOver(), getDown());
            }else

            {
                g.drawImage( getImage(), getOver() - getImage().getWidth()/2, getDown() - getImage().getHeight()/2, getImage().getWidth(), getImage().getHeight(), panel); 
            }
        }
        else{// card is face down
            
          g.drawImage( faceDownImage, getOver() - getImage().getWidth()/2, getDown() - getImage().getHeight()/2, getImage().getWidth(), getImage().getHeight(), panel); 
          
            
            
        }
    }
    
    
}
          