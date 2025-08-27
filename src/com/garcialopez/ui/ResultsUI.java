package com.garcialopez.ui;

import com.garcialopez.metaheuristic.particleswarm.PSO;
import com.garcialopez.metaheuristic.tsmbfoa.TSMBFOA;
import com.garcialopez.optimizationmodel.CNOP;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import java.util.Comparator;

public class ResultsUI extends javax.swing.JFrame {

    CNOP cnop = null;
    TSMBFOA tsmbfoa = null;
    PSO pso = null;
    List<List<Double[]>> solutions = null;

    public ResultsUI() {
        initComponents();
        //extender
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public ResultsUI(CNOP cnop, TSMBFOA tsmbfoa) {
        initComponents();
        //extender
        this.setExtendedState(MAXIMIZED_BOTH);

        this.cnop = cnop;
        this.tsmbfoa = tsmbfoa;

        initSolutions(tsmbfoa.getResultFinal());
        initGeneral();
        initParameters();
        calculateHypervolume(tsmbfoa.getResultFinal());

        
    }

    public ResultsUI(CNOP cnop, PSO pso) {
        initComponents();
        //extender
        this.setExtendedState(MAXIMIZED_BOTH);

        this.cnop = cnop;
        this.pso = pso;

        initSolutions(pso.getResultFinal());
        initGeneral();
        initParameters();
        calculateHypervolume(pso.getResultFinal());


    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBody = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelLeft = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaGeneral = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaParameters = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listSolutions = new javax.swing.JList<>();
        panelCenter = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaSolutions = new javax.swing.JTable();
        panelChart = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaStatistics = new javax.swing.JTextArea();
        Metrics = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaMetrics = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        areaMetrics1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelBody.setLayout(new java.awt.BorderLayout());

        panelHeader.setPreferredSize(new java.awt.Dimension(804, 70));
        panelHeader.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("JMeta");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelHeader.add(jLabel2);

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("BFOP");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelHeader.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 153));
        jLabel5.setText("METAheuristic with Bacteria For Optimization Problems UI");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panelHeader.add(jLabel5);

