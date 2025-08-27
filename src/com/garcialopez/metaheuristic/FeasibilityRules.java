package com.garcialopez.metaheuristic;

import java.util.Arrays;

/**
 *
 * @author adrian
 */
public class FeasibilityRules {

    public double[] verifyFeasibility(double[] current, double[] previous) {

        double[] feasible = Arrays.copyOf(previous, previous.length);
        
        int posSVR = previous.length - 1; //posicion de la svr
        int posFO = posSVR - 1; //

        double svrCurrent = current[posSVR];
        double foCurrent = current[posFO];

        double svrPrevious = previous[posSVR];
        double foPrevious = previous[posFO];

        if (svrCurrent == 0 && svrPrevious == 0) {
            // Verificamos si el nuevo fitness es mejor que el fitness anterior del pbest
            if (foCurrent < foPrevious) {
                // Actualizar pbest con la nueva solución
                feasible = Arrays.copyOf(current, current.length);
            } // cierra if
        } //cierra if

        if (svrCurrent > 0 && svrPrevious > 0) {
            // Verificamos si el nuevo svr es mejor que el svr anterior del pbest
            if (svrCurrent < svrPrevious) {
                // Actualizar pbest con la nueva solución
                feasible = Arrays.copyOf(current, current.length);
            } // cierra if
        } //cierra if

        if (svrCurrent == 0 && svrPrevious > 0) {
            feasible = Arrays.copyOf(current, current.length);
        } // cierra if

        return feasible;

    } //close verifyFeasibility

} //close class
