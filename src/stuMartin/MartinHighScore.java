/*MartinHighScore.java
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
 *
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

public class MartinHighScore {
    int highestScoreOnRecord = 0;
    
    public MartinHighScore(){
        //default constructor
    }//default constructor
    
    public void setHighestScoreOnRecord(int hs){
        //sets the new high score
        highestScoreOnRecord = hs;
    }//end of setHighestScoreOnRecord()
    
    public int getHighestScoreOnRecord(){
        //returns the high score as an int
        return highestScoreOnRecord;
    }//end of getHighestScoreOnRecord()
}//end of MartinHighScore