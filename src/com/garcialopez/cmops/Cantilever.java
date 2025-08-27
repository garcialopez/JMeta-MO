package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class Cantilever extends CNOP {

    private double[] g;

    public Cantilever() {
        this.g = new double[2]; // 2 constraints

        this.setNameProblem("Cantilever Beam Design");

        // Define the objective functions
        this.addObjective("calculateObjective1(x)", CNOP.MINIMIZATION);
        this.addObjective("calculateObjective2(x)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2
        this.setOrderVariables("var{x,1-2}");

        // Define the range of the variables x1, x2
        this.setVariableRange("(0.01 , 0.05);(0.2 , 1)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("x1 <= 0");
        constraints.add("x2 <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double P = 1;
        double E = 207000000;
        double Sy = 300000;
        double delta_max = 0.005;
        double rho = 7800;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            // Objective function 1
            values[i][posFx1] = 0.25 * rho * Math.PI * x[1] * Math.pow(x[0], 2);

            // Objective function 2
            values[i][posFx2] = (64.0 * P * Math.pow(x[1], 3)) / (3 * E * Math.PI * Math.pow(x[0], 4));

            // Constraints
            this.g[0] = -Sy + (32.0 * P * x[1]) / (Math.PI * Math.pow(x[0], 3));
            this.g[1] = -delta_max + (64.0 * P * Math.pow(x[1], 3)) / (3 * E * Math.PI * Math.pow(x[0], 4));

            // Sum of constraint violations
            double svr = 0;
            for (int c = 0; c < g.length; c++) {
                svr += ((this.g[c] > 0) ? this.g[c] : 0);
            }

            values[i][posSvr] = svr;
        }

        return values;
    }
}