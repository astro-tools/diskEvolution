package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.model.Parameters;

public class ParametersView extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private Parameters data;

    private JTextField rminField;
    private JTextField rmaxField;
    private JTextField deltar0Field;
    private JTextField intervalCountField;
    
    private JTextField density0Field;
    private JTextField radius0Field;

    private JTextField exponentField;

    public ParametersView(DiskSimulation simulation) {
        data = simulation.getSimulationData();
        setupLabelsAndFields();
        setFieldValuesFromData();
    }

    private void setFieldValuesFromData() {
        rminField.setText(Double.toString(data.getRmin()));
        rmaxField.setText(Double.toString(data.getRmax()));
        deltar0Field.setText(Double.toString(data.getDeltar0()));
        intervalCountField.setText(Double.toString(data.getIntervalCount()));
        
        density0Field.setText(Double.toString(data.getDensity0()));
        radius0Field.setText(Double.toString(data.getRadius0()));
        exponentField.setText(Double.toString(data.getExponent()));
    }

    private void setupLabelsAndFields() {
        GridLayout layoutManager = new GridLayout(0, 2, 4, 4);
        setLayout(layoutManager);
      
        JLabel rminLabel = new JLabel("<html>r<sub>min</sub>");
        add(rminLabel);
        rminField = new JTextField();
        add(rminField);

        JLabel rmaxLabel = new JLabel("<html>r<sub>max</sub>");
        add(rmaxLabel);
        rmaxField = new JTextField();
        add(rmaxField);
        
        JLabel deltar0Label = new JLabel("<html>Δ r<sub>0</sub>");
        add(deltar0Label);
        deltar0Field = new JTextField();
        add(deltar0Field);
        
        JLabel intervalCountLabel = new JLabel("<html>N<sub>interval</sub>");
        add(intervalCountLabel);
        intervalCountField = new JTextField();
        add(intervalCountField);
        
        JLabel density0Label = new JLabel("<html>ρ<sub>0</sub>");
        add(density0Label);
        density0Field = new JTextField();
        add(density0Field);

        JLabel radius0Label = new JLabel("<html>r<sub>0</sub>");
        add(radius0Label);
        radius0Field = new JTextField();
        add(radius0Field);
        
        JLabel exponentLabel = new JLabel("<html>p (Σ ~ r<sup>p</sup>)");
        add(exponentLabel);
        exponentField = new JTextField();
        add(exponentField);
                
    }

}