        panelBody.add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelLeft.setPreferredSize(new java.awt.Dimension(255, 358));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Used configuration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(255, 0, 0))); // NOI18N
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel8.setText("General");
        jPanel1.add(jLabel8);

        areaGeneral.setEditable(false);
        areaGeneral.setColumns(20);
        areaGeneral.setRows(5);
        jScrollPane2.setViewportView(areaGeneral);

        jPanel1.add(jScrollPane2);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel3.setText("Configured parameters");
        jPanel1.add(jLabel3);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(274, 200));

        areaParameters.setEditable(false);
        areaParameters.setColumns(20);
        areaParameters.setRows(5);
        jScrollPane3.setViewportView(areaParameters);

        jPanel1.add(jScrollPane3);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solutions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14))); // NOI18N

        listSolutions.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listSolutions.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSolutionsValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(listSolutions);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelLeftLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBody.add(panelLeft, java.awt.BorderLayout.LINE_START);

        panelCenter.setLayout(new javax.swing.BoxLayout(panelCenter, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Start searching for solutions", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(404, 55));

        btnPrint.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnPrint.setForeground(new java.awt.Color(0, 102, 0));
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel2.add(btnPrint);

        panelCenter.add(jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problem details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        tablaSolutions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tablaSolutions);

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                    .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelCenter.add(jPanel7);

        panelBody.add(panelCenter, java.awt.BorderLayout.CENTER);

        jPanel12.setPreferredSize(new java.awt.Dimension(250, 683));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14))); // NOI18N

        areaStatistics.setEditable(false);
        areaStatistics.setColumns(20);
        areaStatistics.setRows(5);
        jScrollPane6.setViewportView(areaStatistics);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Metrics.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Metrics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel6.setText("Selected solution metrics");

        areaMetrics.setEditable(false);
        areaMetrics.setColumns(20);
        areaMetrics.setRows(5);
        jScrollPane7.setViewportView(areaMetrics);

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel7.setText("Hypervolume of iterations");

        areaMetrics1.setEditable(false);
        areaMetrics1.setColumns(20);
        areaMetrics1.setRows(5);
        jScrollPane8.setViewportView(areaMetrics1);

        javax.swing.GroupLayout MetricsLayout = new javax.swing.GroupLayout(Metrics);
        Metrics.setLayout(MetricsLayout);
        MetricsLayout.setHorizontalGroup(
            MetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MetricsLayout.createSequentialGroup()
                .addGroup(MetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(MetricsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(187, 187, 187))
        );
        MetricsLayout.setVerticalGroup(
            MetricsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MetricsLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Metrics, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Metrics, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        panelBody.add(jPanel12, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        
        


    }//GEN-LAST:event_btnPrintActionPerformed

    private void listSolutionsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSolutionsValueChanged
        if (evt.getValueIsAdjusting()) { //para evitar que se ejecute dos veces
            return; // Ignore this event
        }

        //si hay un elemento seleccionado en la lista, mostrar la tabla de soluciones
        if (listSolutions.getSelectedIndex() != -1) {
            viewTable();
        }
    }//GEN-LAST:event_listSolutionsValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Metrics;
    private javax.swing.JTextArea areaGeneral;
    private javax.swing.JTextArea areaMetrics;
    private javax.swing.JTextArea areaMetrics1;
    private javax.swing.JTextArea areaParameters;
    private javax.swing.JTextArea areaStatistics;
    private javax.swing.JButton btnPrint;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> listSolutions;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelChart;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JTable tablaSolutions;
    // End of variables declaration//GEN-END:variables

    //iniciar lista de soluciones. la lista sera el tamaño de lo que se obtenga de tsmbfo.getResultFinal()
    // el tipo de dato que retorna es: List<List<Double[]>>. segun el tamaño la lista se llenara de la siguiente manera:
    //Solution i
    //Solution i+1
    //para cada solucion se debe mostrar el numero de soluciones encontradas en cada List<Double[]> del indice i
    private void initSolutions(List<List<Double[]>> solutions) {
        this.solutions = solutions;

        //crear modelo inicial para la lista
        DefaultListModel<String> listModel = new DefaultListModel();

        //iterar cada solucion
        for (int i = 0; i < solutions.size(); i++) {
            //obtener la solucion i
            List<Double[]> solution = solutions.get(i);
            //crear un string con el numero de soluciones encontradas
            String solutionString = "Solution " + (i + 1) + " (" + solution.size() + " found)\n";
            //agregar el string al modelo de la lista
            listModel.addElement(solutionString);
        }
        //setear el modelo de la lista
        listSolutions.setModel(listModel);
    }

    //iniciar areaGeneral
    private void initGeneral() {
        //mostrar en area general nombre del cnop, numero de variables, numero de restricciones,
        // //numero de objetivos, algoritmo usado

        //obtener nombre del cnop
        String cnopName = cnop.getNameProblem();
        //obtener numero de variables
        int numVariables = cnop.getNumberVariable();
        //obtener numero de restricciones
        int numConstraints = cnop.getConstraintsEquality().length + cnop.getConstraintsInequality().length;
        //obtener numero de objetivos
        int numObjectives = cnop.getObjetives().length;

        //obtener algoritmo usado
        String algorithm = "";
        if (tsmbfoa != null) {
            algorithm = "TSMBFOA";
        } else {
            algorithm = "PSO";
        }



        //crear string con la informacion
        String general = "Problem: " + cnopName + "\n"
                + "Variables: " + numVariables + "\n"
                + "Constraints: " + numConstraints + "\n"
                + "Objectives: " + numObjectives + "\n"
                + "Algorithm: " + algorithm + "\n";

        //setear el string en el area general
        areaGeneral.setText(general);
    }

    //iniciar areaParameters
    private void initParameters() {
        String parameters = "";
        if (tsmbfoa != null) {
            //obtener configuracion tsmbfoa:iteraciones, sb, nc, StepSize, ScalingFactor, BacteriaReproduce, setRepcycle, Evaluations
            int iterations = tsmbfoa.getExecutions();
            int sb = tsmbfoa.getSb();
            int nc = tsmbfoa.getNc();
            double stepSize = tsmbfoa.getStepSize();
            double scalingFactor = tsmbfoa.getScalingFactor();
            double bacteriaReproduce = tsmbfoa.getBacteriaReproduce();
            int setRepcycle = tsmbfoa.getRepcycle();
            int evaluations = tsmbfoa.getEvaluations();

            //crear string con la informacion
            parameters = "Iterations: " + iterations + "\n"
                    + "Swarm Size: " + sb + "\n"
                    + "Nc: " + nc + "\n"
                    + "Step Size: " + stepSize + "\n"
                    + "Scaling Factor: " + scalingFactor + "\n"
                    + "Bacteria Reproduce: " + bacteriaReproduce + "\n"
                    + "Repcycle: " + setRepcycle + "\n"
                    + "Evaluations: " + evaluations + "\n";
        }else {
            //obtener configuracion pso:iteraciones, swarmSize, C1, C2
            int iterations = pso.getExecutions();
            int swarmSize = pso.getNP();
            double inertia = pso.getW();
            double c1 = pso.getC1();
            double c2 = pso.getC2();
            int evaluations = pso.getEvaluations();

            //crear string con la informacion
            parameters = "Iterations: " + iterations + "\n"
                    + "Swarm Size: " + swarmSize + "\n"
                    + "Inertia: " + inertia + "\n"
                    + "C1: " + c1 + "\n"
                    + "C2: " + c2 + "\n"
                    + "Evaluations: " + evaluations + "\n";

        }

        //setear el string en el area de parametros
        areaParameters.setText(parameters);
    }

    private void viewTable() {

        List<Double[]> solutionParetoFOs = new ArrayList();

        //imprimir indice
        int index = listSolutions.getSelectedIndex();

        //segun el indice seleccionado, mostrar las soluciones encontradas del numero de variables.
        //obtener la solucion seleccionada
        List<Double[]> solution = solutions.get(index);
        //calcular el numero de soluciones
        int numSolutions = solution.size();
        //crear modelo para la tabla
        DefaultTableModel tableModel = new DefaultTableModel();
        //agregar columnas a la tabla

        int nVar = cnop.getNumberVariable();
        int nObj = cnop.getObjetives().length;
        //for segun el numero de variables
        for (int i = 0; i < nVar; i++) {
            tableModel.addColumn("x" + (i + 1));
        }
        //agregar columnas de las funciones objetivos
        for (int i = 0; i < nObj; i++) {
            tableModel.addColumn("f" + (i + 1));
        }
        //agregar columnas de la suma de violacion de restricciones
        tableModel.addColumn("Sum Violation");

        //extrar los primeros nVar elementos de cada Double[]
        for (Double[] doubles : solution) {
            double[][] row = new double[1][nVar + nObj + 1];
            //agregar los valores de las variables
            for (int j = 0; j < nVar; j++) {
                row[0][j] = doubles[j];
            }

            //agregar los valores de las funciones objetivos
            for (int j = 0; j < nObj; j++) {
                row[0][nVar + j] = 0;
            }
            //agregar los valores de la suma de violacion de restricciones
            row[0][nVar + nObj] = 0;

            double[][] row2 = cnop.evaluateObjectiveFunction(row);

            //crear una copia profunnda de row2 y guardala en solutionParetoFOs
            Double[] row3 = new Double[nVar + nObj + 1];
            for (int j = 0; j < nVar + nObj + 1; j++) {
                row3[j] = row2[0][j];
            }
            solutionParetoFOs.add(row3);

            //crear un nuevo objeto para la fila
            Object[] row4 = new Object[row2[0].length];
            //llenar la fila con los valores de la solucion
            for (int j = 0; j < row2[0].length; j++) {
                row4[j] = row2[0][j];
            }

            //agregar la fila a la tabla
            tableModel.addRow(row4);

        }
        //setear el modelo de la tabla
        tablaSolutions.setModel(tableModel);
        
        

        //mostrar grafico de pareto si la solucion es mayor a 2
        if (numSolutions >= 2) {
            graphPareto(solutionParetoFOs, nVar, nVar + 1);
        }


        //agregar estadisticas en areaStatistics
        showStatistics(solutionParetoFOs, nVar, nVar + 1, index);

        //agregar metricas en areaMetrics
        showMetrics(solutionParetoFOs, solution, nVar, nVar + 1);


    }

    //calcular el hiper volumenpara todas las soluciones
    private void calculateHypervolume(List<List<Double[]>> solutionParetoFOs) {
        //calcular el hiper volumen para todas las soluciones y guardarlas en una lista
        List<Double> hypervolumes = new ArrayList();
        for (List<Double[]> solutionParetoFO : solutionParetoFOs) {
            
            //calcular hipervolumen segun el optimizador
            double hypervolumeAux = 0;
            if (tsmbfoa != null) {
                hypervolumeAux = tsmbfoa.calculateHypervolume(solutionParetoFO);
            }else{
                hypervolumeAux = pso.calculateHypervolume(solutionParetoFO);
            }
            hypervolumes.add(hypervolumeAux);
        }

        //calcular el mejor hipervolumen
        double bestHypervolume = Double.MIN_VALUE;
        for (Double hypervolumeAux : hypervolumes) {
            bestHypervolume = Math.max(bestHypervolume, hypervolumeAux);
        }
        

        //calcula el promedio de los hipervolumenes
        double hypervolume = 0;
        for (Double hypervolumeAux : hypervolumes) {
            hypervolume += hypervolumeAux;
        }
        hypervolume /= hypervolumes.size();

        //calcular la desviacion estandar de los hipervolumenes
        double stdHypervolume = 0;
        for (Double hypervolumeAux : hypervolumes) {
            stdHypervolume += Math.pow(hypervolumeAux - hypervolume, 2);
        }
        stdHypervolume = Math.sqrt(stdHypervolume / hypervolumes.size());

        //calcular la mediana de los hipervolumenes
        double medianHypervolume = 0;
        for (Double hypervolumeAux : hypervolumes) {
            medianHypervolume += hypervolumeAux;
        }
        medianHypervolume /= hypervolumes.size();

        //calcular el peor hipervolumen
        double worstHypervolume = Double.MAX_VALUE;
        for (Double hypervolumeAux : hypervolumes) {
            worstHypervolume = Math.min(worstHypervolume, hypervolumeAux);
        }

        //mostrar en areaMetrics1
        String metrics = "Best: " + bestHypervolume + "\n"
                + "Mean: " + hypervolume + "\n"
                + "St.d: " + stdHypervolume + "\n"
                + "Median: " + medianHypervolume + "\n"
                + "Worst: " + worstHypervolume + "\n";

        areaMetrics1.setText(metrics);



        
    }

    private void showMetrics(List<Double[]> solutionParetoFOs,List<Double[]> solution, int indexFO1, int indexFO2) {
        //calcular el indice de la fo1 y fo2
        int fo1 = indexFO1;
        int fo2 = indexFO2;

        //Calcular la tasa de factibilidad en base a la suma de violaciones de restricciones
        // que es la ultima columna de la tabla
        int numSolutions = solutionParetoFOs.size();
        int numFeasible = 0;
        for (Double[] solutionParetoFO : solutionParetoFOs) {
            if (solutionParetoFO[fo2 + 1] == 0) {
                numFeasible++;
            }
        }
        double feasibilityRate = (double) numFeasible / numSolutions;   

        //Calcular la tasa de dominancia segun el optimizador

        int numSolIniciales = 0;
        double hypervolumeAux = 0;
        if (tsmbfoa != null) {
            numSolIniciales = tsmbfoa.getSb();
            //Calcular y guardar en una lista el hipervolumen para cada solucion
            hypervolumeAux = tsmbfoa.calculateHypervolume(solution);
        } else {
            numSolIniciales = pso.getNP();
            //Calcular y guardar en una lista el hipervolumen para cada solucion
            hypervolumeAux = pso.calculateHypervolume(solution);
        }



        int numDominadas = numSolutions;

        double dominanceRate = (double) numDominadas / numSolIniciales;


          
        //mostrar en areaMetrics
        String metrics = "Feasibility rate: " + feasibilityRate + "\n"
                + "Dominance rate: " + dominanceRate + "\n"
                + "Hypervolume: " + hypervolumeAux + "\n";

        areaMetrics.setText(metrics);
    }

    private void showStatistics(List<Double[]> solutionParetoFOs, int indexFO1, int indexFO2, int index) {
        //calcular el indice de la fo1 y fo2
        int fo1 = indexFO1;
        int fo2 = indexFO2;

        //calcular el numero de soluciones
        int numSolutions = solutionParetoFOs.size();

        //calcular el tiempo de ejecucion segun el optimizador
        double time = 0;
        if (tsmbfoa != null) {
             time = tsmbfoa.getTimesSeconds().get(index);
        } else {
            time = pso.getTimesSeconds().get(index);
        }

        //calcular media de la fo1
        double meanFO1 = 0;
        for (Double[] solutionParetoFO : solutionParetoFOs) {
            meanFO1 += solutionParetoFO[fo1];
        }
        meanFO1 /= numSolutions;

        //calcular media de la fo2
        double meanFO2 = 0;
        for (Double[] solutionParetoFO : solutionParetoFOs) {
            meanFO2 += solutionParetoFO[fo2];
        }
        meanFO2 /= numSolutions;

        //calcular desviacion estandar de la fo1
        double stdFO1 = 0;
        for (Double[] solutionParetoFO : solutionParetoFOs) {
            stdFO1 += Math.pow(solutionParetoFO[fo1] - meanFO1, 2);
        }
        stdFO1 = Math.sqrt(stdFO1 / numSolutions);

        //calcular desviacion estandar de la fo2
        double stdFO2 = 0;
        for (Double[] solutionParetoFO : solutionParetoFOs) {
            stdFO2 += Math.pow(solutionParetoFO[fo2] - meanFO2, 2);
        }
        stdFO2 = Math.sqrt(stdFO2 / numSolutions);

        //calcular mediana de la fo1
        double medianFO1 = 0;
        //ordenar por la fo1
        solutionParetoFOs.sort(Comparator.comparingDouble(a -> a[fo1]));

        for (Double[] solutionParetoFO : solutionParetoFOs) {
            medianFO1 += solutionParetoFO[fo1];
        }
        medianFO1 /= numSolutions;

        //calcular mediana de la fo2
        double medianFO2 = 0;
        //ordenar por la fo2
        solutionParetoFOs.sort(Comparator.comparingDouble(a -> a[fo2]));

        for (Double[] solutionParetoFO : solutionParetoFOs) {
            medianFO2 += solutionParetoFO[fo2];
        }
        medianFO2 /= numSolutions;

        //mostrar en areaStatistics
        String statistics = "Number of solutions: " + numSolutions + "\n"
                + "Execution time: " + time + " seconds\n"
                + "Mean f1: " + meanFO1 + "\n"
                + "Mean f2: " + meanFO2 + "\n"
                + "St.d f1: " + stdFO1 + "\n"
                + "St.d f2: " + stdFO2 + "\n"
                + "Median f1: " + medianFO1 + "\n"
                + "Median f2: " + medianFO2 + "\n";

        areaStatistics.setText(statistics);


    }
    
    private void graphPareto(List<Double[]> solutionParetoFOs, int indexFO1, int indexFO2){

        //calcular el indice de la fo1 y fo2
        int fo1 = indexFO1;
        int fo2 = indexFO2;


        // Ordenar la lista de soluciones Pareto por la primera función objetivo
        solutionParetoFOs.sort(Comparator.comparingDouble(a -> a[fo1]));

        // Crear un nuevo dataset
        DefaultXYDataset dataset = new DefaultXYDataset();

        // Crear un nuevo array de double
        double[][] data = new double[2][solutionParetoFOs.size()];

        // Calcular los valores mínimos y máximos para las funciones objetivo
        double minFO1 = Double.MAX_VALUE;
        double maxFO1 = Double.MIN_VALUE;
        double minFO2 = Double.MAX_VALUE;
        double maxFO2 = Double.MIN_VALUE;

        // Llenar el array con los valores de las funciones objetivos
        for (int i = 0; i < solutionParetoFOs.size(); i++) {
            double fo1Aux = solutionParetoFOs.get(i)[fo1];
            double fo2Aux = solutionParetoFOs.get(i)[fo2];

            data[0][i] = fo1Aux;
            data[1][i] = fo2Aux;

            minFO1 = Math.min(minFO1, fo1Aux);
            maxFO1 = Math.max(maxFO1, fo1Aux);
            minFO2 = Math.min(minFO2, fo2Aux);
            maxFO2 = Math.max(maxFO2, fo2Aux);
        }

        // Agregar el array al dataset
        dataset.addSeries("Pareto", data);

        // Crear un nuevo gráfico
        JFreeChart chart = ChartFactory.createScatterPlot("Pareto Front", "f1", "f2", dataset, PlotOrientation.VERTICAL, true, true, false);

        // Obtener el plot del gráfico
        XYPlot plot = chart.getXYPlot();

        // Establecer el rango de los ejes en función de las funciones objetivo
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(minFO1, maxFO1);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(minFO2, maxFO2);
        

        
        // Crear un nuevo renderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        // Crear un círculo como un objeto Shape
        Shape circle = new Ellipse2D.Double(-3, -3, 6, 6);

        // Usar el círculo para representar los puntos en el gráfico
        renderer.setSeriesShape(0, circle);
        // Cambiar el color de los círculos a rojo
        renderer.setSeriesPaint(0, Color.BLUE);
        // Setear el renderer en el plot
        plot.setRenderer(renderer);

        // Setear el gráfico en el panel
        ChartPanel chartPane = new ChartPanel(chart);
        graph(panelChart, chartPane);
    }
    
    private void graph(JPanel panel, ChartPanel chartPane){
        panel.removeAll(); // Limpiar el panel antes de agregar el nuevo gráfico
        panel.add(chartPane);
        chartPane.setBounds(0, 0, panel.getWidth(), panel.getHeight());    
        panel.validate();
        panel.repaint();
    }



   
}
