/* Erin Cotton
 * March 27,2018
 * CSC 220
 * Description: Creates a modified version of the card game war. The code begins by shuffling a deck of cards,
 *  dealing the cards, and then the game begins. The code then displays the cards on a graphic and the user 
 *  clicks the higher card. The player with the higher card receives a point. At the end, the player with more points
 *  is displayed on the graphic and printed.
 * Enhancement: The live scoreboard and point system is an enhancement.
 */ 
package stuCotton;
// List of imports
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
import java.util.ArrayList;
import java.util.Random;
import java.applet.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import stuCotton.CottonCard;


public class CottonPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener {
// Instantiates variables
    private Font myFont, myFontTitle;
    private MultiCard multiCard;
    private Card currentCard;
    private Boolean playerDone = false;
    private ArrayList<Card> deckOfCards, player1, player2;
    private ArrayList<CottonCard> spareCard;
    private Integer player1Win, player2Win;
    private Random gen;
    private int player1Value = 0;
    private int player2Value = 0;
    private int clickcount = 0;
    
// Main body of code follows:
    public CottonPanel() {
        initComponents();

//   Declaring array lists and others
        myFontTitle = new Font("Serif", Font.BOLD, 30);
        myFont = new Font("Serif", Font.BOLD, 22);
        multiCard = new MultiCard();
        deckOfCards = new ArrayList<Card>();
        player1 = new ArrayList<Card>();
        player2 = new ArrayList<Card>();
        spareCard = new ArrayList<CottonCard>();
        addMouseListener(this);
        addMouseMotionListener(this);
        nextButton.setVisible(true);
        gen = new Random();

// This adds random cards to each players deck/array list
        for (int i = 0; i < 52; i++){
            int randomCardIndex = Utility.getRandomFromTo(0, 51);
            Card randomCard = multiCard.getCardAtIndex(randomCardIndex);
            if(i%2 == 1){
            player1.add(randomCard);
            }else{
            player2.add(randomCard);     
            }
        }
                  
        
        
    }
    
//        GRAPHIC CODE FOLLOWS:
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
// This sets all colors and fonts and such for the graphic
        g.setColor(new Color(255,200,200));
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(myFontTitle);
        g.setColor(Color.BLACK);

// This draws the base strings for the graphic
        g.drawString("War", 350, 40);
        g.setFont(myFont);
        g.drawString("Player 1 score:" + player1Value, 185, 400);
        g.drawString("Player 2 score:" + player2Value, 415, 400);
       
// This draws the two face down cards on the left and right to mimic the real game of War
            BufferedImage faceDown = getImage("src/cards/faceDown.gif");
            g.drawImage(faceDown, 100-faceDown.getWidth()/2, 200-faceDown.getHeight()/2, null);
            g.drawImage(faceDown, 675-faceDown.getWidth()/2, 200-faceDown.getHeight()/2, null);
  
// This draws player 1's deck of cards.
             for (int i = 0; i<player1.size(); i++){
                player1.get(i).setDown(200);
                player1.get(i).setOver(280);
                player1.get(i).drawCard(g, this);}
             
// This draws player 2's deck of cards             
            for (int i = 0; i<player2.size(); i++){
                player2.get(i).setDown(200);
                player2.get(i).setOver(470);
                player2.get(i).drawCard(g, this);}
            
// This determines the winner and prints it in the graphic itself
            if(25-clickcount == -1){
            if (player1Value < player2Value){
                g.drawString("PLAYER 2 WINS!", 290, 200);
            }
            else if (player2Value < player1Value){
                g.drawString("PLAYER 1 WINS!", 290, 200);
            }
            else{
                g.drawString("It's a tie! Press new game...", 250,200);}}
    }
//     This is used to fill the deckOfCards array list
    private void fillDeck(){
      System.out.println("FillDeck is running");
        for (int i = 0; i < 52; i++){
            deckOfCards.add(new CottonCard(multiCard.getCardAtIndex(i),false));
            deckOfCards.get(i).setOver(10);
            deckOfCards.get(i).setDown(10);
        }
    }
//    This can be used to shuffle the "deckOfCards" Array list
    private void shuffle(){
        Card temp;
        Integer x;
        for (int i = 0; i < 100; i++){
            x = gen.nextInt(52);
            temp = multiCard.getCardAtIndex(x);
            deckOfCards.remove(deckOfCards.get(x));
            deckOfCards.add(temp);
        }

    }
    
