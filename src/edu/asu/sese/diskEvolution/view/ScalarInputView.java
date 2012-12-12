package edu.asu.sese.diskEvolution.view;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScalarInputView {
    
    private JLabel label;
    private JTextField textField;

    public ScalarInputView(String labelText) {
        label = new JLabel(labelText);
        textField = new JTextField();
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


    public void addActionListener(ActionListener listener) {
        textField.addActionListener(listener);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String string) {
        textField.setText(string);
    }

}
