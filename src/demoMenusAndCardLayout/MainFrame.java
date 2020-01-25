/*
  Jim Klayder -- spring 2018

CSC 220 class project -- Each student will contribute a package that
contains a card game.
 */


// Jim Klayder --- February 2018

/* demo of using Menus and CardLayout

In NetBeans create a new JFrame Form, then drag a MenuBar onto the Design.

You may now drag multiple Menus onto the MenuBar. 

And you may drag multiple MenuItems onto each Menu.

/////////////////
Finally, handle each MenuItem in NetBeans as you would a Button in NetBeans.
That is, right-click to Edit Text and Change Variable Name.
So now you can double-click the MenuItem to implement the ActionPerformed method.

/////////////////
/////////////////
/////////////////
Code for creating and using CardLayouts is demoed in the MainFrame class below.



Also, this demo may be used as a starting point for a spring CSC 220
class project in UMU.


*/
package demoMenusAndCardLayout;

import java.awt.*;
import javax.swing.*;
import stuBarker.*;
import stuBarros.*;
import stuBrown.*;
import stuBushman.*;
import stuCarper.*;
import stuConde.*;
import stuCotton.*;
import stuGrant.*;
import stuHaiss.*;
import stuHanford.*;
import stuHegidus.*;
import stuLuca.*;
import stuMartin.*;
import stuMoore.*;
import stuNeimayer.*;
import stuRoller.*;
import stuRyan.*;
import stuSanford.*;

/**
 *
 * @author klaydejr
 */
public class MainFrame extends javax.swing.JFrame {

    //There are many web resources for creating and using the CardLayout class,
    //but here is one that was used for the ideas in this example.
    
    //  https://www.javatpoint.com/CardLayout
    
    Container myContentPane;
    CardLayout myCardLayout;
    
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        setSize(800, 500);
        
        myContentPane = getContentPane();
        
        myCardLayout = new CardLayout();
        myContentPane.setLayout(myCardLayout);  
        
        JPanel tempCard = new MyPanel_1();
        myContentPane.add("one", tempCard);
        
        tempCard = new MyPanel_2();
        myContentPane.add("two", tempCard);
        
        tempCard = new MyPanel_3();
        myContentPane.add("three", tempCard);
        
        tempCard = new MyPanel_4();
        myContentPane.add("four", tempCard);
        
        tempCard = new BarkerPanel();
        myContentPane.add("Barker", tempCard);
        
        tempCard = new BarrosPanel();
        myContentPane.add("Barros", tempCard);
        
        tempCard = new BrownPanel();
        myContentPane.add("Brown", tempCard);
        
        tempCard = new BushmanPanel();
        myContentPane.add("Bushman", tempCard);
        
        tempCard = new CarperPanel();
        myContentPane.add("Carper", tempCard);
        
        tempCard = new CondePanel();
        myContentPane.add("Conde", tempCard);
        
        tempCard = new CottonPanel();
        myContentPane.add("Cotton", tempCard);
        
        tempCard = new GrantPanel();
        myContentPane.add("Grant", tempCard);
        
        tempCard = new HaissPanel();
        myContentPane.add("Haiss", tempCard);
        
        tempCard = new HanfordPanel();
        myContentPane.add("Hanford", tempCard);
        
        tempCard = new HegidusPanel();
        myContentPane.add("Hegidus", tempCard);
        
        tempCard = new LucaPanel();
        myContentPane.add("Luca", tempCard);
        
        tempCard = new MartinPanel();
        myContentPane.add("Martin", tempCard);
        
        tempCard = new MoorePanel();
        myContentPane.add("Moore", tempCard);
        
        tempCard = new NeimayerPanel();
        myContentPane.add("Neimayer", tempCard);
        
        tempCard = new RollerPanel();
        myContentPane.add("Roller", tempCard);
        
        tempCard = new RyanPanel();
        myContentPane.add("Ryan", tempCard);
        
        tempCard = new SanfordPanel();
        myContentPane.add("Sanford", tempCard);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        quitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        showPanel_1 = new javax.swing.JMenuItem();
        showPanel_2 = new javax.swing.JMenuItem();
        showPanel_3 = new javax.swing.JMenuItem();
        showPanel_4 = new javax.swing.JMenuItem();
        jMenuItemBarker = new javax.swing.JMenuItem();
        jMenuItemBarros = new javax.swing.JMenuItem();
        jMenuItemBrown = new javax.swing.JMenuItem();
        jMenuItemBushman = new javax.swing.JMenuItem();
        jMenuItemCarper = new javax.swing.JMenuItem();
        jMenuItemConde = new javax.swing.JMenuItem();
        jMenuItemCotton = new javax.swing.JMenuItem();
        jMenuItemGrant = new javax.swing.JMenuItem();
        jMenuItemHaiss = new javax.swing.JMenuItem();
        jMenuItemHanford = new javax.swing.JMenuItem();
        jMenuItemHegidus = new javax.swing.JMenuItem();
        jMenuItemLuca = new javax.swing.JMenuItem();
        jMenuItemMartin = new javax.swing.JMenuItem();
        jMenuItemMoore = new javax.swing.JMenuItem();
        jMenuItemNeimayer = new javax.swing.JMenuItem();
        jMenuItemRoller = new javax.swing.JMenuItem();
        jMenuItemRyan = new javax.swing.JMenuItem();
        jMenuItemSanford = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("MyProgram");

