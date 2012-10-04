package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;


public class SimulationRunnerView<timeStepInputLabel> extends JPanel {
	private JTextField timeStepInputField;
	
	private void setupLabelsAndFields(ActionListener listener) {
	        GridLayout layoutManager = new GridLayout(0, 2, 4, 4);
	        setLayout(layoutManager);
	
	JLabel TimeStepInputLabel = new JLabel("<html>Time<sub>Step<sub>Input</sub>");
    add(TimeStepInputLabel);
    timeStepInputField = new JTextField();
    timeStepInputField.addActionListener(listener);
    add(timeStepInputField);
	}
	private void setFieldValuesFromData() {
        timeStepInputField.setText(Double.toString(getTimeStepInput()));
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
	        String string = timeStepInputField.getText();
	        try {
	            double timeStepInput = Double.parseDouble(string);
	            setTimeStepInput(timeStepInput);
	            System.out.println("timeStepInput = " + Double.toString(getTimeStepInput()));
	        }
	        catch (NumberFormatException exception) {
	            // do nothing
	        }
		}
		private void setTimeStepInput(double timeStepInput) {
		}
			
}          
	  
	 
	 
	 
	 

