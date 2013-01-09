package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.Application;
import edu.asu.sese.diskEvolution.model.InitialConditions;

public class InitialConditionsView extends JPanel {

	private static final long serialVersionUID = 1L;
	private InitialDiskView view;
    private InitialConditions initialConditions;
    
    public InitialConditionsView(Application simulation) {
        initialConditions = simulation.getInitialConditions();
        setLayout(new BorderLayout());
        setupDiskView();
        setupInputView();
    }

    private void setupDiskView() {
        view = new InitialDiskView(initialConditions);
        add(view, BorderLayout.CENTER);
    }

    private void setupInputView() {
        JPanel panel = new InitialConditionsInputView(initialConditions);
        add(panel, BorderLayout.LINE_START);        
    }

}
