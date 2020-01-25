/*
 * Copied from in class. Written by Professor Klayder.
 * 23 February 2018
 * Amanda Hegidus
 */
package stuHegidus;


import java.awt.*;


public class HegidusMovableThing{
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public HegidusMovableThing()
    {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        color = new Color(200,200,0);
    }


    public HegidusMovableThing(
        int x,
        int y,
        int width,
        int height,
        Color color)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Color getColor() { return color; }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setMiddleX(int x)
    {
        this.x = x - width/2;
    }

    public void setMiddleY(int y)
    {
        this.y = y - height/2;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
    
    public void drawMe(Graphics g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    public boolean contains(int theX, int theY)
    {
        if (theX > x && theX < x+width
         && theY > y && theY < y+height)
        {
            return true;
        }else
        {
            return false;
        }
        
    }

    public String toString()
        {
            return 
            "   x  " + x +
            "   y  " + y +
            "   width  " + width +
            "   height  " + height +
            "   color  " + color;
    }

    public static void main(String args[]) {
        System.out.println("test code for MovableThing constructor with no parameters");
        HegidusMovableThing temp = new HegidusMovableThing();
        System.out.println("temp.toString()       "+temp.toString() ); 
        System.out.println("temp.getX()      "+temp.getX() ); 
        System.out.println("temp.getY()      "+temp.getY() ); 
        System.out.println("temp.getWidth()      "+temp.getWidth() ); 
        System.out.println("temp.getHeight()      "+temp.getHeight() ); 
        System.out.println("temp.getColor()      "+temp.getColor() ); 

        System.out.println("\ntest code for MovableThing constructor with parameters");
        temp = new HegidusMovableThing(6, 7, 8, 9, Color.RED);
        System.out.println("temp.toString()       "+temp.toString() ); 
        System.out.println("temp.getX()      "+temp.getX() ); 
        System.out.println("temp.getY()      "+temp.getY() ); 
        System.out.println("temp.getWidth()      "+temp.getWidth() ); 
        System.out.println("temp.getHeight()      "+temp.getHeight() ); 
        System.out.println("temp.getColor()      "+temp.getColor() ); 

        temp.setX(10); 
        temp.setY(11); 
        temp.setWidth(12); 
        temp.setHeight(13); 

        System.out.println("\nafter calling setters");
        //now call all of the getters again
        System.out.println("temp.getX()      "+temp.getX() ); 
        System.out.println("temp.getY()      "+temp.getY() ); 
        System.out.println("temp.getWidth()      "+temp.getWidth() ); 
        System.out.println("temp.getHeight()      "+temp.getHeight() ); 
        System.out.println("temp.getColor()      "+temp.getColor() ); 
    }
}