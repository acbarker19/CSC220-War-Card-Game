/* Trevor Ryan
   CSC 220
   2/21/18
This package was made as a sub class of the cards package. 
This package is used to create the cards and construct how 
the cards will be layed out and how they will be used.
There are also methods in this package that allow the 
user to get the parameters of the card to use towards the 
mouse listeners.
 * 
 */
package stuRyan;

import helperCards.Card;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ryantd2021
 */
public class RyanCard extends Card {

    private boolean faceDown = false;
    private BufferedImage image, imagecopy;

    public RyanCard() {
        super();
    }

    public RyanCard(String suit, int value) {
        super(suit, value);
    }

    public RyanCard(String suit, int value, int over, int down, BufferedImage image) {
        super(suit, value, over, down, image);
        this.image = image;
    }

    public boolean containsPoint(int theX, int theY) {
        int cardOver = getOver() - image.getWidth() / 2;
        int cardDown = getDown() - image.getHeight() / 2;
        if (theX >= super.getOver() && theX <= super.getOver()+71 && theY >= super.getDown() && theY <= super.getDown()+96) {
            return true;
        } else {
            return false;
        }
    }

    public BufferedImage setImage() {
        this.image = this.imagecopy;
        return this.image;
    }

    public void setFaceDown() {
        image = setImage("src/cards/" + "faceDown" + ".gif");
//        this.imagecopy = super.getImage();

    }
    public void setFaceUp(){
        image = setImage("src/cards/");
    }

    public BufferedImage setImage(String fileString) {
        this.imagecopy = super.getImage();
        try {
            this.image = ImageIO.read(new File(fileString));
//            System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) {
//            System.out.println("*********************** error when trying to load image: "+fileString);
            System.out.println(ex);
        }
        return this.image;
    }

    @Override
    public void drawCard(Graphics g, JPanel panel) {

        g.setColor(Color.BLACK);

        if (getImage() == null) {
            g.drawString(getValue() + " of " + getSuit(), getOver(), getDown());
        } else {
            g.drawImage(image, super.getOver(), super.getDown(), image.getWidth(), image.getHeight(), panel);
        }

    }

}
