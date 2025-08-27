package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class TwoBarPlaneTruss extends CNOP {

    private double[] g;

    public TwoBarPlaneTruss() {
        this.g = new double[2];

        this.setNameProblem("Two Bar Plane Truss");

        // Define the objective functions
        this.addObjective("2 * 0.283 * 100 * x2 * Math.sqrt(1 + x1 * x1)", CNOP.MINIMIZATION);
        this.addObjective("0.283 * 100 * Math.pow(1 + x1 * x1, 1.5) * Math.sqrt(1 + x1 * x1 * x1 * x1) / (2 * Math.sqrt(2) * 3e7 * x1 * x1 * x2)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2
        this.setOrderVariables("var{x,1-2}");

        // Define the range of the variables x1, x2
        this.setVariableRange("(0.1 , 2);(0.5 , 2.5)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("104 * (1 + x1) * Math.sqrt(1 + x1 * x1) / (2 * Math.sqrt(2) * x1 * x2) - 20000.0 <= 0");
        constraints.add("104 * (-x1 + 1) * Math.sqrt(1 + x1 * x1) / (2 * Math.sqrt(2) * x1 * x2) - 20000.0 <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double rho = 0.283, h = 100, P = 104, E = 3e7, rho0 = 20000.0;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double x1 = x[0];
            double x2 = x[1];

            // Objective function 1
            values[i][posFx1] = 2 * rho * h * x2 * Math.sqrt(1 + x1 * x1);

            // Objective function 2
            values[i][posFx2] = rho * h * Math.pow(1 + x1 * x1, 1.5) * Math.sqrt(1 + x1 * x1 * x1 * x1) / (2 * Math.sqrt(2) * E * x1 * x1 * x2);

            // Constraints
            g[0] = P * (1 + x1) * Math.sqrt(1 + x1 * x1) / (2 * Math.sqrt(2) * x1 * x2) - rho0;
            g[1] = P * (-x1 + 1) * Math.sqrt(1 + x1 * x1) / (2 * Math.sqrt(2) * x1 * x2) - rho0;

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