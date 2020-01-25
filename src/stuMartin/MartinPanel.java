/*MartinPanel.java
 *Bailey Martin
 *March 28, 2018
 *CSC 220 University of Mount Union
 *Project Description: This project was designed to be a part of the Class Card Project (Project #2).  This class currently has a MartinHighScore,
 *  a MartinPanel, a MartinRulesFrame, and a MartinCard class.  This game is a drag-and-drop card game.  The rules can be displayed by clicking
 *  "Show Rules" while the game is running.  The objective of the game is to drag and drop each legal card into the 'goal' displayed in the 
 *  game window.  The legal cards are : odd cards, aces, Kings, and Jacks.  Each legal card dragged into the goal earns you one point.  For
 *  every illegal card that has been dragged into the goal, it subtracts 2 points from your current score, and displays a pop-up message that
 *  alerts the user that an invalid card has been placed in the goal.  The card then gets assigned random coordinates, moving it out of the goal
 *  and onto a random part of the game window.  You win the game once all of the legal cards have been placed in the goal.  A text message will be 
 *  displayed onscreen when you win the game as well.  The MartinRulesFrame (the GUI frame that shows the rules) explains the rules and regulations of
 *  the game as well.  All code throughout this project has been documented so that others may interpret and understand it.
 *  ----------------------MY ENHANCEMENTS------------------------
 *  MY ENHANCEMENT: After I finished the skeleton of my game, I added a few enhancements to the program.  One enhancement that I added was that
 *  if an illegal card is placed in the goal, it not only alerts you that it was an illegal move, but also moves it out of the goal to a random position
 *  on the screen. This was accomplished by creating random X and Y coordinates using the Math.Random class.  Another enhancement that I added to my game is
 *  the ability to store a high score.  This is a handy feature when playing many games in a row.  So if your score is higher than the stored score
 *  of 0 (each time the game is started), or the highest score from all of your previous games, your current score will be stored as the highest score.
 *  This is a nice feature because ut keeps track of your best score, and keeps the player engaged in the game because they will want to see if they can 
 *  beat their hghest score.  Lastly, I used a pop-up window each time that an illegal card was placed in the goal.  I thought this was a more effective
 *  approach because it forces the user to acknowlege it before continuing a game.  Just printing a string on the GUI would not have the same effect.
 *
 *  USES OF RANDOM: illegal cards moving to a new position from the goal, cards in the deck that are then displayed on screen each time a new
 *  game is initialized.
 *
 *Program Organization: The MartinCard class has been designed to extend, or be a subclass of the Card class provided by Dr. Klayder.  The
 *  MartinCard class contains additional constructors, methods that determine if the mouse is on top of one of the cards on the screen, a method
 *  to determine if a card is placed on top of the goal, as well as a method to determine if the card is a legal card that can be placed in the
 *  goal.  That method returns either true or false, true indicating that the card is of the legal type.
 *
 *  The MartinHighScore class is a basic Java class that creates a MartinHighScore Object, which houses the highest score for the session.  
 *  This class contains a default constructor, as well as a method that allows the computer to set the record high score, as well as return it.
 *  This is a very simple class, but it allows me to effectively store the highest score for the current session.
 *
 *  The MartinPanel class is a major component of my project.  This Java file contains the GUI interface that the user will see.  The GUI
 *  has several buttons: Start Game (press to get a deck of random cards to display on the frame), Show Rules (displays a pop-up frame that
 *  has a scrollable text field explaining the game, as well as all of the scoring and rules.), Quit Game (pressing this button closes the entire GUI
 *  window and terminates the game), and New Game (generates a new set of random cards on the screen and allows the user to start over on a new game).
 *  This program has several methods, such as: startNewGame (gets the program set-up to run a new game), checkForGameOver (which checks to see if all of the
 *  cards of legal type have been removed from the arrayList--if they have the game is stopped and there is a message printed on the screen indicating that
 *  the player has won the game), mousePressed (tells the computer that the user is dragging the mouse)/mouseDragged (allows the card to move with the coordinates
 *  of the mouse while dragging it)/mouseReleased (determines if the card is legal type and in the goal, if so it removes it from the arrayList.  If not,
 *  it gives it random coordinates on the screen, moving it out of the goal and displays a pop-up message that indicares the card is illegal), and paintComponent (draws
 *  all of the graphical components in the frame that the user sees--ex: the cards and goal).
 *
 *  The MartinRulesFrame class is designed to draw an addition pop-up frame when the "Show Rules" Button in MartinPanel is pressed.  This JFrame is designed
 *  to display all of the rules, scoring info, and information on how to win the game.  This class contains code that allows it to become visible when the button
 *  is pressed, and become invisible when the "close" button is pressed.  It is a pretty basic pane that contains a textArea with a scroll bar, allowing the 
 *  player to read all of the rules.
 *
 * URL to Google Project Folder: https://drive.google.com/open?id=1iiLIuCfOQTNyZvxLSDEa0F-sjxPwsNvc
 *
 */
