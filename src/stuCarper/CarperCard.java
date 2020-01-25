/*
Name Austin Carper
Date Due 3/19
Class CSC 220
URL to Project https://drive.google.com/open?id=1iiLIuCfOQTNyZvxLSDEa0F-sjxPwsNvc

This is the sub class for the Card Class. This subclass adds 1 Constructor and 
4 methods. Collect Boolean is used for victory condition in Carper Panel.
Contains method is used to check if click is within the card. 
Move method is used to simplify the setOver and setDown Methods

*/
package stuCarper;

import helperCards.Card;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class CarperCard extends Card {
    
    private Boolean collect;
    
    public CarperCard(String s, int v, int o, int d, BufferedImage i){
        super(s,v,o,d,i);
        collect = false;
    }
    
    public Boolean getCollect(){
        return collect;
    }
    
    public void setCollect(Boolean c){
        collect = c;
    }
    
    public void move(int x, int y){
 
        super.setOver(x);
        super.setDown(y);
        
        
    }
    
    public Boolean contains(int x, int y){

        if ((x>= getOver()-36&& x <= getOver()+ 36) && (y >= getDown()-48 && y <= getDown() + 48) ){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
