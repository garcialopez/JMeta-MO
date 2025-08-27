package com.garcialopez.pruebas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author admin
 */
public class Test2 {

    public static void main(String[] args) {

             Double[] a = {1.3,3.5, 6.5, 5.4,1.0};
             Double[] b = {1.3,3.5, 4.5, 5.4,0.0};
             Double[] c = {1.3,3.5, 7.5, 2.4,1.0};
             Double[] d = {1.3,3.5, 1.5, 5.4,0.0};
             
             List<Double[]> p = new ArrayList();
             
             p.add(a);
             p.add(b);
             p.add(c);
             p.add(d);
             
             
             Collections.sort(p, Comparator.comparingDouble(arr -> arr[2]));
             
             p.forEach(x -> System.out.println(Arrays.toString(x)));
             
             int aux = 0;
                Iterator<Double[]> iterator = p.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next()[4] == 1) {
                        iterator.remove();
                        aux--;
                    }
                    aux++;
                }
                System.out.println("");
             p.forEach(x -> System.out.println(Arrays.toString(x)));                                       
     
    }
    
}
