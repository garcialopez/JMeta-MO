package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class HydroStaticThrustBearingDesign extends CNOP {

    private double[] g;

    public HydroStaticThrustBearingDesign() {
        this.g = new double[7];

        this.setNameProblem("Hydro-static Thrust Bearing Design");

        // Define the objective functions
        this.addObjective("(x4 * (6 * x3 * x4 / (Math.PI * Math.pow((2 * Math.PI * 750 / 60), 2) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) * Math.log(x1 / x2)) / 0.7 + 9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560))) / 12", CNOP.MINIMIZATION);
        this.addObjective("0.0307 / (386.4 * (6 * x3 * x4 / (Math.PI * Math.pow((2 * Math.PI * 750 / 60), 2) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) * Math.log(x1 / x2))) * (x4 / (2 * Math.PI * x1 * (2 * Math.PI * 750 / 60) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4))))", CNOP.MINIMIZATION);

        // Define the order of the variables R, Ro, mu, Q
        this.setOrderVariables("var{x,1-4}");

        // Define the range of the variables R, Ro, mu, Q
        this.setVariableRange("(1 , 16);(1 , 16);(1e-6 , 16e-6);(1 , 16)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("101000 - (Math.PI * (6 * x3 * x4 / (Math.PI * Math.pow((2 * Math.PI * 750 / 60), 2) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) * Math.log(x1 / x2)) / 2 * (Math.pow(x1, 2) - Math.pow(x2, 2)) / (Math.log(x1 / x2) - 1e-5)) <= 0");
        constraints.add("(6 * x3 * x4 / (Math.PI * Math.pow((2 * Math.PI * 750 / 60), 2) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) * Math.log(x1 / x2)) - 1000 <= 0");
        constraints.add("(2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 50 <= 0");
        constraints.add("0.001 - ((2 * Math.PI * 750 / 60) * (2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) - 1e-5) <= 0");
        constraints.add("x2 - x1 <= 0");
        constraints.add("(0.0307 / (386.4 * (6 * x3 * x4 / (Math.PI * Math.pow((2 * Math.PI * 750 / 60), 2) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) * Math.log(x1 / x2))) * (x4 / (2 * Math.PI * x1 * (2 * Math.PI * 750 / 60) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)))) - 0.001 <= 0");
        constraints.add("(Math.PI * (6 * x3 * x4 / (Math.PI * Math.pow((2 * Math.PI * 750 / 60), 2) * 2 * Math.PI * x3 / (9336 * x4 * 0.0307 * 0.5 * (2 * (Math.pow(10, (Math.log10(Math.log10(8.122 * 1e6 * x3 + 0.8)) - 10.04) / -3.55) - 560)) - 1e-5) * (Math.pow(x1, 4) / 4 - Math.pow(x2, 4) / 4)) * Math.log(x1 / x2)) / (Math.PI * (Math.pow(x1, 2) - Math.pow(x2, 2)) + 1e-5) - 5000 <= 0");
        this.setConstraints(constraints);
    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        double gamma = 0.0307, C = 0.5, n = -3.55, C1 = 10.04;
        double Ws = 101000, Pmax = 1000, delTmax = 50, hmin = 0.001;
        double gg = 386.4, N = 750;

        for (int i = 0; i < sizeRow; i++) {
            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            double R = x[0];
            double Ro = x[1];
            double mu = x[2];
            double Q = x[3];

            double P = (Math.log10(Math.log10(8.122 * 1e6 * mu + 0.8)) - C1) / n;
            double delT = 2 * (Math.pow(10, P) - 560);
            double Ef = 9336 * Q * gamma * C * delT;
            double h = Math.pow((2 * Math.PI * N / 60), 2) * 2 * Math.PI * mu / Ef * (Math.pow(R, 4) / 4 - Math.pow(Ro, 4) / 4) - 1e-5;
            double Po = (6 * mu * Q / (Math.PI * Math.pow(h, 3))) * Math.log(R / Ro);
            double W = Math.PI * Po / 2 * (Math.pow(R, 2) - Math.pow(Ro, 2)) / (Math.log(R / Ro) - 1e-5);

            // Objective function 1
            values[i][posFx1] = (Q * Po / 0.7 + Ef) / 12;

            // Objective function 2
            values[i][posFx2] = gamma / (gg * Po) * (Q / (2 * Math.PI * R * h));

            // Constraints
            g[0] = Ws - W;
            g[1] = Po - Pmax;
            g[2] = delT - delTmax;
            g[3] = hmin - h;
            g[4] = Ro - R;
            g[5] = values[i][posFx2] - 0.001;
            g[6] = W / (Math.PI * (Math.pow(R, 2) - Math.pow(Ro, 2)) + 1e-5) - 5000;

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