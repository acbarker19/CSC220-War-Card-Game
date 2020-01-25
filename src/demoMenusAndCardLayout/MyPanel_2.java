// Jim Klayder --- February 2018

//simple demo panel

//NOTE the use of Card and MultiCard to display a card image

package demoMenusAndCardLayout;

import helperCards.Card;
import helperCards.MultiCard;
import helpers.Utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author klaydejr
 */
public class MyPanel_2 extends javax.swing.JPanel {

    private Font myFont;
    private MultiCard multiCard;
    private Card currentCard;
    /**
     * Creates new form MyPanel_2
     */
    public MyPanel_2() {
        initComponents();
        
        myFont = new Font("Serif", Font.BOLD, 22);
        multiCard = new MultiCard();
        
        int randomCardIndex = Utility.getRandomFromTo(0, 51);
        currentCard = multiCard.getCardAtIndex(randomCardIndex);
        
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setColor(new Color(200,255,200));
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(myFont);
        
        g.setColor(Color.BLACK);
        g.drawString("MyPanel_2", 50, 200);
        
        currentCard.drawCard(g, this);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
