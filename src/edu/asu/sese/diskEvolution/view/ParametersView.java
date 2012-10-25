package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.model.Parameters;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class ParametersView extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private Parameters parameters;

    private JTextField rminField;
    private JTextField rmaxField;
    private JTextField deltar0Field;
    private JTextField intervalCountField;    
    private JTextField density0Field;
    private JTextField radius0Field;
    private JTextField exponentField;

    public ParametersView(DiskSimulation simulation) {
        parameters = simulation.getSimulationData();
        ActionListener listener = createListener();
        setupLabelsAndFields(listener);
        setFieldValuesFromData();
    }

    private void setFieldValuesFromData() {
        rminField.setText(Double.toString(parameters.getRmin()/PhysicalConstants.earthRadiusInCm));
        rmaxField.setText(Double.toString(parameters.getRmax()/PhysicalConstants.earthRadiusInCm));
        deltar0Field.setText(Double.toString(parameters.getDeltar0()/PhysicalConstants.earthRadiusInCm));
        intervalCountField.setText(Integer.toString(parameters.getIntervalCount()));
        density0Field.setText(Double.toString(parameters.getDensity0()));
        radius0Field.setText(Double.toString(parameters.getRadius0()/PhysicalConstants.earthRadiusInCm));
        exponentField.setText(Double.toString(parameters.getExponent()));
    }

    private void setupLabelsAndFields(ActionListener listener) {
        GridLayout layoutManager = new GridLayout(0, 2, 4, 4);
        setLayout(layoutManager);
      
        JLabel rminLabel = new JLabel("<html>r<sub>min</sub>");
        add(rminLabel);
        rminField = new JTextField();
        rminField.addActionListener(listener);
        add(rminField);

        JLabel rmaxLabel = new JLabel("<html>r<sub>max</sub>");
        add(rmaxLabel);
        rmaxField = new JTextField();
        rmaxField.addActionListener(listener);
        add(rmaxField);
        
        JLabel deltar0Label = new JLabel("<html>Δ r<sub>0</sub>");
        add(deltar0Label);
        deltar0Field = new JTextField();
        deltar0Field.addActionListener(listener);
        add(deltar0Field);
        
        JLabel intervalCountLabel = new JLabel("<html>N<sub>interval</sub>");
        add(intervalCountLabel);
        intervalCountField = new JTextField();
        intervalCountField.addActionListener(listener);
        add(intervalCountField);
        
        JLabel density0Label = new JLabel("<html>ρ<sub>0</sub>");
        add(density0Label);
        density0Field = new JTextField();
        density0Field.addActionListener(listener);
        add(density0Field);

        JLabel radius0Label = new JLabel("<html>r<sub>0</sub>");
        add(radius0Label);
        radius0Field = new JTextField();
        radius0Field.addActionListener(listener);
        add(radius0Field);
        
        JLabel exponentLabel = new JLabel("<html>p (Σ ~ r<sup>p</sup>)");
        add(exponentLabel);
        exponentField = new JTextField();
        exponentField.addActionListener(listener);
        add(exponentField);
                
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
        String string = rminField.getText();
        try {
            double diskRmin = Double.parseDouble(string) * PhysicalConstants.earthRadiusInCm;
            parameters.setRmin(diskRmin);
        } catch (NumberFormatException exception) {
            // do nothing
        }
        
        string = rmaxField.getText();
        try {
            double rmax = Double.parseDouble(string) * PhysicalConstants.earthRadiusInCm;
            parameters.setRmax(rmax);
        } catch (NumberFormatException exception) {
        }
       
        string = deltar0Field.getText();
        try {
            double deltar0 = Double.parseDouble(string) * PhysicalConstants.earthRadiusInCm;
            parameters.setDeltar0(deltar0);
        } catch (NumberFormatException exception) {

        }
       
        string = intervalCountField.getText();
        try {
            int intervalCount = Integer.parseInt(string);
            parameters.setIntervalCount(intervalCount);
        } catch (NumberFormatException exception) {
        }
        
        string = density0Field.getText();
        try {
            double density0 = Double.parseDouble(string);
            parameters.setDensity0(density0);
        } catch (NumberFormatException exception) {
        }
        
        string = radius0Field.getText();
        try {
            double radius0 = Double.parseDouble(string) * PhysicalConstants.earthRadiusInCm;
            parameters.setRadius0(radius0);
        } catch (NumberFormatException exception) {
        }
        
        string = exponentField.getText();
        try {
            double exponent = Double.parseDouble(string);
            parameters.setExponent(exponent);
        } catch (NumberFormatException exception) {
        }        
        setFieldValuesFromData();
    }
}
