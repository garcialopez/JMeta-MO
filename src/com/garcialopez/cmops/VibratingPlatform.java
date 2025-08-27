package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class VibratingPlatform extends CNOP {

    private double[] g;

    public VibratingPlatform() {
        this.g = new double[5];

        this.setNameProblem("Vibrating Platform Design");

        // Define the objective functions
        this.addObjective("(-Math.PI) / (2 * L)^2 * Math.sqrt(Math.abs(EI / mu))", CNOP.MINIMIZATION);
        this.addObjective("2 * b * L * (c1 * d1 + c2 * (d2 - d1) + c3 * (d3 - d2))", CNOP.MINIMIZATION);

        // Define the order of the variables d1, d2, d3, b, L
        this.setOrderVariables("var{x,1-5}");

        // Define the range of the variables d1, d2, d3, b, L
        this.setVariableRange("(0.05 , 0.5);(0.2 , 0.5);(0.2 , 0.6);(0.35 , 0.5);(3.0 , 6.0)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("mu * L - 2800 <= 0");
        constraints.add("d1 - d2 <= 0");
        constraints.add("d2 - d1 - 0.15 <= 0");
        constraints.add("d2 - d3 <= 0");
        constraints.add("d3 - d2 - 0.01 <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double rho1 = 100, rho2 = 2770, rho3 = 7780;
        double E1 = 1.6, E2 = 70, E3 = 200;
        double c1 = 500, c2 = 1500, c3 = 800;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double d1 = x[0];
            double d2 = x[1];
            double d3 = x[2];
            double b = x[3];
            double L = x[4];

            double mu = 2 * b * (rho1 * d1 + rho2 * (d2 - d1) + rho3 * (d3 - d2));
            double EI = (2 * b / 3) * (E1 * Math.pow(d1, 3) + E2 * (Math.pow(d2, 3) - Math.pow(d1, 3)) + E3 * (Math.pow(d3, 3) - Math.pow(d2, 3)));

            // Objective function 1
            values[i][posFx1] = (-Math.PI) / Math.pow(2 * L, 2) * Math.sqrt(Math.abs(EI / mu));

            // Objective function 2
            values[i][posFx2] = 2 * b * L * (c1 * d1 + c2 * (d2 - d1) + c3 * (d3 - d2));

            // Constraints
            g[0] = mu * L - 2800;
            g[1] = d1 - d2;
            g[2] = d2 - d1 - 0.15;
            g[3] = d2 - d3;
            g[4] = d3 - d2 - 0.01;

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