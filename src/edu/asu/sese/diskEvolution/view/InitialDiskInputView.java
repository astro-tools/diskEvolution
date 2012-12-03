package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class InitialDiskInputView extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private InitialConditions conditions;

    private JTextField rminField;
    private JTextField rmaxField;
    private JTextField density0Field;
    private JTextField radius0Field;
    private JTextField exponentField;

    public InitialDiskInputView(InitialConditions initialConditions) {
        this.conditions = initialConditions;
        ActionListener listener = createListener();
        setupLabelsAndFields(listener);
        setFieldValuesFromData();
    }

    private void setTextFieldValue(JTextField field, double value, double scale) {
        Double scaledValue = value / scale;
        field.setText(scaledValue.toString());
    }
    
    private void setFieldValuesFromData() {
        double lengthScale = PhysicalConstants.earthRadiusInCm;
        setTextFieldValue(rminField, conditions.getRMin(), lengthScale);
        setTextFieldValue(rmaxField, conditions.getRMax(), lengthScale);
        setTextFieldValue(radius0Field, conditions.getRadius0(), lengthScale);
        setTextFieldValue(density0Field, conditions.getDensity0(), 1.0);
        density0Field.setText(Double.toString(conditions.getDensity0()));
        exponentField.setText(Double.toString(conditions.getExponent()));
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
            double diskRmin = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRMin(diskRmin);
        } catch (NumberFormatException exception) {
            // do nothing
        }
        
        string = rmaxField.getText();
        try {
            double rmax = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRMax(rmax);
        } catch (NumberFormatException exception) {
        }
               
        string = density0Field.getText();
        try {
            double density0 = Double.parseDouble(string);
            conditions.setDensity0(density0);
        } catch (NumberFormatException exception) {
        }
        
        string = radius0Field.getText();
        try {
            double radius0 = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRadius0(radius0);
        } catch (NumberFormatException exception) {
        }
        
        string = exponentField.getText();
        try {
            double exponent = Double.parseDouble(string);
            conditions.setExponent(exponent);
        } catch (NumberFormatException exception) {
        }        
        setFieldValuesFromData();
    }
}
