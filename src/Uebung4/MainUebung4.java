/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uebung4;

/**
 *
 * @author santi
 */
public class MainUebung4 {
    
    public static void main(String[] args) {
    SkipList<Integer> skp=new SkipList<>(5);
    
        for (int i = 0; i < 20; i++) {
            skp.add(i, (int)Math.random()*100);
            if (i>17) {
                System.out.println("Uebung4.MainUebung4.main()");
            }
        }
        
        skp.remove(13);
    
    
    }
    
}
