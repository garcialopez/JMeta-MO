package com.garcialopez.metaheuristic.differentialevolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProcessDE {

    public ProcessDE() {
    } //method close

    /**
     * <strong>Random selection without replacement</strong><br>
     * Generates an array of three random positions within the specified upper
     * limit, excluding a specific position.
     *
     * @param upperLimit The upper limit for generating random positions
     * (exclusive).
     * @param exclude The position to be excluded from the generated positions.
     * @return An array of three unique random positions within the range [0,
     * upperLimit - 1], excluding 'exclude'.
     */
    public int[] randomPositions(int upperLimit, int exclude) {
        // We create a list of possible positions
        List<Integer> positions = new ArrayList();
        for (int i = 0; i < upperLimit; i++) {
            if (i != exclude) {
                positions.add(i);
            } // if close
        } // for close
        
        // We randomly shuffle the positions
        Collections.shuffle(positions);
        
        //We take the first three positions in the list        
        return new int[]{positions.get(0), positions.get(1), positions.get(2)};
    } // randomPositions close
    
    public double mutation(double r1, double r2, double r3, double F){        
        return r1 + F * (r2 - r3);
    } // mutation close

} // class close
