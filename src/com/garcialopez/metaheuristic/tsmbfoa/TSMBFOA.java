package com.garcialopez.metaheuristic.tsmbfoa;

import com.garcialopez.metaheuristic.MOProcess;
import com.garcialopez.metaheuristic.MetaheuristicBase;
import com.garcialopez.metaheuristic.Population;
import com.garcialopez.optimizationmodel.CNOP;

import java.util.ArrayList;
import java.util.List;

public class TSMBFOA extends MetaheuristicBase {

    private int sb = 15;
    private int nc = 7;
    private int bacteriaReproduce = 1;
    private int repcycle = 100;
    private double scalingFactor = 1.9;
    private double stepSize;
    private final CNOP cnop;
    private final boolean debug;
    int progress = 0;

    private double[][] individuals;
    private double[][] individualsMat;

    private List<Double[]> matriz;
    private List<Double[]> copia;
    List<List<Double[]>> resultFinal;
    //lista para guardar el numero de evaluaciones de cada frente de soluciones del resultFinal
    List<Integer> evaluations = new ArrayList();
    
    List<Double> timesSeconds = new ArrayList();
    
    
    public TSMBFOA(CNOP cnop, boolean debug) {

        this.cnop = cnop;
        this.debug = debug;

        this.resultFinal = new ArrayList();
    }

    public double[][] getIndividualsMat() {
        return individualsMat;
    }

