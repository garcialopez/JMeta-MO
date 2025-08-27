package com.garcialopez.metaheuristic.particleswarm;

import com.garcialopez.metaheuristic.*;
import com.garcialopez.optimizationmodel.CNOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author admin
 */
public class PSO extends MetaheuristicBase {

    private final CNOP cnop;   // modelo del problema de optimización
    private final boolean debug;
    private NRandom nrandom;
    private FeasibilityRules fr;

    int progress = 0;

    private double[][] xMat;
    private List<Double[]> matriz;
    private List<Double[]> copia;
    List<List<Double[]>> resultFinal;
    List<Double> timesSeconds = new ArrayList();

    private int NP = 50;          // Número de partículas
    private double w = 0.729844;       // Inercia
    private double c1 = 1.496180;       // Coeficiente cognitivo
    private double c2 = 1.496180;       // Coeficiente social

    // Matrices de posiciones y velocidades de las partículas
    private double[][] x; //posiciones
    private double[][] v; //velocidades
    private double[][] pBest; //mejor posición personal
    private double[] gBest; //mejor posición global

    public PSO(CNOP cnop, boolean debug) {
        this.resultFinal = new ArrayList();
        this.fr = new FeasibilityRules();
        this.cnop = cnop;
        this.debug = debug;
        this.nrandom = new NRandom();

    }

