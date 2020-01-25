/*  Zachriah Brown   2/23/2018   CSC 220
    This is the classic game Klondike Solitaire.
    Rules for the game can be found here: 
    https://www.thespruce.com/klondike-solitaire-card-game-rules-412473
    The foundation is set1, set2, set3, and set4
    The tableau is pile1, pile2, pile3, pile4, pile5, pile6, pile7
    The enhancment is dragging multiple cards
 */
package stuBrown;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import helperCards.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author klaydejr
 */
public class BrownPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener {

    private Font myFont;
    private Random gen;
    private ArrayList<BrownCard> deck, holdPile, pile1, pile2, pile3, pile4, pile5, pile6, pile7, set1, set2, set3, set4;
    private MultiCard tempDeck;
    
    public BrownPanel() {
        initComponents();
        
        myFont = new Font("Serif", Font.BOLD, 50);
        addMouseListener(this);
        addMouseMotionListener(this);
        gen = new Random();
        deck = new ArrayList<BrownCard>();
        holdPile = new ArrayList<BrownCard>();
        pile1 = new ArrayList<BrownCard>();
        pile2 = new ArrayList<BrownCard>();
        pile3 = new ArrayList<BrownCard>();
        pile4 = new ArrayList<BrownCard>();
        pile5 = new ArrayList<BrownCard>();
        pile6 = new ArrayList<BrownCard>();
        pile7 = new ArrayList<BrownCard>();
        set1 = new ArrayList<BrownCard>();
        set2 = new ArrayList<BrownCard>();
        set3 = new ArrayList<BrownCard>();
        set4 = new ArrayList<BrownCard>();
        tempDeck = new MultiCard();
        fillDeck();
        shuffle();
        deal();
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setColor(new Color(40,200,60));
        g.fillRect(0, 0, 10000, 10000);
        g.setFont(myFont);
        //draw black rectangles
        g.setColor(Color.BLACK);
        g.drawRect(10,10,71,96);
        g.drawRect(280, 5, 71, 96);
        g.drawRect(360, 5, 71, 96);
        g.drawRect(440, 5, 71, 96);
        g.drawRect(520, 5, 71, 96);
        g.drawRect(40, 120, 71, 96);
        g.drawRect(120, 120, 71, 96);
        g.drawRect(200, 120, 71, 96);
        g.drawRect(280, 120, 71, 96);
        g.drawRect(360, 120, 71, 96);
        g.drawRect(440, 120, 71, 96);
        g.drawRect(520, 120, 71, 96);
        
        //draw cards
        for (int i = 0; i < pile1.size(); i++){pile1.get(i).drawCard(g, this);}
        for (int i = 0; i < pile2.size(); i++){pile2.get(i).drawCard(g, this);}
        for (int i = 0; i < pile3.size(); i++){pile3.get(i).drawCard(g, this);}
        for (int i = 0; i < pile4.size(); i++){pile4.get(i).drawCard(g, this);}
        for (int i = 0; i < pile5.size(); i++){pile5.get(i).drawCard(g, this);}
        for (int i = 0; i < pile6.size(); i++){pile6.get(i).drawCard(g, this);}
        for (int i = 0; i < pile7.size(); i++){pile7.get(i).drawCard(g, this);}
        for (int i = 0; i < set1.size(); i++){set1.get(i).drawCard(g, this);}
        for (int i = 0; i < set2.size(); i++){set2.get(i).drawCard(g, this);}
        for (int i = 0; i < set3.size(); i++){set3.get(i).drawCard(g, this);}
        for (int i = 0; i < set4.size(); i++){set4.get(i).drawCard(g, this);}
        for (int i = 0; i < deck.size(); i++){deck.get(i).drawCard(g, this);}
        for (int i = 0; i < holdPile.size(); i++){holdPile.get(i).drawCard(g, this);}
        
        //if the cards are being dragged then draw them on top
        for (int i = 0; i < deck.size(); i++){
            if (deck.get(i).getDragging()){deck.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile1.size(); i++){
            if (pile1.get(i).getDragging()){pile1.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile2.size(); i++){
            if (pile2.get(i).getDragging()){pile2.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile3.size(); i++){
            if (pile3.get(i).getDragging()){pile3.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile4.size(); i++){
            if (pile4.get(i).getDragging()){pile4.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile5.size(); i++){
            if (pile5.get(i).getDragging()){pile5.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile6.size(); i++){
            if (pile6.get(i).getDragging()){pile6.get(i).drawCard(g, this);}
        }
        for (int i = 0; i < pile7.size(); i++){
            if (pile7.get(i).getDragging()){pile7.get(i).drawCard(g, this);}
        }
        
        //if the user wins then display a message
        if (set1.size()==13 && set2.size()==13 && set3.size()==13 && set4.size()==13){
            g.setColor(new Color(40, 200, 60));
            g.fillRect(0, 0, 10000, 10000);
            g.setColor(Color.BLACK);
            g.drawString("You Win!!!",100,100);
            System.out.println("The user won the game");
        }
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newGameButton = new javax.swing.JButton();
        rulesButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(500, 300));
        setPreferredSize(new java.awt.Dimension(500, 300));

        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        rulesButton.setText("Rules");
        rulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(407, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newGameButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rulesButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newGameButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rulesButton)
                .addContainerGap(237, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        System.out.println("Starting new game");
        collectCards();
        shuffle();
        deal();
        repaint();
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesButtonActionPerformed
        System.out.println("Showing Rules");
        JOptionPane.showMessageDialog(this, "This is the classic game Klondike Solitaire.\n"+
                "This version does one card draw with no limit on how many times you can go throught the draw pile.\n"+
                "for full list of rules follow this link:\nhttps://www.thespruce.com/klondike-solitaire-card-game-rules-412473");
    }//GEN-LAST:event_rulesButtonActionPerformed

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX()>=10 && e.getX()<=81 && e.getY()>=10 && e.getY()<= 106){
            if (!holdPile.isEmpty()){
                holdPile.get(0).setOver(90);
                holdPile.get(0).setFaceUp(true);
                deck.add(holdPile.get(0));
                holdPile.remove(0);
            }
            else {
                while (!deck.isEmpty()){
                    deck.get(0).setOver(10);
                    deck.get(0).setFaceUp(false);
                    holdPile.add(deck.get(0));
                    deck.remove(0);
                }
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // press pile1
        for (int i = pile1.size()-1; i >= 0; i--){
            if (pile1.get(i).containsPoint(e.getX(), e.getY()) && pile1.get(i).getFaceUp()){
                for (int n = i; n < pile1.size(); n++){
                    pile1.get(n).setDragging(true);
                }
                break;
            }
        }
        // press pile2
        for (int i = pile2.size()-1; i >= 0; i--){
            if (pile2.get(i).containsPoint(e.getX(), e.getY()) && pile2.get(i).getFaceUp()){
                for (int n = i; n < pile2.size(); n++){
                    pile2.get(n).setDragging(true);
                }
                break;
            }
        }
        // press pile3
        for (int i = pile3.size()-1; i >= 0; i--){
            if (pile3.get(i).containsPoint(e.getX(), e.getY()) && pile3.get(i).getFaceUp()){
                for (int n = i; n < pile3.size(); n++){
                    pile3.get(n).setDragging(true);
                }
                break;
            }
        }
        // press pile4
        for (int i = pile4.size()-1; i >= 0; i--){
            if (pile4.get(i).containsPoint(e.getX(), e.getY()) && pile4.get(i).getFaceUp()){
                for (int n = i; n < pile4.size(); n++){
                    pile4.get(n).setDragging(true);
                }
                break;
            }
        }
        // press pile5
        for (int i = pile5.size()-1; i >= 0; i--){
            if (pile5.get(i).containsPoint(e.getX(), e.getY()) && pile5.get(i).getFaceUp()){
                for (int n = i; n < pile5.size(); n++){
                    pile5.get(n).setDragging(true);
                }
                break;
            }
        }
        // press pile6
        for (int i = pile6.size()-1; i >= 0; i--){
            if (pile6.get(i).containsPoint(e.getX(), e.getY()) && pile6.get(i).getFaceUp()){
                for (int n = i; n < pile6.size(); n++){
                    pile6.get(n).setDragging(true);
                }
                break;
            }
        }
        // press pile7
        for (int i = pile7.size()-1; i >= 0; i--){
            if (pile7.get(i).containsPoint(e.getX(), e.getY()) && pile7.get(i).getFaceUp()){
                for (int n = i; n < pile7.size(); n++){
                    pile7.get(n).setDragging(true);
                }
                break;
            }
        }
        // press deck
        for (int i = deck.size()-1; i >= 0; i--){
            if (deck.get(i).containsPoint(e.getX(), e.getY()) && deck.get(i).getFaceUp()){
                deck.get(i).setDragging(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasePile1(e);
        releasePile2(e);
        releasePile3(e);
        releasePile4(e);
        releasePile5(e);
        releasePile6(e);
        releasePile7(e);
        releaseDeck(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //drags cards from pile1
        for (int i = 0; i <pile1.size(); i++){
            if (pile1.get(i).getDragging()){
                for (int n = i; n < pile1.size(); n++){
                    pile1.get(n).setOver(e.getX()-71/2);
                    pile1.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from pile2
        for (int i = 0; i <pile2.size(); i++){
            if (pile2.get(i).getDragging()){
                for (int n = i; n < pile2.size(); n++){
                    pile2.get(n).setOver(e.getX()-71/2);
                    pile2.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from pile3
        for (int i = 0; i <pile3.size(); i++){
            if (pile3.get(i).getDragging()){
                for (int n = i; n < pile3.size(); n++){
                    pile3.get(n).setOver(e.getX()-71/2);
                    pile3.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from pile4
        for (int i = 0; i <pile4.size(); i++){
            if (pile4.get(i).getDragging()){
                for (int n = i; n < pile4.size(); n++){
                    pile4.get(n).setOver(e.getX()-71/2);
                    pile4.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from pile5
        for (int i = 0; i <pile5.size(); i++){
            if (pile5.get(i).getDragging()){
                for (int n = i; n < pile5.size(); n++){
                    pile5.get(n).setOver(e.getX()-71/2);
                    pile5.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from pile6
        for (int i = 0; i <pile6.size(); i++){
            if (pile6.get(i).getDragging()){
                for (int n = i; n < pile6.size(); n++){
                    pile6.get(n).setOver(e.getX()-71/2);
                    pile6.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from pile7
        for (int i = 0; i <pile7.size(); i++){
            if (pile7.get(i).getDragging()){
                for (int n = i; n < pile7.size(); n++){
                    pile7.get(n).setOver(e.getX()-71/2);
                    pile7.get(n).setDown(e.getY()-96/2+(n-i)*20);
                }
                break;
            }
        }
        //drags cards from deck
        for (int i = 0; i <deck.size(); i++){
            if (deck.get(i).getDragging()){
                deck.get(i).setOver(e.getX()-71/2);
                deck.get(i).setDown(e.getY()-96/2);
                break;
            }
        }
        
        
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
    private void collectCards(){
        System.out.println("Collecting cards in BrownPanel");
        //reseting cards in deck
        for (int i = 0; i <deck.size(); i++){
            deck.get(i).setFaceUp(false);
            deck.get(i).setOver(10);
        }
        //collect cards to deck from pile1
        while (!pile1.isEmpty()){
            pile1.get(0).setFaceUp(false);
            pile1.get(0).setOver(10);
            pile1.get(0).setDown(10);
            deck.add(pile1.get(0));
            pile1.remove(0);
        }
        //collect cards to deck from pile2
        while (!pile2.isEmpty()){
            pile2.get(0).setFaceUp(false);
            pile2.get(0).setOver(10);
            pile2.get(0).setDown(10);
            deck.add(pile2.get(0));
            pile2.remove(0);
        }
        //collect cards to deck from pile3
        while (!pile3.isEmpty()){
            pile3.get(0).setFaceUp(false);
            pile3.get(0).setOver(10);
            pile3.get(0).setDown(10);
            deck.add(pile3.get(0));
            pile3.remove(0);
        }
        //collect cards to deck from pile4
        while (!pile4.isEmpty()){
            pile4.get(0).setFaceUp(false);
            pile4.get(0).setOver(10);
            pile4.get(0).setDown(10);
            deck.add(pile4.get(0));
            pile4.remove(0);
        }
        //collect cards to deck from pile5
        while (!pile5.isEmpty()){
            pile5.get(0).setFaceUp(false);
            pile5.get(0).setOver(10);
            pile5.get(0).setDown(10);
            deck.add(pile5.get(0));
            pile5.remove(0);
        }
        //collect cards to deck from pile6
        while (!pile6.isEmpty()){
            pile6.get(0).setFaceUp(false);
            pile6.get(0).setOver(10);
            pile6.get(0).setDown(10);
            deck.add(pile6.get(0));
            pile6.remove(0);
        }
        //collect cards to deck from pile7
        while (!pile7.isEmpty()){
            pile7.get(0).setFaceUp(false);
            pile7.get(0).setOver(10);
            pile7.get(0).setDown(10);
            deck.add(pile7.get(0));
            pile7.remove(0);
        }
        //collect cards to deck from set1
        while (!set1.isEmpty()){
            set1.get(0).setFaceUp(false);
            set1.get(0).setOver(10);
            set1.get(0).setDown(10);
            deck.add(set1.get(0));
            set1.remove(0);
        }
        //collect cards to deck from set2
        while (!set2.isEmpty()){
            set2.get(0).setFaceUp(false);
            set2.get(0).setOver(10);
            set2.get(0).setDown(10);
            deck.add(set2.get(0));
            set2.remove(0);
        }
        //collect cards to deck from set3
        while (!set3.isEmpty()){
            set3.get(0).setFaceUp(false);
            set3.get(0).setOver(10);
            set3.get(0).setDown(10);
            deck.add(set3.get(0));
            set3.remove(0);
        }
        //collect cards to deck from set4
        while (!set4.isEmpty()){
            set4.get(0).setFaceUp(false);
            set4.get(0).setOver(10);
            set4.get(0).setDown(10);
            deck.add(set4.get(0));
            set4.remove(0);
        }
        //collect cards to deck from holdPile
        while (!holdPile.isEmpty()){
            holdPile.get(0).setFaceUp(false);
            holdPile.get(0).setOver(10);
            holdPile.get(0).setDown(10);
            deck.add(holdPile.get(0));
            holdPile.remove(0);
        }
    }
    
    private void shuffle(){
        System.out.println("Shuffling cards in BrownPanel");
        BrownCard temp;
        Integer x;
        for (int i = 0; i < 100; i++){
            x = gen.nextInt(52);
            temp = deck.get(x);
            deck.remove(deck.get(x));
            deck.add(temp);
        }
    }
    
    private void deal(){
        System.out.println("Dealing cards in BrownPanel");
        for (int i = 0; i <7; i++){
            //dealing pile1
            if (i<1){
                pile1.add(deck.get(0));
                deck.remove(0);
                pile1.get(i).setDown(120);
                pile1.get(i).setOver(40);
                pile1.get(i).setFaceUp(true);
            }
            //dealing pile2
            if (i<2){
                pile2.add(deck.get(0));
                deck.remove(0);
                pile2.get(i).setDown(120+i*10);
                pile2.get(i).setOver(120);
                if (i==1){pile2.get(i).setFaceUp(true);}
            }
            //dealing pile3
            if (i<3){
                pile3.add(deck.get(0));
                deck.remove(0);
                pile3.get(i).setDown(120+i*10);
                pile3.get(i).setOver(200);
                if (i==2){pile3.get(i).setFaceUp(true);}
            }
            //dealing pile4
            if (i<4){
                pile4.add(deck.get(0));
                deck.remove(0);
                pile4.get(i).setDown(120+i*10);
                pile4.get(i).setOver(280);
                if (i==3){pile4.get(i).setFaceUp(true);}
            }
            //dealing pile5
            if (i<5){
                pile5.add(deck.get(0));
                deck.remove(0);
                pile5.get(i).setDown(120+i*10);
                pile5.get(i).setOver(360);
                if (i==4){pile5.get(i).setFaceUp(true);}
            }
            //dealing pile6
            if (i<6){
                pile6.add(deck.get(0));
                deck.remove(0);
                pile6.get(i).setDown(120+i*10);
                pile6.get(i).setOver(440);
                if (i==5){pile6.get(i).setFaceUp(true);}
            }
            //dealing pile7
            if (i<7){
                pile7.add(deck.get(0));
                deck.remove(0);
                pile7.get(i).setDown(120+i*10);
                pile7.get(i).setOver(520);
                if (i==6){pile7.get(i).setFaceUp(true);}
            }
        }
        //dealing holdPile
        while(!deck.isEmpty()){
            deck.get(0).setFaceUp(false);
            holdPile.add(deck.get(0));
            deck.remove(0);
        }
    }
    
    private void fillDeck(){
        System.out.println("filling deck in BrownPanel");
        for (int i = 0; i < 52; i++){
            deck.add(new BrownCard(tempDeck.getCardAtIndex(i),false));
            deck.get(i).setOver(10);
            deck.get(i).setDown(10);
        }
    }
    
    private void releasePile1(MouseEvent e){
        // move pile 1
        for (int i = 0; i <pile1.size(); i++){
            if (pile1.get(i).getDragging()){
                // released on pile2
                if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(pile1, pile2, i)){
                        while (i < pile1.size()){
                            pile1.get(i).setOver(pile2.get(0).getOver());
                            pile1.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                            pile1.get(i).setDragging(false);
                            pile2.add(pile1.get(i));
                            pile1.remove(i);
                        }
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(pile1, pile3, i)){
                        while (i < pile1.size()){
                            pile1.get(i).setOver(pile3.get(0).getOver());
                            pile1.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                            pile1.get(i).setDragging(false);
                            pile3.add(pile1.get(i));
                            pile1.remove(i);
                        }
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(pile1, pile4, i)){
                        while (i < pile1.size()){
                            pile1.get(i).setOver(pile4.get(0).getOver());
                            pile1.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                            pile1.get(i).setDragging(false);
                            pile4.add(pile1.get(i));
                            pile1.remove(i);
                        }
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(pile1, pile5, i)){
                        while (i < pile1.size()){
                            pile1.get(i).setOver(pile5.get(0).getOver());
                            pile1.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                            pile1.get(i).setDragging(false);
                            pile5.add(pile1.get(i));
                            pile1.remove(i);
                        }
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(pile1, pile6, i)){
                        while (i < pile1.size()){
                            pile1.get(i).setOver(pile6.get(0).getOver());
                            pile1.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                            pile1.get(i).setDragging(false);
                            pile6.add(pile1.get(i));
                            pile1.remove(i);
                        }
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(pile1, pile7, i)){
                        while (i < pile1.size()){
                            pile1.get(i).setOver(pile7.get(0).getOver());
                            pile1.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                            pile1.get(i).setDragging(false);
                            pile7.add(pile1.get(i));
                            pile1.remove(i);
                        }
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile1.get(i).getValue()==1){
                        pile1.get(i).setOver(280);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set1.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile1.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile1.get(i).getSuit())){
                        pile1.get(i).setOver(280);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set1.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile1.get(i).getValue()==1){
                        pile1.get(i).setOver(360);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set2.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile1.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile1.get(i).getSuit())){
                        pile1.get(i).setOver(360);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set2.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile1.get(i).getValue()==1){
                        pile1.get(i).setOver(440);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set3.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile1.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile1.get(i).getSuit())){
                        pile1.get(i).setOver(440);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set3.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile1.get(i).getValue()==1){
                        pile1.get(i).setOver(520);
                            pile1.get(i).setDown(5);
                            pile1.get(i).setDragging(false);
                            set4.add(pile1.get(i));
                            pile1.remove(i);
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile1.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile1.get(i).getSuit())){
                        pile1.get(i).setOver(520);
                        pile1.get(i).setDown(5);
                        pile1.get(i).setDragging(false);
                        set4.add(pile1.get(i));
                        pile1.remove(i);
                    }
                    else {
                        for (int n = i; n < pile1.size(); n++){
                            pile1.get(n).setOver(40);
                            if (n == 0){
                                pile1.get(n).setDown(120);
                            }
                            else {
                                pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                            }
                            pile1.get(n).setDragging(false);
                        }
                    }
                }
                // released on empty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && pile1.get(i).getValue()==13){
                    while (i < pile1.size()){
                        pile1.get(i).setOver(120);
                        if (pile1.get(i).getValue() == 13){
                            pile1.get(i).setDown(120);
                        }
                        else {
                            pile1.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        }
                        pile1.get(i).setDragging(false);
                        pile2.add(pile1.get(i));
                        pile1.remove(i);
                    }
                }
                // released on empty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && pile1.get(i).getValue()==13){
                    while (i < pile1.size()){
                        pile1.get(i).setOver(200);
                        if (pile1.get(i).getValue() == 13){
                            pile1.get(i).setDown(120);
                        }
                        else {
                            pile1.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        }
                        pile1.get(i).setDragging(false);
                        pile3.add(pile1.get(i));
                        pile1.remove(i);
                    }
                }
                // released on empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && pile1.get(i).getValue()==13){
                    while (i < pile1.size()){
                        pile1.get(i).setOver(280);
                        if (pile1.get(i).getValue() == 13){
                            pile1.get(i).setDown(120);
                        }
                        else {
                            pile1.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                        }
                        pile1.get(i).setDragging(false);
                        pile4.add(pile1.get(i));
                        pile1.remove(i);
                    }
                }
                // released on empty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && pile1.get(i).getValue()==13){
                    while (i < pile1.size()){
                        pile1.get(i).setOver(360);
                        if (pile1.get(i).getValue() == 13){
                            pile1.get(i).setDown(120);
                        }
                        else {
                            pile1.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                        }
                        pile1.get(i).setDragging(false);
                        pile5.add(pile1.get(i));
                        pile1.remove(i);
                    }
                }
                // released on empty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && pile1.get(i).getValue()==13){
                    while (i < pile1.size()){
                        pile1.get(i).setOver(440);
                        if (pile1.get(i).getValue() == 13){
                            pile1.get(i).setDown(120);
                        }
                        else {
                            pile1.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile1.get(i).setDragging(false);
                        pile6.add(pile1.get(i));
                        pile1.remove(i);
                    }
                }
                // released on empty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && pile1.get(i).getValue()==13){
                    while (i < pile1.size()){
                        pile1.get(i).setOver(520);
                        if (pile1.get(i).getValue() == 13){
                            pile1.get(i).setDown(120);
                        }
                        else {
                            pile1.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile1.get(i).setDragging(false);
                        pile7.add(pile1.get(i));
                        pile1.remove(i);
                    }
                }
                // released anywhere that is not a pile or set
                else {
                    for (int n = i; n < pile1.size(); n++){
                        pile1.get(n).setOver(40);
                        if (n == 0){
                            pile1.get(n).setDown(120);
                        }
                        else {
                            pile1.get(n).setDown(pile1.get(n-1).getDown()+20);
                        }
                        pile1.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releasePile2(MouseEvent e){
        // move pile 2
        for (int i = 0; i <pile2.size(); i++){
            if (pile2.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(pile2, pile1, i)){
                        while (i < pile2.size()){
                            pile2.get(i).setOver(pile1.get(0).getOver());
                            pile2.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                            pile2.get(i).setDragging(false);
                            pile1.add(pile2.get(i));
                            pile2.remove(i);
                        }
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(pile2, pile3, i)){
                        while (i < pile2.size()){
                            pile2.get(i).setOver(pile3.get(0).getOver());
                            pile2.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                            pile2.get(i).setDragging(false);
                            pile3.add(pile2.get(i));
                            pile2.remove(i);
                        }
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(pile2, pile4, i)){
                        while (i < pile2.size()){
                            pile2.get(i).setOver(pile4.get(0).getOver());
                            pile2.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                            pile2.get(i).setDragging(false);
                            pile4.add(pile2.get(i));
                            pile2.remove(i);
                        }
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(pile2, pile5, i)){
                        while (i < pile2.size()){
                            pile2.get(i).setOver(pile5.get(0).getOver());
                            pile2.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                            pile2.get(i).setDragging(false);
                            pile5.add(pile2.get(i));
                            pile2.remove(i);
                        }
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(pile2, pile6, i)){
                        while (i < pile2.size()){
                            pile2.get(i).setOver(pile6.get(0).getOver());
                            pile2.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                            pile2.get(i).setDragging(false);
                            pile6.add(pile2.get(i));
                            pile2.remove(i);
                        }
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(pile2, pile7, i)){
                        while (i < pile2.size()){
                            pile2.get(i).setOver(pile7.get(0).getOver());
                            pile2.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                            pile2.get(i).setDragging(false);
                            pile7.add(pile2.get(i));
                            pile2.remove(i);
                        }
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile2.get(i).getValue()==1){
                        pile2.get(i).setOver(280);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set1.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile2.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile2.get(i).getSuit())){
                        pile2.get(i).setOver(280);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set1.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile2.get(i).getValue()==1){
                        pile2.get(i).setOver(360);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set2.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile2.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile2.get(i).getSuit())){
                        pile2.get(i).setOver(360);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set2.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile2.get(i).getValue()==1){
                        pile2.get(i).setOver(440);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set3.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile2.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile2.get(i).getSuit())){
                        pile2.get(i).setOver(440);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set3.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile2.get(i).getValue()==1){
                        pile2.get(i).setOver(520);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set4.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile2.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile2.get(i).getSuit())){
                        pile2.get(i).setOver(520);
                        pile2.get(i).setDown(5);
                        pile2.get(i).setDragging(false);
                        set4.add(pile2.get(i));
                        pile2.remove(i);
                        if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile2.size(); n++){
                            pile2.get(n).setOver(120);
                            if (n == 0){
                                pile2.get(n).setDown(120);
                            }
                            else if (!pile2.get(n-1).getFaceUp()){
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                            }
                            else {
                                pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                            }
                            pile2.get(n).setDragging(false);
                        }
                    }
                }
                // released on empty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && pile2.get(i).getValue()==13){
                    while (i < pile2.size()){
                        pile2.get(i).setOver(40);
                        if (pile2.get(i).getValue() == 13){
                            pile2.get(i).setDown(120);
                        }
                        else {
                            pile2.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        }
                        pile2.get(i).setDragging(false);
                        pile1.add(pile2.get(i));
                        pile2.remove(i);
                    }
                    if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                }
                // released on empty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && pile2.get(i).getValue()==13){
                    while (i < pile2.size()){
                        pile2.get(i).setOver(200);
                        if (pile2.get(i).getValue() == 13){
                            pile2.get(i).setDown(120);
                        }
                        else {
                            pile2.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        }
                        pile2.get(i).setDragging(false);
                        pile3.add(pile2.get(i));
                        pile2.remove(i);
                    }
                    if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                }// released on empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && pile2.get(i).getValue()==13){
                    while (i < pile2.size()){
                        pile2.get(i).setOver(280);
                        if (pile2.get(i).getValue() == 13){
                            pile2.get(i).setDown(120);
                        }
                        else {
                            pile2.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                        }
                        pile2.get(i).setDragging(false);
                        pile4.add(pile2.get(i));
                        pile2.remove(i);
                    }
                    if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                }
                // released on empty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && pile2.get(i).getValue()==13){
                    while (i < pile2.size()){
                        pile2.get(i).setOver(360);
                        if (pile2.get(i).getValue() == 13){
                            pile2.get(i).setDown(120);
                        }
                        else {
                            pile2.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                        }
                        pile2.get(i).setDragging(false);
                        pile5.add(pile2.get(i));
                        pile2.remove(i);
                    }
                    if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                }
                // released on empty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && pile2.get(i).getValue()==13){
                    while (i < pile2.size()){
                        pile2.get(i).setOver(440);
                        if (pile2.get(i).getValue() == 13){
                            pile2.get(i).setDown(120);
                        }
                        else {
                            pile2.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile2.get(i).setDragging(false);
                        pile6.add(pile2.get(i));
                        pile2.remove(i);
                    }
                    if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                }
                // released on empty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && pile2.get(i).getValue()==13){
                    while (i < pile2.size()){
                        pile2.get(i).setOver(520);
                        if (pile2.get(i).getValue() == 13){
                            pile2.get(i).setDown(120);
                        }
                        else {
                            pile2.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile2.get(i).setDragging(false);
                        pile7.add(pile2.get(i));
                        pile2.remove(i);
                    }
                    if (!pile2.isEmpty()){pile2.get(pile2.size()-1).setFaceUp(true);}
                }
                // released anywhere else
                else {
                    for (int n = i; n < pile2.size(); n++){
                        pile2.get(n).setOver(120);
                        if (n == 0){
                            pile2.get(n).setDown(120);
                        }
                        else if (!pile2.get(n-1).getFaceUp()){
                            pile2.get(n).setDown(pile2.get(n-1).getDown()+10);
                        }
                        else {
                            pile2.get(n).setDown(pile2.get(n-1).getDown()+20);
                        }
                        pile2.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releasePile3(MouseEvent e){
        // move pile 3
        for (int i = 0; i <pile3.size(); i++){
            if (pile3.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(pile3, pile1, i)){
                        while (i < pile3.size()){
                            pile3.get(i).setOver(pile1.get(0).getOver());
                            pile3.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                            pile3.get(i).setDragging(false);
                            pile1.add(pile3.get(i));
                            pile3.remove(i);
                        }
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile2
                else if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(pile3, pile2, i)){
                        while (i < pile3.size()){
                            pile3.get(i).setOver(pile2.get(0).getOver());
                            pile3.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                            pile3.get(i).setDragging(false);
                            pile2.add(pile3.get(i));
                            pile3.remove(i);
                        }
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(pile3, pile4, i)){
                        while (i < pile3.size()){
                            pile3.get(i).setOver(pile4.get(0).getOver());
                            pile3.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                            pile3.get(i).setDragging(false);
                            pile4.add(pile3.get(i));
                            pile3.remove(i);
                        }
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(pile3, pile5, i)){
                        while (i < pile3.size()){
                            pile3.get(i).setOver(pile5.get(0).getOver());
                            pile3.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                            pile3.get(i).setDragging(false);
                            pile5.add(pile3.get(i));
                            pile3.remove(i);
                        }
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(pile3, pile6, i)){
                        while (i < pile3.size()){
                            pile3.get(i).setOver(pile6.get(0).getOver());
                            pile3.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                            pile3.get(i).setDragging(false);
                            pile6.add(pile3.get(i));
                            pile3.remove(i);
                        }
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(pile3, pile7, i)){
                        while (i < pile3.size()){
                            pile3.get(i).setOver(pile7.get(0).getOver());
                            pile3.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                            pile3.get(i).setDragging(false);
                            pile7.add(pile3.get(i));
                            pile3.remove(i);
                        }
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile3.get(i).getValue()==1){
                        pile3.get(i).setOver(280);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set1.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile3.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile3.get(i).getSuit())){
                        pile3.get(i).setOver(280);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set1.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile3.get(i).getValue()==1){
                        pile3.get(i).setOver(360);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set2.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile3.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile3.get(i).getSuit())){
                        pile3.get(i).setOver(360);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set2.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile3.get(i).getValue()==1){
                        pile3.get(i).setOver(440);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set3.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile3.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile3.get(i).getSuit())){
                        pile3.get(i).setOver(440);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set3.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile3.get(i).getValue()==1){
                        pile3.get(i).setOver(520);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set4.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile3.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile3.get(i).getSuit())){
                        pile3.get(i).setOver(520);
                        pile3.get(i).setDown(5);
                        pile3.get(i).setDragging(false);
                        set4.add(pile3.get(i));
                        pile3.remove(i);
                        if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile3.size(); n++){
                            pile3.get(n).setOver(200);
                            if (n == 0){
                                pile3.get(n).setDown(120);
                            }
                            else if (!pile3.get(n-1).getFaceUp()){
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                            }
                            else {
                                pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                            }
                            pile3.get(n).setDragging(false);
                        }
                    }
                }
                // released on empty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && pile3.get(i).getValue()==13){
                    while (i < pile3.size()){
                        pile3.get(i).setOver(40);
                        if (pile3.get(i).getValue() == 13){
                            pile3.get(i).setDown(120);
                        }
                        else {
                            pile3.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        }
                        pile3.get(i).setDragging(false);
                        pile1.add(pile3.get(i));
                        pile3.remove(i);
                    }
                    if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                }
                // released on empty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && pile3.get(i).getValue()==13){
                    while (i < pile3.size()){
                        pile3.get(i).setOver(120);
                        if (pile3.get(i).getValue() == 13){
                            pile3.get(i).setDown(120);
                        }
                        else {
                            pile3.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        }
                        pile3.get(i).setDragging(false);
                        pile2.add(pile3.get(i));
                        pile3.remove(i);
                    }
                    if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                }
                // released on empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && pile3.get(i).getValue()==13){
                    while (i < pile3.size()){
                        pile3.get(i).setOver(280);
                        if (pile3.get(i).getValue() == 13){
                            pile3.get(i).setDown(120);
                        }
                        else {
                            pile3.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                        }
                        pile3.get(i).setDragging(false);
                        pile4.add(pile3.get(i));
                        pile3.remove(i);
                    }
                    if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                }
                // released on empty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && pile3.get(i).getValue()==13){
                    while (i < pile3.size()){
                        pile3.get(i).setOver(360);
                        if (pile3.get(i).getValue() == 13){
                            pile3.get(i).setDown(120);
                        }
                        else {
                            pile3.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                        }
                        pile3.get(i).setDragging(false);
                        pile5.add(pile3.get(i));
                        pile3.remove(i);
                    }
                    if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                }
                // released on empty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && pile3.get(i).getValue()==13){
                    while (i < pile3.size()){
                        pile3.get(i).setOver(440);
                        if (pile3.get(i).getValue() == 13){
                            pile3.get(i).setDown(120);
                        }
                        else {
                            pile3.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile3.get(i).setDragging(false);
                        pile6.add(pile3.get(i));
                        pile3.remove(i);
                    }
                    if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                }
                // released on empty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && pile3.get(i).getValue()==13){
                    while (i < pile3.size()){
                        pile3.get(i).setOver(520);
                        if (pile3.get(i).getValue() == 13){
                            pile3.get(i).setDown(120);
                        }
                        else {
                            pile3.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile3.get(i).setDragging(false);
                        pile7.add(pile3.get(i));
                        pile3.remove(i);
                    }
                    if (!pile3.isEmpty()){pile3.get(pile3.size()-1).setFaceUp(true);}
                }
                // released anywhere else
                else {
                    for (int n = i; n < pile3.size(); n++){
                        pile3.get(n).setOver(200);
                        if (n == 0){
                            pile3.get(n).setDown(120);
                        }
                        else if (!pile3.get(n-1).getFaceUp()){
                            pile3.get(n).setDown(pile3.get(n-1).getDown()+10);
                        }
                        else {
                            pile3.get(n).setDown(pile3.get(n-1).getDown()+20);
                        }
                        pile3.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releasePile4(MouseEvent e){
        // move pile 4
        for (int i = 0; i <pile4.size(); i++){
            if (pile4.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(pile4, pile1, i)){
                        while (i < pile4.size()){
                            pile4.get(i).setOver(pile1.get(0).getOver());
                            pile4.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                            pile4.get(i).setDragging(false);
                            pile1.add(pile4.get(i));
                            pile4.remove(i);
                        }
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile2
                else if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(pile4, pile2, i)){
                        while (i < pile4.size()){
                            pile4.get(i).setOver(pile2.get(0).getOver());
                            pile4.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                            pile4.get(i).setDragging(false);
                            pile2.add(pile4.get(i));
                            pile4.remove(i);
                        }
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(pile4, pile3, i)){
                        while (i < pile4.size()){
                            pile4.get(i).setOver(pile3.get(0).getOver());
                            pile4.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                            pile4.get(i).setDragging(false);
                            pile3.add(pile4.get(i));
                            pile4.remove(i);
                        }
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(pile4, pile5, i)){
                        while (i < pile4.size()){
                            pile4.get(i).setOver(pile5.get(0).getOver());
                            pile4.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                            pile4.get(i).setDragging(false);
                            pile5.add(pile4.get(i));
                            pile4.remove(i);
                        }
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(pile4, pile6, i)){
                        while (i < pile4.size()){
                            pile4.get(i).setOver(pile6.get(0).getOver());
                            pile4.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                            pile4.get(i).setDragging(false);
                            pile6.add(pile4.get(i));
                            pile4.remove(i);
                        }
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(pile4, pile7, i)){
                        while (i < pile4.size()){
                            pile4.get(i).setOver(pile7.get(0).getOver());
                            pile4.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                            pile4.get(i).setDragging(false);
                            pile7.add(pile4.get(i));
                            pile4.remove(i);
                        }
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile4.get(i).getValue()==1){
                        pile4.get(i).setOver(280);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set1.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile4.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile4.get(i).getSuit())){
                        pile4.get(i).setOver(280);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set1.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile4.get(i).getValue()==1){
                        pile4.get(i).setOver(360);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set2.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile4.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile4.get(i).getSuit())){
                        pile4.get(i).setOver(360);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set2.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile4.get(i).getValue()==1){
                        pile4.get(i).setOver(440);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set3.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile4.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile4.get(i).getSuit())){
                        pile4.get(i).setOver(440);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set3.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile4.get(i).getValue()==1){
                        pile4.get(i).setOver(520);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set4.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile4.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile4.get(i).getSuit())){
                        pile4.get(i).setOver(520);
                        pile4.get(i).setDown(5);
                        pile4.get(i).setDragging(false);
                        set4.add(pile4.get(i));
                        pile4.remove(i);
                        if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile4.size(); n++){
                            pile4.get(n).setOver(280);
                            if (n == 0){
                                pile4.get(n).setDown(120);
                            }
                            else if (!pile4.get(n-1).getFaceUp()){
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                            }
                            else {
                                pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                            }
                            pile4.get(n).setDragging(false);
                        }
                    }
                }
                // released on epmty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && pile4.get(i).getValue()==13){
                    while (i < pile4.size()){
                        pile4.get(i).setOver(40);
                        if (pile4.get(i).getValue() == 13){
                            pile4.get(i).setDown(120);
                        }
                        else {
                            pile4.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        }
                        pile4.get(i).setDragging(false);
                        pile1.add(pile4.get(i));
                        pile4.remove(i);
                    }
                    if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                }
                // released on epmty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && pile4.get(i).getValue()==13){
                    while (i < pile4.size()){
                        pile4.get(i).setOver(120);
                        if (pile4.get(i).getValue() == 13){
                            pile4.get(i).setDown(120);
                        }
                        else {
                            pile4.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        }
                        pile4.get(i).setDragging(false);
                        pile2.add(pile4.get(i));
                        pile4.remove(i);
                    }
                    if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                }
                // released on epmty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && pile4.get(i).getValue()==13){
                    while (i < pile4.size()){
                        pile4.get(i).setOver(200);
                        if (pile4.get(i).getValue() == 13){
                            pile4.get(i).setDown(120);
                        }
                        else {
                            pile4.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        }
                        pile4.get(i).setDragging(false);
                        pile3.add(pile4.get(i));
                        pile4.remove(i);
                    }
                    if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                }
                // released on epmty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && pile4.get(i).getValue()==13){
                    while (i < pile4.size()){
                        pile4.get(i).setOver(360);
                        if (pile4.get(i).getValue() == 13){
                            pile4.get(i).setDown(120);
                        }
                        else {
                            pile4.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                        }
                        pile4.get(i).setDragging(false);
                        pile5.add(pile4.get(i));
                        pile4.remove(i);
                    }
                    if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                }
                // released on epmty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && pile4.get(i).getValue()==13){
                    while (i < pile4.size()){
                        pile4.get(i).setOver(440);
                        if (pile4.get(i).getValue() == 13){
                            pile4.get(i).setDown(120);
                        }
                        else {
                            pile4.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile4.get(i).setDragging(false);
                        pile6.add(pile4.get(i));
                        pile4.remove(i);
                    }
                    if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                }
                // released on epmty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && pile4.get(i).getValue()==13){
                    while (i < pile4.size()){
                        pile4.get(i).setOver(520);
                        if (pile4.get(i).getValue() == 13){
                            pile4.get(i).setDown(120);
                        }
                        else {
                            pile4.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile4.get(i).setDragging(false);
                        pile7.add(pile4.get(i));
                        pile4.remove(i);
                    }
                    if (!pile4.isEmpty()){pile4.get(pile4.size()-1).setFaceUp(true);}
                }
                //released anywhere else
                else {
                    for (int n = i; n < pile4.size(); n++){
                        pile4.get(n).setOver(280);
                        if (n == 0){
                            pile4.get(n).setDown(120);
                        }
                        else if (!pile4.get(n-1).getFaceUp()){
                            pile4.get(n).setDown(pile4.get(n-1).getDown()+10);
                        }
                        else {
                            pile4.get(n).setDown(pile4.get(n-1).getDown()+20);
                        }
                        pile4.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releasePile5(MouseEvent e){
        // move pile 5
        for (int i = 0; i <pile5.size(); i++){
            if (pile5.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(pile5, pile1, i)){
                        while (i < pile5.size()){
                            pile5.get(i).setOver(pile1.get(0).getOver());
                            pile5.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                            pile5.get(i).setDragging(false);
                            pile1.add(pile5.get(i));
                            pile5.remove(i);
                        }
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile2
                else if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(pile5, pile2, i)){
                        while (i < pile5.size()){
                            pile5.get(i).setOver(pile2.get(0).getOver());
                            pile5.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                            pile5.get(i).setDragging(false);
                            pile2.add(pile5.get(i));
                            pile5.remove(i);
                        }
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(pile5, pile3, i)){
                        while (i < pile5.size()){
                            pile5.get(i).setOver(pile3.get(0).getOver());
                            pile5.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                            pile5.get(i).setDragging(false);
                            pile3.add(pile5.get(i));
                            pile5.remove(i);
                        }
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(pile5, pile4, i)){
                        while (i < pile5.size()){
                            pile5.get(i).setOver(pile4.get(0).getOver());
                            pile5.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                            pile5.get(i).setDragging(false);
                            pile4.add(pile5.get(i));
                            pile5.remove(i);
                        }
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(pile5, pile6, i)){
                        while (i < pile5.size()){
                            pile5.get(i).setOver(pile6.get(0).getOver());
                            pile5.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                            pile5.get(i).setDragging(false);
                            pile6.add(pile5.get(i));
                            pile5.remove(i);
                        }
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(pile5, pile7, i)){
                        while (i < pile5.size()){
                            pile5.get(i).setOver(pile7.get(0).getOver());
                            pile5.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                            pile5.get(i).setDragging(false);
                            pile7.add(pile5.get(i));
                            pile5.remove(i);
                        }
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile5.get(i).getValue()==1){
                        pile5.get(i).setOver(280);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set1.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile5.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile5.get(i).getSuit())){
                        pile5.get(i).setOver(280);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set1.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile5.get(i).getValue()==1){
                        pile5.get(i).setOver(360);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set2.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile5.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile5.get(i).getSuit())){
                        pile5.get(i).setOver(360);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set2.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile5.get(i).getValue()==1){
                        pile5.get(i).setOver(440);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set3.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile5.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile5.get(i).getSuit())){
                        pile5.get(i).setOver(440);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set3.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile5.get(i).getValue()==1){
                        pile5.get(i).setOver(520);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set4.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile5.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile5.get(i).getSuit())){
                        pile5.get(i).setOver(520);
                        pile5.get(i).setDown(5);
                        pile5.get(i).setDragging(false);
                        set4.add(pile5.get(i));
                        pile5.remove(i);
                        if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile5.size(); n++){
                            pile5.get(n).setOver(360);
                            if (n == 0){
                                pile5.get(n).setDown(120);
                            }
                            else if (!pile5.get(n-1).getFaceUp()){
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                            }
                            else {
                                pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                            }
                            pile5.get(n).setDragging(false);
                        }
                    }
                }
                // released on empty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && pile5.get(i).getValue()==13){
                    while (i < pile5.size()){
                        pile5.get(i).setOver(40);
                        if (pile5.get(i).getValue() == 13){
                            pile5.get(i).setDown(120);
                        }
                        else {
                            pile5.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        }
                        pile5.get(i).setDragging(false);
                        pile1.add(pile5.get(i));
                        pile5.remove(i);
                    }
                    if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                }
                // released on empty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && pile5.get(i).getValue()==13){
                    while (i < pile5.size()){
                        pile5.get(i).setOver(120);
                        if (pile5.get(i).getValue() == 13){
                            pile5.get(i).setDown(120);
                        }
                        else {
                            pile5.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        }
                        pile5.get(i).setDragging(false);
                        pile2.add(pile5.get(i));
                        pile5.remove(i);
                    }
                    if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                }
                // released on empty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && pile5.get(i).getValue()==13){
                    while (i < pile5.size()){
                        pile5.get(i).setOver(200);
                        if (pile5.get(i).getValue() == 13){
                            pile5.get(i).setDown(120);
                        }
                        else {
                            pile5.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        }
                        pile5.get(i).setDragging(false);
                        pile3.add(pile5.get(i));
                        pile5.remove(i);
                    }
                    if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                }
                // released on empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && pile5.get(i).getValue()==13){
                    while (i < pile5.size()){
                        pile5.get(i).setOver(280);
                        if (pile5.get(i).getValue() == 13){
                            pile5.get(i).setDown(120);
                        }
                        else {
                            pile5.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                        }
                        pile5.get(i).setDragging(false);
                        pile4.add(pile5.get(i));
                        pile5.remove(i);
                    }
                    if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                }
                // released on empty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && pile5.get(i).getValue()==13){
                    while (i < pile5.size()){
                        pile5.get(i).setOver(440);
                        if (pile5.get(i).getValue() == 13){
                            pile5.get(i).setDown(120);
                        }
                        else {
                            pile5.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile5.get(i).setDragging(false);
                        pile6.add(pile5.get(i));
                        pile5.remove(i);
                    }
                    if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                }
                // released on empty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && pile5.get(i).getValue()==13){
                    while (i < pile5.size()){
                        pile5.get(i).setOver(520);
                        if (pile5.get(i).getValue() == 13){
                            pile5.get(i).setDown(120);
                        }
                        else {
                            pile5.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile5.get(i).setDragging(false);
                        pile7.add(pile5.get(i));
                        pile5.remove(i);
                    }
                    if (!pile5.isEmpty()){pile5.get(pile5.size()-1).setFaceUp(true);}
                }
                //released anywhere else
                else {
                    for (int n = i; n < pile5.size(); n++){
                        pile5.get(n).setOver(360);
                        if (n == 0){
                            pile5.get(n).setDown(120);
                        }
                        else if (!pile5.get(n-1).getFaceUp()){
                            pile5.get(n).setDown(pile5.get(n-1).getDown()+10);
                        }
                        else {
                            pile5.get(n).setDown(pile5.get(n-1).getDown()+20);
                        }
                        pile5.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releasePile6(MouseEvent e){
        // move pile 6
        for (int i = 0; i <pile6.size(); i++){
            if (pile6.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(pile6, pile1, i)){
                        while (i < pile6.size()){
                            pile6.get(i).setOver(pile1.get(0).getOver());
                            pile6.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                            pile6.get(i).setDragging(false);
                            pile1.add(pile6.get(i));
                            pile6.remove(i);
                        }
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile2
                else if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(pile6, pile2, i)){
                        while (i < pile6.size()){
                            pile6.get(i).setOver(pile2.get(0).getOver());
                            pile6.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                            pile6.get(i).setDragging(false);
                            pile2.add(pile6.get(i));
                            pile6.remove(i);
                        }
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(pile6, pile3, i)){
                        while (i < pile6.size()){
                            pile6.get(i).setOver(pile3.get(0).getOver());
                            pile6.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                            pile6.get(i).setDragging(false);
                            pile3.add(pile6.get(i));
                            pile6.remove(i);
                        }
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(pile6, pile4, i)){
                        while (i < pile6.size()){
                            pile6.get(i).setOver(pile4.get(0).getOver());
                            pile6.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                            pile6.get(i).setDragging(false);
                            pile4.add(pile6.get(i));
                            pile6.remove(i);
                        }
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(pile6, pile5, i)){
                        while (i < pile6.size()){
                            pile6.get(i).setOver(pile5.get(0).getOver());
                            pile6.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                            pile6.get(i).setDragging(false);
                            pile5.add(pile6.get(i));
                            pile6.remove(i);
                        }
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(pile6, pile7, i)){
                        while (i < pile6.size()){
                            pile6.get(i).setOver(pile7.get(0).getOver());
                            pile6.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                            pile6.get(i).setDragging(false);
                            pile7.add(pile6.get(i));
                            pile6.remove(i);
                        }
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile6.get(i).getValue()==1){
                        pile6.get(i).setOver(280);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set1.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile6.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile6.get(i).getSuit())){
                        pile6.get(i).setOver(280);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set1.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile6.get(i).getValue()==1){
                        pile6.get(i).setOver(360);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set2.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile6.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile6.get(i).getSuit())){
                        pile6.get(i).setOver(360);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set2.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile6.get(i).getValue()==1){
                        pile6.get(i).setOver(440);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set3.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile6.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile6.get(i).getSuit())){
                        pile6.get(i).setOver(440);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set3.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile6.get(i).getValue()==1){
                        pile6.get(i).setOver(520);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set4.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile6.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile6.get(i).getSuit())){
                        pile6.get(i).setOver(520);
                        pile6.get(i).setDown(5);
                        pile6.get(i).setDragging(false);
                        set4.add(pile6.get(i));
                        pile6.remove(i);
                        if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile6.size(); n++){
                            pile6.get(n).setOver(440);
                            if (n == 0){
                                pile6.get(n).setDown(120);
                            }
                            else if (!pile6.get(n-1).getFaceUp()){
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                            }
                            else {
                                pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                            }
                            pile6.get(n).setDragging(false);
                        }
                    }
                }
                // released on empty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && pile6.get(i).getValue()==13){
                    while (i < pile6.size()){
                        pile6.get(i).setOver(40);
                        if (pile6.get(i).getValue() == 13){
                            pile6.get(i).setDown(120);
                        }
                        else {
                            pile6.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        }
                        pile6.get(i).setDragging(false);
                        pile1.add(pile6.get(i));
                        pile6.remove(i);
                    }
                    if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                }
                // released on empty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && pile6.get(i).getValue()==13){
                    while (i < pile6.size()){
                        pile6.get(i).setOver(120);
                        if (pile6.get(i).getValue() == 13){
                            pile6.get(i).setDown(120);
                        }
                        else {
                            pile6.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        }
                        pile6.get(i).setDragging(false);
                        pile2.add(pile6.get(i));
                        pile6.remove(i);
                    }
                    if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                }
                // released on empty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && pile6.get(i).getValue()==13){
                    while (i < pile6.size()){
                        pile6.get(i).setOver(200);
                        if (pile6.get(i).getValue() == 13){
                            pile6.get(i).setDown(120);
                        }
                        else {
                            pile6.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        }
                        pile6.get(i).setDragging(false);
                        pile3.add(pile6.get(i));
                        pile6.remove(i);
                    }
                    if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                }
                // released on empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && pile6.get(i).getValue()==13){
                    while (i < pile6.size()){
                        pile6.get(i).setOver(280);
                        if (pile6.get(i).getValue() == 13){
                            pile6.get(i).setDown(120);
                        }
                        else {
                            pile6.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile6.get(i).setDragging(false);
                        pile4.add(pile6.get(i));
                        pile6.remove(i);
                    }
                    if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                }
                // released on empty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && pile6.get(i).getValue()==13){
                    while (i < pile6.size()){
                        pile6.get(i).setOver(360);
                        if (pile6.get(i).getValue() == 13){
                            pile6.get(i).setDown(120);
                        }
                        else {
                            pile6.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        }
                        pile6.get(i).setDragging(false);
                        pile5.add(pile6.get(i));
                        pile6.remove(i);
                    }
                    if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                }
                // released on empty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && pile6.get(i).getValue()==13){
                    while (i < pile6.size()){
                        pile6.get(i).setOver(520);
                        if (pile6.get(i).getValue() == 13){
                            pile6.get(i).setDown(120);
                        }
                        else {
                            pile6.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile6.get(i).setDragging(false);
                        pile7.add(pile6.get(i));
                        pile6.remove(i);
                    }
                    if (!pile6.isEmpty()){pile6.get(pile6.size()-1).setFaceUp(true);}
                }
                //released anywhere else
                else {
                    for (int n = i; n < pile6.size(); n++){
                        pile6.get(n).setOver(440);
                        if (n == 0){
                            pile6.get(n).setDown(120);
                        }
                        else if (!pile6.get(n-1).getFaceUp()){
                            pile6.get(n).setDown(pile6.get(n-1).getDown()+10);
                        }
                        else {
                            pile6.get(n).setDown(pile6.get(n-1).getDown()+20);
                        }
                        pile6.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releasePile7(MouseEvent e){
        // move pile 7
        for (int i = 0; i <pile7.size(); i++){
            if (pile7.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(pile7, pile1, i)){
                        while (i < pile7.size()){
                            pile7.get(i).setOver(pile1.get(0).getOver());
                            pile7.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                            pile7.get(i).setDragging(false);
                            pile1.add(pile7.get(i));
                            pile7.remove(i);
                        }
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile2
                else if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(pile7, pile2, i)){
                        while (i < pile7.size()){
                            pile7.get(i).setOver(pile2.get(0).getOver());
                            pile7.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                            pile7.get(i).setDragging(false);
                            pile2.add(pile7.get(i));
                            pile7.remove(i);
                        }
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(pile7, pile3, i)){
                        while (i < pile7.size()){
                            pile7.get(i).setOver(pile3.get(0).getOver());
                            pile7.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                            pile7.get(i).setDragging(false);
                            pile3.add(pile7.get(i));
                            pile7.remove(i);
                        }
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(pile7, pile4, i)){
                        while (i < pile7.size()){
                            pile7.get(i).setOver(pile4.get(0).getOver());
                            pile7.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                            pile7.get(i).setDragging(false);
                            pile4.add(pile7.get(i));
                            pile7.remove(i);
                        }
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(pile7, pile5, i)){
                        while (i < pile7.size()){
                            pile7.get(i).setOver(pile5.get(0).getOver());
                            pile7.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                            pile7.get(i).setDragging(false);
                            pile5.add(pile7.get(i));
                            pile7.remove(i);
                        }
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(pile7, pile6, i)){
                        while (i < pile7.size()){
                            pile7.get(i).setOver(pile6.get(0).getOver());
                            pile7.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                            pile7.get(i).setDragging(false);
                            pile6.add(pile7.get(i));
                            pile7.remove(i);
                        }
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && pile7.get(i).getValue()==1){
                        pile7.get(i).setOver(280);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set1.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==pile7.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(pile7.get(i).getSuit())){
                        pile7.get(i).setOver(280);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set1.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && pile7.get(i).getValue()==1){
                        pile7.get(i).setOver(360);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set2.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==pile7.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(pile7.get(i).getSuit())){
                        pile7.get(i).setOver(360);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set2.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && pile7.get(i).getValue()==1){
                        pile7.get(i).setOver(440);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set3.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==pile7.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(pile7.get(i).getSuit())){
                        pile7.get(i).setOver(440);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set3.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && pile7.get(i).getValue()==1){
                        pile7.get(i).setOver(520);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set4.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==pile7.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(pile7.get(i).getSuit())){
                        pile7.get(i).setOver(520);
                        pile7.get(i).setDown(5);
                        pile7.get(i).setDragging(false);
                        set4.add(pile7.get(i));
                        pile7.remove(i);
                        if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                    }
                    else {
                        for (int n = i; n < pile7.size(); n++){
                            pile7.get(n).setOver(520);
                            if (n == 0){
                                pile7.get(n).setDown(120);
                            }
                            else if (!pile7.get(n-1).getFaceUp()){
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                            }
                            else {
                                pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                            }
                            pile7.get(n).setDragging(false);
                        }
                    }
                }
                // released on empty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && pile7.get(i).getValue()==13){
                    while (i < pile7.size()){
                        pile7.get(i).setOver(40);
                        if (pile7.get(i).getValue() == 13){
                            pile7.get(i).setDown(120);
                        }
                        else {
                            pile7.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        }
                        pile7.get(i).setDragging(false);
                        pile1.add(pile7.get(i));
                        pile7.remove(i);
                    }
                    if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                }
                // released on empty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && pile7.get(i).getValue()==13){
                    while (i < pile7.size()){
                        pile7.get(i).setOver(120);
                        if (pile7.get(i).getValue() == 13){
                            pile7.get(i).setDown(120);
                        }
                        else {
                            pile7.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        }
                        pile7.get(i).setDragging(false);
                        pile2.add(pile7.get(i));
                        pile7.remove(i);
                    }
                    if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                }
                // released on empty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && pile7.get(i).getValue()==13){
                    while (i < pile7.size()){
                        pile7.get(i).setOver(200);
                        if (pile7.get(i).getValue() == 13){
                            pile7.get(i).setDown(120);
                        }
                        else {
                            pile7.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        }
                        pile7.get(i).setDragging(false);
                        pile3.add(pile7.get(i));
                        pile7.remove(i);
                    }
                    if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                }
                // released on empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && pile7.get(i).getValue()==13){
                    while (i < pile7.size()){
                        pile7.get(i).setOver(280);
                        if (pile7.get(i).getValue() == 13){
                            pile7.get(i).setDown(120);
                        }
                        else {
                            pile7.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile7.get(i).setDragging(false);
                        pile4.add(pile7.get(i));
                        pile7.remove(i);
                    }
                    if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                }
                // released on empty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && pile7.get(i).getValue()==13){
                    while (i < pile7.size()){
                        pile7.get(i).setOver(360);
                        if (pile7.get(i).getValue() == 13){
                            pile7.get(i).setDown(120);
                        }
                        else {
                            pile7.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile7.get(i).setDragging(false);
                        pile5.add(pile7.get(i));
                        pile7.remove(i);
                    }
                    if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                }
                // released on empty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && pile7.get(i).getValue()==13){
                    while (i < pile7.size()){
                        pile7.get(i).setOver(440);
                        if (pile7.get(i).getValue() == 13){
                            pile7.get(i).setDown(120);
                        }
                        else {
                            pile7.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        }
                        pile7.get(i).setDragging(false);
                        pile6.add(pile7.get(i));
                        pile7.remove(i);
                    }
                    if (!pile7.isEmpty()){pile7.get(pile7.size()-1).setFaceUp(true);}
                }
                //released anywhere else
                else {
                    for (int n = i; n < pile7.size(); n++){
                        pile7.get(n).setOver(520);
                        if (n == 0){
                            pile7.get(n).setDown(120);
                        }
                        else if (!pile7.get(n-1).getFaceUp()){
                            pile7.get(n).setDown(pile7.get(n-1).getDown()+10);
                        }
                        else {
                            pile7.get(n).setDown(pile7.get(n-1).getDown()+20);
                        }
                        pile7.get(n).setDragging(false);
                    }
                }
                repaint();
                break;
                
            }
        }
    }
    
    private void releaseDeck(MouseEvent e){
        // move deck card
        for (int i = 0; i <deck.size(); i++){
            if (deck.get(i).getDragging()){
                // released on pile1
                if (mouseOnPile(pile1, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile1, i)){
                        deck.get(i).setOver(pile1.get(0).getOver());
                        deck.get(i).setDown(pile1.get(pile1.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile1.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on pile2
                else if (mouseOnPile(pile2, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile2, i)){
                        deck.get(i).setOver(pile2.get(0).getOver());
                        deck.get(i).setDown(pile2.get(pile2.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile2.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on pile3
                else if (mouseOnPile(pile3, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile3, i)){
                        deck.get(i).setOver(pile3.get(0).getOver());
                        deck.get(i).setDown(pile3.get(pile3.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile3.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on pile4
                else if (mouseOnPile(pile4, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile4, i)){
                        deck.get(i).setOver(pile4.get(0).getOver());
                        deck.get(i).setDown(pile4.get(pile4.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile4.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on pile5
                else if (mouseOnPile(pile5, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile5, i)){
                        deck.get(i).setOver(pile5.get(0).getOver());
                        deck.get(i).setDown(pile5.get(pile5.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile5.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on pile6
                else if (mouseOnPile(pile6, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile6, i)){
                        deck.get(i).setOver(pile6.get(0).getOver());
                        deck.get(i).setDown(pile6.get(pile6.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile6.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on pile7
                else if (mouseOnPile(pile7, e)){
                    if (cardIsOneLessAndOppositeColor(deck, pile7, i)){
                        deck.get(i).setOver(pile7.get(0).getOver());
                        deck.get(i).setDown(pile7.get(pile7.size()-1).getDown()+20);
                        deck.get(i).setDragging(false);
                        pile7.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on set1
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=5 && e.getY()<=101){
                    if (set1.isEmpty() && deck.get(i).getValue()==1){
                        deck.get(i).setOver(280);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set1.add(deck.get(i));
                        deck.remove(i);
                    }
                    else if (!set1.isEmpty() && set1.get(set1.size()-1).getValue()==deck.get(i).getValue()-1
                            && set1.get(0).getSuit().equals(deck.get(i).getSuit())){
                        deck.get(i).setOver(280);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set1.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on set2
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=5 && e.getY()<=101){
                    if (set2.isEmpty() && deck.get(i).getValue()==1){
                        deck.get(i).setOver(360);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set2.add(deck.get(i));
                        deck.remove(i);
                    }
                    else if (!set2.isEmpty() && set2.get(set2.size()-1).getValue()==deck.get(i).getValue()-1
                            && set2.get(0).getSuit().equals(deck.get(i).getSuit())){
                        deck.get(i).setOver(360);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set2.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on set3
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=5 && e.getY()<=101){
                    if (set3.isEmpty() && deck.get(i).getValue()==1){
                        deck.get(i).setOver(440);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set3.add(deck.get(i));
                        deck.remove(i);
                    }
                    else if (!set3.isEmpty() && set3.get(set3.size()-1).getValue()==deck.get(i).getValue()-1
                            && set3.get(0).getSuit().equals(deck.get(i).getSuit())){
                        deck.get(i).setOver(440);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set3.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on set4
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=5 && e.getY()<=101){
                    if (set4.isEmpty() && deck.get(i).getValue()==1){
                        deck.get(i).setOver(520);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set4.add(deck.get(i));
                        deck.remove(i);
                    }
                    else if (!set4.isEmpty() && set4.get(set4.size()-1).getValue()==deck.get(i).getValue()-1
                            && set4.get(0).getSuit().equals(deck.get(i).getSuit())){
                        deck.get(i).setOver(520);
                        deck.get(i).setDown(5);
                        deck.get(i).setDragging(false);
                        set4.add(deck.get(i));
                        deck.remove(i);
                    }
                    else {
                        deck.get(i).setOver(90);
                        deck.get(i).setDown(10);
                        deck.get(i).setDragging(false);
                    }
                }
                // released on an empty pile1
                else if (e.getX()>=40 && e.getX()<=111 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(40);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile1.add(deck.get(i));
                    deck.remove(i);
                }
                // released on an empty pile2
                else if (e.getX()>=120 && e.getX()<=191 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(120);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile2.add(deck.get(i));
                    deck.remove(i);
                }
                // released on an empty pile3
                else if (e.getX()>=200 && e.getX()<=271 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(200);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile3.add(deck.get(i));
                    deck.remove(i);
                }
                // released on an empty pile4
                else if (e.getX()>=280 && e.getX()<=351 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(280);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile4.add(deck.get(i));
                    deck.remove(i);
                }
                // released on an empty pile5
                else if (e.getX()>=360 && e.getX()<=431 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(360);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile5.add(deck.get(i));
                    deck.remove(i);
                }
                // released on an empty pile6
                else if (e.getX()>=440 && e.getX()<=511 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(440);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile6.add(deck.get(i));
                    deck.remove(i);
                }
                // released on an empty pile7
                else if (e.getX()>=520 && e.getX()<=591 && e.getY()>=120 && e.getY()<=216 && deck.get(i).getValue()==13){
                    deck.get(i).setOver(520);
                    deck.get(i).setDown(120);
                    deck.get(i).setDragging(false);
                    pile7.add(deck.get(i));
                    deck.remove(i);
                }
                // released anywhere else
                else {
                    deck.get(i).setOver(90);
                    deck.get(i).setDown(10);
                    deck.get(i).setDragging(false);
                }
                repaint();
                break;
                
            }
        }
    }
    
    private Boolean mouseOnPile(ArrayList<BrownCard> pile, MouseEvent e){
        Boolean answer = false;
        if (pile.isEmpty()){}
        else if (pile.get(0).getOver()<=e.getX() && pile.get(0).getRight()>=e.getX()
                && pile.get(0).getDown()<=e.getY() && pile.get(pile.size()-1).getBottom()>=e.getY()){
            answer = true;
        }
        return answer;
    }
    
    private Boolean cardIsOneLessAndOppositeColor(ArrayList<BrownCard> StartPile,ArrayList<BrownCard> TargetPile, Integer i){
        Boolean answer = false;
        if (StartPile.get(i).isBlack() && !TargetPile.get(TargetPile.size()-1).isBlack()){
            if (StartPile.get(i).getValue()==TargetPile.get(TargetPile.size()-1).getValue()-1){
                answer = true;
            }
        }
        if (!StartPile.get(i).isBlack() && TargetPile.get(TargetPile.size()-1).isBlack()){
            if (StartPile.get(i).getValue()==TargetPile.get(TargetPile.size()-1).getValue()-1){
                answer = true;
            }
        }
        return answer;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton rulesButton;
    // End of variables declaration//GEN-END:variables
}
