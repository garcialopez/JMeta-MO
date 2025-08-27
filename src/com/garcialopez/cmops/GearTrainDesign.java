package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class GearTrainDesign extends CNOP {

    private double[] g;

    public GearTrainDesign() {
        this.g = new double[1];

        this.setNameProblem("Gear Train Design");

        // Define the objective functions
        this.addObjective("Math.abs(6.931 - x3 * x4 / (x1 * x2))", CNOP.MINIMIZATION);
        this.addObjective("Math.max(Math.max(Math.max(x1, x2), x3), x4)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3, x4
        this.setOrderVariables("var{x,1-4}");

        // Define the range of the variables x1, x2, x3, x4
        this.setVariableRange("(11.51 , 60.49);(11.51 , 60.49);(11.51 , 60.49);(11.51 , 60.49)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("Math.abs(6.931 - x3 * x4 / (x1 * x2)) / 6.931 - 0.5 <= 0");
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
            double x4 = x[3];

            // Objective function 1
            values[i][posFx1] = Math.abs(6.931 - x3 * x4 / (x1 * x2));

            // Objective function 2
            values[i][posFx2] = Math.max(Math.max(Math.max(x1, x2), x3), x4);

            // Constraints
            g[0] = values[i][posFx1] / 6.931 - 0.5;

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