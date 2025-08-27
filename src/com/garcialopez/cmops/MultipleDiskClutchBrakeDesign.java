package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class MultipleDiskClutchBrakeDesign extends CNOP {

    private double[] g;

    public MultipleDiskClutchBrakeDesign() {
        this.g = new double[8];

        this.setNameProblem("Multiple-disk Clutch Brake Design");

        // Define the objective functions
        this.addObjective("Math.PI * (x2 * x2 - x1 * x1) * x3 * (x5 + 1) * 0.0000078", CNOP.MINIMIZATION);
        this.addObjective("55 * Math.PI * 250 / 30 / (2 / 3 * 0.6 * x4 * x5 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 - x1 * x1) + 3)", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3, x4, x5
        this.setOrderVariables("var{x,1-5}");

        // Define the range of the variables x1, x2, x3, x4, x5
        this.setVariableRange("(60 , 80);(90 , 110);(1 , 3);(0 , 1000);(2 , 9)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("-x2 + x1 + 20 <= 0");
        constraints.add("(x5 + 1) * (x3 + 0.5) - 30 <= 0");
        constraints.add("x4 / (Math.PI * (x2 * x2 - x1 * x1)) - 1 <= 0");
        constraints.add("x4 / (Math.PI * (x2 * x2 - x1 * x1)) * Math.PI * 2 / 3 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 * x1 * x1) * 250 / 30 - 1 * 10 <= 0");
        constraints.add("Math.PI * 2 / 3 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 * x1 * x1) * 250 / 30 - 10 <= 0");
        constraints.add("55 * Math.PI * 250 / 30 / (2 / 3 * 0.6 * x4 * x5 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 - x1 * x1) + 3) - 15 <= 0");
        constraints.add("1.5 * 40 - 2 / 3 * 0.6 * x4 * x5 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 - x1 * x1) <= 0");
        constraints.add("-55 * Math.PI * 250 / 30 / (2 / 3 * 0.6 * x4 * x5 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 - x1 * x1) + 3) <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double Mf = 3, Ms = 40, Iz = 55, n = 250, Tmax = 15, s = 1.5, delta = 0.5;
        double Vsrmax = 10, rho = 0.0000078, pmax = 1, mu = 0.6, Lmax = 30, delR = 20;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double x1 = x[0];
            double x2 = x[1];
            double x3 = x[2];
            double x4 = x[3];
            double x5 = x[4];

            double Rsr = 2.0 / 3.0 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 * x1 * x1);
            double Vsr = Math.PI * Rsr * n / 30;
            double A = Math.PI * (x2 * x2 - x1 * x1);
            double Prz = x4 / A;
            double w = Math.PI * n / 30;
            double Mh = 2.0 / 3.0 * mu * x4 * x5 * (Math.pow(x2, 3) - Math.pow(x1, 3)) / (x2 * x2 - x1 * x1);
            double T = Iz * w / (Mh + Mf);

            // Objective function 1
            values[i][posFx1] = Math.PI * (x2 * x2 - x1 * x1) * x3 * (x5 + 1) * rho;

            // Objective function 2
            values[i][posFx2] = T;

            // Constraints
            g[0] = -x2 + x1 + delR;
            g[1] = (x5 + 1) * (x3 + delta) - Lmax;
            g[2] = Prz - pmax;
            g[3] = Prz * Vsr - pmax * Vsrmax;
            g[4] = Vsr - Vsrmax;
            g[5] = T - Tmax;
            g[6] = s * Ms - Mh;
            g[7] = -T;

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