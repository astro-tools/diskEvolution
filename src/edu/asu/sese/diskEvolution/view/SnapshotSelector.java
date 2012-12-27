package edu.asu.sese.diskEvolution.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class SnapshotSelector {
    
    private JSlider slider;

    SnapshotSelector() {
        createSlider();
    }

    private void createSlider() {
        int min = 0;
        int max = 30;
        int value = 0;
        slider = new JSlider(SwingConstants.HORIZONTAL, min, max, value);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
    }

    public JComponent getComponent() {
        return slider;
    }

}
