package com.garcialopez.metaheuristic.particleswarm;

import com.garcialopez.metaheuristic.FeasibilityRules;

/**
 *
 * @author adrian
 */
public class ProcessPSO {
    FeasibilityRules fr;
    public ProcessPSO() {
        this.fr = new FeasibilityRules();
    } //Close

    public double[] posGBestCurrent(double[][] induviduals, double[] gBest) {       
        
        
        //se elige el mejor de acuerdo a las reglas de factibilidad.
        for (double[] induvidual : induviduals) {
            gBest = this.fr.verifyFeasibility(induvidual, gBest);
        }        
        return gBest;

    } // close posGBest

} //Close class
