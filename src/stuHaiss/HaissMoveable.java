/* 
 * Faith Haiss
 * February 21st, 2018
 * CSC 220
 *
 * Fellow student Amanda Hegidus helped me with making this class.
 * Code copied in class from Professor Klayder.
 */

package stuHaiss;

import java.awt.Color;
import java.awt.Graphics;

/*
 * Begin HaissMoveable
 */
public class HaissMoveable {
    private Color color;
    private int x;
    private int y;
    private int height;
    private int width;
    
    public HaissMoveable(){
        x = 0;
        y = 0;
        height = 0;
        width = 0;
        color = Color.BLACK;
    }
    
    public HaissMoveable(int x, int y, int height, int width, Color color){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }
    
    
    //Getters
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getHeight(){ return height; }
    public int getWidth(){ return width; }
    public Color getColor(){ return color; }
    
    
    //Setters
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setMiddleX(int x){
        this.x = x - width/2;
    }

    public void setMiddleY(int y){
        this.y = y - height/2;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    
    //Draws test moveable thing
    public void drawMe(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    //Used to determine if the mouse has clicked inside the area
    public boolean contains(int theX, int theY){
        if (theX > x && theX < x+width
         && theY > y && theY < y+height)
        {
            return true;
        }else
        {
            return false;
        }
        
    }
    
    //Used for testing purposes.
    public String toString(){
            return 
            "   x  " + x +
            "   y  " + y +
            "   width  " + width +
            "   height  " + height +
            "   color  " + color;
    }

    public static void main(String args[]) {
        //System.out.println("test code for MovableThing constructor with no parameters");
        HaissMoveable temp = new HaissMoveable();
        //System.out.println("temp.toString()       "+temp.toString() ); 
        //System.out.println("temp.getX()      "+temp.getX() ); 
        //System.out.println("temp.getY()      "+temp.getY() ); 
        //System.out.println("temp.getWidth()      "+temp.getWidth() ); 
        //System.out.println("temp.getHeight()      "+temp.getHeight() ); 
        //System.out.println("temp.getColor()      "+temp.getColor() ); 

        //System.out.println("\ntest code for MovableThing constructor with parameters");
        temp = new HaissMoveable(6, 7, 8, 9, Color.RED);
        //System.out.println("temp.toString()       "+temp.toString() ); 
        //System.out.println("temp.getX()      "+temp.getX() ); 
        //System.out.println("temp.getY()      "+temp.getY() ); 
        //System.out.println("temp.getWidth()      "+temp.getWidth() ); 
        //System.out.println("temp.getHeight()      "+temp.getHeight() ); 
        //System.out.println("temp.getColor()      "+temp.getColor() ); 

        temp.setX(10); 
        temp.setY(11); 
        temp.setWidth(12); 
        temp.setHeight(13); 

        //System.out.println("\nafter calling setters");
        //Now call all of the getters again
        //System.out.println("temp.getX()      "+temp.getX() ); 
        //System.out.println("temp.getY()      "+temp.getY() ); 
        //System.out.println("temp.getWidth()      "+temp.getWidth() ); 
        //System.out.println("temp.getHeight()      "+temp.getHeight() ); 
        //System.out.println("temp.getColor()      "+temp.getColor() ); 
    }
}