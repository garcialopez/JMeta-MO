package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class SOPWMFor3LevelInverters extends CNOP {

    private double[] g;

    public SOPWMFor3LevelInverters() {
        this.g = new double[24]; // D-1 constraints

        this.setNameProblem("SOPWM for 3-level Inverters");

        // Define the objective functions
        this.addObjective("calculateObjective1(x)", CNOP.MINIMIZATION);
        this.addObjective("calculateObjective2(x)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, ..., x25
        this.setOrderVariables("var{x,1-25}");

        // Define the range of the variables x1, x2, ..., x25
        this.setVariableRange("(-0 , 90);".repeat(25));

        // Define the constraints
        Constraints constraints = new Constraints();
        for (int i = 1; i < 25; i++) {
            constraints.add("x" + i + " - x" + (i + 1) + " + 1e-6 <= 0");
        }
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double m = 0.32;
        int[] k = {5, 7, 11, 13, 17, 19, 23, 25, 29, 31, 35, 37, 41, 43, 47, 49, 53, 55, 59, 61, 65, 67, 71, 73, 77, 79, 83, 85, 91, 95, 97};
        double[] s = new double[25];
        for (int i = 0; i < 25; i++) {
            s[i] = Math.pow(-1, i + 2);
        }

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            // Objective function 1
            double su = 0;
            for (int j = 0; j < 31; j++) {
                double su2 = 0;
                for (int l = 0; l < 25; l++) {
                    su2 += s[l] * Math.cos(k[j] * x[l] * Math.PI / 180);
                }
                su += Math.pow(su2, 2) / Math.pow(k[j], 4);
            }
            values[i][posFx1] = Math.sqrt(su) / Math.sqrt(sumInverseK4(k));

            // Objective function 2
            double sumS = 0;
            for (int l = 0; l < 25; l++) {
                sumS += s[l] * Math.cos(x[l] * Math.PI / 180);
            }
            values[i][posFx2] = Math.pow(sumS - m, 2);

            // Constraints
            for (int j = 0; j < 24; j++) {
                g[j] = x[j] - x[j + 1] + 1e-6;
            }

            // Sum of constraint violations
            double svr = 0;
            for (int c = 0; c < g.length; c++) {
                svr += (g[c] <= 0) ? 0 : g[c];
            }

            values[i][posSvr] = svr;
        }

        return values;
    }

    private double sumInverseK4(int[] k) {
        double sum = 0;
        for (int value : k) {
            sum += 1.0 / Math.pow(value, 4);
        }
        return sum;
    }
}