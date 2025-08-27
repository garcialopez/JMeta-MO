package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

public class CrashEnergyManagement extends CNOP {

    private double[] g;

    public CrashEnergyManagement() {
        this.g = new double[4];

        this.setNameProblem("Crash Energy Management for High-Speed Train");

        // Define the objective functions
        this.addObjective("1.3667145844797 - 0.00904459793976106 * x1 - 0.0016193573938033 * x2 - 0.00758531275221425 * x3 - 0.00440727360327102 * x4 - 0.00572216860791644 * x5 - 0.00936039926190721 * x6 + 2.62510221107328e-6 * Math.pow(x1, 2) + 4.92982681358861e-7 * Math.pow(x2, 2) + 2.25524989067108e-6 * Math.pow(x3, 2) + 1.84605439400301e-6 * Math.pow(x4, 2) + 2.17175358243416e-6 * Math.pow(x5, 2) + 3.90158043948054e-6 * Math.pow(x6, 2) + 4.55276994245781e-7 * x1 * x2 - 6.37013576290982e-7 * x1 * x3 + 8.26736480446359e-7 * x1 * x4 + 5.66352809442276e-8 * x1 * x5 - 3.20213897443278e-7 * x1 * x6 + 1.18015467772812e-8 * x2 * x3 + 9.25820391546515e-8 * x2 * x4 - 1.05705364119837e-7 * x2 * x5 - 4.74797783014687e-7 * x2 * x6 - 5.02319867013788e-7 * x3 * x4 + 9.54284258085225e-7 * x3 * x5 + 1.80533309229454e-7 * x3 * x6 - 1.07938022118477e-6 * x4 * x5 - 1.81370642220182e-7 * x4 * x6 - 2.24238851688047e-7 * x5 * x6", CNOP.MINIMIZATION);
        this.addObjective("-1.19896668942683 + 3.04107017009774 * x1 + 1.23535701600191 * x2 + 2.13882039381528 * x3 + 2.33495178382303 * x4 + 2.68632494801975 * x5 + 3.43918953617606 * x6 - 7.89144544980703e-4 * Math.pow(x1, 2) - 2.06085185698215e-4 * Math.pow(x2, 2) - 7.15269900037858e-4 * Math.pow(x3, 2) - 7.8449237573837e-4 * Math.pow(x4, 2) - 9.31396896237177e-4 * Math.pow(x5, 2) - 1.40826531972195e-3 * Math.pow(x6, 2) - 1.60434988248392e-4 * x1 * x2 + 2.0824655419411e-4 * x1 * x3 - 3.0530659653553e-4 * x1 * x4 - 8.10145973591615e-5 * x1 * x5 + 6.94728759651311e-5 * x1 * x6 + 1.18015467772812e-8 * x2 * x3 + 9.25820391546515e-8 * x2 * x4 - 1.05705364119837e-7 * x2 * x5 + 1.69935290196781e-4 * x2 * x6 + 2.32421829190088e-5 * x3 * x4 - 2.0808624041163476e-4 * x3 * x5 + 1.75576341867273e-5 * x3 * x6 + 2.68422081654044e-4 * x4 * x5 + 4.39852066801981e-5 * x4 * x6 + 2.96785446021357e-5 * x5 * x6", CNOP.MINIMIZATION);

        // Define the order of the variables x1, x2, x3, x4, x5, x6
        this.setOrderVariables("var{x,1-6}");

        // Define the range of the variables x1, x2, x3, x4, x5, x6
        this.setVariableRange("(1.3 , 1.7);(2.5 , 3.5);(1.3 , 1.7);(1.3 , 1.7);(1.3 , 1.7);(1.3 , 1.7)");

        // Define the constraints
        Constraints constraints = new Constraints();
        constraints.add("f1 - 5 <= 0");
        constraints.add("-f1 <= 0");
        constraints.add("f2 - 28 <= 0");
        constraints.add("-f2 <= 0");
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
            double x5 = x[4];
            double x6 = x[5];

            // Objective function 1
            values[i][posFx1] = 1.3667145844797 - 0.00904459793976106 * x1 - 0.0016193573938033 * x2 - 0.00758531275221425 * x3 - 0.00440727360327102 * x4 - 0.00572216860791644 * x5 - 0.00936039926190721 * x6 + 2.62510221107328e-6 * Math.pow(x1, 2) + 4.92982681358861e-7 * Math.pow(x2, 2) + 2.25524989067108e-6 * Math.pow(x3, 2) + 1.84605439400301e-6 * Math.pow(x4, 2) + 2.17175358243416e-6 * Math.pow(x5, 2) + 3.90158043948054e-6 * Math.pow(x6, 2) + 4.55276994245781e-7 * x1 * x2 - 6.37013576290982e-7 * x1 * x3 + 8.26736480446359e-7 * x1 * x4 + 5.66352809442276e-8 * x1 * x5 - 3.20213897443278e-7 * x1 * x6 + 1.18015467772812e-8 * x2 * x3 + 9.25820391546515e-8 * x2 * x4 - 1.05705364119837e-7 * x2 * x5 - 4.74797783014687e-7 * x2 * x6 - 5.02319867013788e-7 * x3 * x4 + 9.54284258085225e-7 * x3 * x5 + 1.80533309229454e-7 * x3 * x6 - 1.07938022118477e-6 * x4 * x5 - 1.81370642220182e-7 * x4 * x6 - 2.24238851688047e-7 * x5 * x6;

            // Objective function 2
            values[i][posFx2] = -1.19896668942683 + 3.04107017009774 * x1 + 1.23535701600191 * x2 + 2.13882039381528 * x3 + 2.33495178382303 * x4 + 2.68632494801975 * x5 + 3.43918953617606 * x6 - 7.89144544980703e-4 * Math.pow(x1, 2) - 2.06085185698215e-4 * Math.pow(x2, 2) - 7.15269900037858e-4 * Math.pow(x3, 2) - 7.8449237573837e-4 * Math.pow(x4, 2) - 9.31396896237177e-4 * Math.pow(x5, 2) - 1.40826531972195e-3 * Math.pow(x6, 2) - 1.60434988248392e-4 * x1 * x2 + 2.0824655419411e-4 * x1 * x3 - 3.0530659653553e-4 * x1 * x4 - 8.10145973591615e-5 * x1 * x5 + 6.94728759651311e-5 * x1 * x6 + 1.18015467772812e-8 * x2 * x3 + 9.25820391546515e-8 * x2 * x4 - 1.05705364119837e-7 * x2 * x5 + 1.69935290196781e-4 * x2 * x6 + 2.32421829190088e-5 * x3 * x4 - 2.0808624041163476e-4 * x3 * x5 + 1.75576341867273e-5 * x3 * x6 + 2.68422081654044e-4 * x4 * x5 + 4.39852066801981e-5 * x4 * x6 + 2.96785446021357e-5 * x5 * x6;

            // Constraints
            g[0] = values[i][posFx1] - 5;
            g[1] = -values[i][posFx1];
            g[2] = values[i][posFx2] - 28;
            g[3] = -values[i][posFx2];

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