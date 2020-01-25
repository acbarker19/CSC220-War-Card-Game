/*
CSC 220 Card Game (Project #2)

Project Description:
Create a card game NetBeans package and contribute it to the Google Drive
CSC220_spring2018_classProject. In your package create your own card game. The
exact nature of your card game is up to you, but the game should:
    be well documented
    use a sub class of the Card class (found in the helperCards package)
    use an ArrayList of your Card sub class objects
    display several card images during the game
    use MouseListener and/or MouseMotionListener
    use a random number in some way
    display a special congratulations message if the user wins the game
    have a button to view "game rules"
    have a button to start a "new game"
    have at least one enhancement that is documented at the top of your Panel class
Other than those requirements, the game itself is of your own design.
The completed program should be turned in by Wednesday, March 28. Upload your
Netbeans package to Google Drive as we have practiced in class.

Created By: Alec Barker
Due: 3/28/18
CSC 220

Enhancements:
Alert boxes show when the game ends or you click on the "Game Rules" button. You
can find their code in the getNewCards() and getCardButtonActionPerformed()
methods.
The values of the cards are compared to see which is larger. The player with the
higher card value then gets both cards. You can find this code in the
mouseClicked() method.

How the Program Works:
This panel creates the classic card game of War. Each player draws a card, and
the player with the higher value card gets both cards. Once the entire deck is
used, the player with the most cards wins.
A MultiCard object is used to fill an ArrayList of subCards. Two random numbers
are drawn and are used to get two cards from the ArrayList. If the player clicks
inside the card while it is in the designated area, it will flip both cards. The
player must move their card from the deck to their area. The value of each card
is then calculated and the two cards are given to player with the higher card
value. Once 52 cards have been dealt, the program checks to see which player has
the higher number of cards.
 */
