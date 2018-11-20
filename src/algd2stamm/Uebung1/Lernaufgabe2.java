/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algd2stamm.Uebung1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.binding.MapBinding;

/**
 *
 * @author santi
 */
public class Lernaufgabe2 {
      public static void main(String[] args) throws FileNotFoundException {
        Lernaufgabe2 l=new Lernaufgabe2();
        l.CreateHashMap();
      }

    public Lernaufgabe2() {
    }

    Map<String, Counter> elemente = new HashMap<>();

    public void CreateHashMap() throws FileNotFoundException {
        for (String wort : ReadTextFile()) {
            if (elemente.containsKey(wort)) {
                elemente.get(wort).increment();

            } else {
                elemente.put(wort, new Counter());
                elemente.get(wort).increment();
            }
        }
        
        elemente.values().forEach(s-> System.out.println(s.counter));
    }

    public List<String> ReadTextFile() throws FileNotFoundException {
        List<String> zeilen = new ArrayList<>();
        List<String> woerter = new ArrayList<>();
        String[] temp;
        BufferedReader reader = new BufferedReader(new FileReader("F:\\FileSharing\\OneDrive\\FH\\Algd2\\Uebungen\\Uebung1\\MapDoc.txt"));
        //reader.lines().forEach((s)-> {temp=s.split( @"\W+");});

        reader.lines().forEach(s -> zeilen.add(s));
        for (String line : zeilen) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                woerter.add(word);
            }
        }
        return woerter;
    }

    private class Counter {

        public int counter = 0;

        public Counter() {
        }

        public void increment() {
            counter++;
        }
        

    }
}
