/*
Name: Jacob Sanford
Date: 3/27/2018
Description: Creates a thread that run a clock that counts down.
            This is the main updater also for the paint mechanic.
*/
package stuSanford;
        
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class SanfordClock implements Runnable{
    private int milSecond;
    private int minute;
    private int second;
    private SanfordPanel panel;
    private volatile Thread timer;
    
    public SanfordClock(SanfordPanel panel){
        this.panel = panel;
    }
    
    /**
     * Checks the time for errors with formating, used to keep time measurements accurate
     */
    public void checkTime(){
        if(second <= 0 && minute <= 0 && milSecond <= 0){
            panel.setInGame(false);
            timer = null;
        }
        else{
            if(second <= 0 && minute >= 1 && milSecond <= 0){
                second = 59;
                milSecond = 999;
                minute--;
            } 
            if(second >= 1 && milSecond <= 0){
                milSecond = 999;
                second--;
            }
        }
    }
    /**
     * This starts the thread up
     */
    public void start() {
        if (timer == null) {
            timer = new Thread (this);
            timer.start();
            System.out.println("stuSanford.SanfordClock.java: Thread started");
        } // end if
    } // end of start()
    /**
     * This is the main thread and the process it uses to make the game work
     */
    @Override
    public void run(){
        Thread thisThread = Thread.currentThread();
        while(timer == thisThread && (second > 0 || minute > 0 || milSecond > 0)){
            milSecond--;
            checkTime();
            panel.callRepaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println("stuSanford.SanfordClock.java: Thread did not sleep");
            }
        }
        System.out.println("stuSanford.SanfordClock.java: Thread stopped");
    }
    /**
     * Formats time and returns it as a string
     * @return 
     */
    public String getTime(){
        String time;
        time = "";
        if(minute >= 1){
        time += minute + ":";
        }
        if(second <= 10){
            time += "0" + second;
        }
        else{
            time += second;
        }
        if(minute < 1){
            if(milSecond <= 10){
                time += ":00" + milSecond;
            }
            else if(milSecond <= 100){
                time += ":0" + milSecond;
            }
            else{
                time += ":" + milSecond;
            }
        }
        return time;
    }
    /**
     * Sets time to a 1:31 seconds
     */
    public void resetTime(){
        minute = 1;
        second = 30;
        milSecond = 0;
    }
}
