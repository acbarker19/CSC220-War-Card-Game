/* 
 * Faith Haiss
 * February 21st, 2018
 * CSC 220
 *
 * Cat class made for testing purposes in the project's early stages.
 */

package stuHaiss;

/*
 * Begin HaissCat
 */
public class HaissCat {
    
    private String name;
    
    //Constructor
    public HaissCat(String n){
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String n) {
        name = n;
    }
    
    public String toString() {
        String answer;
        answer = "A cat named "+ name + ".";
        return answer;
    }
}