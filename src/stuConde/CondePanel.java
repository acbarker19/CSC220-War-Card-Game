/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuConde;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author klaydejr
 */
public class CondePanel extends javax.swing.JPanel {

    private Font myFont;
    
    /**
     * Creates new form MyPanel_1
     */
    public CondePanel() {
        initComponents();
        
        myFont = new Font("Serif", Font.BOLD, 22);
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        
        g.setColor(new Color(255,200,200));
        g.fillRect(0, 0, 1000, 1000);
        g.setFont(myFont);
        
        g.setColor(Color.BLACK);
        g.drawString("CondePanel", 50, 150);
        
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
