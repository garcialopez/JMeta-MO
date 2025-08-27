package com.garcialopez.ui;

import com.garcialopez.cmops.*;
import com.garcialopez.metaheuristic.particleswarm.PSO;
import com.garcialopez.optimizationmodel.CNOP;
import com.garcialopez.metaheuristic.tsmbfoa.TSMBFOA;

import javax.swing.*;

public class MainUI extends javax.swing.JFrame {

    CNOP cnop = null;
    TSMBFOA tsmbfoa = null;
    PSO pso = null;
    SwingWorker<Void, Void> worker = null;

    public MainUI() {
        initComponents();
        //extender
        this.setExtendedState(MAXIMIZED_BOTH);

        //iniciamos el boxCNOPs
        loadCNOPs();




        //inicializamos el boxOptimizer
        loadOptimizer();

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
        jLabel1 = new javax.swing.JLabel();
        boxCNOPs = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        boxOptimizer = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        spinnerIteration = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        spinnerEvaluation = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        spinnerPopulation = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        contenerdorPanel = new javax.swing.JTabbedPane();
        pnpTSMBFOA = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtStepSize = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        spinnerChemotaxicCycles = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        txtScalingFactor = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        spinerBacteriaReproduce = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        spinnerReproductionFrequency = new javax.swing.JSpinner();
        pnpPSO = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtInertia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtC2 = new javax.swing.JTextField();
        txtC1 = new javax.swing.JTextField();
        panelCenter = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        btnResults = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtNameProblem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtKNownValue = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaRanges = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaFunctions = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaConstrainsts = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        panelLeft.setPreferredSize(new java.awt.Dimension(400, 358));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Step 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel1.setLayout(new java.awt.GridLayout(4, 0));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel1.setText("Preloaded CNOPs");
        jPanel1.add(jLabel1);

        boxCNOPs.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        boxCNOPs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxCNOPs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCNOPsActionPerformed(evt);
            }
        });
        jPanel1.add(boxCNOPs);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel3.setText("Optimizer");
        jPanel1.add(jLabel3);

        boxOptimizer.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        boxOptimizer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        boxOptimizer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxOptimizerActionPerformed(evt);
            }
        });
        jPanel1.add(boxOptimizer);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Step 2 - General parameters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel4.setLayout(new java.awt.GridLayout(3, 0, 0, 5));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel6.setText("Iterations");
        jPanel4.add(jLabel6);

        spinnerIteration.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        spinnerIteration.setModel(new javax.swing.SpinnerNumberModel(25, 1, 30, 1));
        jPanel4.add(spinnerIteration);

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel7.setText("Evaluations by Iterations");
        jPanel4.add(jLabel7);

        spinnerEvaluation.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        spinnerEvaluation.setModel(new javax.swing.SpinnerNumberModel(20000, 1000, 100000, 500));
        jPanel4.add(spinnerEvaluation);

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel8.setText("Initial population");
        jPanel4.add(jLabel8);

        spinnerPopulation.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        spinnerPopulation.setModel(new javax.swing.SpinnerNumberModel(100, 14, 500, 1));
        jPanel4.add(spinnerPopulation);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Step 2 - Customize parameters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        contenerdorPanel.setToolTipText("");

        pnpTSMBFOA.setLayout(new java.awt.GridLayout(5, 0, 0, 4));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel9.setText("Stepsize");
        pnpTSMBFOA.add(jLabel9);

        txtStepSize.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        txtStepSize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtStepSize.setText("0.0005");
        pnpTSMBFOA.add(txtStepSize);

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel10.setText("Chemotaxic Cycles");
        pnpTSMBFOA.add(jLabel10);

        spinnerChemotaxicCycles.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        spinnerChemotaxicCycles.setModel(new javax.swing.SpinnerNumberModel(50, 7, 500, 1));
        pnpTSMBFOA.add(spinnerChemotaxicCycles);

        jLabel11.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel11.setText("Scaling Factor");
        pnpTSMBFOA.add(jLabel11);

        txtScalingFactor.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        txtScalingFactor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtScalingFactor.setText("1.95");
        pnpTSMBFOA.add(txtScalingFactor);

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel12.setText("Bacteria to reproduce");
        pnpTSMBFOA.add(jLabel12);

        spinerBacteriaReproduce.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        spinerBacteriaReproduce.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        pnpTSMBFOA.add(spinerBacteriaReproduce);

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel13.setText("Reproduction frequency");
        pnpTSMBFOA.add(jLabel13);

        spinnerReproductionFrequency.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        spinnerReproductionFrequency.setModel(new javax.swing.SpinnerNumberModel(100, 1, 500, 1));
        pnpTSMBFOA.add(spinnerReproductionFrequency);

        contenerdorPanel.addTab("TS-MBFOA", pnpTSMBFOA);

        jLabel16.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel16.setText("Inertia");

        txtInertia.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        txtInertia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtInertia.setText("0.729844");

        jLabel17.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel17.setText("Cognitive coefficient");

        jLabel18.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel18.setText("Social coefficient");

        txtC2.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        txtC2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtC2.setText("2");

        txtC1.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        txtC1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtC1.setText("2");

        javax.swing.GroupLayout pnpPSOLayout = new javax.swing.GroupLayout(pnpPSO);
        pnpPSO.setLayout(pnpPSOLayout);
        pnpPSOLayout.setHorizontalGroup(
            pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnpPSOLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnpPSOLayout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(pnpPSOLayout.createSequentialGroup()
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)))
                    .addGroup(pnpPSOLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtC1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtInertia)
                    .addComponent(txtC2))
                .addContainerGap())
        );
        pnpPSOLayout.setVerticalGroup(
            pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnpPSOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInertia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtC1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnpPSOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtC2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        contenerdorPanel.addTab("PSO", pnpPSO);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenerdorPanel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenerdorPanel))
        );

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        panelBody.add(panelLeft, java.awt.BorderLayout.LINE_START);

        panelCenter.setLayout(new javax.swing.BoxLayout(panelCenter, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Start searching for solutions", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(404, 70));

        btnStart.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnStart.setForeground(new java.awt.Color(0, 102, 0));
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jPanel2.add(btnStart);

        btnStop.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnStop.setForeground(new java.awt.Color(204, 0, 0));
        btnStop.setText("Stop");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        jPanel2.add(btnStop);

        progressBar.setForeground(new java.awt.Color(0, 153, 51));
        progressBar.setPreferredSize(new java.awt.Dimension(146, 20));
        jPanel2.add(progressBar);

        btnResults.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        btnResults.setForeground(new java.awt.Color(0, 0, 255));
        btnResults.setText("View Results");
        btnResults.setEnabled(false);
        btnResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultsActionPerformed(evt);
            }
        });
        jPanel2.add(btnResults);

        panelCenter.add(jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problem details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 255))); // NOI18N

        jPanel8.setLayout(new java.awt.GridLayout(2, 0, 0, 6));

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel14.setText("Name problem");
        jPanel8.add(jLabel14);

        txtNameProblem.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        jPanel8.add(txtNameProblem);

        jLabel15.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel15.setText("Known value");
        jPanel8.add(jLabel15);

        txtKNownValue.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        txtKNownValue.setText("1000000");
        jPanel8.add(txtKNownValue);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Variable ranges", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 16))); // NOI18N
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.Y_AXIS));

        areaRanges.setColumns(20);
        areaRanges.setRows(5);
        jScrollPane1.setViewportView(areaRanges);

        jPanel9.add(jScrollPane1);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Functions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 16))); // NOI18N
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.Y_AXIS));

        areaFunctions.setColumns(20);
        areaFunctions.setRows(5);
        jScrollPane2.setViewportView(areaFunctions);

        jPanel10.add(jScrollPane2);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Constraints", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Liberation Sans", 1, 16))); // NOI18N
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.Y_AXIS));

        areaConstrainsts.setColumns(20);
        areaConstrainsts.setRows(5);
        jScrollPane3.setViewportView(areaConstrainsts);

        jPanel11.add(jScrollPane3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        panelCenter.add(jPanel7);

        panelBody.add(panelCenter, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        btnResults.setEnabled(false);
        //vaciar el progressBar
        progressBar.setValue(20);
        worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {

                //capturamos del boxCNOPs el indice seleccionado
                int indexCNOP = boxCNOPs.getSelectedIndex();

                //en un swicth instaciomos el objeto cnop segiun indiceseleccionado
                switch (indexCNOP) {
                    case 0:
                        cnop = new Cantilever();
                        break;
                    case 1:
                        cnop = new DesignDiscBrake();
                        break;
                }

                //capturamos el indice del optimizador seleccionado
                int indexOptimizer = boxOptimizer.getSelectedIndex();

                int iteration = (int) spinnerIteration.getValue();

                int evaluation = (int) spinnerEvaluation.getValue();
                int population = (int) spinnerPopulation.getValue();

                if (indexOptimizer == 0) {
                    double stepSize = Double.parseDouble(txtStepSize.getText());
                    int chemotaxicCycles = (int) spinnerChemotaxicCycles.getValue();
                    double scalingFactor = Double.parseDouble(txtScalingFactor.getText());
                    int bacteriaReproduce = (int) spinerBacteriaReproduce.getValue();
                    int reproductionFrequency = (int) spinnerReproductionFrequency.getValue();

                    tsmbfoa = new TSMBFOA(cnop, false);
                    tsmbfoa.setExecutions(iteration);

                    tsmbfoa.setSb(population);
                    tsmbfoa.setStepSize(stepSize);
                    tsmbfoa.setNc(chemotaxicCycles);
                    tsmbfoa.setScalingFactor(scalingFactor);
                    tsmbfoa.setBacteriaReproduce(bacteriaReproduce);
                    tsmbfoa.setRepcycle(reproductionFrequency);
                    tsmbfoa.setEvaluations(evaluation);

                    tsmbfoa.run();

                    // Actualiza la barra de progreso con el progreso del algoritmo
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            progressBar.setValue(tsmbfoa.getProgress());
                        }
                    });

                }else {
                
                    double inertia = Double.parseDouble(txtInertia.getText());
                    double c1 = Double.parseDouble(txtC1.getText());
                    double c2 = Double.parseDouble(txtC2.getText());

                    pso = new PSO(cnop, false);
                    pso.setExecutions(iteration);
                    pso.setEvaluations(evaluation);
                    pso.setNP(population);
                    pso.setW(inertia);
                    pso.setC1(c1);
                    pso.setC2(c2);

                    pso.run();

                    // Actualiza la barra de progreso con el progreso del algoritmo
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            progressBar.setValue(pso.getProgress());
                        }
                    });
                }

                btnResults.setEnabled(true);

                return null;
            }
        };

        // Inicia el SwingWorker
        worker.execute();


    }//GEN-LAST:event_btnStartActionPerformed

    // metodo para cargar los CNOPs en Preloaded CNOPs en boxCNOPs
    private void loadCNOPs() {
        // cargar los CNOPs en boxCNOPs
        boxCNOPs.removeAllItems();
        boxCNOPs.addItem("Cantilever");
        boxCNOPs.addItem("Design of a Disc Brake");
        boxCNOPs.addItem("Vibrating Platform");
        boxCNOPs.addItem("Two Bar Truss Design");
        boxCNOPs.addItem("Welded Beam Design");
        boxCNOPs.addItem("Two Bar Plane Truss");
        boxCNOPs.addItem("Simply Supported I-Beam Design");
        boxCNOPs.addItem("Multiple-disk Clutch Brake Design");
        boxCNOPs.addItem("Front Rail Design");
        boxCNOPs.addItem("Hydro-static Thrust Bearing Design");
    } // cierra loadCNOPs
    
    private void boxCNOPsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCNOPsActionPerformed
        // obtener indice seleccionado
        int indexCNOP = boxCNOPs.getSelectedIndex();
        // segun el indice seleccionado
        switch (indexCNOP) {
            case 0:
                cnop = new Cantilever();
                showDetails(cnop);
                break;
            case 1:
                cnop = new DesignDiscBrake();
                showDetails(cnop);
                break;
            case 2:
                cnop = new VibratingPlatform();
                showDetails(cnop);
                break;
            case 3:
                cnop = new TwoBarTrussDesign();
                showDetails(cnop);
                break;
            case 4:
                cnop = new WeldedBeamDesign();
                showDetails(cnop);
                break;
            case 5:
                cnop = new TwoBarPlaneTruss();
                showDetails(cnop);
                break;
            case 6:
                cnop = new SimplySupportedIBeamDesign();
                showDetails(cnop);
                break;
            case 7:
                cnop = new MultipleDiskClutchBrakeDesign();
                showDetails(cnop);
                break;
            case 8:
                cnop = new FrontRailDesign();
                showDetails(cnop);
                break;
            case 9:
                cnop = new HydroStaticThrustBearingDesign();
                showDetails(cnop);
                break;
        }
    }//GEN-LAST:event_boxCNOPsActionPerformed

    private void btnResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultsActionPerformed
        //abrir la ventana de resultados segun el optimizador seleccionado

        if (boxOptimizer.getSelectedIndex() == 0) {
            ResultsUI resultsUI = new ResultsUI(cnop, tsmbfoa);
            resultsUI.setVisible(true);
        } else {
            ResultsUI resultsUI = new ResultsUI(cnop, pso);
            resultsUI.setVisible(true);
        }



    }//GEN-LAST:event_btnResultsActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed


    }//GEN-LAST:event_btnStopActionPerformed

    private void boxOptimizerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxOptimizerActionPerformed
        // obtener indice seleccionado
        int indexOptimizer = boxOptimizer.getSelectedIndex();
        // segun el indice seleccionado
        switch (indexOptimizer) {
            case 0:
                //mostrar panel de configuracion de tsmbfoa
                contenerdorPanel.setSelectedIndex(0);
                contenerdorPanel.setEnabledAt(0, true);
                contenerdorPanel.setEnabledAt(1, false);

                break;
            case 1:
                //mostrar panel de configuracion de pso
                contenerdorPanel.setSelectedIndex(1);
                contenerdorPanel.setEnabledAt(1, true);
                contenerdorPanel.setEnabledAt(0, false);
                break;
        }
    }//GEN-LAST:event_boxOptimizerActionPerformed

    //metodo para mostrar en el panel de detalles
    private void showDetails(CNOP cnop) {
        //mostrar el nombre del problema
        txtNameProblem.setText(cnop.getNameProblem());

        //mostrar el valor conocido
        txtKNownValue.setText(String.valueOf(cnop.getBestKnownValue()));

        //mostrar los rangos de las variables
        double[][] ranges = cnop.getVariableRange();
        String rangesStr = "";
        for (int i = 0; i < ranges.length; i++) {
            rangesStr += "x" + (i + 1) + ": [" + ranges[i][0] + ", " + ranges[i][1] + "]\n";
        }
        areaRanges.setText(rangesStr);

        //mostrar las funciones
        String[][] functions = cnop.getObjetives();

        String functionsStr = "";
        for (int i = 0; i < functions.length; i++) {
            functionsStr += functions[i][1] + ": " + functions[i][0] + "\n";
        }
        areaFunctions.setText(functionsStr);

        String[][] constraintsInequality = cnop.getConstraintsInequality();
        String[][] constraintsEquality = cnop.getConstraintsEquality();
        System.out.println(constraintsInequality.length);
        String constraintsStr = "";
        for (int i = 0; i < constraintsInequality.length; i++) {

            constraintsStr += "g" + (i + 1) + "(X)" + constraintsInequality[i][0] + " " + constraintsInequality[i][1] + " " + constraintsInequality[i][2] + "\n";
        }
        for (int i = 0; i < constraintsEquality.length; i++) {
            constraintsStr += "h" + (i + 1) + "(X)" + constraintsEquality[i][0] + " " + constraintsEquality[i][1] + " " + constraintsEquality[i][2] + "\n";
        }
        areaConstrainsts.setText(constraintsStr);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaConstrainsts;
    private javax.swing.JTextArea areaFunctions;
    private javax.swing.JTextArea areaRanges;
    private javax.swing.JComboBox<String> boxCNOPs;
    private javax.swing.JComboBox<String> boxOptimizer;
    private javax.swing.JButton btnResults;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JTabbedPane contenerdorPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel pnpPSO;
    private javax.swing.JPanel pnpTSMBFOA;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JSpinner spinerBacteriaReproduce;
    private javax.swing.JSpinner spinnerChemotaxicCycles;
    private javax.swing.JSpinner spinnerEvaluation;
    private javax.swing.JSpinner spinnerIteration;
    private javax.swing.JSpinner spinnerPopulation;
    private javax.swing.JSpinner spinnerReproductionFrequency;
    private javax.swing.JTextField txtC1;
    private javax.swing.JTextField txtC2;
    private javax.swing.JTextField txtInertia;
    private javax.swing.JTextField txtKNownValue;
    private javax.swing.JTextField txtNameProblem;
    private javax.swing.JTextField txtScalingFactor;
    private javax.swing.JTextField txtStepSize;
    // End of variables declaration//GEN-END:variables

    

    // metodo para cargar optimizadores en boxOptimizer
    private void loadOptimizer() {
        // cargar los CNOPs en boxOptimizer
        boxOptimizer.removeAllItems();
        boxOptimizer.addItem("TS-MBFOA");
        boxOptimizer.addItem("PSO");
    } // Close loadOptimizer

}
