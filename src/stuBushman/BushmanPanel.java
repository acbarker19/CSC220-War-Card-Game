/*
 * Nick Bushman  February- March 2018
 * Made for CSC 220
 *
 *  This is a game that mimics BlackJack but is to a score of 35 and you do 
 *  not "hit" but take cards away. 
 * 
 * An enhancement I added is the use of the JOptionPane for the directions and 
 * win/loss messages
 */
package stuBushman;

import helperCards.MultiCard;
import helperCards.Card;
import helpers.Utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import helperCards.Card;
import helperCards.MultiCard;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author klaydejr
 */
public class BushmanPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener {
    
   
    private Font myFont, myFont1;
    private MultiCard deck, deck2;
    private ArrayList<BushmanCat> player1, player2;
    private final int MAX_NUMBER_OF_CARDS_IN_THE_GAME = 20; 
    private Boolean nowDragging, checkGame;
    private int total, total2;
    /**
     * Creates new form MyPanel_1
     */
    public BushmanPanel() {
        initComponents();
        
        myFont = new Font("Monospaced", Font.PLAIN, 14);
        myFont1 = new Font("TimesRoman", Font.ITALIC, 24);
        addMouseListener(this);
        addMouseMotionListener(this); 
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        
        deck = new MultiCard();
        deck2 = new MultiCard();
        score.setEditable(false);
        score2.setEditable(false);
        int random = Utility.getRandomFromTo(0, 51); 
       
    }
       
    
    
    
    @Override
    public void paintComponent(Graphics g) 
    {
     
        
        g.setColor(new Color(50,200,0));
        g.fillRect(0, 0, 1000, 1000);
        
        g.setColor(Color.BLACK);
        g.setFont(myFont);
        g.drawString("MADE BY: NICK BUSHMAN FOR CSC 220", 25, 400);
        
        g.setColor(Color.BLACK);
        g.setFont(myFont1);
        g.drawString("PLAY THE CARDS YOU ARE DEALT", 25, 350);
        
         for (int i = 0; i < player1.size(); i++) 
            {
              
                player1.get(i).drawCard(g, this);
            }

         for (int i = 0; i < player2.size(); i++) 
            {
              
                player2.get(i).drawCard(g, this);
            }
           
            


    }
    public void newGame()
    {
        //code for new game adapted from Dr. Klayders MyPanel_3
        player1 = new ArrayList < BushmanCat > ();
        player2 = new ArrayList < BushmanCat > ();
        for (int i = 0; i < 10; i++) 
        {   
            int random = Utility.getRandomFromTo(0, 51);
            Card randomCard = deck.getCardAtIndex(random);
            BushmanCat bushmanCat = new BushmanCat(randomCard);           
            bushmanCat.setOver(60+ i *75 );
            bushmanCat.setDown(65);
            player1.add(bushmanCat);   
        }
                for (int i = 0; i < player1.size(); i++) 
        {
            BushmanCat temp = player1.get(i);
            
        }
                
                
                
        for (int i = 0; i < 10; i++) 
        {
            int random = Utility.getRandomFromTo(0, 51);
            Card randomCard = deck.getCardAtIndex(random);
            BushmanCat bushmanCat2 = new BushmanCat(randomCard);
            bushmanCat2.setOver(60+ i *75 );
            bushmanCat2.setDown(180);
            player2.add(bushmanCat2);
        }
        for (int i = 0; i < player2.size(); i++) 
        {
            BushmanCat temp = player2.get(i);
            
        }
        
    }
    private void checkGame(){

        for (int i = 0; i < player1.size(); i++) 
        {
           
           total =  total + player1.get(i).getTotal();
        }
        for (int i = 0; i < player2.size(); i++) 
        {
           
           total2 =  total2 + player2.get(i).getTotal();
        }
            if (total<36 && total2>35)
            {
                
                JOptionPane.showMessageDialog(null, 
                "PLAYER 1 WINS!!!");         
            }
        
           if(total2< 36 && total >35)
           {
               
                JOptionPane.showMessageDialog(null, 
                "PLAYER 2 WINS!!!");
            }
           
           if(total >35&& total2>35)
           {
               JOptionPane.showMessageDialog(null, 
                "NO WINNER! TRY AGAIN!!");
           }
           
           if (total<36 && total2 <36)
           {    if(total>total2)
                {JOptionPane.showMessageDialog(null,
                   "PLAYER 1 WiNS!!"
                   );
                }
                 if(total<total2)
                {JOptionPane.showMessageDialog(null,
                   "PLAYER 2 WiNS!!"
                   );
                }
                else{JOptionPane.showMessageDialog(null, "TIE!!");}
           }
        
            score.setText(Integer.toString(total));
            score2.setText(Integer.toString(total2));
            total =0;
            total2 = 0;
        }
        
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newGame = new javax.swing.JButton();
        directions = new javax.swing.JButton();
        checkButton = new javax.swing.JButton();
        score = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        score2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        newGame.setText("New Game");
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });

        directions.setText("Directions");
        directions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directionsActionPerformed(evt);
            }
        });

        checkButton.setText("Check");
        checkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtonActionPerformed(evt);
            }
        });

        score.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreActionPerformed(evt);
            }
        });

        jLabel1.setText("Player 1 Score");

        jLabel2.setText("Player 2 Score");

        jLabel3.setText("PLAYER1");

        jLabel4.setText("PLAYER 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 241, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(score, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(score2))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(directions)
                                    .addGap(14, 14, 14))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(newGame)
                                    .addContainerGap()))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(99, 99, 99)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(newGame)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(checkButton))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(directions))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(score2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void directionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directionsActionPerformed
      //https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html      
        JOptionPane.showMessageDialog(null, 
                "Directions: \n The goal of the game is to get a score of 35. \n You "
                        + "cannot go over 35 but you can go under.\n Player 1 has cards in the top row. \n "
                        + "Player 2 has cards in the bottom row. \n \n \n "
                        + "1- Click New Game \n "
                        + "2- Click the cards that you want removed to lower your score. \n "
                        + "3- When ready, click Check to see who won. \n "
                        + "4- Celebrate your win! \n "
                        + "5- Click New Game to reset the cards.");      
    }//GEN-LAST:event_directionsActionPerformed

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
    newGame();
    repaint();
    }//GEN-LAST:event_newGameActionPerformed

    private void scoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreActionPerformed

    }//GEN-LAST:event_scoreActionPerformed

    private void checkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtonActionPerformed
       checkGame();    
       repaint();
    }//GEN-LAST:event_checkButtonActionPerformed
   
    @Override
    public void mouseClicked(MouseEvent me) {
            for (int i = 0; i < player1.size(); i++) 
            {
                BushmanCat bushmanCat = player1.get(i);
                if (bushmanCat.containsMousePoint(me.getX(), me.getY()))
                {
                     player1.remove(i);
                     repaint();
                }
            }
                    int total = 0;
                    
        for (int i = 0; i < player1.size(); i++) 
        { 
        total = total+ player1.get(i).getValue();
        }
        
        score.setText(Integer.toString(total));
            
        
      //=================================================//
        for (int i = 0; i < player2.size(); i++) 
            {
                BushmanCat bushmanCat2 = player2.get(i);
                if (bushmanCat2.containsMousePoint(me.getX(), me.getY()))
                {
                     player2.remove(i);
                     repaint();
                }
            }
                    int total2 = 0;
        for (int i = 0; i < player2.size(); i++) 
        { 
        total2 = total2+ player2.get(i).getValue();
        }
        
        score2.setText(Integer.toString(total2));
        
       repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
      
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        System.out.println("mouseMoved " + me.getX()+ " "+me.getY());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkButton;
    private javax.swing.JButton directions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton newGame;
    private javax.swing.JTextField score;
    private javax.swing.JTextField score2;
    // End of variables declaration//GEN-END:variables
}
