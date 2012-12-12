package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class InitialDiskInputView extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private InitialConditions conditions;

    private ScalarInputView rminInputView;
    private ScalarInputView rmaxInputView;
    private ScalarInputView density0InputView;
    private ScalarInputView radius0InputView;
    private ScalarInputView exponentInputView;
    

    public InitialDiskInputView(InitialConditions initialConditions) {
        this.conditions = initialConditions;
        ActionListener listener = createListener();
        setupLabelsAndFields(listener);
        setFieldValuesFromData();
    }

    private void setFieldValuesFromData() {
        double lengthScale = PhysicalConstants.earthRadiusInCm;
        setScalarInputViewValue(rminInputView, conditions.getRMin(), lengthScale);
        setScalarInputViewValue(rmaxInputView, conditions.getRMax(), lengthScale);
        setScalarInputViewValue(radius0InputView, conditions.getRadius0(), lengthScale);
        setScalarInputViewValue(density0InputView, conditions.getDensity0(), 1.0);
        density0InputView.setText(Double.toString(conditions.getDensity0()));
        exponentInputView.setText(Double.toString(conditions.getExponent()));
    }

    private void setScalarInputViewValue(ScalarInputView view,
            double value, double scale) {
        Double scaledValue = value / scale;
        view.setFieldText(scaledValue.toString());
    }

    private void setupLabelsAndFields(ActionListener listener) {
        GridLayout layoutManager = new GridLayout(0, 2, 4, 4);
        setLayout(layoutManager);
      
        rminInputView = new ScalarInputView("<html>r<sub>min</sub>");
        add(rminInputView.getLabel());
        add(rminInputView.getTextField());
        rminInputView.addActionListener(listener);

        rmaxInputView = new ScalarInputView("<html>r<sub>max</sub>");
        add(rmaxInputView.getLabel());
        add(rmaxInputView.getTextField());
        rmaxInputView.addActionListener(listener);
                
        density0InputView = new ScalarInputView("<html>ρ<sub>0</sub>");
        add(density0InputView.getLabel());
        add(density0InputView.getTextField());
        density0InputView.addActionListener(listener);

        radius0InputView = new ScalarInputView("<html>r<sub>0</sub>");
        add(radius0InputView.getLabel());
        add(radius0InputView.getTextField());
        radius0InputView.addActionListener(listener);
        
        exponentInputView = new ScalarInputView("<html>p (Σ ~ r<sup>p</sup>)");
        add(exponentInputView.getLabel());
        add(exponentInputView.getTextField());
        exponentInputView.addActionListener(listener);
                
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
        String string = rminInputView.getText();
        try {
            double diskRmin = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRMin(diskRmin);
        } catch (NumberFormatException exception) {
            // do nothing
        }
        
        string = rmaxInputView.getText();
        try {
            double rmax = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRMax(rmax);
        } catch (NumberFormatException exception) {
        }
               
        string = density0InputView.getText();
        try {
            double density0 = Double.parseDouble(string);
            conditions.setDensity0(density0);
        } catch (NumberFormatException exception) {
        }
        
        string = radius0InputView.getText();
        try {
            double radius0 = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRadius0(radius0);
        } catch (NumberFormatException exception) {
        }
        
        string = exponentInputView.getText();
        try {
            double exponent = Double.parseDouble(string);
            conditions.setExponent(exponent);
        } catch (NumberFormatException exception) {
        }        
        setFieldValuesFromData();
    }
}
