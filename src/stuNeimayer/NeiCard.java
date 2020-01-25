/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuNeimayer;


import helperCards.Card;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author neimaykh2021
 */
public class NeiCard extends Card 
    {
    
   
    private int width;
    private int height;
    private int x;
    private int y;
    
    private boolean isfacedown;
    private BufferedImage faceDownImage;
    private NeimayerPanel panel;
    public NeiCard()
    {
        
        
        width = 71;
        height = 96;
        x = super.getOver();
        y = super.getDown();
        isfacedown = true;
        
    }

    public NeiCard(Card theCard, BufferedImage faceDown,NeimayerPanel panel){
    super(theCard.getSuit(),
            theCard.getValue(),
            theCard.getOver(),
            theCard.getDown(),
            theCard.getImage()); 
            faceDownImage = faceDown;
            this.panel = panel;
        //String suit,
        //int value,
        //int over, 
        //int down,
        //BufferedImage image
            
    }
            


    
    
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    

 
        public void setMiddleX(int x)
    {
        this.x = x - super.getImage().getWidth()/2;
    }
  
    public void setMiddleY(int y)
    {
        this.y = y - super.getImage().getHeight()/2;
    }

     public void setFaceDown(boolean a)
    {
        isfacedown = a;
    }
   



     

       public void setValue(int Value)
    {
        super.setValue(Value);
    }
    
    
    
    public void drawMe(Graphics g)
    {
        
        if (isfacedown == true){
           g.drawImage(faceDownImage, getOver() - faceDownImage.getWidth()/2, getDown() - faceDownImage.getHeight()/2, faceDownImage.getWidth(), faceDownImage.getHeight(), panel); 
        
        }
        
        
        
        if (isfacedown == false){
        
            super.drawCard(g, panel);
         
            
            
        }
    }
    
    public boolean contains(int theX, int theY)
    {
        if (theX > super.getOver() - super.getImage().getWidth()/2 && theX < super.getOver()+ width + super.getImage().getWidth()/2
         && theY > super.getDown() - super.getImage().getHeight()/2 && theY < super.getDown() + height + super.getImage().getHeight()/2)
        {
            return true;
        }else
        {
            return false;
        }
        
    }

    
    
    
    
    
}
