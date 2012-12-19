package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.asu.sese.diskEvolution.util.Unit;

public class ScalarInputView {
    
    private JLabel label;
    private JTextField valueField;
    private Unit unit;
    private JLabel unitLabel;

    public ScalarInputView(String labelText, Unit unit) {
        this.unit = unit;
        label = new JLabel("<html>" + labelText + " = </html>");
        valueField = new JTextField();
        valueField.setHorizontalAlignment(JTextField.RIGHT);
        unitLabel = new JLabel("<html>" + unit.getHtmlLabel() + "</html>");
    }

    public void setValue(double value) {
        Double scaledValue = value / unit.getScale();
        valueField.setText(scaledValue.toString());        
    }

    public double getValue() {
      String string = valueField.getText();
      return Double.parseDouble(string) * unit.getScale();
    }

    public void addActionListener(ActionListener listener) {
        valueField.addActionListener(listener);
    }

    public JLabel getLabel() {
        return label;
    }
        
    public JTextField getValueField() {
        return valueField;
    }

    public JLabel getUnitLabel() {
        return unitLabel;
    }
}
