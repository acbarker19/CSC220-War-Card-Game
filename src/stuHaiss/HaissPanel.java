/* 
 * Faith Haiss
 * February 21st, 2018
 * CSC 220
 * http://raider.mountunion.edu/csc/CSC220/Spring2018/
 *
 * Project 3: Card Game. I made a card matching game. Find all matches to win.
 *
 * Enhancement: Using an image for the conratulations message.
 *
 * Notes: I commented out all System.out.println lines for the final project as
 * they were only used for testing.
 */

package stuHaiss;

import helperCards.Card;
import helperCards.MultiCard;
import helpers.Utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


/* 
 * Begin HaissPanel
 */
public class HaissPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener{

    private Font myFont;
    
    private HaissCat kitty;
    
    private HaissMoveable movingThing;
    
    private boolean boxMoving;
    private boolean cardMoving;
    
    private MultiCard multiCard;
    private HaissCard currentCard;
    
    private HaissCard[] cardList;
    private HaissCard[] secondList;
    
    private boolean isFirstClick;
    
    private HaissCard firstCard;
    private HaissCard secondCard;
    
    private int matchCounter;
    
    private BufferedImage congratsImage;
    
    /*
     * Creates new form MyPanel_1
     */
    public HaissPanel() {
        initComponents();
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //Sets font
        myFont = new Font("Serif", Font.BOLD, 22);
        
        //Used for testing back in early stages of project
        kitty = new HaissCat ("Pickles");
        movingThing = new HaissMoveable(0, 0, 50, 50, new Color(204, 204, 255));
        
        boxMoving=false;
        cardMoving=false;
        
        // Begin card related stuff
        multiCard = new MultiCard();
        
        int randomCardIndex = Utility.getRandomFromTo(0, 51);
        Card temp= multiCard.getCardAtIndex(randomCardIndex);
        //System.out.println(temp.toString());
        currentCard = new HaissCard(temp.getSuit(), temp.getValue(), temp.getOver(), temp.getDown(), temp.getImage());
        
        cardList = new HaissCard[5];
        secondList = new HaissCard[5];
        
        //Sets the first click to be on first click
        isFirstClick = true;
        
        //Set the match counter at the start
        matchCounter = 0;
        
        //Sets image to Congratulations.png
        congratsImage = getImage("src/stuHaiss/"+"Congratulations"+".png");
        
        //Creates the pairs of cards
        for (int i = 0; i < cardList.length; i++) {
            cardList[i] = getRandomCard();
            cardList[i].setOver(i * 100 + 100);
            cardList[i].setFaceDown(true);
        }
        
        for (int i = 0; i < secondList.length; i++) {
            secondList[i] = new HaissCard(cardList[i].getSuit(), cardList[i].getValue(), 
                    cardList[i].getOver(), cardList[i].getDown() + 100,
                    cardList[i].getImage());
            secondList[i].setFaceDown(true);
        }
        
        //Shuffles cards
        shuffleCards();
    }
    
    //Assigns a random card value to the cards
    private HaissCard getRandomCard(){
        int randomCardIndex = Utility.getRandomFromTo(0, 51);
        Card temp= multiCard.getCardAtIndex(randomCardIndex);
        //System.out.println(temp.toString());
        HaissCard tempHaiss = new HaissCard(temp.getSuit(), temp.getValue(), temp.getOver(), temp.getDown(), temp.getImage());
        
        return tempHaiss;
    }
    
    //Shuffles card order of the second row
    public void shuffleCards(){
        for (int i = 0; i < secondList.length; i++) {
            int randomNum = Utility.getRandomFromTo(0, 4);
            int overOfRandomCard = secondList[randomNum].getOver();
            int overOfCurrentCard = secondList[i].getOver();
            
            secondList[i].setOver(overOfRandomCard);
            secondList[randomNum].setOver(overOfCurrentCard);
        }
    }
    
    //Get congrats image
    private BufferedImage getImage(String fileString)
    {
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
            //System.out.println("image "+fileString+"       ok");
        } catch (IOException ex) 
        {
            System.out.println("*********************** error when trying to load image: "+fileString);
            //System.out.println(ex);
        }
        return image;
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setColor(new Color(204, 255, 204));
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(myFont);
        
