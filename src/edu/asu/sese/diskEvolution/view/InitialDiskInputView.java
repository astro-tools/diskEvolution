package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class InitialDiskInputView extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private InitialConditions conditions;

    private ScalarInputView rminInputView;
    private ScalarInputView rmaxInputView;
    private ScalarInputView density0InputView;
    private ScalarInputView radius0InputView;
    private ScalarInputView exponentInputView;
    
    private ScalarListInputView inputListView;
    
    public InitialDiskInputView(InitialConditions initialConditions) {
        this.conditions = initialConditions;
        ActionListener listener = createListener();
        setupLabelsAndFields(listener);
        setFieldValuesFromData();
    }

    private void setFieldValuesFromData() {
        rminInputView.setValue(conditions.getRMin());
        rmaxInputView.setValue(conditions.getRMax());
        radius0InputView.setValue(conditions.getRadius0());
        density0InputView.setValue(conditions.getDensity0());
        exponentInputView.setValue(conditions.getExponent());
    }

    private void setupLabelsAndFields(ActionListener listener) {
        inputListView = new ScalarListInputView();
        add(inputListView);
      
        Unit earthRadius = new Unit("R⊕", "R<sub>⊕</sub>",
                PhysicalConstants.earthRadiusInCm);
        Unit gramsPerCm2 = new Unit("g/cm2", "g/cm<sup>2</sup>", 1.0);
        Unit noUnit = new Unit("", "", 1.0);
        
        rminInputView = 
                new ScalarInputView("<html>r<sub>min</sub>", earthRadius);
        inputListView.add(rminInputView);

        density0InputView = 
                new ScalarInputView("<html>ρ<sub>0</sub>", gramsPerCm2);
        inputListView.add(density0InputView);

        rmaxInputView = 
                new ScalarInputView("<html>r<sub>max</sub>", earthRadius);
        inputListView.add(rmaxInputView);

        radius0InputView = 
                new ScalarInputView("<html>r<sub>0</sub>", earthRadius);
        inputListView.add(radius0InputView);
        
        exponentInputView = 
                new ScalarInputView("<html>p (Σ ~ r<sup>p</sup>)", noUnit);
        inputListView.add(exponentInputView);
        
        inputListView.addActionListener(listener);
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
        System.out.println("value changed");
        
        String string = rminInputView.getText();
        try {
            double diskRmin = Double.parseDouble(string) 
                    * PhysicalConstants.earthRadiusInCm;
            conditions.setRMin(diskRmin);
        } catch (NumberFormatException exception) {
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
