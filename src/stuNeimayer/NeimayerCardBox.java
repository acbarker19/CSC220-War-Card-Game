/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuNeimayer;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author neimaykh2021
 */
public class NeimayerCardBox {
        private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    


    public NeimayerCardBox(
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
    
}