    @Override
    public void run() { // Ejecutar el algoritmo PSO

        // Validate if the number of iterations is between [1 - 30].
        if (this.getExecutions() >= 1 && this.getExecutions() <= 30) {

            System.out.println("Starting PSO algorithm.");
            ProcessPSO processPSO = new ProcessPSO(); //clase externa

            //obtener el número de objetivos y variables
            int numberObjective = cnop.getObjetives().length;
            int numberVar = cnop.getNumberVariable();

            for (int e = 0; e < this.getExecutions(); e++) { //inicia el ciclo de iteraciones

                long startTime = System.nanoTime();

                double[][] temp = null;

                // ---------------- - - - - inicializar las posiciones aleatorias                                

                //---------------- INICIAMOS POBLACIÓN ---------------- -----------------------------
                double[][] auxStart = Population.startPopulation(this.cnop, this.getNP(), false);
                this.x = new double[auxStart.length][auxStart[0].length];
                for (int ci = 0; ci < auxStart.length; ci++) {
                    System.arraycopy(auxStart[ci], 0, this.x[ci], 0, auxStart[ci].length);
                }
                //-------------------------------------------------------------------------------------

                this.v = new double[x.length][x[0].length];

                //inicializamos la velocidad de las particulas en 0
                for (int i = 0; i < v.length; i++) {
                    //inicia el 0, solo hasta el numero de variables
                    //ya que v y x tienen las mismas dimensiones
                    for (int j = 0; j < this.cnop.getNumberVariable(); j++) {
                        this.v[i][j] = 0.0;
                    }
                } //close for

                //Evaluar la función objetivo para cada partícula.
                //---------------- EVALUAMOS ---------------- -----------------------------
                this.cnop.evaluateObjectiveFunction(this.x);
                //-------------------------------------------------------------------------------------

                //------- GENERAMOS LA MEJOR MARCA PERSONAL ---------------- --------------------------
                //COMO ES LA PRIMERA VEZ, LA MEJOR MARCA PERSONAL ES LA POSICIÓN ACTUAL
                //COPIAMOS PROFUNDO x en pBest
                this.pBest = new double[this.x.length][this.x[0].length];
                for (int i = 0; i < this.x.length; i++) {
                    pBest[i] = Arrays.copyOf(this.x[i], this.x[i].length);
                } //close for

                //---------------- COPIAMOS x a xMat---------------- -----------------------------
                //generamos una copia de individual
                this.xMat = new double[x.length][x[0].length];

                for (int ci = 0; ci < x.length; ci++) {
                    System.arraycopy(x[ci], 0, xMat[ci], 0, x[ci].length);
                }
                //--------------- ORDENAMOS xMat---------------- -----------------------------
                Population.sortPopulationMO(this.xMat, cnop);
                //-------------------------------------------------------------------------------------

                //----------- BUSCAMOS LA MEJOR MARCA GLOBAL ---------------- --------------------------
                //Pero como xMat es una matriz ordenada por svr, la mejor marca global es la primera
                // entonces la posicion 0 de xMat es la mejor marca global y la copiamos
                //profundo en gBest
                this.gBest = Arrays.copyOf(this.xMat[0], this.xMat[0].length);

                matriz = new ArrayList();
                //Copia de la matriz
                copia = new ArrayList();

                //-------------------------------------------------------------------------------------
                // Contar cuántos individuos cumplen con la condición de svr = 0
                int counter = MOProcess.counterSvr_0(xMat);
                //-------------------------------------------------------------------------------------

                if (counter > 0) { //si hay svr = 0
                    // Inicializar la matriz de svr = 0
                    MOProcess.getIndividualsSvr0(xMat, counter, numberVar, numberObjective, matriz, true);
                    //-------------------------------------------------------------------------------------

                    //ordenamos por fx1
                    MOProcess.sortFx(matriz, numberVar);

                    //dominancia
                    MOProcess.dominance(matriz, cnop);
                    MOProcess.crowding(matriz, cnop);
                    //ordenamos por crowding
                    int siz = matriz.get(0).length - 1;
                    MOProcess.sortFx(matriz, siz);

                    // Recorrer la matriz original y copiar cada array
                    MOProcess.copyMat(matriz, copia);

                }
                //-------------------------------------------------------------------------------------

                // contadores auxiliares
                int count = this.getNP();
                this.setGmax(0);

                // Calculate current evaluations for the stopping condition
                while (count <= this.getEvaluations()) {

                    //Generation increment
                    this.incrementGmax(1);

                    // para cada particula
                    for (int i = 0; i < this.getNP(); i++){
                        //Ajustar la velocidad y posicion de acuerdo con las ecuaciones.
                        //iteramos cada dimensión D

                        double[][] particulas = new double[1][this.x[i].length];

                        for(int d = 0; d < this.cnop.getNumberVariable(); d++){
                            //extraemos partes de la formula de posicion y velocidad
                            double vAux = this.v[i][d]; //velocidad actual
                            double xAux = this.x[i][d]; //posicion actual
                            double pb = this.pBest[i][d]; //mejor posicion personal
                            double gb = this.gBest[d]; //mejor posicion global
                            double r1 = this.nrandom.getRandomUnif(); //r1 es un numero aleatorio para la ecuacion que ayuda a moverse
                            double r2 = this.nrandom.getRandomUnif(); //r2 es un numero aleatorio para la ecuacion que ayuda a moverse

                            //formula de velocidad
                            double newV = w * vAux + c1 * r1 * (pb - xAux) + c2 * r2 * (gb - xAux);

                            //actualizamos la velocidad
                            this.v[i][d] = newV;

                            //formula de posicion
                            double newX = xAux + newV;

                            //verificamos si la nueva posicion es valida
                            if (newX < cnop.getVariableRange()[d][0]) {
                                newX = (cnop.getVariableRange()[d][0] * 2.0 - newX);
                            }
                            if (newX > cnop.getVariableRange()[d][1]) {
                                newX = (cnop.getVariableRange()[d][1] * 2.0 - newX);
                            }
                            if (newX < cnop.getVariableRange()[d][0] || newX > cnop.getVariableRange()[d][1]) {
                                newX = this.nrandom.getRandomRankUnif(cnop.getVariableRange()[d][0], cnop.getVariableRange()[d][1]);
                            }

                            //actualizamos la posicion
                            particulas[0][d] = newX;
                        } //close for d- dimensiones

                        //evaluamos la nueva posicion
                        this.cnop.evaluateObjectiveFunction(particulas);

                        //remplazamos x, por las nuevas particulas, copia profunda
                        for (int j = 0; j < this.x[i].length; j++) {
                            this.x[i][j] = particulas[0][j];
                        }

                        //actualizamos la mejor marca personal
                        // si la nueva posicion es mejor que la anterior

                        //evaluamos por svr
                        int pSvr = x[i].length-1; //posicion de la suma de violacion de restricciones
                        int pFO1 = pSvr - 1; //posicion de la fx1
                        int pFO2 = pFO1 - 1; //posicion de la fx2

                        boolean comparacion1FO1 = CNOP.compare(this.x[i][pFO1], this.pBest[i][pFO1], cnop.getObjetives()[0][1]);
                        boolean comparacion2FO2 = CNOP.compare(this.x[i][pFO2], this.pBest[i][pFO2], cnop.getObjetives()[1][1]);

                        //verificamos si la nueva posicion es mejor que la anterior
                        if (this.x[i][pSvr] == 0 && this.pBest[i][pSvr] == 0) {
                            //si la nueva posicion es mejor que la anterior

                            if (comparacion1FO1 && comparacion2FO2) {
                                //actualizamos la mejor marca personal
                                for (int j = 0; j < this.x[i].length; j++) {
                                    this.pBest[i][j] = this.x[i][j];
                                }//close for
                            }//close if
                        }else if (this.x[i][pSvr] < this.pBest[i][pSvr]) {
                            //si la nueva posicion es mejor que la anterior
                            for (int j = 0; j < this.x[i].length; j++) {
                                this.pBest[i][j] = this.x[i][j];
                            }//close for
                        }//close if

                        //actualizamos la mejor marca global
                        // si la nueva posicion es mejor que la anterior
                        if (this.x[i][pSvr] == 0 && this.gBest[pSvr] == 0) {
                            //si la nueva posicion es mejor que la anterior
                            if (comparacion1FO1 && comparacion2FO2) {
                                //actualizamos la mejor marca global
                                for (int j = 0; j < this.x[i].length; j++) {
                                    this.gBest[j] = this.x[i][j];
                                }//close for
                            }//close if
                        }else if (this.x[i][pSvr] < this.gBest[pSvr]) {
                            //si la nueva posicion es mejor que la anterior
                            for (int j = 0; j < this.x[i].length; j++) {
                                this.gBest[j] = this.x[i][j];
                            }//close for
                        }//close if
                    } //close for i - iteraciones de las particulas

                    //---------------- COPIAMOS ---------------- -----------------------------
                    //generamos una copia de individual
                    this.xMat = new double[x.length][x[0].length];

                    for (int ci = 0; ci < x.length; ci++) {
                        System.arraycopy(x[ci], 0, xMat[ci], 0, x[ci].length);
                    }

                    //se ordena
                    Population.sortPopulationMO(this.xMat, cnop);
                    //-------------------------------------------------------------------------------------

                    matriz = new ArrayList();

                    //-------------------------------------------------------------------------------------
                    counter = MOProcess.counterSvr_0(x);
                    //-------------------------------------------------------------------------------------
                    // Inicializar la matriz
                    MOProcess.getIndividualsSvr0(xMat, counter, numberVar, numberObjective, matriz, true);
                    //-------------------------------------------------------------------------------------

                    //copiar a la matriz el archivo externo pasado
                    MOProcess.copyMat(copia, matriz);

                    //-------------------------------------------------------------------------------------
                    if (counter > 0) { //si hay svr = 0
                        //ordenamos por fx1
                        MOProcess.sortFx(matriz, numberVar);
                        //dominancia
                        MOProcess.dominance(matriz, cnop);
                        MOProcess.crowding(matriz, cnop);
                        //ordenamos por crowding
                        if (!matriz.isEmpty()) {
                            int siz = matriz.get(0).length - 1;
                            MOProcess.sortFx(matriz, siz);
                        }
                        // Recorrer la matriz original y copiar cada array
                        //vaciar copia
                        copia.clear();
                        MOProcess.copyMat(matriz, copia);
                    }

                    count += this.getNP();

                } //close while

                //aplicamos dominancia
                MOProcess.dominance(matriz, cnop);
                //guardar copia de cada iteracion en una lista
                this.addresultFinal(copia);

                //imprimir copia

              /*  System.out.println("Iteración: " + e);
                for (Double[] array : copia) {
                    for (Double value : array) {
                        System.out.print(value + " ");
                    }
                    System.out.println();
                }
*/

                //limpiamos copia
                copia.clear();
                progress = (int) ((e + 1) * 100 / this.getExecutions());

                long endTime = System.nanoTime(); // Tiempo después de finalizar el bucle
                long duration = endTime - startTime; // Duración en nanosegundos

                // Convertir a segundos
                double durationInSeconds = duration / 1_000_000_000.0;

                timesSeconds.add(durationInSeconds);

                System.out.println(count);

            } //close for ex

            if (debug) {
 //               System.out.println("Final Results.\n" + this.getDescription(this.getBestResults(), cnop.getNumberVariable()));
            }
        } else {
            System.err.println("The number of iterations should be between [1 - 30.]");
        }
        System.out.println("End PSO algorithm.");
    } // close run

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
     * @return the w
     */
    public double getW() {
        return w;
    }

