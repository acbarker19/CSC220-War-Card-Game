/*
Name Austin Carper
Date Due 3/19
Class CSC 220
URL to Project https://drive.google.com/open?id=1iiLIuCfOQTNyZvxLSDEa0F-sjxPwsNvc

This project is 52 Card pickup. At the start of a new game all cards are scattered
across the screen. Click on each card to collect each card.
Enhancement is the extra button allowing for a speed toggle variable.
This toggle alows collecting cards by dragging a mouse click.

*/


package stuCarper;

import helperCards.MultiCard;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class CarperPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener{

    private Font myFont;
    private ArrayList<CarperCard> deck;
    private Boolean showFacedown, speedPickup, victory;
    private Random rand;
    
    /**
     * Creates new form MyPanel_1
     */
    public CarperPanel() {
        initComponents();
        addMouseListener(this);
        addMouseMotionListener(this);
        
        myFont = new Font("Serif", Font.BOLD, 22);        
        deck = new ArrayList<CarperCard>();
        rand = new Random();
        
        showFacedown = true;
        speedPickup = false;
        victory = false;
        
        //Adds All cards to deck
        deck.add( new CarperCard("C", 1, 400, 200, getImage("src/cards/"+"ac"+".gif")));
        deck.add( new CarperCard("C", 2, 400, 200, getImage("src/cards/"+"2c"+".gif")));
        deck.add( new CarperCard("C", 3, 400, 200, getImage("src/cards/"+"3c"+".gif")));
        deck.add( new CarperCard("C", 4, 400, 200, getImage("src/cards/"+"4c"+".gif")));
        deck.add( new CarperCard("C", 5, 400, 200, getImage("src/cards/"+"5c"+".gif")));
        deck.add( new CarperCard("C", 6, 400, 200, getImage("src/cards/"+"6c"+".gif")));
        deck.add( new CarperCard("C", 7, 400, 200, getImage("src/cards/"+"7c"+".gif")));
        deck.add( new CarperCard("C", 8, 400, 200, getImage("src/cards/"+"8c"+".gif")));
        deck.add( new CarperCard("C", 9, 400, 200, getImage("src/cards/"+"9c"+".gif")));
        deck.add( new CarperCard("C", 10, 400, 200, getImage("src/cards/"+"10c"+".gif")));
        deck.add( new CarperCard("C", 11, 400, 200, getImage("src/cards/"+"jc"+".gif")));
        deck.add( new CarperCard("C", 12, 400, 200, getImage("src/cards/"+"qc"+".gif")));
        deck.add( new CarperCard("C", 13, 400, 200, getImage("src/cards/"+"kc"+".gif")));
        
        deck.add( new CarperCard("D", 1, 400, 200, getImage("src/cards/"+"ad"+".gif")));
        deck.add( new CarperCard("D", 2, 400, 200, getImage("src/cards/"+"2d"+".gif")));
        deck.add( new CarperCard("D", 3, 400, 200, getImage("src/cards/"+"3d"+".gif")));
        deck.add( new CarperCard("D", 4, 400, 200, getImage("src/cards/"+"4d"+".gif")));
        deck.add( new CarperCard("D", 5, 400, 200, getImage("src/cards/"+"5d"+".gif")));
        deck.add( new CarperCard("D", 6, 400, 200, getImage("src/cards/"+"6d"+".gif")));
        deck.add( new CarperCard("D", 7, 400, 200, getImage("src/cards/"+"7d"+".gif")));
        deck.add( new CarperCard("D", 8, 400, 200, getImage("src/cards/"+"8d"+".gif")));
        deck.add( new CarperCard("D", 9, 400, 200, getImage("src/cards/"+"9d"+".gif")));
        deck.add( new CarperCard("D", 10, 400, 200, getImage("src/cards/"+"10d"+".gif")));
        deck.add( new CarperCard("D", 11, 400, 200, getImage("src/cards/"+"jd"+".gif")));
        deck.add( new CarperCard("D", 12, 400, 200, getImage("src/cards/"+"qd"+".gif")));
        deck.add( new CarperCard("D", 13, 400, 200, getImage("src/cards/"+"kd"+".gif")));
        
        deck.add( new CarperCard("H", 1, 400, 200, getImage("src/cards/"+"ah"+".gif")));
        deck.add( new CarperCard("H", 2, 400, 200, getImage("src/cards/"+"2h"+".gif")));
        deck.add( new CarperCard("H", 3, 400, 200, getImage("src/cards/"+"3h"+".gif")));
        deck.add( new CarperCard("H", 4, 400, 200, getImage("src/cards/"+"4h"+".gif")));
        deck.add( new CarperCard("H", 5, 400, 200, getImage("src/cards/"+"5h"+".gif")));
        deck.add( new CarperCard("H", 6, 400, 200, getImage("src/cards/"+"6h"+".gif")));
        deck.add( new CarperCard("H", 7, 400, 200, getImage("src/cards/"+"7h"+".gif")));
        deck.add( new CarperCard("H", 8, 400, 200, getImage("src/cards/"+"8h"+".gif")));
        deck.add( new CarperCard("H", 9, 400, 200, getImage("src/cards/"+"9h"+".gif")));
        deck.add( new CarperCard("H", 10, 400, 200, getImage("src/cards/"+"10h"+".gif")));
        deck.add( new CarperCard("H", 11, 400, 200, getImage("src/cards/"+"jh"+".gif")));
        deck.add( new CarperCard("H", 12, 400, 200, getImage("src/cards/"+"qh"+".gif")));
        deck.add( new CarperCard("H", 13, 400, 200, getImage("src/cards/"+"kh"+".gif")));
        
        deck.add( new CarperCard("S", 1, 400, 200, getImage("src/cards/"+"as"+".gif")));
        deck.add( new CarperCard("S", 2, 400, 200, getImage("src/cards/"+"2s"+".gif")));
        deck.add( new CarperCard("S", 3, 400, 200, getImage("src/cards/"+"3s"+".gif")));
        deck.add( new CarperCard("S", 4, 400, 200, getImage("src/cards/"+"4s"+".gif")));
        deck.add( new CarperCard("S", 5, 400, 200, getImage("src/cards/"+"5s"+".gif")));
        deck.add( new CarperCard("S", 6, 400, 200, getImage("src/cards/"+"6s"+".gif")));
        deck.add( new CarperCard("S", 7, 400, 200, getImage("src/cards/"+"7s"+".gif")));
        deck.add( new CarperCard("S", 8, 400, 200, getImage("src/cards/"+"8s"+".gif")));
        deck.add( new CarperCard("S", 9, 400, 200, getImage("src/cards/"+"9s"+".gif")));
        deck.add( new CarperCard("S", 10, 400, 200, getImage("src/cards/"+"10s"+".gif")));
        deck.add( new CarperCard("S", 11, 400, 200, getImage("src/cards/"+"js"+".gif")));
        deck.add( new CarperCard("S", 12, 400, 200, getImage("src/cards/"+"qs"+".gif")));
        deck.add( new CarperCard("S", 13, 400, 200, getImage("src/cards/"+"ks"+".gif")));   

    }
    
    
    public void checkVictory(){
        Boolean temp = false;
        for (int i =0; i < deck.size(); i++){
            if (!deck.get(i).getCollect()){   
                temp = true;
            }    
        }
        if (!temp){
            victory = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Checking");
        for (int i = 51; i > -1 ; i--){//reverse to check top cards first
            if (deck.get(i).contains(e.getX(), e.getY())){
                deck.get(i).move(400, 200);
                deck.get(i).setCollect(true);
                System.out.println("True");
                i = -1;//Ends Forloop
                showFacedown = true;
            }
        }
        //System.out.println("mousePressed at "+e.getX()+", "+e.getY());
        repaint();
    }
    
       @Override
    public void mouseDragged(MouseEvent e) {
         //System.out.println("mouseDragged at "+e.getX()+", "+e.getY());
        if (speedPickup){
        System.out.println("Checking");
        for (int i = 51; i > -1 ; i--){//reverse to check top cards first
            if (deck.get(i).contains(e.getX(), e.getY())){
                deck.get(i).move(400, 200);
                deck.get(i).setCollect(true);
                System.out.println("True");
                i = -1;//Ends Forloop
                showFacedown = true;
            }
        }
        repaint();
         }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased at "+e.getX()+", "+e.getY());
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
    public void mouseMoved(MouseEvent e) {
        //System.out.println("mouseMoved at "+e.getX()+", "+e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked at "+e.getX()+", "+e.getY());
    }
    
       //Following method taken from MultiCard
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
    
    
    
    
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setColor(new Color(0,128,255));
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(myFont);
        
        g.setColor(Color.BLACK);
        g.drawString("CarperPanel", 25, 25);
        g.drawString("52 Card Pick-Up!", 25, 50);
        
        for (int i =0; i < deck.size(); i++){
            if (!deck.get(i).getCollect()){   
            deck.get(i).drawCard(g, this);
            }
            
            
        }

        if (showFacedown){
            g.drawImage(getImage("src/cards/"+"faceDown"+".gif"), 365, 151, this);
        }
        
        checkVictory();
        
        if (victory){
            g.drawImage(getImage("src/stuCarper/"+"victory"+".gif"), 150, 100, this);
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

        newGame = new javax.swing.JButton();
        helpMenu = new javax.swing.JButton();
        speedToggle = new javax.swing.JToggleButton();

        setPreferredSize(new java.awt.Dimension(800, 500));

        newGame.setText("New Game");
        newGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        });

        helpMenu.setText("How To Play");
        helpMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuActionPerformed(evt);
            }
        });

        speedToggle.setText("Toggle Speed");
        speedToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speedToggleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newGame)
                .addGap(18, 18, 18)
                .addComponent(helpMenu)
                .addGap(18, 18, 18)
                .addComponent(speedToggle)
                .addContainerGap(482, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(438, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newGame)
                    .addComponent(helpMenu)
                    .addComponent(speedToggle))
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
        victory = false;
        for (int i =0; i < deck.size(); i++){

            deck.get(i).move(rand.nextInt(700)+50,rand.nextInt(300)+50);
            deck.get(i).setCollect(false);
            
        }
        showFacedown = false;
        repaint();
    }//GEN-LAST:event_newGameActionPerformed

    private void helpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuActionPerformed
        JOptionPane.showMessageDialog(this, "Welcome to the classic game of 52 Card pickup!\n\n"+
                "This game originated by throwing an entire deck of card into the air.\n"+
                "At the start of a new game all cards are scattered across the screen.\n"+
                "Click on each card to collect each card. \n "+
                "Victory is reached once all cards have been collected.\n\n"+
                "Enhancement: SpeedToggle by using the button");
    }//GEN-LAST:event_helpMenuActionPerformed

    private void speedToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speedToggleActionPerformed
        speedPickup = !speedPickup;
    }//GEN-LAST:event_speedToggleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton helpMenu;
    private javax.swing.JButton newGame;
    private javax.swing.JToggleButton speedToggle;
    // End of variables declaration//GEN-END:variables

}