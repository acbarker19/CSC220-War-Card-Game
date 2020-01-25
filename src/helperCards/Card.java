
package helperCards;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

//Jim Klayder -- February 2018 -- updated for graphic display

/* Jim Klayder -- spring 2016

   This is a basic Card class with constructors, getters, setters, 
   toString, and main methods.

   Validity checks should be added to the setters.

   Use the main method to test this class and to see how to use the included
   methods.
*/
public class Card
{
    private String suit;
    private int value;
    
    private int over;
    private int down;
    private BufferedImage image;
    
    private static final Font myFont = new Font("Serif", Font.BOLD, 22);

    public Card()
    {
        suit = "clubs";
        value = 11;
        over = 50;
        down = 50;
        image = null;
    }


    public Card(
        String suit,
        int value)
    {
        this.suit = suit;
        this.value = value;
        over = 50;
        down = 50;
        image = null;
    }
    
    public Card(
        String suit,
        int value,
        int over, 
        int down,
        BufferedImage image)
    {
        this.suit = suit;
        this.value = value;
        this.over = over;
        this.down = down;
        this.image = image;
    }

    public String getSuit() { return suit; }
    public int getValue() { return value; }
    public int getOver() { return over; }
    public int getDown() { return down; }
    public BufferedImage getImage() { return image; }

    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setOver(int over)
    {
        this.over = over;
    }
    
    public void setDown(int down)
    {
        this.down = down;
    }
    
    public void drawCard(Graphics g, JPanel panel)
    {
        g.setColor(Color.red);
        g.setFont(myFont);
        
        if (image == null)
        {
            g.drawString(value+" of "+suit, over, down);
        }else
        {
            g.drawImage(image, over - image.getWidth()/2, down - image.getHeight()/2, image.getWidth(), image.getHeight(), panel); 
        }
        
    }

    public String toString()
        {
            return 
            "   suit  " + suit +
            "   value  " + value;
    }

    public static void main(String args[]) {
        System.out.println("test code for Card constructor with no parameters");
        Card temp = new Card();
        System.out.println("temp.toString()       "+temp.toString() ); 
        System.out.println("temp.getSuit()      "+temp.getSuit() ); 
        System.out.println("temp.getValue()      "+temp.getValue() ); 

        System.out.println("\ntest code for Card constructor with parameters");
        temp = new Card("hearts", 6);
        System.out.println("temp.toString()       "+temp.toString() ); 
        System.out.println("temp.getSuit()      "+temp.getSuit() ); 
        System.out.println("temp.getValue()      "+temp.getValue() ); 

        temp.setSuit("spades"); 
        temp.setValue(7); 

        System.out.println("\nafter calling setters");
        //now call all of the getters again
        System.out.println("temp.getSuit()      "+temp.getSuit() ); 
        System.out.println("temp.getValue()      "+temp.getValue() ); 
    }
}
