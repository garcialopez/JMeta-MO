package com.garcialopez.metaheuristic;

import com.garcialopez.optimizationmodel.CNOP;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Adrian
 */
public class MOProcess {

    /**
     * Ordenamiento por burbuja
     *
     * @param matriz
     * @param fx
     */
    public static void sortFx(List<Double[]> matriz, int fx) {
        Collections.sort(matriz, Comparator.comparingDouble(arr -> arr[fx]));
    } //close sortFx

    public static void dominance(List<Double[]> matriz, CNOP cnop) {
        int var = cnop.getNumberVariable();

        for (int x = 0; x < matriz.size(); x++) {
            if (matriz.get(x)[var + 2] == 1.0) {
                // Si ya está marcado como dominado, pasmos al siguiente vector
                continue;
            }

            for (int y = 0; y < matriz.size(); y++) {
                if (x != y) {
                    if ((matriz.get(y)[var] <= matriz.get(x)[var])
                            && (matriz.get(y)[var + 1] <= matriz.get(x)[var + 1])) {
                        matriz.get(x)[var + 2] = 1.0;
                        break;  // Salir del bucle si el vector i está dominado por algún vector j
                    }
                }
            }
        }

        // Eliminar vectores dominados
        matriz.removeIf(vector -> vector[var + 2] == 1.0);
    } // close dominance

    public static void crowding(List<Double[]> matriz, CNOP cnop) {

        int fila = matriz.size();

        if (fila > 2) {
            int nCol = matriz.get(0).length;
            for (int f = 0; f < cnop.getObjetives().length; f++) {
                // Ordenar cada función objetivo de mayor a menor
                final int indice = cnop.getNumberVariable() + f; // Copiar f a una variable final
                Collections.sort(matriz, Comparator.comparingDouble(arr -> arr[indice]));

                // Inicializar columnas de crowding
                for (int h = 0; h < fila; h++) {
                    matriz.get(h)[nCol - 2] = 0.0;
                    matriz.get(h)[nCol - 1] = 0.0;
                }

                // Crowding infinito para la primera y última fila
                matriz.get(0)[nCol - 1] = 1000.0;
                matriz.get(fila - 1)[nCol - 1] = 1000.0;
                for (int i = 1; i < fila - 1; i++) {
                    matriz.get(i)[nCol - 1]
                            += Math.abs(
                                    matriz.get(i - 1)[indice] - matriz.get(i + 1)[indice]
                            )
                            / (matriz.get(0)[indice] - matriz.get(fila - 1)[indice]);
                }
            }
        } else {
            System.out.println("No se puede hacer Crowding. Fila menor o igual a 2.");
        }

    }

    public static int counterSvr_0(double[][] individuals) {
        // Contar cuántos individuos cumplen con la condición de svr = 0
        int counter = 0;
        for (double[] individual : individuals) {
            if (individual[individual.length - 1] == 0.0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Llena matriz con individuos con svr = 0
     *
     * @param individualsMat
     * @param counter
     * @param numberVar
     * @param numberObjective
     * @param matriz
     */
    public static void getIndividualsSvr0(double[][] individualsMat, int counter, int numberVar, int numberObjective, List<Double[]> matriz, boolean esPSO) {
        // Inicializar la matriz
        Double[] matrizAux;
        for (int j = 0; j < counter; j++) {
            matrizAux = new Double[numberVar + numberObjective + 2];
            int k;
            for (k = 0; k < numberVar; k++) {
                matrizAux[k] = individualsMat[j][k];
            }

            for (int l = 0; l < numberObjective; l++) {
                int ind = 0;

                    if (esPSO){
                        ind = numberVar + l;
                    } else {
                        ind = (numberVar * 2) + l;
                    }


                matrizAux[l + numberVar] = individualsMat[j][ind];
            }
            matrizAux[matrizAux.length - 2] = 0.0;
            matrizAux[matrizAux.length - 1] = 0.0;

            matriz.add(matrizAux);
        } //close for indexs with srv = 0
    }

    /**
     * 
     * @param matriz desde
     * @param copia  hasta
     */
    public static void copyMat(List<Double[]> matriz, List<Double[]> copia) {
        // Recorrer la matriz original y copiar cada array
        for (Double[] arrayOriginal : matriz) {
            // Crear una copia del array y agregarlo a la copia de la matriz
            Double[] arrayCopia = new Double[arrayOriginal.length];
            System.arraycopy(arrayOriginal, 0, arrayCopia, 0, arrayOriginal.length);
            copia.add(arrayCopia);
        }
        
    } //close copyMat



} // close class
