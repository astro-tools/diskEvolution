package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class InitialConditionsView extends JPanel {

	private static final long serialVersionUID = 1L;
	private DiskView view;
    private DiskSimulation simulation;
    
    public InitialConditionsView(DiskSimulation simulation) {
        this.simulation = simulation;
        BorderLayout layoutManager = new BorderLayout();
        setLayout(layoutManager);
        setupDiskView();
        setupInputView();
    }

    private void setupDiskView() {
        view = new DiskView(simulation);
        add(view, BorderLayout.CENTER);
    }

    private void setupInputView() {
        JPanel panel = new ParametersView(simulation);
        add(panel, BorderLayout.LINE_START);        
    }

}
