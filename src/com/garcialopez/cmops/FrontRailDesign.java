package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class FrontRailDesign extends CNOP {

    private double[] g;

    public FrontRailDesign() {
        this.g = new double[3];

        this.setNameProblem("Front Rail Design");

        // Define the objective functions
        this.addObjective("14496.5 / (-70973.4 + 958.656 * x2 + 614.173 * x1 - 3.827 * x2 * x1 + 57.023 * x2 * x3 + 63.274 * x1 * x3 - 3.582 * x2 * x2 - 1.4842 * x1 * x1 - 1890.174 * x3 * x3)", CNOP.MINIMIZATION);
        this.addObjective("(111.854 - 20.210 * x2 + 7.560 * x1 - 0.025 * x2 * x1 + 2.731 * x2 * x3 - 1.479 * x1 * x3 + 0.165 * x2 * x2) / 234.9", CNOP.MINIMIZATION);

        // Define the order of the variables hh, w, t
        this.setOrderVariables("var{x,1-3}");

        // Define the range of the variables hh, w, t
        this.setVariableRange("(136 , 146);(56 , 68);(1.4 , 2.2)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("-(x1 - 136) * (146 - x1) <= 0");
        constraints.add("-(x2 - 58) * (66 - x2) <= 0");
        constraints.add("-(x3 - 1.4) * (2.2 - x3) <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double Ea = 14496.5, Fa = 234.9;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double hh = x[0];
            double w = x[1];
            double t = x[2];

            double E = -70973.4 + 958.656 * w + 614.173 * hh - 3.827 * w * hh + 57.023 * w * t + 63.274 * hh * t - 3.582 * w * w - 1.4842 * hh * hh - 1890.174 * t * t;
            double F = 111.854 - 20.210 * w + 7.560 * hh - 0.025 * w * hh + 2.731 * w * t - 1.479 * hh * t + 0.165 * w * w;

            // Objective function 1
            values[i][posFx1] = Ea / E;

            // Objective function 2
            values[i][posFx2] = F / Fa;

            // Constraints
            g[0] = -(hh - 136) * (146 - hh);
            g[1] = -(w - 58) * (66 - w);
            g[2] = -(t - 1.4) * (2.2 - t);

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