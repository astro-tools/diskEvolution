package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class SimulationRunnerView extends JPanel {
	private static final long serialVersionUID = 1L;
	private ScalarInputView timeStepInputView;
	private ScalarInputView rmin;
	private ScalarInputView rmax;
	private ScalarInputView deltar0;
	private ScalarInputView intervalCount;
    private SimulationRunner runner;

	public SimulationRunnerView(SimulationRunner runner) {
	    this.runner = runner;
		ActionListener listener = createListener();
		setupLabelsAndFields(listener);
        setFieldValuesFromData();
	}
	
	private void setFieldValuesFromData() {
        double timeStep = runner.getSimulationTimeStep();
        timeStepInputView.setValue(timeStep);
    }


    private void setupLabelsAndFields(ActionListener listener) {
	    setupLoopParameters(listener);
	    setupGridParameters(listener);
	    setupExecutionButtons();
	}

    private void setupLoopParameters(ActionListener listener) {
        ScalarListView list = new ScalarListView();
        list.setAlignmentX(LEFT_ALIGNMENT);
        add(list);
        
        Unit day = new Unit("days", "days", PhysicalConstants.day);
    
        timeStepInputView = new ScalarInputView("Time Step", day);
        list.add(timeStepInputView);
        list.addActionListener(listener);
    }

    private void setupGridParameters(ActionListener listener) {
        ScalarListView list = new ScalarListView();
        list.setAlignmentX(LEFT_ALIGNMENT);
        add(list);

        Unit earthRadius = new Unit("R⊕", "R<sub>⊕</sub>",
	            PhysicalConstants.earthRadiusInCm);
        
	    Unit noUnit = new Unit("", "", 1.0);

	    rmin = new ScalarInputView("R<sub>min</sub>", earthRadius);
	    list.add(rmin);
	    list.addActionListener(listener);
	   
	    rmax = new ScalarInputView("R<sub>max</sub>", earthRadius);
	    list.add(rmax);
	    list.addActionListener(listener);
	    
	    deltar0 = new ScalarInputView("ΔR<sub>0</sub>", earthRadius);
	    list.add(deltar0);
	    list.addActionListener(listener);
	    
	    intervalCount = new ScalarInputView("Interval Count", noUnit);
	    list.add(intervalCount);
	    list.addActionListener(listener);
    }

    private void setupExecutionButtons() {
        JButton startButton = new JButton("Run Simulation");
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        add(startButton);
        
        JButton pauseButton = new JButton("Pause");
        pauseButton.setAlignmentX(CENTER_ALIGNMENT);
        add(pauseButton);
    }

    public ActionListener createListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateParameterValuesFromFields();
			}
		};
		return listener;
	}

	protected void updateParameterValuesFromFields()  {
	    try {
            double timeStep = timeStepInputView.getValue();
            runner.setSimulationTimeStep(timeStep);
        } catch (NumberFormatException exception) {
        }
        setFieldValuesFromData();
	}


}
