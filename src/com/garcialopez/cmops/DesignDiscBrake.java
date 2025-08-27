package com.garcialopez.cmops;

import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.optimizationmodel.Constraints;

/**
 *
 * @author adrian
 */
public class DesignDiscBrake extends CNOP {

    //restricciones
    private double[] g;

    public DesignDiscBrake() {
        //Restricciones - todas deben ser <= 0
        this.g = new double[5];

        this.setNameProblem("Design of a Disc Brake");

        this.addObjective("4.9 * 10^(-5) * (x2^2 - x1^2) * (x4-1)", CNOP.MINIMIZATION);
        this.addObjective("(9.82 * 10^6 * (x2^2 - x1^2)) / (x3 * x4 * (x2^3 - x1^3))", CNOP.MINIMIZATION);

        //definir el orden de las variables x1, x2, x3, x4
        this.setOrderVariables("var{x,1-4}");
        //definir el rango de las variables x1, x2, x3, x4
        this.setVariableRange("(55.0 , 80.0);(75.0 , 110.0);(1000.0 , 3000.0);(2.0 , 20.0)");

        Constraints constraints = new Constraints(); //restricciones
        constraints.add("(x2 - x1) - 20 >= 0"); //restricción 1
        constraints.add("30 - 2.5 * (x4 + 1) >= 0"); //restricción 2
        constraints.add("0.4 - ( (x3) / (3.14 * (x2^2 - x1^2) ) ) >= 0"); //restricción 3
        constraints.add("1 - ( (2.22 * 10^(-3) * x3 * (x2^3 - x1^3)) / ( (x2^2 - x1^2)^2 ) ) >= 0"); //restricción 4
        constraints.add("( (2.66 * 10^(/2) * x3 * x4 * (x2^3 - x1^3)) / (x2^2 - x1^2) ) - 900 >= 0"); //restricción 5
        this.setConstraints(constraints); //asignar las restricciones al problema

    }

    @Override
    public double[][] evaluateObjectiveFunction(double[][] values) {

        //posiciones de las funciones objetivo
        int sizeRow = values.length;
        int sizeColumn = values[0].length;
        int posSvr = sizeColumn - 1;
        int posFx2 = sizeColumn - 2;
        int posFx1 = sizeColumn - 3;

        //iniciamos a evaluar las funciones objetivo y a realizar la suma de violación de restricciones
        for (int i = 0; i < sizeRow; i++) {

            double[] x = new double[sizeColumn];
            System.arraycopy(values[i], 0, x, 0, sizeColumn);

            //funcion objetivo 1: 4.9 * 10^(-5) * (x2^2 - x1^2) * (x4-1)
            values[i][posFx1] = 4.9 * Math.pow(10, -5) * (Math.pow(x[1], 2) - Math.pow(x[0], 2)) * (x[3] - 1);

            //funcion objetivo 2: (9.82 * 10^6 * (x2^2 - x1^2)) / (x3 * x4 * (x2^3 - x1^3))
            values[i][posFx2] = (9.82 * Math.pow(10, 6) * (Math.pow(x[1], 2) - Math.pow(x[0], 2))) / (x[2] * x[3] * (Math.pow(x[1], 3) - Math.pow(x[0], 3)));

            //restricciones
            //r1: (x2 - x1) - 20 >= 0
            this.g[0] = (x[1] - x[0]) - 20;
            //r2: 30 - 2.5 * (x4 + 1) >= 0
            this.g[1] = 30 - 2.5 * (x[3] + 1);
            //r3: 0.4 - ( (x3) / (3.14 * (x2^2 - x1^2) ) ) >= 0
            this.g[2] = 0.4 - ( (x[2]) / (3.14 * (Math.pow(x[1], 2) - Math.pow(x[0], 2) ) ) );
            //r4: 1 - ( (2.22 * 10^(-3) * x3 * (x2^3 - x1^3)) / ( (x2^2 - x1^2)^2 ) ) >= 0
            this.g[3] = 1 - ( ( 2.22 * Math.pow(10, -3) * x[2] * (Math.pow(x[1], 3) - Math.pow(x[0], 3)) )  / ( Math.pow((Math.pow(x[1], 2) - Math.pow(x[0], 2)), 2) ));
            //r5: ( (2.66 * 10^(/2) * x3 * x4 * (x2^3 - x1^3)) / (x2^2 - x1^2) ) - 900 >= 0
            this.g[4] = ( (2.66 * Math.pow(10, -2) * x[2] * x[3] * (Math.pow(x[1], 3) - Math.pow(x[0], 3)) ) / (Math.pow(x[1], 2) - Math.pow(x[0], 2)) ) - 900;

            //Suma de violación de restricciones
            double svr = 0;

            for (int c = 0; c < g.length; c++) {
                svr += ((this.g[c] >= 0) ? 0 : this.g[c] * -1);
            }//close for

            values[i][posSvr] = svr;

        }// close for

        return values;
    } //close evaluate

} //close class
