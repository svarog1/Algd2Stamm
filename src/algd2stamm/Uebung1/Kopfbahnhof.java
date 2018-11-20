package algd2stamm.Uebung1;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author santi
 */
public class Kopfbahnhof<T> {

    LinkedList<T> s1;
    LinkedList<T> s2;
    LinkedList<T> s3;
    T values1;
    T values2;

    public Kopfbahnhof(LinkedList<T> s1, LinkedList<T> s2, T values1, T values2) {
        this.s1 = s1;
        this.s2 = s2;
        s3 = new LinkedList<T>();
        this.values1 = values1;
        this.values2 = values2;
    }

    public void Sort() {
        {
            Iterator<T> it = s1.iterator();
            while (it.hasNext()) {
                T element = (T) it.next();
                if (element != values1) {

                } else {
                    s2.add(element);
                    it.remove();
                }
            }
        }
        {
            Iterator<T> it = s2.iterator();
            while (it.hasNext()) {
                T element = (T) it.next();
                if (element != values2) {

                } else {
                    s1.add(element);
                    it.remove();
                }
            }
        }
    }

    public void Print() {
        System.out.println("s1");
        for (T element : s1) {
            System.out.println(element.toString());
        }

        System.out.println("s2");
        for (T element : s2) {
            System.out.println(element.toString());
        }

    }

}
