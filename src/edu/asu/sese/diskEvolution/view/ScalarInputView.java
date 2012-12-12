package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.asu.sese.diskEvolution.util.Unit;

public class ScalarInputView {
    
    private JLabel label;
    private JTextField textField;
    private Unit unit;
    private JLabel unitLabel;

    public ScalarInputView(String labelText, Unit unit) {
        this.unit = unit;
        label = new JLabel(labelText + " = ");
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        unitLabel = new JLabel("<html>" + unit.getHtmlLabel() + "</html>");
    }

    public void setValue(double value) {
        Double scaledValue = value / unit.getScale();
        setFieldText(scaledValue.toString());        
    }

    public double getValue() {
      String string = textField.getText();
      return Double.parseDouble(string) * unit.getScale();
    }

    public void addActionListener(ActionListener listener) {
        textField.addActionListener(listener);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setFieldText(String string) {
        textField.setText(string);
    }
        
    public JTextField getTextField() {
        return textField;
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String string) {
        textField.setText(string);
    }

    public JLabel getUnitLabel() {
        return unitLabel;
    }

}
