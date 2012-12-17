package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class SimulationRunnerView extends JPanel {
	private static final long serialVersionUID = 1L;
	private ScalarInputView timeStepInputView;
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
	    ScalarListInputView list = new ScalarListInputView();
	    add(list);
	    
	    Unit unit = new Unit("days", "days", PhysicalConstants.day);
	    timeStepInputView = new ScalarInputView("Time Step", unit);
	    list.add(timeStepInputView);
	    list.addActionListener(listener);
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
