/*
Name:Jacob Sanford
Date: 3/28/17
Description: extends the Card class and adds a few new getters, setLocation(x,y), a check if a location is in the card, and
    changes the draw method. 
*/
package stuSanford.card;
import helperCards.Card;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class SanfordCard extends Card
{ 
    public SanfordCard(){
        super();
    }
    
    SanfordCard(String suit, int value, int over, int down, BufferedImage image) {
       super(suit, value, over, down, image);
    }
    
    /*
    Sets both an X and Y cordinate for the card
    */
    public void setCardLocation(int x, int y){
        setOver(x);
        setDown(y);
    }
    
    public Integer getWidth(){
        return getImage().getWidth();
    }
    public Integer getHeight(){
        return getImage().getHeight();
    }
        
    /**
     * Checks to see if an object is over the card. If it is, you will be returned true.
     * @param x
     * @param y
     * @return 
     */
    public Boolean checkIsOnCard(int x, int y){
        if(x > getOver() && x < getOver() + getImage().getWidth() 
                && y > getDown() && y < getDown() + getImage().getWidth()){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void drawCard(Graphics g, JPanel panel)
    {
        g.setColor(Color.red);
/**        
        if (this.image == null)
        {
            System.out.println("StuSanford.card.SanfordCard.java: No card to draw");
        }else
        {
            g.drawImage(this.getImage(), this.getOver(), this.getDown(), this.getImage().getWidth(), this.getImage().getHeight(), panel); 
        }
*/
    }
}