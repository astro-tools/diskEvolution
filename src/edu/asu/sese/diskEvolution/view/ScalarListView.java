package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ScalarListView extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<ScalarInputView> scalarInputViews;

    public ScalarListView() {
        scalarInputViews = new ArrayList<ScalarInputView>();
        GridLayout layoutManager = new GridLayout(0, 3, 4, 4);
        setLayout(layoutManager);
    }

    public void add(ScalarInputView view) {
        scalarInputViews.add(view);
        add(view.getLabel());
        add(view.getValueField());
        add(view.getUnitLabel());
    }

    public void add(ScalarOutputView view) {
        add(view.getLabel());
        add(view.getValueField());
        add(view.getUnitLabel());
    }

    public void addActionListener(ActionListener listener) {
        for (ScalarInputView view : scalarInputViews) {
            view.addActionListener(listener);
        }
    }
}