        g.setColor(new Color(204, 204, 255));
        myFont=new Font("Helvetica", Font.BOLD | Font.ITALIC,22);
        g.setFont(myFont);
        g.drawString("HaissPanel", 175, 29);
        g.drawString("Welcome to Faith's matching game!", 350, 29);
        g.drawString("Matches made:", 600, 75);
        g.drawString(matchCounter + " out of 5", 600, 100);
        
        matchesMade(g);
        
        //g.drawString(kitty.toString(), 50, 200);
        
        //For testing moveable
        //movingThing.drawMe(g);
        
        //Used for testing in early stages, is commented out because it made an extra card
        //if(currentCard != null){
        //    currentCard.drawCard(g, this);
        //}
        
        //Draws the pairs of cards
        
        //First row
        for (int i = 0; i < cardList.length; i++) {
            cardList[i].drawCard(g, this);
            //System.out.println(cardList[i].getDown());
        }
        
        //Second row
        for (int i = 0; i < secondList.length; i++) {
            secondList[i].drawCard(g, this);
           //System.out.println(secondList[i].getDown());
        }
    }

    
    //Auto-generated code, do not modify.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Rules = new javax.swing.JButton();
        NewGame = new javax.swing.JButton();

        Rules.setText("Rules");
        Rules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RulesActionPerformed(evt);
            }
        });

        NewGame.setText("New Game");
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(NewGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Rules)
                .addContainerGap(206, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Rules)
                    .addComponent(NewGame))
                .addContainerGap(265, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RulesActionPerformed
        //Button used to display the rules
        
        JOptionPane.showMessageDialog(this,
            "Turn over one card then choose another card to flip over.\n"
          + "If the cards match, the match counter will go up.\n"
          + "If they don't match they'll be flipped back over.\n"
          + "Remember where the cards are located and keep flipping them over until you find all the matches.");
    }//GEN-LAST:event_RulesActionPerformed

    private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameActionPerformed
        //Button used to start a new game
        
        //System.out.println("Starting new game.");
        
        //Resets cards and begins new game
        int randomCardIndex = Utility.getRandomFromTo(0, 51);
        //currentCard = HaissCard.getCardAtIndex(randomCardIndex);
        Card temp= multiCard.getCardAtIndex(randomCardIndex);
        //System.out.println(temp.toString());
        currentCard = new HaissCard(temp.getSuit(), temp.getValue(), temp.getOver(), temp.getDown(), temp.getImage());
        
        //Creates new pairs of cards
        for (int i = 0; i < cardList.length; i++) {
            cardList[i] = getRandomCard();
            cardList[i].setOver(i * 100 + 100);
            cardList[i].setFaceDown(true);
        }
        
        for (int i = 0; i < secondList.length; i++) {
            secondList[i] = new HaissCard(cardList[i].getSuit(), cardList[i].getValue(), 
                    cardList[i].getOver(), cardList[i].getDown() + 100,
                    cardList[i].getImage());
            secondList[i].setFaceDown(true);
        }
        
        matchCounter = 0;
        
        shuffleCards();
        
        repaint();
    }//GEN-LAST:event_NewGameActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewGame;
    private javax.swing.JButton Rules;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e){
        //System.out.println("mouseClicked at "+e.getX()+", "+e.getY());
        int x=e.getX();
        int y=e.getY();
        //System.out.println("mouseClicked at "+x+", "+y);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e){
        //System.out.println("mousePressed at "+e.getX()+", "+e.getY());
        //System.out.println("mousePressed at "+e.getX()+", "+e.getY());
        
        //Used for comparing cards to see if they match
        if(isFirstClick){
            firstClick(e);
            isFirstClick = false;
        }
        else{
            secondClick(e);
            isFirstClick = true;
            if(comparesClicks() == true){
                //System.out.println("They match!");
            }
            else{
                //System.out.println("They don't match!");
            }
        }
        
        //int x=e.getX();
        //int y=e.getY();
       
        //if (movingThing.contains(x, y)){
        //    boxMoving = true;
        //}
        //else{
        //    boxMoving = false;
        //}
        
        
        repaint();
    }
    
    //The firstClick method
    public void firstClick(MouseEvent e){
        int i = 0;
        
        //System.out.println("Do first click.");
        
        //Turns one card face over when clicked upon in the first array
        for (i = 0; i < cardList.length; i++) {
                if(e.getX()>(cardList[i].getOver()-(cardList[i].getImage().getWidth()/2))
                        && 
                   e.getX()<(cardList[i].getOver()+(cardList[i].getImage().getWidth()/2))
                        && 
                   e.getY()>(cardList[i].getDown()-(cardList[i].getImage().getHeight()/2))
                        &&
                   e.getY()<(cardList[i].getDown()+(cardList[i].getImage().getHeight()/2))
                   )
                {
                    cardList[i].setFaceDown(false);
                    firstCard = cardList[i];
                }
                else{
                    cardList[i].setFaceDown(true);
                }
        }
        
        //Turns one card face over when clicked upon in the second array
        for (i = 0; i < secondList.length; i++) {
                if(e.getX()>(secondList[i].getOver()-(secondList[i].getImage().getWidth()/2))
                        &&
                   e.getX()<(secondList[i].getOver()+(secondList[i].getImage().getWidth()/2))
                        &&
                   e.getY()>(secondList[i].getDown()-(secondList[i].getImage().getHeight()/2))
                        &&
                   e.getY()<(secondList[i].getDown()+(secondList[i].getImage().getHeight()/2))
                   )
                {
                    secondList[i].setFaceDown(false);
                    firstCard = secondList[i];
                }
                else{
                    secondList[i].setFaceDown(true);
                }
        }
    }
    
    //The secondclick method
    public void secondClick(MouseEvent e){
        int i = 0;
        
        //System.out.println("Do second click.");
        
        if(isFirstClick == false){
            //Turns one card face over when clicked upon
            for (i = 0; i < cardList.length; i++) {
                    if(e.getX()>(cardList[i].getOver()-(cardList[i].getImage().getWidth()/2))
                            && 
                       e.getX()<(cardList[i].getOver()+(cardList[i].getImage().getWidth()/2))
                            && 
                       e.getY()>(cardList[i].getDown()-(cardList[i].getImage().getHeight()/2))
                            &&
                       e.getY()<(cardList[i].getDown()+(cardList[i].getImage().getHeight()/2))
                       )
                    {
                        cardList[i].setFaceDown(false);
                        secondCard = cardList[i];
                    }
            }

            //Turns one card face over when clicked upon
            for (i = 0; i < secondList.length; i++) {
                    if(e.getX()>(secondList[i].getOver()-(secondList[i].getImage().getWidth()/2))
                            &&
                       e.getX()<(secondList[i].getOver()+(secondList[i].getImage().getWidth()/2))
                            &&
                       e.getY()>(secondList[i].getDown()-(secondList[i].getImage().getHeight()/2))
                            &&
                       e.getY()<(secondList[i].getDown()+(secondList[i].getImage().getHeight()/2))
                       )
                    {
                        secondList[i].setFaceDown(false);
                        secondCard = secondList[i];
                    }
            }
        }
    }
    
    //Compares firstClick and secondClick to see if the cards match
    public boolean comparesClicks(){
        if(firstCard.getSuit() == secondCard.getSuit()
                &&
           firstCard.getValue() == secondCard.getValue())
        {
            matchCounter++;
            firstCard.setDown(-500);
            secondCard.setDown(-500);
            return true;
        }
        else{
            return false;
        }
    }
    
    //Checks to see if all matches have been made, displays congratulations message
    public void matchesMade(Graphics g){
        if(matchCounter == 5){
            //System.out.println("All matches have been made!");
            g.drawImage(congratsImage, 150, 100, 300, 231, this); 
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        //System.out.println("mousePressed at "+e.getX()+", "+e.getY());
        //System.out.println("mouseReleased at "+e.getX()+", "+e.getY());
        
        //System.out.println("   moving  "+boxMoving);
        //boxMoving = false;
        //System.out.println("   moving  "+boxMoving);
        
        if (boxMoving){boxMoving=false;}
        if (cardMoving){cardMoving=false;}
    }

    @Override
    public void mouseEntered(MouseEvent e){
        //System.out.println("mouseEntered at "+e.getX()+", "+e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e){
        //System.out.println("mouseExited at "+e.getX()+", "+e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e){
        //System.out.println("mouseDragged at "+e.getX()+", "+e.getY());
        
        int x=e.getX();
        int y=e.getY();
        int i = 0;
        
        //System.out.println("mouseDragged at "+x+", "+y);
        if (boxMoving){
            movingThing.setMiddleX(x);
            movingThing.setMiddleY(y);
            repaint();
        }
        
        if (cardMoving){
            cardList[i].setDown(e.getY());
            cardList[i].setOver(e.getX());
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        //System.out.println("mouseMoved at "+e.getX()+", "+e.getY());
    }
}