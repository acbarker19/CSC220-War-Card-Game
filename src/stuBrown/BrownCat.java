/*
    Zachriah Brown CSC 220 2/20/2018
    Cat class to test mouse movement
 */
package stuBrown;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author brownzt2021
 */
public class BrownCat {
    
    private BufferedImage cat;
    private Integer over, down;
    
    public BrownCat(Integer x, Integer y){
        over = x;
        down = y;
        try{
        cat = ImageIO.read(new File("src/stuBrown/Cat.png"));
        }
        catch (IOException ex){
            System.out.println("Error loading image");
        }
    }
    
    public String toString(){
        return "にやん~ Nyan~";
    }
    
    public void draw(Graphics g){
        g.drawImage(cat, over, down, null);
    }
    
    public Integer getOver(){return over;}
    public Integer getDown(){return down;}
    public Integer getRight(){return over+cat.getWidth();}
    public Integer getBottom(){return down+cat.getHeight();}
    public void setOver(Integer x){over = x-(cat.getWidth()/2);}
    public void setDown(Integer y){down = y-(cat.getHeight()/2);}
    
}