    /**
     * @param w the w to set
     */
    public void setW(double w) {
        this.w = w;
    }

    /**
     * @return the c1
     */
    public double getC1() {
        return c1;
    }

    /**
     * @param c1 the c1 to set
     */
    public void setC1(double c1) {
        this.c1 = c1;
    }

    /**
     * @return the c2
     */
    public double getC2() {
        return c2;
    }

    /**
     * @param c2 the c2 to set
     */
    public void setC2(double c2) {
        this.c2 = c2;
    }

    public void addresultFinal(List<Double[]> copia) {

        List<Double[]> copiaAux = new ArrayList();
        for (Double[] arrayOriginal : copia) {
            Double[] arrayCopia = new Double[arrayOriginal.length];
            System.arraycopy(arrayOriginal, 0, arrayCopia, 0, arrayOriginal.length);
            copiaAux.add(arrayCopia);
        }
        resultFinal.add(copiaAux);
    }

    public int getProgress() {
        return progress;
    }
    public List<List<Double[]>> getResultFinal() {
        return resultFinal;
    }

    public List<Double> getTimesSeconds(){
        return timesSeconds;
    }
/*
    public double calculateHypervolume(List<Double[]> copia) {
        double hypervolume = 0.0;
        double puntoRef = 1.0; // Cambiado a 1.0 para la normalización

        if (!copia.isEmpty()) {
            int nCol =  copia.get(0).length;
            int nObj2 = nCol - 2 - 1; //3
            int nObj1 = nObj2 - 1; //2

            // Encuentra los valores máximos para cada objetivo
            double maxObj1 = copia.stream().mapToDouble(sol -> sol[nObj1]).max().orElse(1.0);
            double maxObj2 = copia.stream().mapToDouble(sol -> sol[nObj2]).max().orElse(1.0);

            for (int i = 0; i < copia.size() - 1; i++) {
                Double[] actual = copia.get(i);
                Double[] siguiente = copia.get(i + 1);

                // Normaliza los valores de los objetivos
                double normActualObj1 = actual[nObj1] / maxObj1;
                double normActualObj2 = actual[nObj2] / maxObj2;
                double normSiguienteObj1 = siguiente[nObj1] / maxObj1;

                hypervolume += (normActualObj1 - normSiguienteObj1) * (puntoRef - normActualObj2);
            }

            Double[] ultimo = copia.get(copia.size() - 1);

            // Normaliza los valores de los objetivos
            double normUltimoObj1 = ultimo[nObj1] / maxObj1;
            double normUltimoObj2 = ultimo[nObj2] / maxObj2;

            hypervolume += normUltimoObj1 * (puntoRef - normUltimoObj2);
        }

        return hypervolume;
    }

    */
public double calculateHypervolume(List<Double[]> copia) {
    double hypervolume = 0.0;
    double puntoRef = 1.0; // Reference point for normalization

    if (!copia.isEmpty()) {
        int nCol = copia.get(0).length;
        int nObj2 = nCol - 2 - 1; // Index for the second objective
        int nObj1 = nObj2 - 1; // Index for the first objective

        // Find the maximum values for each objective
        double maxObj1 = copia.stream().mapToDouble(sol -> sol[nObj1]).max().orElse(1.0);
        double maxObj2 = copia.stream().mapToDouble(sol -> sol[nObj2]).max().orElse(1.0);

        for (int i = 0; i < copia.size() - 1; i++) {
            Double[] actual = copia.get(i);
            Double[] siguiente = copia.get(i + 1);

            // Normalize the objective values
            double normActualObj1 = actual[nObj1] / maxObj1;
            double normActualObj2 = actual[nObj2] / maxObj2;
            double normSiguienteObj1 = siguiente[nObj1] / maxObj1;

            // Ensure normalized values are within [0, 1]
            normActualObj1 = Math.min(Math.max(normActualObj1, 0.0), 1.0);
            normActualObj2 = Math.min(Math.max(normActualObj2, 0.0), 1.0);
            normSiguienteObj1 = Math.min(Math.max(normSiguienteObj1, 0.0), 1.0);

            hypervolume += (normActualObj1 - normSiguienteObj1) * (puntoRef - normActualObj2);
        }

        Double[] ultimo = copia.get(copia.size() - 1);

        // Normalize the objective values
        double normUltimoObj1 = ultimo[nObj1] / maxObj1;
        double normUltimoObj2 = ultimo[nObj2] / maxObj2;

        // Ensure normalized values are within [0, 1]
        normUltimoObj1 = Math.min(Math.max(normUltimoObj1, 0.0), 1.0);
        normUltimoObj2 = Math.min(Math.max(normUltimoObj2, 0.0), 1.0);

        hypervolume += normUltimoObj1 * (puntoRef - normUltimoObj2);
    }

    return hypervolume;
}

} // class close
