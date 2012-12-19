package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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

    private ScalarOutputView massOutputView;

    
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
        massOutputView.setValue(conditions.getTotalMass());
    }

    String decorateLabel(String text) {
        return "<html><i><b>" + text + "</b></i></html>";
    }
    
    private void setupLabelsAndFields(ActionListener listener) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);
        
        JLabel inputLabel = new JLabel(decorateLabel("Input:"));
        inputLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(inputLabel);
        
        inputListView = new ScalarListInputView();
        inputListView.setAlignmentX(LEFT_ALIGNMENT);
        add(inputListView);

        Unit earthRadius = new Unit("R⊕", "R<sub>⊕</sub>",
                PhysicalConstants.earthRadiusInCm);
        Unit gramsPerCm2 = new Unit("g/cm2", "g/cm<sup>2</sup>", 1.0);
        Unit noUnit = new Unit("", "", 1.0);
        
        rminInputView = 
                new ScalarInputView("r<sub>min</sub>", earthRadius);
        inputListView.add(rminInputView);

        density0InputView = 
                new ScalarInputView("Σ<sub>0</sub>", gramsPerCm2);
        inputListView.add(density0InputView);

        rmaxInputView = 
                new ScalarInputView("r<sub>max</sub>", earthRadius);
        inputListView.add(rmaxInputView);

        radius0InputView = 
                new ScalarInputView("r<sub>0</sub>", earthRadius);
        inputListView.add(radius0InputView);
        
        exponentInputView = 
                new ScalarInputView("p (Σ ~ r<sup>p</sup>)", noUnit);
        inputListView.add(exponentInputView);
        
        inputListView.addActionListener(listener);
        
        add(Box.createVerticalGlue());
        
        JLabel outputLabel = new JLabel(decorateLabel("Output:"));
        outputLabel.setAlignmentX(LEFT_ALIGNMENT);
        add(outputLabel);        
        
        ScalarListInputView outputListView = new ScalarListInputView();
        outputListView.setAlignmentX(LEFT_ALIGNMENT);
        add(outputListView);

        Unit lunarMass= new Unit("M☽", "M<sub>☽</sub>", 
                PhysicalConstants.lunarMass);
        massOutputView =
                new ScalarOutputView("M<sub>tot</sub>", lunarMass, "#.##");
        outputListView.add(massOutputView);

        add(Box.createVerticalGlue());
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
