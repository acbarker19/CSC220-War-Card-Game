/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stuCarper;

/**
 *
 * @author carperaj2021
 */
public class CarperCat {
    private String name;
    
    public CarperCat(String n){
        name = n;
    }
    
    public CarperCat(){
        name = "???";
    }
    
    public String toString(){
        String ans = "This is a cat named "+ name;
        return ans;
    }
    
    public void soutToString(){
        System.out.println(toString());
    }
    
    
    
    
}
