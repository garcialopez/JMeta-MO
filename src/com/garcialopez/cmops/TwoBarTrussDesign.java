package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class TwoBarTrussDesign extends CNOP {

    private double[] g;

    public TwoBarTrussDesign() {
        this.g = new double[3];

        this.setNameProblem("Two Bar Truss Design");

        // Define the objective functions
        this.addObjective("x1 * Math.sqrt(16 + x3^2) + x2 * Math.sqrt(1 + x3^2)", CNOP.MINIMIZATION);
        this.addObjective("(20 * Math.sqrt(16 + x3^2)) / (x3 * x1)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3
        this.setOrderVariables("var{x,1-3}");

        // Define the range of the variables x1, x2, x3
        this.setVariableRange("(1e-5 , 100);(1e-5 , 100);(1 , 3)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("f1 - 0.1 <= 0");
        constraints.add("f2 - 1e5 <= 0");
        constraints.add("(80 * Math.sqrt(1 + x3^2)) / (x3 * x2) - 1e5 <= 0");
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
            double x3 = x[2];

            // Objective function 1
            values[i][posFx1] = x1 * Math.sqrt(16 + Math.pow(x3, 2)) + x2 * Math.sqrt(1 + Math.pow(x3, 2));

            // Objective function 2
            values[i][posFx2] = (20 * Math.sqrt(16 + Math.pow(x3, 2))) / (x3 * x1);

            // Constraints
            g[0] = values[i][posFx1] - 0.1;
            g[1] = values[i][posFx2] - 1e5;
            g[2] = (80 * Math.sqrt(1 + Math.pow(x3, 2))) / (x3 * x2) - 1e5;

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