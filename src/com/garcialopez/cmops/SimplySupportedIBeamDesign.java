package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class SimplySupportedIBeamDesign extends CNOP {

    private double[] g;

    public SimplySupportedIBeamDesign() {
        this.g = new double[1];

        this.setNameProblem("Simply Supported I-beam Design");

        // Define the objective functions
        this.addObjective("2 * x2 * x4 + x3 * (x1 - 2 * x4)", CNOP.MINIMIZATION);
        this.addObjective("600 * Math.pow(200, 3) / (48 * 2e4 * (x3 * Math.pow(x1 - 2 * x4, 3) + 2 * x2 * x4 * (4 * Math.pow(x4, 2) + 3 * x1 * (x1 - 2 * x4))) / 12)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3, x4
        this.setOrderVariables("var{x,1-4}");

        // Define the range of the variables x1, x2, x3, x4
        this.setVariableRange("(10 , 80);(10 , 50);(0.9 , 5);(0.9 , 5)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("-16 + 180000 * x1 / (x3 * Math.pow(x1 - 2 * x4, 3) + 2 * x2 * x4 * (4 * Math.pow(x4, 2) + 3 * x1 * (x1 - 2 * x4))) + 15000 * x2 / ((x1 - 2 * x4) * Math.pow(x3, 3) + 2 * x4 * Math.pow(x2, 3)) <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double P = 600, L = 200, E = 2e4;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double x1 = x[0];
            double x2 = x[1];
            double x3 = x[2];
            double x4 = x[3];

            // Objective function 1
            values[i][posFx1] = 2 * x2 * x4 + x3 * (x1 - 2 * x4);

            // Objective function 2
            values[i][posFx2] = P * Math.pow(L, 3) / (48 * E * (x3 * Math.pow(x1 - 2 * x4, 3) + 2 * x2 * x4 * (4 * Math.pow(x4, 2) + 3 * x1 * (x1 - 2 * x4))) / 12);

            // Constraints
            g[0] = -16 + 180000 * x1 / (x3 * Math.pow(x1 - 2 * x4, 3) + 2 * x2 * x4 * (4 * Math.pow(x4, 2) + 3 * x1 * (x1 - 2 * x4))) + 15000 * x2 / ((x1 - 2 * x4) * Math.pow(x3, 3) + 2 * x4 * Math.pow(x2, 3));

            // Sum of constraint violations
            double svr = 0;
            for (int c = 0; c < g.length; c++) {
                svr += (g[c] <= 0) ? 0 : g[c];
            }

            values[i][posSvr] = svr;
        }

        return values;
    }
}