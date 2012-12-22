package edu.asu.sese.diskEvolution.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScalarListView extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<ScalarInputView> scalarInputViews;
    private GridBagConstraints constraints;

    public ScalarListView() {
        scalarInputViews = new ArrayList<ScalarInputView>();
        GridBagLayout layoutManager = new GridBagLayout();
        setLayout(layoutManager);
        initializeGridConstraints();
    }

    private void initializeGridConstraints() {
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(2, 2, 4, 2);
    }

    public void add(ScalarInputView view) {
        scalarInputViews.add(view);
        addScalarView(view.getLabel(), view.getValueField(), view.getUnitLabel());
    }

    public void add(ScalarOutputView view) {
        addScalarView(view.getLabel(), view.getValueField(), view.getUnitLabel());        
    }

    private void addScalarView(JComponent label, JComponent valueField,
            JComponent unitLabel) {
        add(label, constraints);
        constraints.gridx = 2;
        add(valueField, constraints);
        constraints.gridx = 3;
        add(unitLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy += 1;
    }

    public void addActionListener(ActionListener listener) {
        for (ScalarInputView view : scalarInputViews) {
            view.addActionListener(listener);
        }
    }

    public void addLabel(String labelText) {
        JLabel inputLabel = new JLabel(
                "<html><i><b>" + labelText + "</b></i></html>");
        inputLabel.setAlignmentX(LEFT_ALIGNMENT);
        constraints.gridwidth = 3;
        add(inputLabel, constraints);
        constraints.gridwidth = 1;
        constraints.gridy += 1;
    }
}
