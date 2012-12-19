package edu.asu.sese.diskEvolution.view;

import java.text.DecimalFormat;
import java.util.Formatter;

import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.asu.sese.diskEvolution.util.Unit;

public class ScalarOutputView {
    
    private JLabel label;
    private JLabel valueField;
    private Unit unit;
    private JLabel unitLabel;
    private DecimalFormat formatter;

    public ScalarOutputView(String labelText, Unit unit, String format) {
        this.unit = unit;
        this.formatter = new DecimalFormat(format);
        label = new JLabel("<html>" + labelText + " = </html>");
        valueField = new JLabel();
        valueField.setHorizontalAlignment(JTextField.RIGHT);
        unitLabel = new JLabel("<html>" + unit.getHtmlLabel() + "</html>");
    }

    public void setValue(double value) {
        Double scaledValue = value / unit.getScale();
        valueField.setText(formatter.format(scaledValue));        
    }

    public double getValue() {
      String string = valueField.getText();
      return Double.parseDouble(string) * unit.getScale();
    }

    public JLabel getLabel() {
        return label;
    }
        
    public JLabel getValueField() {
        return valueField;
    }

    public JLabel getUnitLabel() {
        return unitLabel;
    }

}
