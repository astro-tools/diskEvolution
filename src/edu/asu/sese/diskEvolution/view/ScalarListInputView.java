package edu.asu.sese.diskEvolution.view;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class ScalarListInputView extends JPanel {
    private static final long serialVersionUID = 1L;

    public ScalarListInputView() {
        GridLayout layoutManager = new GridLayout(0, 2, 4, 4);
        setLayout(layoutManager);
    }

    public void add(ScalarInputView rminInputView) {
        add(rminInputView.getLabel());
        add(rminInputView.getTextField());
    }

}