package stuBarker;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class BarkerPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener{

    private Font myFont;
    private MultiCard fullDeck;
    private ArrayList<subCard> currentDeck;
    private subCard yourCard;
    private subCard theirCard;
    private Card faceDown1;
    private Card faceDown2;
    private Card faceDown3;
    private Integer yourWins;   //The number of cards you have
    private Integer theirWins;  //The number of cards they have
    private Integer thePot;
    private Integer theRandomNumberChosen;
    private Boolean isTurnable; //Determines if the card can be flipped
    private Boolean isMovable;  //Determines if the card can be moved
    private Integer cardsDealt;
    
    /**
     * Creates new form MyPanel_1
     */
    public BarkerPanel() {
        initComponents();
        
        addMouseListener(this);
        addMouseMotionListener(this);
        
        //Creates a deck of every possible card
        fullDeck = new MultiCard();

        // Creates and fills an arraylist with every possible (sub)card
        currentDeck = new ArrayList(); 
        fillCurrentDeck();
        
        yourWins = 0;
        theirWins = 0;
        thePot = 0;
        theRandomNumberChosen = 0;
        isTurnable = true;
        cardsDealt = 0;
        
        yourScore.setText("Your Score: " + yourWins);
        theirScore.setText("Their Score: " + theirWins);
        potLabel.setText("Cards in the Pot: " + thePot);
        
        //Gets a random card out of the deck for your card
        yourCard = currentDeck.get(getRandomNumber());
        yourCard.setOver(400);
        yourCard.setDown(197);
        
        //Gets a random card out of the deck for their card
        theirCard = currentDeck.get(getRandomNumber());
        theirCard.setOver(600);
        theirCard.setDown(197);
        
        System.out.println(yourCard);
        System.out.println(theirCard);

        //Cards seem to be 70 wide and 90 tall
        //According to Dr. Klayder's screen, they are 71 wide and 96 tall
        
        isMovable = false;
        
        myFont = new Font("Serif", Font.BOLD, 55);
 
        //Creates a deck of facedown cards in the center of the panel
        faceDown1 = new Card("None", 0, 400, 207, getImage("src/cards/"+"faceDown"+".gif"));
        faceDown2 = new Card("None", 0, 400, 202, getImage("src/cards/"+"faceDown"+".gif"));
        faceDown3 = new Card("None", 0, 400, 197, getImage("src/cards/"+"faceDown"+".gif"));
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setColor(new Color(255,200,200));
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(myFont);
        
        g.setColor(Color.BLACK);
        g.drawString("WAR", 330, 50);
        
        g.setFont(new Font("Serif", Font.BOLD, 22));
        g.drawString("By Alec Barker", 327, 75);

        //Draws the deck of face down cards in the center of the panel
        faceDown1.drawCard(g, this);
        faceDown2.drawCard(g, this);
        faceDown3.drawCard(g, this);
        g.drawString("The Deck", 355, 140);
        
        //Draws a box to place your card in
        g.drawString("Your Card", 160, 140);
        g.drawRect(165, 145, 90, 110);
        
        //Draws your card and their card on the screen
        g.drawString("Their Card", 545, 140);
        theirCard.drawCard(g, this);
        yourCard.drawCard(g, this);
        
        //Draws a box to place your card
    }
    
    //Returns a random card from currentDeck
    public subCard getRandomCard(){
        subCard tempCard;
        theRandomNumberChosen = getRandomNumber();
        tempCard = currentDeck.get(theRandomNumberChosen);
        System.out.println(theRandomNumberChosen + " " + tempCard);
        
        return tempCard;
    }
    
    //Returns a random number
    public Integer getRandomNumber(){
        Integer randomCardIndex = Utility.getRandomFromTo(0, 51);
        return randomCardIndex;
    }
    
    //Fill currentDeck with subCards of every card in MultiCard
    public void fillCurrentDeck(){
        for(Integer i = 0; i < 51; i++){
            currentDeck.add(new subCard(fullDeck.getCardAtIndex(i)));
        }
    }
    
    //Starts a new game
    public void newGame(){
        yourWins = 0;
        theirWins = 0;
        thePot = 0;
        cardsDealt = 0;
        
        yourScore.setText("Your Score: " + yourWins);
        theirScore.setText("Their Score: " + theirWins);
    }
    
    //Gets new random subCards from currentDeck and makes them turnable
    private void getNewCards(){
        yourCard = currentDeck.get(getRandomNumber());
        theirCard = currentDeck.get(getRandomNumber());
        
        yourCard.setOver(400);
        yourCard.setDown(197);
        yourCard.setFaceDown(true);

        theirCard.setOver(600);
        theirCard.setDown(197);
        theirCard.setFaceDown(true);

        //System.out.println("new cards");
        //System.out.println(yourCard);
        //System.out.println(theirCard);
        
        isTurnable = true;
        
        //If 52 cards are dealt, it checks to see who has more cards, gives a
        //notification, and starts a new game
        if(cardsDealt == 52){
            if(yourWins > theirWins){
            //Alert box code taken from Troyseph and stefanobaghino on StackOverflow
            //https://stackoverflow.com/questions/7080205/popup-message-boxes?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            JOptionPane.showMessageDialog(null, "You Won!\nThe game will now"
                    + " reset.", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(yourWins < theirWins){
            //Alert box code taken from Troyseph and stefanobaghino on StackOverflow
            //https://stackoverflow.com/questions/7080205/popup-message-boxes?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            JOptionPane.showMessageDialog(null, "You Lost.\nThe game will now"
                    + " reset.", "Sorry", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
            //Alert box code taken from Troyseph and stefanobaghino on StackOverflow
            //https://stackoverflow.com/questions/7080205/popup-message-boxes?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            JOptionPane.showMessageDialog(null, "You Tied.\nThe game will now"
                    + " reset.", "Tie Game", JOptionPane.INFORMATION_MESSAGE);
            }
            newGame();
        }
        
        repaint();
    }
    
    //Draws a specific card image
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        yourScore = new javax.swing.JLabel();
        theirScore = new javax.swing.JLabel();
        yourCardCount = new javax.swing.JLabel();
        getCardButton = new javax.swing.JButton();
        theirCardCount = new javax.swing.JLabel();
        rulesButton = new javax.swing.JButton();
        newGameButton = new javax.swing.JButton();
        potLabel = new javax.swing.JLabel();

        yourScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yourScore.setText("Your Score:");

        theirScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        theirScore.setText("Their Score:");

        getCardButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        getCardButton.setText("Get New Cards");
        getCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getCardButtonActionPerformed(evt);
            }
        });

        rulesButton.setText("Game Rules");
        rulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesButtonActionPerformed(evt);
            }
        });

        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        potLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        potLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yourCardCount)
                        .addGap(246, 246, 246))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yourScore, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rulesButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(potLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getCardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(theirCardCount)
                        .addGap(65, 65, 65))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newGameButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(theirScore, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(425, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(yourCardCount)
                            .addComponent(theirCardCount))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(getCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rulesButton)
                            .addComponent(newGameButton)
                            .addComponent(yourScore)
                            .addComponent(theirScore))
                        .addGap(4, 4, 4)))
                .addComponent(potLabel)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void getCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getCardButtonActionPerformed
        getNewCards();
    }//GEN-LAST:event_getCardButtonActionPerformed

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesButtonActionPerformed
        //Alert box code taken from Troyseph and stefanobaghino on StackOverflow
        //https://stackoverflow.com/questions/7080205/popup-message-boxes?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        JOptionPane.showMessageDialog(null, "1. Drag a card from the center deck"
                + " to the square that says \"Your Deck\".\n2. Click on your card, and both cards will flip."
                + "\n3. The player with the highest card value gets both cards."
                + "\n     If both cards have the same value, they are added to the pot."
                + "\n     The next player who has the highest card will get both cards"
                + " and every card in the pot.\n4. Once 52 cards are dealt, the"
                + " player with the highest number of cards wins.\n\nPLEASE NOTE: Randomly"
                + " choosing cards causes some odd bugs that I can't fix."
                + "\n     If you encounter a bug, such as not being able to flip or drag"
                + " a card,\n     a card appears in the wrong place onscreen, or an"
                + " error appears in the Java console,\n     try hitting the \"Get New"
                + " Cards\" button a few times. The bug should fix itself.",
                "How to Play and Rules", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_rulesButtonActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        newGame();        
        getNewCards();
    }//GEN-LAST:event_newGameButtonActionPerformed
  
    public void mouseClicked(MouseEvent e){
        //Checks if you are clicking in the card, in the "Your Card" box, and if
        //isTurnable is true. If it meets all criteria, it will flip the card,
        //change isTurnable to false (so you can't keept flipping it), and add
        //points to the correct person or the pot.
        if(e.getX() > yourCard.getOver() - 35 && e.getX() < yourCard.getOver() + 35
                && e.getY() > yourCard.getDown() - 45 && e.getY() < yourCard.getDown() + 45
                && e.getX() > 165 && e.getX() < 255 && e.getY() > 145 && e.getY() < 255 &&
                isTurnable == true){
            yourCard.turnCardOver();
            theirCard.turnCardOver();

            if(yourCard.getFaceDown() == false){
                if(yourCard.getValue() > theirCard.getValue()){
                    yourWins += 2 + thePot;
                    thePot = 0;
                    cardsDealt += 2;
                }
                else if(yourCard.getValue() < theirCard.getValue()){
                    theirWins += 2 + thePot;
                    thePot = 0;
                    cardsDealt += 2;
                }
                else{
                    thePot += 2;
                    cardsDealt += 2;
                }

                yourScore.setText("Your Score: " + yourWins);           
                theirScore.setText("Their Score: " + theirWins);
                potLabel.setText("Cards in the Pot: " + thePot);
                
                isTurnable = false;
            }
        }
        
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mousePressed at "+e.getX()+", "+e.getY());
        if(e.getX() > yourCard.getOver() - 35 && e.getX() < yourCard.getOver() + 35
                && e.getY() > yourCard.getDown() - 45 && e.getY() < yourCard.getDown() + 45){
            //System.out.println("Pressing in card");
            isMovable = true;
            //System.out.println("isMovable " + isMovable);
        }
        //System.out.println(yourCard.getOver() + " " + yourCard.getOver());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased at "+e.getX()+", "+e.getY());
        isMovable = false;
        //System.out.println("isMovable " + isMovable);
        //System.out.println(yourCard.getOver() + " " + yourCard.getOver());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered at "+e.getX()+", "+e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited at "+e.getX()+", "+e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("mouseDragged at "+e.getX()+", "+e.getY());
        if(isMovable == true){
            //System.out.println("Dragging in card");
            yourCard.setOver(e.getX());
            yourCard.setDown(e.getY());

            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("mouseMoved at "+e.getX()+", "+e.getY());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton getCardButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton newGameButton;
    private javax.swing.JLabel potLabel;
    private javax.swing.JButton rulesButton;
    private javax.swing.JLabel theirCardCount;
    private javax.swing.JLabel theirScore;
    private javax.swing.JLabel yourCardCount;
    private javax.swing.JLabel yourScore;
    // End of variables declaration//GEN-END:variables
}
