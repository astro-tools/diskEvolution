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

public class InitialConditionsInputView extends JPanel {
    private static final long serialVersionUID = 1L;

    private InitialConditions conditions;

    private ScalarInputView rInInputView;
    private ScalarInputView rOutInputView;
    private ScalarInputView totalmass0InputView; // density0InputView;
    private ScalarInputView radius0InputView;
    private ScalarInputView exponentInputView;
    private ScalarOutputView density0OutputView; // massOutputView;

    public InitialConditionsInputView(InitialConditions initialConditions) {
        this.conditions = initialConditions;
        ActionListener listener = createListener();
        setupLabelsAndFields(listener);
        setFieldValuesFromData();
    }

    private void setFieldValuesFromData() {
        rInInputView.setValue(conditions.getRIn());
        rOutInputView.setValue(conditions.getROut());
        radius0InputView.setValue(conditions.getRadius0());
        totalmass0InputView.setValue(conditions.getTotalmass0()); // massOutputView 
        exponentInputView.setValue(-conditions.getExponent());
        density0OutputView.setValue(conditions.getDensity0()); //density0InputView
        
    }

    private void setupLabelsAndFields(ActionListener listener) {
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);

        setupDescription();
        setupInputLabelsAndField(listener);
        add(Box.createVerticalGlue());
        setupOutputLabelsAndFields();
        add(Box.createVerticalGlue());
    }

    private void setupDescription() {
        String text = "<html>"
                + "<p>Set up the initial disk.</p>" 
                + "<p align='center'>"
                + "Σ(r) = Σ<sub>0</sub> (r/r<sub>0</sub>)<sup>-p</sup></p> "
                + "</html>";
        add(new JLabel(text));
    }

    private void setupInputLabelsAndField(ActionListener listener) {

        ScalarListView inputListView = new ScalarListView();
        inputListView.setAlignmentX(LEFT_ALIGNMENT);
        add(inputListView);

        inputListView.addLabel("Input:");
        
        Unit earthRadius = new Unit("R⊕", "R<sub>⊕</sub>",
                PhysicalConstants.earthRadiusInCm);
        Unit lunarMass = new Unit("M☽", "M<sub>☽</sub>", 
        		PhysicalConstants.lunarMass);
        
        Unit noUnit = new Unit("", "", 1.0);

        rInInputView = new ScalarInputView("r<sub>in</sub>", earthRadius);
        inputListView.add(rInInputView);

        totalmass0InputView = new ScalarInputView("M<sub>☽</sub>", lunarMass); //  "Σ<sub>0</sub>"
        inputListView.add(totalmass0InputView);

        rOutInputView = new ScalarInputView("r<sub>out</sub>", earthRadius);
        inputListView.add(rOutInputView);

        radius0InputView = new ScalarInputView("r<sub>0</sub>", earthRadius);
        inputListView.add(radius0InputView);

        exponentInputView = new ScalarInputView("p", noUnit);
        inputListView.add(exponentInputView);

        inputListView.addActionListener(listener);
    }

    private void setupOutputLabelsAndFields() {

    	Unit gramsPerCm2 = new Unit("g/cm2", "g/cm<sup>2</sup>", 1.0);
    	ScalarListView outputListView = new ScalarListView();
        outputListView.setAlignmentX(LEFT_ALIGNMENT);
        add(outputListView);

        outputListView.addLabel("Output:"); 
       
        
        density0OutputView = new ScalarOutputView("Σ<sub>0</sub>", gramsPerCm2, "#.###"); //density0OutputView
        outputListView.add(density0OutputView); //density0OutputView
    }

    String decorateLabel(String text) {
        return "<html><i><b>" + text + "</b></i></html>";
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
            double diskRMin = rInInputView.getValue();
            conditions.setRIn(diskRMin);
        } catch (NumberFormatException exception) {
        }

        try {
            double rmax = rOutInputView.getValue();
            conditions.setROut(rmax);
        } catch (NumberFormatException exception) {
        }

        try {
            double totalmass0 = totalmass0InputView.getValue(); // density0InputView
            conditions.setTotalmass0(totalmass0);
        } catch (NumberFormatException exception) {
        }

        try {
            double radius0 = radius0InputView.getValue();
            conditions.setRadius0(radius0);
        } catch (NumberFormatException exception) {
        }

        try {
            double exponent = exponentInputView.getValue();
            conditions.setExponent(-exponent);
        } catch (NumberFormatException exception) {
        }

        setFieldValuesFromData();
    }
}
