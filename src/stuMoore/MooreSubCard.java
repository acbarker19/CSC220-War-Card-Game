/*
 * Name: Andrew Moore
 * Date: 3/14/18
 * Class: MooreSubCard
 * Decribition: Be able to use all methods of super class, Cards, and use personalized
 * methods like Contains, and set the card to show facedown or show faceup.
 */

package stuMoore;

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
 * @author andrewmoore
 */
public class MooreSubCard extends Card {
    
    private BufferedImage image;
    private BufferedImage imagecopy;
    private Boolean faceDown=false;
    
    public MooreSubCard(String suit, int value, int over, int down, BufferedImage image){
        super(suit, value, over, down, image);
        this.image=image;
    }
    
    public Boolean Contains(int theX, int theY){
        if (theX >= super.getOver() && theX <= super.getOver()+71 && theY >= super.getDown() && theY <= super.getDown()+96){
            return true;
        }else{
            return false;
        }
    }
    
    public BufferedImage setImage(){
        this.image=this.imagecopy;
        return this.image;
    }
    
    public void setFaceDown(){
        image = setImage("src/cards/"+"faceDown"+".gif");
//        this.imagecopy = super.getImage();
        
    }
    
    public BufferedImage setImage(String fileString){
        this.imagecopy = super.getImage();
        try {
            this.image = ImageIO.read(new File(fileString));
//            System.out.println("image "+fileString+"       ok");
        } catch (IOException ex)
            {
//            System.out.println("*********************** error when trying to load image: "+fileString);
            System.out.println(ex);
            }
            return this.image;
    }
    
    @Override
    public void drawCard(Graphics g, JPanel panel){
        g.setColor(Color.red);
        g.setFont(new Font("Serif", Font.BOLD, 22)/*myFont*/);
        
        if (image == null)
        {
            g.drawString(super.getValue()+" of "+super.getSuit(), super.getOver(), super.getDown());
        }else
        {
//            g.drawImage(image, this.getOver() - image.getWidth()/2, super.getDown() - image.getHeight()/2, image.getWidth(), image.getHeight(), panel); 
            g.drawImage(image, this.getOver(), super.getDown(), image.getWidth(), image.getHeight(), panel);
        }
    }
    
    
}
