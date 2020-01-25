/*
 * Amanda Hegidus
 * 21 February 2018
 * CSC 220

makes a Cat object with a name.
 */
package stuHegidus;

public class HegidusCat {
    private String catname;
    
    public HegidusCat(){
        catname="adorable";
    }//end of default constructor
    public HegidusCat(String name){
        catname=name;
    }//end of constructor
    public void setName(String name){
        catname=name;
    }//end of setter
    public String getName(){
        return catname;
    }//end of getter
    @Override
    public String toString(){
        return "A cat named "+catname;
    }//end of toString override
}
