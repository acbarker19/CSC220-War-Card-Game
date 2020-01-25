// Jim Klayder --- spring 2018

/*
This sub class of helperCards.Card has two utility methods added to the
Card class.

containsMousePoint will return true iff x, y is within the current graphical 
location of the Card

isRed will return true iff the suit of the Card is either "D" or "H".

*/

package demoMenusAndCardLayout;

import helperCards.Card;


public class MyPanel_3_Card extends Card
{
    public static final int WIDTH = 71;
    public static final int HEIGHT = 96;
    
    public MyPanel_3_Card(Card theCard)
    {
        super(theCard.getSuit(), theCard.getValue(), theCard.getOver(), theCard.getDown(), theCard.getImage());
        
    }
    
    public boolean containsMousePoint(int x, int y)
    {
        //note: getOver() and getDown() return the over and down of the center of the card
        //so we will subtract half the WIDTH and HEIGHT to get the over and down
        //of the upper left corner of the card
        
        int cardOver = getOver() - WIDTH / 2;
        int cardDown = getDown() - HEIGHT / 2;
        
        if (  x > cardOver && x < cardOver + WIDTH
           && y > cardDown && y < cardDown + HEIGHT)
        {
            return true;
        }else
        {
            return false;
        }
    }
    
    public boolean isRed()
    {
        if (getSuit().equals("D") || getSuit().equals("H"))
        {
            return true;
        }else
        {
            return false;
        }
    }
}