    @Override
    public void run() {
        // Validate if the number of iterations is between [1 - 30].
        if (this.getExecutions() >= 1 && this.getExecutions() <= 30) {

            System.out.println("Starting TS-MBFOA...");

            ProcessTSMBFOA tsmbfoa = new ProcessTSMBFOA();

            int numberObjective = cnop.getObjetives().length;
            int numberVar = cnop.getNumberVariable();

            for (int i = 0; i < this.getExecutions(); i++) { // Inicia el i iteraciones
                
                long startTime = System.nanoTime();

                //---------------- INICIAMOS POBLACIÓN ---------------- -----------------------------
                double[][] auxStart = Population.startPopulation(this.cnop, this.sb, true);
                this.individuals = new double[auxStart.length][auxStart[0].length];
                for (int ci = 0; ci < auxStart.length; ci++) {
                    System.arraycopy(auxStart[ci], 0, this.individuals[ci], 0, auxStart[ci].length);
                }
                //-------------------------------------------------------------------------------------

                //---------------- EVALUAMOS ---------------- -----------------------------
                this.cnop.evaluateObjectiveFunction(this.individuals);
                //-------------------------------------------------------------------------------------

                //---------------- COPIAMOS ---------------- -----------------------------
                //generamos una copia de individual
                this.individualsMat = new double[individuals.length][individuals[0].length];

                for (int ci = 0; ci < individuals.length; ci++) {
                    System.arraycopy(individuals[ci], 0, individualsMat[ci], 0, individuals[ci].length);
                }
                //se ordena
                Population.sortPopulationMO(this.individualsMat, cnop);
                //-------------------------------------------------------------------------------------

                matriz = new ArrayList();
                //Copia de la matriz
                copia = new ArrayList();

                //-------------------------------------------------------------------------------------
                // Contar cuántos individuos cumplen con la condición de svr = 0
                int counter = MOProcess.counterSvr_0(individualsMat);
                //-------------------------------------------------------------------------------------

                if (counter > 0) { //si hay svr = 0
                    // Inicializar la matriz de svr = 0
                    MOProcess.getIndividualsSvr0(individualsMat, counter, numberVar, numberObjective, matriz, false);
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
                int count = (this.getSb() * this.getNc());
                this.setGmax(0);

                // Calculate current evaluations for the stopping condition
                while (count <= this.getEvaluations()) {
                    //Generation increment
                    this.incrementGmax(1);
                    //Chemotactic process
                    tsmbfoa.chemotaxis(this.cnop, this);

                    //---------------- COPIAMOS ---------------- -----------------------------
                    //generamos una copia de individual
                    this.individualsMat = new double[individuals.length][individuals[0].length];

                    for (int ci = 0; ci < individuals.length; ci++) {
                        System.arraycopy(individuals[ci], 0, individualsMat[ci], 0, individuals[ci].length);
                    }

                    //se ordena
                    Population.sortPopulationMO(this.individualsMat, cnop);
                    //-------------------------------------------------------------------------------------

                    matriz = new ArrayList();

                    //-------------------------------------------------------------------------------------
                    counter = MOProcess.counterSvr_0(individuals);
                    //-------------------------------------------------------------------------------------
                    // Inicializar la matriz
                    MOProcess.getIndividualsSvr0(individualsMat, counter, numberVar, numberObjective, matriz, false);
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
                    //-------------------------------------------------------------------------------------
                    //ordenamos la población principal
                    Population.sortPopulationMO(this.individuals,cnop);

                    count += this.getSb() * this.getNc();

                    //reproduction
                    tsmbfoa.reproduction(this, numberObjective,cnop);

                    // Elimination-dispersion
                    tsmbfoa.eliminationDispersal(cnop, this);
                    // Increment counter
                    count++;

                    // Updating static step size
                    tsmbfoa.updateStepSize(this.cnop, this);
                    Population.sortPopulationMO(this.individuals,cnop);
                } //END WHILE

                //aplicamos dominancia
                MOProcess.dominance(matriz, cnop);
                //guardar copia de cada iteracion en una lista
                this.addresultFinal(copia);

                //imprimir copia

                    /*System.out.println("Iteración: " + i);
                    for (Double[] array : copia) {
                        for (Double value : array) {
                            System.out.print(value + " ");
                        }
                        System.out.println();
                    }*/


                //limpiamos copia
                copia.clear();
                progress = (int) ((i + 1) * 100 / this.getExecutions());
                
                long endTime = System.nanoTime(); // Tiempo después de finalizar el bucle
                long duration = endTime - startTime; // Duración en nanosegundos

                // Convertir a segundos
                double durationInSeconds = duration / 1_000_000_000.0;
                
                timesSeconds.add(durationInSeconds);
                
                //imprimir count
                System.out.println("Count: " + count);
            } // CIERRA NUMERO DE EJECUCIONES

        } else {
            System.err.println("The number of iterations should be between [1 - 30.]");
        }// close else
    }//close run

    public int getProgress() {
        return progress;
    }
    
    public List<Double> getTimesSeconds(){
        return timesSeconds;
    }

    /*public double calculateHypervolume(List<Double[]> copia) {
        double hypervolume = 0.0;
        double puntoRef = 0.7; // Este es el valor de referencia que se usó en el código de MATLAB

        if (!copia.isEmpty()) {
            int nCol =  copia.get(0).length;
            int nObj2 = nCol - 2 - 1; //3
            int nObj1 = nObj2 - 1; //2

            for (int i = 0; i < copia.size() - 1; i++) {
                Double[] actual = copia.get(i);
                Double[] siguiente = copia.get(i + 1);
                hypervolume += (actual[nObj1] - siguiente[nObj1]) * (puntoRef - actual[nObj2]);
            }

            Double[] ultimo = copia.get(copia.size() - 1);
            hypervolume += ultimo[nObj1] * (puntoRef - ultimo[nObj2]);
        }

        return hypervolume;
    }*/
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

    public void addresultFinal(List<Double[]> copia) {

        List<Double[]> copiaAux = new ArrayList();
        for (Double[] arrayOriginal : copia) {
            Double[] arrayCopia = new Double[arrayOriginal.length];
            System.arraycopy(arrayOriginal, 0, arrayCopia, 0, arrayOriginal.length);
            copiaAux.add(arrayCopia);
        }
        resultFinal.add(copiaAux);
    }

    public List<List<Double[]>> getResultFinal() {
        return resultFinal;
    }

    /**
     *
     * Prints the parameter values in a formatted string.
     * <br>
     *
     * @return the formatted string with parameter values
     */
    private String printParameters1() {
        String line = "-".repeat(37);
        String format = "Preparing CNOP: %s for %d iterations.%n"
                + "|%s|%n"
                + "| Parameter calibration for TS-MBFOA: |%n"
                + "|%s|%n"
                + "| %-23s | %9s |%n"
                + "| %-23s | %9s |%n"
                + "| %-23s | %9s |%n"
                + "| %-23s | %9s |%n"
                + "| %-23s | %9s |%n"
                + "| %-23s | %9s |%n"
                + "| %-23s | %9s |%n"
                + "|%s|%n";

        return String.format(format, cnop.getNameProblem(), this.getExecutions(),
                line,
                line,
                "Bacteria", this.getSb(),
                "StepSize", this.getStepSize(),
                "Chemotoxic cycles", this.getNc(),
                "Bacteria to reproduce", this.getBacteriaReproduce(),
                "Scaling factor", this.getScalingFactor(),
                "Evaluations", this.getEvaluations(),
                "Reproduction frequency", this.getRepcycle(),
                line);
    }

    /**
     * Method returns the stored value for the sb parameter of the TS-MBFOA.
     *
     * @return value of parameter Sb
     */
    public int getSb() {
        return sb;
    }

    /**
     * Method to assign the number of bacteria in the population.<br>
     * The number of the population is determined between [10, 500].
     *
     * @param sb the sb to set
     */
    public void setSb(int sb) {
        this.sb = sb;
    }

    /**
     * Method returns the stored value for the Nc parameter of the TS-MBFOA.
     *
     * @return value of parameter Nc
     */
    public int getNc() {
        return nc;
    }

    /**
     * Method that assigns value to the Nc parameter of the TS-MBFOA.<br>
     * the number of chemotaxis cycles [1,Sb]
     *
     * @param nc the nc to set
     */
    public void setNc(int nc) {
        this.nc = nc;
    }

    /**
     * Method returns the stored value for the Sr parameter of the TS-MBFOA.
     *
     * @return value of parameter bacteriaReproduce (Sr)
     */
    public int getBacteriaReproduce() {
        return bacteriaReproduce;
    }

    /**
     * Method that assigns value to the Sr parameter of the TS-MBFOA.<br>
     * (sr) the number of bacteria to reproduce [1, Sb/2]
     *
     * @param bacteriaReproduce the bacteriaReproduce (Sr) to set
     */
    public void setBacteriaReproduce(int bacteriaReproduce) {
        this.bacteriaReproduce = bacteriaReproduce;
    }

    /**
     * Method returns the stored value for the RepClycle parameter of the
     * TS-MBFOA.
     *
     * @return value of parameter repcycle
     */
    public int getRepcycle() {
        return repcycle;
    }

    /**
     * Method that assigns value to the RepCycle parameter of the TS-MBFOA.<br>
     * The reproduction frequency [1, GMAX/2]
     *
     * @param repcycle the repcycle to set
     */
    public void setRepcycle(int repcycle) {
        this.repcycle = repcycle;
    }

    /**
     * Method returns the stored value for the &beta; parameter of the TS-MBFOA.
     *
     * @return value of parameter scalingFactor (&beta;)
     */
    public double getScalingFactor() {
        return scalingFactor;
    }

    /**
     * Method that assigns value to the &beta; parameter of the TS-MBFOA.<br>
     * (&beta;) the scaling factor [0,2]
     *
     * @param scalingFactor the scalingFactor (&beta;) to set
     */
    public void setScalingFactor(double scalingFactor) {
        this.scalingFactor = scalingFactor;
    }

    /**
     * Method returns the stored value for the stepSize parameter of the
     * TS-MBFOA.
     *
     * @return value of parameter stepSize (R)
     */
    public double getStepSize() {
        return stepSize;
    }

    /**
     * Method that assigns value to the (R) parameter of the TS-MBFOA.<br>
     * Static step size [0,1].
     *
     * @param stepSize the stepSize to set
     */
    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    @Override
    public double[][] getIndividuals() {
        return individuals;
    }

    @Override
    public void setIndividuals(double[][] individuals) {
        for (int i = 0; i < individuals.length; i++) {
            double[] individual = individuals[i];
            System.arraycopy(individuals[i], 0, this.individuals[i], 0, individuals[i].length);
        }

    }

}
