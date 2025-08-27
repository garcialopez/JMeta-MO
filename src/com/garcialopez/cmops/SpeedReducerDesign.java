package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class SpeedReducerDesign extends CNOP {

    private double[] g;

    public SpeedReducerDesign() {
        this.g = new double[11];

        this.setNameProblem("Speed Reducer Design");

        // Define the objective functions
        this.addObjective("0.7854 * x1 * x2^2 * (10 * x3^2 / 3 + 14.933 * x3 - 43.0934) - 1.508 * x1 * (x6^2 + x7^2) + 7.477 * (x6^3 + x7^3) + 0.7854 * (x4 * x6^2 + x5 * x7^2)", CNOP.MINIMIZATION);
        this.addObjective("sqrt((745 * x4 / (x2 * x3))^2 + 1.69e7) / (0.1 * x6^3)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3, x4, x5, x6, x7
        this.setOrderVariables("var{x,1-7}");

        // Define the range of the variables x1, x2, x3, x4, x5, x6, x7
        this.setVariableRange("(2.6 , 3.6);(0.7 , 0.8);(16.51 , 28.49);(7.3 , 8.3);(7.3 , 8.3);(2.9 , 3.9);(5 , 5.5)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("1 / (x1 * x2^2 * x3) - 1 / 27.0 <= 0");
        constraints.add("1 / (x1 * x2^2 * x3^2) - 1 / 397.5 <= 0");
        constraints.add("x4^3 / (x2 * x3 * x6^4) - 1 / 1.93 <= 0");
        constraints.add("x5^3 / (x2 * x3 * x7^4) - 1 / 1.93 <= 0");
        constraints.add("x2 * x3 - 40.0 <= 0");
        constraints.add("x1 / x2 - 12.0 <= 0");
        constraints.add("-x1 / x2 + 5.0 <= 0");
        constraints.add("1.9 - x4 + 1.5 * x6 <= 0");
        constraints.add("1.9 - x5 + 1.1 * x7 <= 0");
        constraints.add("sqrt((745 * x4 / (x2 * x3))^2 + 1.69e7) / (0.1 * x6^3) - 1300.0 <= 0");
        constraints.add("sqrt((745 * x5 / (x2 * x3))^2 + 1.575e8) / (0.1 * x7^3) - 850.0 <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double x1 = x[0];
            double x2 = x[1];
            double x3 = Math.round(x[2]);
            double x4 = x[3];
            double x5 = x[4];
            double x6 = x[5];
            double x7 = x[6];

            // Objective function 1
            values[i][posFx1] = 0.7854 * x1 * Math.pow(x2, 2) * (10 * Math.pow(x3, 2) / 3 + 14.933 * x3 - 43.0934) - 1.508 * x1 * (Math.pow(x6, 2) + Math.pow(x7, 2)) + 7.477 * (Math.pow(x6, 3) + Math.pow(x7, 3)) + 0.7854 * (x4 * Math.pow(x6, 2) + x5 * Math.pow(x7, 2));

            // Objective function 2
            values[i][posFx2] = Math.sqrt(Math.pow(745 * x4 / (x2 * x3), 2) + 1.69e7) / (0.1 * Math.pow(x6, 3));

            // Constraints
            g[0] = 1 / (x1 * Math.pow(x2, 2) * x3) - 1 / 27.0;
            g[1] = 1 / (x1 * Math.pow(x2, 2) * Math.pow(x3, 2)) - 1 / 397.5;
            g[2] = Math.pow(x4, 3) / (x2 * x3 * Math.pow(x6, 4)) - 1 / 1.93;
            g[3] = Math.pow(x5, 3) / (x2 * x3 * Math.pow(x7, 4)) - 1 / 1.93;
            g[4] = x2 * x3 - 40.0;
            g[5] = x1 / x2 - 12.0;
            g[6] = -x1 / x2 + 5.0;
            g[7] = 1.9 - x4 + 1.5 * x6;
            g[8] = 1.9 - x5 + 1.1 * x7;
            g[9] = values[i][posFx2] - 1300.0;
            g[10] = Math.sqrt(Math.pow(745 * x5 / (x2 * x3), 2) + 1.575e8) / (0.1 * Math.pow(x7, 3)) - 850.0;

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