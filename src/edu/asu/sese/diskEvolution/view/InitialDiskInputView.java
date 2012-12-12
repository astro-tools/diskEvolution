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
        
        try {
            double diskRMin = rminInputView.getValue();
            conditions.setRMin(diskRMin);
        } catch (NumberFormatException exception) {
        }
        
        try {
            double rmax = rmaxInputView.getValue();
            conditions.setRMax(rmax);
        } catch (NumberFormatException exception) {
        }
               
        try {
            double density0 = density0InputView.getValue();
            conditions.setDensity0(density0);
        } catch (NumberFormatException exception) {
        }
        
        try {
            double radius0 = radius0InputView.getValue();
            conditions.setRadius0(radius0);
        } catch (NumberFormatException exception) {
        }
        
        try {
            double exponent = exponentInputView.getValue();
            conditions.setExponent(exponent);
        } catch (NumberFormatException exception) {
        }        

        setFieldValuesFromData();
    }
}
