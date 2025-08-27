
package com.garcialopez.ui;

import com.jtattoo.plaf.aero.AeroLookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.garcialopez.optimizationmodel.CNOP;

/**
 *
 * @author adrian
 */
public class RunUI {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new AeroLookAndFeel());

        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error de LookAndFeel");
        }

        //abrir UI MainUI
        MainUI mainUI = new MainUI();
        mainUI.setVisible(true);


    }
}
