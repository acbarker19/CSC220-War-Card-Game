
package helperCards;

import helperCards.Card;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/* Jim Klayder -- spring 2016

   This is a MultiCard class that has an array of Card objects as the 
   basic data structure. There is only one constructor that fills the
   array with 52 cards which make a deck of cards.

   The fillCardList() method fills the array data member. 

   Use the main method to test this class and to see how to use the included
   methods.

   Add lots of your own methods to this class. Here are some of the 
   possibiliites:

   * get a card at a given index
   * change the value of a card at a given index to a given value
   * set the card at a given index to a given card
   * is there a card with a given value
   * how many cards have the same suit as a given suit
   * get a random card
   * swap the cards at 2 given indexes (change the order of the 2 cards in the array)
   * shuffle (call swap many times with 2 random indexes)
 

*/

public class MultiCard
{
    private Card[] list;

    public MultiCard()
    {
        fillCardList();
    }

    private void fillCardList()
    {
        
        list = new Card[52];
        list[0] = new Card("C", 1, 100, 100, getImage("src/cards/"+"ac"+".gif"));
        list[1] = new Card("C", 2, 100, 100, getImage("src/cards/"+"2c"+".gif"));
        list[2] = new Card("C", 3, 100, 100, getImage("src/cards/"+"3c"+".gif"));
        list[3] = new Card("C", 4, 100, 100, getImage("src/cards/"+"4c"+".gif"));
        list[4] = new Card("C", 5, 100, 100, getImage("src/cards/"+"5c"+".gif"));
        list[5] = new Card("C", 6, 100, 100, getImage("src/cards/"+"6c"+".gif"));
        list[6] = new Card("C", 7, 100, 100, getImage("src/cards/"+"7c"+".gif"));
        list[7] = new Card("C", 8, 100, 100, getImage("src/cards/"+"8c"+".gif"));
        list[8] = new Card("C", 9, 100, 100, getImage("src/cards/"+"9c"+".gif"));
        list[9] = new Card("C", 10, 100, 100, getImage("src/cards/"+"10c"+".gif"));
        list[10] = new Card("C", 11, 100, 100, getImage("src/cards/"+"jc"+".gif"));
        list[11] = new Card("C", 12, 100, 100, getImage("src/cards/"+"qc"+".gif"));
        list[12] = new Card("C", 13, 100, 100, getImage("src/cards/"+"kc"+".gif"));
        
        list[13] = new Card("D", 1, 100, 100, getImage("src/cards/"+"ad"+".gif"));
        list[14] = new Card("D", 2, 100, 100, getImage("src/cards/"+"2d"+".gif"));
        list[15] = new Card("D", 3, 100, 100, getImage("src/cards/"+"3d"+".gif"));
        list[16] = new Card("D", 4, 100, 100, getImage("src/cards/"+"4d"+".gif"));
        list[17] = new Card("D", 5, 100, 100, getImage("src/cards/"+"5d"+".gif"));
        list[18] = new Card("D", 6, 100, 100, getImage("src/cards/"+"6d"+".gif"));
        list[19] = new Card("D", 7, 100, 100, getImage("src/cards/"+"7d"+".gif"));
        list[20] = new Card("D", 8, 100, 100, getImage("src/cards/"+"8d"+".gif"));
        list[21] = new Card("D", 9, 100, 100, getImage("src/cards/"+"9d"+".gif"));
        list[22] = new Card("D", 10, 100, 100, getImage("src/cards/"+"10d"+".gif"));
        list[23] = new Card("D", 11, 100, 100, getImage("src/cards/"+"jd"+".gif"));
        list[24] = new Card("D", 12, 100, 100, getImage("src/cards/"+"qd"+".gif"));
        list[25] = new Card("D", 13, 100, 100, getImage("src/cards/"+"kd"+".gif"));
        
        list[26] = new Card("H", 1, 100, 100, getImage("src/cards/"+"ah"+".gif"));
        list[27] = new Card("H", 2, 100, 100, getImage("src/cards/"+"2h"+".gif"));
        list[28] = new Card("H", 3, 100, 100, getImage("src/cards/"+"3h"+".gif"));
        list[29] = new Card("H", 4, 100, 100, getImage("src/cards/"+"4h"+".gif"));
        list[30] = new Card("H", 5, 100, 100, getImage("src/cards/"+"5h"+".gif"));
        list[31] = new Card("H", 6, 100, 100, getImage("src/cards/"+"6h"+".gif"));
        list[32] = new Card("H", 7, 100, 100, getImage("src/cards/"+"7h"+".gif"));
        list[33] = new Card("H", 8, 100, 100, getImage("src/cards/"+"8h"+".gif"));
        list[34] = new Card("H", 9, 100, 100, getImage("src/cards/"+"9h"+".gif"));
        list[35] = new Card("H", 10, 100, 100, getImage("src/cards/"+"10h"+".gif"));
        list[36] = new Card("H", 11, 100, 100, getImage("src/cards/"+"jh"+".gif"));
        list[37] = new Card("H", 12, 100, 100, getImage("src/cards/"+"qh"+".gif"));
        list[38] = new Card("H", 13, 100, 100, getImage("src/cards/"+"kh"+".gif"));
        
        list[39] = new Card("S", 1, 100, 100, getImage("src/cards/"+"as"+".gif"));
        list[40] = new Card("S", 2, 100, 100, getImage("src/cards/"+"2s"+".gif"));
        list[41] = new Card("S", 3, 100, 100, getImage("src/cards/"+"3s"+".gif"));
        list[42] = new Card("S", 4, 100, 100, getImage("src/cards/"+"4s"+".gif"));
        list[43] = new Card("S", 5, 100, 100, getImage("src/cards/"+"5s"+".gif"));
        list[44] = new Card("S", 6, 100, 100, getImage("src/cards/"+"6s"+".gif"));
        list[45] = new Card("S", 7, 100, 100, getImage("src/cards/"+"7s"+".gif"));
        list[46] = new Card("S", 8, 100, 100, getImage("src/cards/"+"8s"+".gif"));
        list[47] = new Card("S", 9, 100, 100, getImage("src/cards/"+"9s"+".gif"));
        list[48] = new Card("S", 10, 100, 100, getImage("src/cards/"+"10s"+".gif"));
        list[49] = new Card("S", 11, 100, 100, getImage("src/cards/"+"js"+".gif"));
        list[50] = new Card("S", 12, 100, 100, getImage("src/cards/"+"qs"+".gif"));
        list[51] = new Card("S", 13, 100, 100, getImage("src/cards/"+"ks"+".gif"));
   
        
    }


