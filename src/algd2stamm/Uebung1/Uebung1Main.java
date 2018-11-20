/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algd2stamm.Uebung1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author santi
 */
public class Uebung1Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Integer> s1 = new LinkedList<>();
        s1.add(1);
        s1.add(1);
        s1.add(0);
        s1.add(1);
        s1.add(0);
        s1.add(1);
        LinkedList<Integer> s2 = new LinkedList<>();
        s2.add(0);
        s2.add(0);
        s2.add(1);
        s2.add(1);
        s2.add(0);
        s2.add(1);
        Kopfbahnhof<Integer> temp = new Kopfbahnhof<>(s1, s2, 0, 1);
        temp.Sort();
        temp.Print();

        System.out.println("StackComputer");
        StackComputer("1 9 1 - 3 1 + / +");
    }

    public static void StackComputer(String s) {
        List<Integer> li = new ArrayList<>();
        for (char o : s.toCharArray()) {
            switch (o) {
                case ' ':
                    break;
                case '+':
                    li.set(li.size() - 2, li.get(li.size() - 2) + li.get(li.size() - 1));
                   li.remove(li.size() - 1);
                    break;
                case '-':
                    li.set(li.size() - 2, li.get(li.size() - 2) - li.get(li.size() - 1));
                    li.remove(li.size() - 1);
                    break;
                case '/':
                    li.set(li.size() - 2, li.get(li.size() - 2) / li.get(li.size() - 1));
                     li.remove(li.size() - 1);
                    break;
                default:
                    li.add(Character.getNumericValue(o));
                    break;
            }
        }
        li.forEach(n -> System.out.println(n));
    }
}
