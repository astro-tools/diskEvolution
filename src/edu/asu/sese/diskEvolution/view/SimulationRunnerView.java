package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class SimulationRunnerView extends JPanel {
	private static final long serialVersionUID = 1L;
	private ScalarInputView timeStepInputView;

	public SimulationRunnerView() {
		ActionListener listener = null;
		setupLabelsAndFields(listener);
	}
	
	
	private void setupLabelsAndFields(ActionListener listener) {
	    ScalarListInputView list = new ScalarListInputView();
	    add(list);
	    
	    Unit unit = new Unit("yr", "yr", PhysicalConstants.year);
	    timeStepInputView = new ScalarInputView("Time Step", unit);
	    list.add(timeStepInputView);
	    list.addActionListener(listener);
	}

	private double getTimeStepInput() {
		return 0;
	}

	public ActionListener createListener() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				updateParameterValuesFromFields();
			}
		};
		return listener;
	}

	protected void updateParameterValuesFromFields() {
		String string = timeStepInputView.getText();
		try {
			double timeStepInput = Double.parseDouble(string);
			setTimeStepInput(timeStepInput);
			System.out.println("timeStepInput = "
					+ Double.toString(getTimeStepInput()));
		} catch (NumberFormatException exception) {
			// do nothing
		}
	}

	private void setTimeStepInput(double timeStepInput) {
	}

}