//   This deals cards to both players
     private void deal(){
        player1.add(deckOfCards.get(0));
        player2.add(deckOfCards.get(0));
        deckOfCards.remove(0);
        deckOfCards.remove(0);
    }

// This is used to compare the final scores and determine a winner.
     private void compareTotal(){
            if (player1Value < player2Value){
                System.out.println("PLAYER 2 WINS!");
            }
            else if (player2Value < player1Value){
                System.out.println("PLAYER 1 WINS!");
            }
            else{
              System.out.println("It's a tie..try again");
              deal();
            }
        }  
//   The following code is for buttons and this is not to be touched.
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rulesButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        rulesButton.setText("Show Rules");
        rulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next Game");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(rulesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(nextButton)
                .addGap(91, 91, 91))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rulesButton)
                    .addComponent(nextButton))
                .addGap(34, 34, 34))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesButtonActionPerformed
        JOptionPane.showMessageDialog(this, "This is a modified form of the card game War.\n"+
                "This version uses ace as the low card and voids ties.\n"+
                "Visit the following link for a list of the original rules:\nhttps://www.bicyclecards.com/how-to-play/war/");
    }//GEN-LAST:event_rulesButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        repaint();
        clickcount=0;
        player1Value = 0;
        player2Value = 0;
        for (int i = 0; i<player1.size(); i++){
            player1.remove(i);
            player2.remove(i);
        }

        for (int i = 0; i < 52; i++){
            int randomCardIndex = Utility.getRandomFromTo(0, 51);
            Card temp = multiCard.getCardAtIndex(randomCardIndex);
            if(i%2 == 1){
            player1.add(temp);
            }else{
            player2.add(temp);     
            }
    }     
    }//GEN-LAST:event_nextButtonActionPerformed
 
//    This is used to print who scores and add a point to the correct player.
    @Override
    public void mouseClicked(MouseEvent me) {
                if(25-clickcount == -1){
                    System.out.println("There are no more cards. Try clicking \"New game\"");
                    compareTotal();
                }
                else if (me.getY()>= 152 && me.getY()<=249 && me.getX()>=435 && me.getX()<= 505){ 
                    if(player2.get(25-clickcount).getValue()==player1.get(25-clickcount).getValue()){
                        System.out.println("It's a tie! New cards.");
                        player1.remove(25-clickcount);
                        player2.remove(25-clickcount);
                        clickcount ++;}
                    else if(player2.get(25-clickcount).getValue()>player1.get(25-clickcount).getValue()){
                    player2Value++;
                    System.out.println("Player 2 scored");
                    player1.remove(25-clickcount);
                    player2.remove(25-clickcount);
                    clickcount ++;}
                    else
                    {System.out.println("Player 2 is not the higher card. Try again.");}}
                else if (me.getY()>= 152 && me.getY()<=249 && me.getX()>=245 && me.getX()<= 316){
                    if(player2.get(25-clickcount).getValue()==player1.get(25-clickcount).getValue()){
                        System.out.println("It's a tie! New cards.");
                        player1.remove(25-clickcount);
                        player2.remove(25-clickcount);
                        clickcount ++;}
                    else if(player1.get(25-clickcount).getValue()>player2.get(25-clickcount).getValue()){
                    player1Value++;
                    System.out.println("Player 1 scored");
                    player2.remove(25-clickcount);
                    player1.remove(25-clickcount);
                    clickcount ++;}
                    else
                    {System.out.println("Player 1 is not the higher card. Try again.");}}
                      
               repaint();
                }
            


// This can be used to show coordinates of multiple user interactions.
    @Override
    public void mousePressed(MouseEvent me) {
//         System.out.println("mousePressed " + me.getX()+ " "+me.getY());
       
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//         System.out.println("mouseReleased " + me.getX()+ " "+me.getY());
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//         System.out.println("mouseEntered " + me.getX()+ " "+me.getY());
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
//         System.out.println("mouseExited " + me.getX()+ " "+me.getY());
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
//         System.out.println("mouseDragged " + me.getX()+ " "+me.getY());
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
//        System.out.println("mouseMoved " + me.getX()+ " "+me.getY());
       
    }

    private BufferedImage getImage(String fileString)
    {
        BufferedImage image = null;
        
        try { 
            image = ImageIO.read(new File(fileString));
        } 
        catch (IOException ex) {
            System.out.println("Error when trying to load image");
        }
        return image;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton nextButton;
    private javax.swing.JButton rulesButton;
    // End of variables declaration//GEN-END:variables
}
