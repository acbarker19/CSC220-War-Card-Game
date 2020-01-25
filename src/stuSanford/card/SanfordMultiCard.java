/*
Name:Jacob Sanford
Date: 3/27/17
Description: extends the MultiCard class and works as a bridge to convert Cards to MultiCards for the SanfordDeck class.
*/
package stuSanford.card;

import helperCards.Card;
import helperCards.MultiCard;
import java.util.Random;

public class SanfordMultiCard extends MultiCard
{
    private Random ranGen;
    public SanfordMultiCard(){
        super();
        ranGen = new Random();
    }
    /**
     * Returns a random SanfordCard from the deck
     * @return 
     */
    public SanfordCard getSanfordCard(){
        Card temp = getCardAtIndex(ranGen.nextInt(51));
        SanfordCard answer = new SanfordCard(temp.getSuit(), temp.getValue(), temp.getOver(), temp.getDown(), temp.getImage());
        return answer;
    }
    /**
     * Returns a specific SanfordCard From the Deck
     * @param number
     * @return 
     */
    public SanfordCard getSanfordCard(Integer number){
        return (SanfordCard) getCardAtIndex(number);
    }
}