package stuMartin;

import helperCards.Card;
import helperCards.MultiCard;
import helpers.Utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MartinPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener{ 
    //Width and Height of the Cards created in the helperCards package.
    public static final int width = 71;
    public static final int height = 96;
    //Number of cards that I want to include in my game and display in the GUI window.
    private final int num_cards_in_game = 25;
    //Creates a new NultiCard object called fullDeck.
    private MultiCard fullDeck;
    //Creates the currentList, which will serve as my deck in this game.
    private ArrayList < MartinCard > currentList;
    //Creates the variables x and y, which will be used as coordinate values for cards later in the code.  X is over and Y is down.
    private int x = 0, y = 0;
    //Boolean that lets us know if the player is dragging the mouse.
    private boolean nowDragging;
    //Variable pos will store the position in the currentList array (deck) of a targeted card.
    public int pos = -1;
    //Creates new font variables for my custom fonts.
    private Font myFont, highScoreFont;
    //Creates multiCard, a new object of class MultiCard
    private MultiCard multiCard;
    //Creates currentCard object, which is of type Card
    private Card currentCard;
    //Creates a variable called counter that counts the number of cards that have been displayed horizontally in row
    private int counter;
    //Creates a variable called rounds that counts the number of rounds, or times the rows horizontally have been drawn.
    private int rounds;
    //Variables that hold the player's current score and the highest score.
    private int currentScore, highScore;
    //Creates frameRules that will open the JFrame that contains the rules.
    private MartinRulesFrame frameRules;
    //Boolean variable startButtonPressed set to false, because the player hasn't pressed "Start Game" yet.
    private boolean startButtonPressed = false;

    public MartinPanel(){
        initComponents();
        setSize(700, 900);
        //Custom font
        myFont = new Font("Serif", Font.BOLD, 22);
        highScoreFont = new Font("Serif", Font.BOLD, 14);
        addMouseListener(this);
        addMouseMotionListener(this);
        multiCard = new MultiCard();
        //Gets a random value from 0-51 (which is the number of possivle cards, and each has a value)
        int randomCardIndex = Utility.getRandomFromTo(0, 51);
        //Pulls a card at the random position
        currentCard = multiCard.getCardAtIndex(randomCardIndex);
        //nowDragging = false because the player is not dragging the mouse yet.
        nowDragging = false;
        //Creates a new MultiCard deck
        fullDeck = new MultiCard();
        //Starts a new game
        startNewGame();
        //Sets up the rules frame
        frameRules = new MartinRulesFrame(this);
        frameRules.setVisible(false);
    }//end of MartinPanel constructor
    
    
        public void startNewGame(){
            currentList = new ArrayList <MartinCard>();
            //Counter = number of cards printed in a row horizontally in the GUI
            counter = 0;
            //Rounds = number of columns of cards printed in the GUI
            rounds = 1;
            //CurrentScore keeps track of the player's score
            currentScore = 0;
            for (int i = 0; i < num_cards_in_game; i++) {
                //Generates a random in between 0-51 (which matches the positions of the cards in the arrayList).
                int randomNum = Utility.getRandomFromTo(0, 51);
                Card randomCard = fullDeck.getCardAtIndex(randomNum);
                //Creates a new MartinCard object, which is a random card that will be displayed in the GUI
                MartinCard mc = new MartinCard(randomCard);
                if (counter < 10 && rounds == 1){
                    mc.setOver(40 + counter * 80);
                    mc.setDown(100);
                    counter++;
                }//end of if-statement that sets the first row of cards
                else if (counter == 10){
                    rounds ++;
                    counter = 0;
                }//end of else-if that checks to see if we are ready to display the 2nd row of cards
                if (counter < 10 && rounds == 2){
                    mc.setOver(40 + counter * 80);
                    mc.setDown(200);
                    counter++;
                }//end of if-statement that sets the second row of cards
                else if (counter == 10){
                    rounds ++;
                    counter = 0;
                }//end of else-if that checks to see if we are ready to display the 3rd row of cards
                if (counter < 5 && rounds == 3){
                    mc.setOver(40 + counter * 80);
                    mc.setDown(300);
                    counter++;
                }//end of if-statement that sets the third row of cards

                //adds the card to the arrayList currentList
                currentList.add(mc);   
            }//end of for-loop
    }//end of startNewGame()

        private void checkForGameOver(Graphics g){
        //Creates a new MartinHighScore object, which holds the highest score for the session.
        MartinHighScore recordScore = new MartinHighScore();
        //Checks to see if the card game is over.  The boolean gameOver is set to true, and is then tested with a for-loop to make sure that no Aces, Kings, Jacks, or odd
        //     numbered cards remain in the deck.  If one or more is found, the game still goes on and does not end.
        boolean gameOver = true;
        for (int i = 0; i < currentList.size(); i++){
            if (currentList.get(i).isValidType() == true){
                gameOver = false;
            }//end of if-statement that checks to make sure that no cards of the valid goal type are still in the ArrayList.
        }//end of for-loop that checks to ensure that the game is over
        
        if (gameOver==true){
            //Executes when the game has ended.
            //Checks to see if the user's score is higher than the high score record.  If it is, the user's score will be set as the value of highScore.
            if (currentScore > highScore){
                    highScore = currentScore;
                    //Sets the highest record score session to the MartinHighScore object recordScore
                    recordScore.setHighestScoreOnRecord(currentScore);
              }//end of if-statement

            //Displays a congratulations message indicating that the user has 'won' the game.
            //Sets the text color to white
            g.setColor (Color.WHITE);
            g.drawString("Congratulations, you won!! You sorted all of the cards!!", 40, 380);
            //Message that displays the scoring in the GUI for the user to see.
            g.drawString("Your Score: " + currentScore + " High Score for this session: " + highScore, 40, 400);
            }//end of if-statement
    }//end of checkForGameOver()
    @Override
    public void mouseClicked(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        System.out.println("mouseClicked    " + me.getX() + "    " + me.getY());
        repaint();
    }//end of mouseClicked()

    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("mousePressed    " + me.getX() + "    " + me.getY());
        x = me.getX();
        y = me.getY();
        for (int i = 0; i < currentList.size(); i++){
                MartinCard mcc = currentList.get(i);
                //Tests to see if the mouse pointer is in the card
                if (mcc.containsMousePoint(me.getX(), me.getY())){
                    //sets variable position to the position of the card in the arrayList.
                    pos = i;
                    //Displays the card that the player clicked on in the Java Console.
                    System.out.println("    just clicked on "+mcc.toString());
                    //sets the boolean nowDragging to true, indicating that the player is dragging the card in the GUI window.
                    nowDragging = true; 
                }//end of if-statement checking to see if the mouse is in a card
            }//end of for-loop
        repaint();
    }//end of mousePressed()
    
    @Override
    public void mouseReleased(MouseEvent me) {
        x = me.getX();
        y = me.getY();
        System.out.println("mouseReleased    " + me.getX() + "    " + me.getY());
        System.out.println("                        nowDragging  "+nowDragging);
        //sets boolean nowDragging to false, because the player has let go of the mouse, and is no longer dragging it.
        nowDragging = false;
        System.out.println("                        nowDragging  "+nowDragging);
        for (int i = 0; i < currentList.size(); i++){
                MartinCard mcc = currentList.get(i);
                //Checks to see if a card is in the Goal.
                if (mcc.containsGoal(me.getX(), me.getY())){
                    pos = i;
                    System.out.println("    just clicked on "+mcc.toString());
                    
                    //takes the card object that is in the goal and calls the isValidType() method in MartinCard to verify that the card is either odd, an Ace, King or Jack.
                    if (mcc.isValidType() == true){
                        //It's a valid card, so the player scored a goal.
                        System.out.println ("GOAL!!");
                        //Removes the card from the arrayList, so that it is no longer visible on the screen because it has been scored into the goal.
                        currentList.remove(i);
                        //Adds a point to the user's score.
                        currentScore++;
                    }//end of if-statement that checks to see if the card is a valid type.
        
                    else{
                        //Evaluates if the if-statment checking to see if the card is valid is false.  This means that an invalid card has been placed in the goal.
                        //Creates two random values that will be used as random coordinates.
                        int randomCoord1 = helpers.Utility.getRandomFromTo(70, 700);
                        int randomCoord2 = helpers.Utility.getRandomFromTo(70, 380);
                        //Gives the invalid card random coordinates so that the card is not in the goal anymore and moves it to another area in the playing field, indicating to the
                        //  user that this is an invalid goal.
                        mcc.setOver(randomCoord1);
                        mcc.setDown(randomCoord2);
                        //Displays a message in the Java Console explaining that the goal was invalid.
                        System.out.println("Invalid goal!!");
                        //Displays a pop-up window with an error message explaining to the player that this is not a valid goal.
                        JOptionPane.showMessageDialog(null, "Invalid move.  This card is not accepted.");
                        //Subtracts two points from the user's score as a penalty for placing the wrong card in the goal.
                        currentScore = currentScore - 2;
                    }//end of else
                }//end of if-statement that checks to see if the mouse is in any card on the screen.
        }//end of for-loop that sorts through every card in my deck (arrayList currentList)
    }//end of mouseReleased()

    @Override
    public void mouseEntered(MouseEvent me) {
        repaint();
        System.out.println("mouseEntered    " + me.getX() + "    " + me.getY());
    }//end of mouseEntered()

    @Override
    public void mouseExited(MouseEvent me) {
        System.out.println("mouseExited    " + me.getX() + "    " + me.getY());
    }//end of mouseExited()

    @Override
    public void mouseDragged(MouseEvent me) {
        x = me.getX();
        y = me.getY();
        System.out.println("mouseDragged    " + me.getX() + "    " + me.getY());
        //Checks to see if the player is dragging the mouse
        if (nowDragging==true){
            //The player is dragging the mouse (nowDragging is true),so we go to the deck (currentList) and then use the variable pos to find the correct card.  Then the
            //  setOver() and setDown() methods are called, setting the location of the card to where the mouse is dragging.  This is a loop until nowDragging is false, or
            //  when the player lets go of the mouse.
            currentList.get(pos).setOver(x);
            currentList.get(pos).setDown(y);
        }//end of if-statement

        //Updates/refreshes the GUI window.
        repaint();
    }//end of mouseDragged()

    @Override
    public void mouseMoved(MouseEvent me) {
        System.out.println("mouseMoved    " + me.getX() + "    " + me.getY());
    }//end of mouseMoved()
    
    @Override
    public void paintComponent(Graphics g) {
        //Calls the super class, or parent paintComponent method.
        super.paintComponent(g);
        //Sets the font color to a redish color.
        g.setColor(new Color(255,200,200));
        //Makes the background of the game.
        g.fillRect(0, 0, 1000, 1000);
        //Sets the font to myFont (the custom font that I created earlier in the program).
        g.setFont(myFont);
        
        //The Goal
        //Sets the color to a custom purple color
        g.setColor (new Color (123, 34, 212));
        //Fills a rectangle that will serve as the 'goal'.
        g.fillRect(500, 300, 290, 140);
        //Sets the color to Red
        g.setColor(Color.RED);
        //Titles and text for the GUI Window.
        g.drawString ("Bailey's CSC 220 Card Game", 250, 30);
        g.drawString ("Drag cards here", 570, 370);
        //Sets the font to the highScoreFont that I created earlier in the program.  It is the same font as all of the other text in the GUI, but it is smaller, as I needed it
        //  to fit in a small area in the bottom of the screen.
        g.setFont(highScoreFont);
        //Displays the record score.
        g.drawString ("Highest Record Score for this session: " + highScore, 300, 450);
    
            if (startButtonPressed == true){    //checks to see if the "Start Game" button has been pressed.
                //The "Start Game" button has been pressed by the player.  We can now display the deck of random cards in the GUI.
                for (int i = 0; i < currentList.size(); i++){
                    currentList.get(i).drawCard(g, this);
                }//end of for-loop      
            }//end of if
        //Updates/refreshes the GUI window.
        repaint();
        //Checks to see if the game is over.
        checkForGameOver(g);
    }//end of paintComponent()

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        rulesButton = new javax.swing.JButton();
        quitGameButton = new javax.swing.JButton();
        newGameButton = new javax.swing.JButton();

        startButton.setText("Start Game");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        rulesButton.setText("Show Rules");
        rulesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rulesButtonActionPerformed(evt);
            }
        });

        quitGameButton.setText("Quit Game");
        quitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitGameButtonActionPerformed(evt);
            }
        });

        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rulesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitGameButton)
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newGameButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newGameButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(rulesButton)
                    .addComponent(quitGameButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rulesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rulesButtonActionPerformed
        System.out.println("open frameOne");
        //Makes the MartinRulesFrame visible so that the player may read the rules of the game.
        frameRules.setLocation(this.getLocation());
        frameRules.setVisible(true);
    }//GEN-LAST:event_rulesButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        System.out.println ("The Game has been started");
        //sets the startButtonPressed Varibale to be true, allowing the dealt cards to be displayed on the screen.
        startButtonPressed = true;
    }//GEN-LAST:event_startButtonActionPerformed

    private void quitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitGameButtonActionPerformed
        //When the "Quit Game" Button is pressed, the entire GUI windows will be closed, similar to pressing the Red X button.
        System.exit(0);
    }//GEN-LAST:event_quitGameButtonActionPerformed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        //When the "New Game Button is pressed, the entire GUI window displays a new set of random cards, and resets the gameplay to a new game.
        startNewGame();
    }//GEN-LAST:event_newGameButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton quitGameButton;
    private javax.swing.JButton rulesButton;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}//end of MartinPanel.java