        aboutMenuItem.setText("About MyProgram");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(aboutMenuItem);

        quitMenuItem.setText("Quit MyProgram");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(quitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        showPanel_1.setText("one");
        showPanel_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPanel_1ActionPerformed(evt);
            }
        });
        jMenu2.add(showPanel_1);

        showPanel_2.setText("two");
        showPanel_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPanel_2ActionPerformed(evt);
            }
        });
        jMenu2.add(showPanel_2);

        showPanel_3.setText("three");
        showPanel_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPanel_3ActionPerformed(evt);
            }
        });
        jMenu2.add(showPanel_3);

        showPanel_4.setText("four");
        showPanel_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPanel_4ActionPerformed(evt);
            }
        });
        jMenu2.add(showPanel_4);

        jMenuItemBarker.setText("Barker");
        jMenuItemBarker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBarkerActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemBarker);

        jMenuItemBarros.setText("Barros");
        jMenuItemBarros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBarrosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemBarros);

        jMenuItemBrown.setText("Brown");
        jMenuItemBrown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBrownActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemBrown);

        jMenuItemBushman.setText("Bushman");
        jMenuItemBushman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBushmanActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemBushman);

        jMenuItemCarper.setText("Carper");
        jMenuItemCarper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCarperActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCarper);

        jMenuItemConde.setText("Conde");
        jMenuItemConde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCondeActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemConde);

        jMenuItemCotton.setText("Cotton");
        jMenuItemCotton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCottonActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCotton);

        jMenuItemGrant.setText("Grant");
        jMenuItemGrant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGrantActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemGrant);

        jMenuItemHaiss.setText("Haiss");
        jMenuItemHaiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHaissActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHaiss);

        jMenuItemHanford.setText("Hanford");
        jMenuItemHanford.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHanfordActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHanford);

        jMenuItemHegidus.setText("Hegidus");
        jMenuItemHegidus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHegidusActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHegidus);

        jMenuItemLuca.setText("Luca");
        jMenuItemLuca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLucaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemLuca);

        jMenuItemMartin.setText("Martin");
        jMenuItemMartin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMartinActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemMartin);

        jMenuItemMoore.setText("Moore");
        jMenuItemMoore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMooreActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemMoore);

        jMenuItemNeimayer.setText("Neimayer");
        jMenuItemNeimayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNeimayerActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemNeimayer);

        jMenuItemRoller.setText("Roller");
        jMenuItemRoller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRollerActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemRoller);

        jMenuItemRyan.setText("Ryan");
        jMenuItemRyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRyanActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemRyan);

        jMenuItemSanford.setText("Sanford");
        jMenuItemSanford.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSanfordActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemSanford);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        System.out.println("aboutMenuItemAction");
        
        JOptionPane.showMessageDialog(this, "CSC 220 Card Game -- spring 2018 -- University of Mount Union");

    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuItemActionPerformed
        System.out.println("quitMenuItemAction");
        System.exit(0);
    }//GEN-LAST:event_quitMenuItemActionPerformed

    private void showPanel_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPanel_1ActionPerformed
        System.out.println("showPanel_1Action");
        myCardLayout.show(myContentPane, "one");
    }//GEN-LAST:event_showPanel_1ActionPerformed

    private void showPanel_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPanel_2ActionPerformed
        System.out.println("showPanel_2Action");
        myCardLayout.show(myContentPane, "two");
    }//GEN-LAST:event_showPanel_2ActionPerformed

    private void showPanel_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPanel_3ActionPerformed
        System.out.println("showPanel_3Action");
        myCardLayout.show(myContentPane, "three");
    }//GEN-LAST:event_showPanel_3ActionPerformed

    private void showPanel_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPanel_4ActionPerformed
        System.out.println("showPanel_4Action");
        myCardLayout.show(myContentPane, "four");
    }//GEN-LAST:event_showPanel_4ActionPerformed

    private void jMenuItemCondeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCondeActionPerformed
        System.out.println("Conde");
        myCardLayout.show(myContentPane, "Conde");
    }//GEN-LAST:event_jMenuItemCondeActionPerformed

    private void jMenuItemBarkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBarkerActionPerformed
        System.out.println("Barker");
        myCardLayout.show(myContentPane, "Barker");
    }//GEN-LAST:event_jMenuItemBarkerActionPerformed

    private void jMenuItemBarrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBarrosActionPerformed
        System.out.println("Barros");
        myCardLayout.show(myContentPane, "Barros");
    }//GEN-LAST:event_jMenuItemBarrosActionPerformed

    private void jMenuItemBrownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBrownActionPerformed
        System.out.println("Brown");
        myCardLayout.show(myContentPane, "Brown");
    }//GEN-LAST:event_jMenuItemBrownActionPerformed

    private void jMenuItemBushmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBushmanActionPerformed
        System.out.println("Bushman");
        myCardLayout.show(myContentPane, "Bushman");
    }//GEN-LAST:event_jMenuItemBushmanActionPerformed

    private void jMenuItemCarperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCarperActionPerformed
        System.out.println("Carper");
        myCardLayout.show(myContentPane, "Carper");
    }//GEN-LAST:event_jMenuItemCarperActionPerformed

    private void jMenuItemGrantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGrantActionPerformed
        System.out.println("Grant");
        myCardLayout.show(myContentPane, "Grant");
    }//GEN-LAST:event_jMenuItemGrantActionPerformed

    private void jMenuItemHaissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHaissActionPerformed
        System.out.println("Haiss");
        myCardLayout.show(myContentPane, "Haiss");
    }//GEN-LAST:event_jMenuItemHaissActionPerformed

    private void jMenuItemHanfordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHanfordActionPerformed
        System.out.println("Hanford");
        myCardLayout.show(myContentPane, "Hanford");
    }//GEN-LAST:event_jMenuItemHanfordActionPerformed

    private void jMenuItemHegidusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHegidusActionPerformed
        System.out.println("Hegidus");
        myCardLayout.show(myContentPane, "Hegidus");
    }//GEN-LAST:event_jMenuItemHegidusActionPerformed

    private void jMenuItemLucaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLucaActionPerformed
        System.out.println("Luca");
        myCardLayout.show(myContentPane, "Luca");
    }//GEN-LAST:event_jMenuItemLucaActionPerformed

    private void jMenuItemMartinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMartinActionPerformed
        System.out.println("Martin");
        myCardLayout.show(myContentPane, "Martin");
    }//GEN-LAST:event_jMenuItemMartinActionPerformed

    private void jMenuItemNeimayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNeimayerActionPerformed
        System.out.println("Neimayer");
        myCardLayout.show(myContentPane, "Neimayer");
    }//GEN-LAST:event_jMenuItemNeimayerActionPerformed

    private void jMenuItemRollerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRollerActionPerformed
        System.out.println("Roller");
        myCardLayout.show(myContentPane, "Roller");
    }//GEN-LAST:event_jMenuItemRollerActionPerformed

    private void jMenuItemRyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRyanActionPerformed
        System.out.println("Ryan");
        myCardLayout.show(myContentPane, "Ryan");
    }//GEN-LAST:event_jMenuItemRyanActionPerformed

    private void jMenuItemSanfordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSanfordActionPerformed
       System.out.println("Sanford");
       myCardLayout.show(myContentPane, "Sanford");
    }//GEN-LAST:event_jMenuItemSanfordActionPerformed

    private void jMenuItemCottonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCottonActionPerformed
       System.out.println("Cotton");
       myCardLayout.show(myContentPane, "Cotton");
    }//GEN-LAST:event_jMenuItemCottonActionPerformed

    private void jMenuItemMooreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMooreActionPerformed
       System.out.println("Moore");
       myCardLayout.show(myContentPane, "Moore");
    }//GEN-LAST:event_jMenuItemMooreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemBarker;
    private javax.swing.JMenuItem jMenuItemBarros;
    private javax.swing.JMenuItem jMenuItemBrown;
    private javax.swing.JMenuItem jMenuItemBushman;
    private javax.swing.JMenuItem jMenuItemCarper;
    private javax.swing.JMenuItem jMenuItemConde;
    private javax.swing.JMenuItem jMenuItemCotton;
    private javax.swing.JMenuItem jMenuItemGrant;
    private javax.swing.JMenuItem jMenuItemHaiss;
    private javax.swing.JMenuItem jMenuItemHanford;
    private javax.swing.JMenuItem jMenuItemHegidus;
    private javax.swing.JMenuItem jMenuItemLuca;
    private javax.swing.JMenuItem jMenuItemMartin;
    private javax.swing.JMenuItem jMenuItemMoore;
    private javax.swing.JMenuItem jMenuItemNeimayer;
    private javax.swing.JMenuItem jMenuItemRoller;
    private javax.swing.JMenuItem jMenuItemRyan;
    private javax.swing.JMenuItem jMenuItemSanford;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JMenuItem showPanel_1;
    private javax.swing.JMenuItem showPanel_2;
    private javax.swing.JMenuItem showPanel_3;
    private javax.swing.JMenuItem showPanel_4;
    // End of variables declaration//GEN-END:variables
}
