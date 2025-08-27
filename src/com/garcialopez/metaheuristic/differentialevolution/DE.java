package com.garcialopez.metaheuristic.differentialevolution;

import com.garcialopez.metaheuristic.MetaheuristicBase;
import com.garcialopez.metaheuristic.NRandom;
import com.garcialopez.metaheuristic.Population;
import com.garcialopez.optimizationmodel.CNOP;
import java.util.Arrays;

/**
 *
 * @author admin
 */
public class DE extends MetaheuristicBase {
  
    private final CNOP cnop;
    private final boolean debug;
    private ProcessDE processDE;
    private NRandom nrandom;
    private double[][] individuals;

    //population
    private int NP = 30;

    //Crossover [0 - 1]
    private double CR = 0.7;

    //Mutation [0 - 1]
    private double F = 0.62;

    public DE(CNOP cnop, boolean debug) {
        this.cnop = cnop;
        this.debug = debug;
        this.processDE = new ProcessDE();
        this.nrandom = new NRandom();
        this.setGmax(500);
    } // DE close
    
     /**
     * @return the NP
     */
    public int getNP() {
        return NP;
    }

    /**
     * @param NP the NP to set
     */
    public void setNP(int NP) {
        this.NP = NP;
    }

    /**
     * @return the CR
     */
    public double getCR() {
        return CR;
    }

    /**
     * @param CR the CR to set
     */
    public void setCR(double CR) {
        this.CR = CR;
    }

    /**
     * @return the F
     */
    public double getF() {
        return F;
    }

    /**
     * @param F the F to set
     */
    public void setF(double F) {
        this.F = F;
    }

    @Override
    public void run() {
        // Validate if the number of iterations is between [1 - 30].
        if (this.getExecutions() >= 1 && this.getExecutions() <= 30) {

            this.iniResults();

            for (int e = 0; e < this.getExecutions(); e++) {

                // The bacteria population is initialized.
                this.individuals = null;
                double[][] temp = null;
                temp = Population.startPopulation(this.cnop, this.getNP(), false);
                this.individuals = Arrays.copyOf(temp, temp.length);

                // The objective function and constraints are evaluated, and assigned to the population matrix.
                temp = null;
                temp = cnop.evaluateObjectiveFunction(this.individuals);
                this.individuals = Arrays.copyOf(temp,temp.length);

                for (int g = 0; g < this.getGmax(); g++) {

                    for (int i = 0; i < this.getNP(); i++) {

                        //prepararnos para la mutacion o recombinacion
                        //seleccion de 3 individuos aleatorios
                        int[] ind = this.processDE.randomPositions(this.getNP(), i);

                        //instaciamos el vector hijo
                        double[][] hijo = new double[1][this.individuals[i].length];

                        //iteramos cada dimensión D
                        for (int j = 0; j < this.cnop.getNumberVariable(); j++) {

                            //generamos un número aleatorio
                            double rand = this.nrandom.getRandomUnif();

                            if (rand < this.getCR()) { //p cruza controla la recombinación   
                                                                
                                if (this.cnop.isContinuousVariable()[j]) {
                                    hijo[0][j] = processDE.mutation(this.individuals[ind[0]][j], this.individuals[ind[1]][j], this.individuals[ind[2]][j], this.getF());
                                } else {
                                    hijo[0][j] = (int) processDE.mutation(this.individuals[ind[0]][j], this.individuals[ind[1]][j], this.individuals[ind[2]][j], this.getF());
                                } // else close

                                //verificamos los rangos de cada variable de acuerdo a la mutación
                                if ((hijo[0][j] < this.cnop.getVariableRange()[j][0]) || (hijo[0][j] > this.cnop.getVariableRange()[j][1])) {

                                    if (this.cnop.isContinuousVariable()[j]) {
                                        hijo[0][j] = this.individuals[i][j];
                                    } else {
                                        hijo[0][j] = (int) this.individuals[i][j];
                                    } //else close

                                }// if close

                            } else { //no se mutan
                                hijo[0][j] = this.individuals[i][j];
                            } // else close

                        } // for d dimension close

                        //evaluar hijo
                        temp = null;
                        temp = cnop.evaluateObjectiveFunction(hijo);
                        hijo = Arrays.copyOf(temp, temp.length);
                        
                        //comparando hijo vs padre y reemplazar en la poblacion
                        if ((hijo[0][this.cnop.getNumberVariable() + 1] == 0) && (this.individuals[i][this.cnop.getNumberVariable() + 1] == 0)) {

                            if (hijo[0][this.cnop.getNumberVariable()] < this.individuals[i][this.cnop.getNumberVariable()]) {
                                //copiamos todo el hijo por ser mejor
                                this.individuals[i] = Arrays.copyOf(hijo[0], hijo[0].length);
                            } // if close

                        }// if close

                        if ((hijo[0][this.cnop.getNumberVariable() + 1] > 0) && (this.individuals[i][this.cnop.getNumberVariable() + 1] > 0)) {
                            if (hijo[0][this.cnop.getNumberVariable()] < this.individuals[i][this.cnop.getNumberVariable()]) {
                                //copiamos todo el hijo por ser mejor
                                this.individuals[i] = Arrays.copyOf(hijo[0], hijo[0].length);
                            }
                        }//cierra if

                        if ((hijo[0][this.cnop.getNumberVariable() + 1] == 0) && (this.individuals[i][this.cnop.getNumberVariable() + 1] > 0)) {
                            //copiamos todo el hijo por ser mejor
                            this.individuals[i] = Arrays.copyOf(hijo[0], hijo[0].length);
                        }//cierra if

                    } //for NP close

                    //ordenamos los individuos
                    temp = null;
                    temp = Population.sortPopulation(this.individuals);
                    this.individuals = Arrays.copyOf(temp, temp.length);
                    //hacer cosas depues de cada generacion
                } // for close gmax

                if (this.getExecutions() > 1) {
                    this.addBestResults(this.individuals[0]);
                } else {

                    for (double[] individual : this.individuals) {
                        this.addBestResults(individual);
                    } //for close

                }

            } // for executions close

            if (debug) {
 //               System.out.println("Final Results.\n" + this.getDescription(this.getBestResults(), cnop.getNumberVariable()));
            }

        } else {
            System.err.println("The number of iterations should be between [1 - 30.]");
        }// close else
    } //run close

} // MetaheuristicBase close