    //NOTE: this is a PRIVATE method
    //  (it only needs to be called in the fillCardList method)
    private BufferedImage getImage(String fileString)
    {
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
//            System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) 
        {
            System.out.println("*********************** error when trying to load image: "+fileString);
            //System.out.println(ex);
        }
        return image;
    }
    
    public Card getCardAtIndex(int index)
    {
        if (index < 0 || index >= list.length)
        {
            System.out.println("error in MultiCard.getCardAtIndex -- index out of range -- "+index);
            System.out.println("   (setting index to 0)");
            index = 0;
        }
        
        return list[index];
    }

    public int getIndexOfCard(String theSuit, int theValue)
    {
        for (int i=0; i < list.length; i++)
        {
            if (list[i].getSuit().equals(theSuit) && list[i].getValue() == theValue)
            return i;
        }
        return -999;
    }
    
    public int getTotalValue()
    {
        int total = 0;
        for (int i=0; i < list.length; i++)
        {
//            System.out.println("i "+i);
            total += list[i].getValue();
        }
        return total;
    }
    
    
    public String toString()
    {
        String result = "MultiCard has "+list.length+" cards\n";
        for (int i=0; i < list.length; i++)
        {
            //System.out.println(i+".  "+list[i].toString());
            result += "list["+i+"]     "+list[i].toString() + "\n";
        }
        return result;
    }

    public static void main(String args[]) {
        System.out.println("test code for MultiCard class");
        MultiCard temp = new MultiCard();
        System.out.println("temp.toString()       "+temp.toString() ); 
        System.out.println("\n\n\n");
        System.out.println("temp.getTotalValue()      "+temp.getTotalValue() ); 
        System.out.println("\n\n\n");
        System.out.println("temp.getIndexOfCard(\"hearts\", 1)      "+temp.getIndexOfCard("hearts", 1) ); 
        System.out.println("\n\n\n");
        System.out.println("temp.getIndexOfCard(\"hearts\", 123)      "+temp.getIndexOfCard("hearts", 123) ); 
        
    }
}


