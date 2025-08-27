package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class WeldedBeamDesign extends CNOP {

    private double[] g;

    public WeldedBeamDesign() {
        this.g = new double[4];

        this.setNameProblem("Welded Beam Design");

        // Define the objective functions
        this.addObjective("1.10471 * x1^2 * x2 + 0.04811 * x3 * x4 * (14 + x2)", CNOP.MINIMIZATION);
        this.addObjective("0.125 * x1 * x2 * x3 * x4", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3, x4
        this.setOrderVariables("var{x,1-4}");

        // Define the range of the variables x1, x2, x3, x4
        this.setVariableRange("(0.125 , 5);(0.1 , 10);(0.1 , 10);(0.125 , 5)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("tho - tmax <= 0");
        constraints.add("sigma - sigmax <= 0");
        constraints.add("x1 - x4 <= 0");
        constraints.add("Pc - P <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double P = 6000.0, L = 14.0, E = 30e6, tmax = 13600.0, sigmax = 30000.0, G = 12e6;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double x1 = x[0];
            double x2 = x[1];
            double x3 = x[2];
            double x4 = x[3];

            double Pc = (4.013 * E * Math.sqrt((x3 * x3 + x4 * x4 * x4 * x4 * x4 * x4) / 36)) / (L * L) * (1 - x3 / (2 * L) * Math.sqrt(E / (4 * G)));
            double sigma = (6 * P * L) / (x4 * x3 * x3);
            double J = 2 * (Math.sqrt(2) * x1 * x2 * (x2 * x2 / 12 + Math.pow((x1 + x3) / 2, 2)));
            double R = Math.sqrt(x2 * x2 / 4 + Math.pow((x1 + x3) / 2, 2));
            double M = P * (L + x2 / 2);
            double tho1 = P / (Math.sqrt(2) * x1 * x2);
            double tho2 = M * R / J;
            double tho = Math.sqrt(tho1 * tho1 + 2 * tho1 * tho2 * x2 / (2 * R) + tho2 * tho2);

            // Objective function 1
            values[i][posFx1] = 1.10471 * x1 * x1 * x2 + 0.04811 * x3 * x4 * (14.0 + x2);

            // Objective function 2
            values[i][posFx2] = 0.125 * x1 * x2 * x3 * x4;

            // Constraints
            g[0] = tho - tmax;
            g[1] = sigma - sigmax;
            g[2] = x1 - x4;
            g[3] = Pc - P;

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