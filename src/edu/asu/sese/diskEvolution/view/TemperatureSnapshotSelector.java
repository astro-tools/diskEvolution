package edu.asu.sese.diskEvolution.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.asu.sese.diskEvolution.model.TemperatureSnapshotCollection;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class TemperatureSnapshotSelector implements Observer, ChangeListener {
    
    private JSlider slider;
    private TemperatureSnapshotCollection collection;
    private SimpleObservable observable = new SimpleObservable();

    TemperatureSnapshotSelector(TemperatureSnapshotCollection collection) {
        this.collection = collection;
        createSlider();
        collection.addObserver(this);
    }

    private void createSlider() {
        slider = new JSlider(SwingConstants.HORIZONTAL, 0, 0, 0);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(this);        
        update();
    }

    public JComponent getComponent() {
        return slider;
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        update();
        observable.notifyObservers();
    }

    private void update() {
        int count = collection.getSnapshotCount();
        if (count > 0) {
            slider.setMaximum(count - 1);
            slider.setEnabled(true);
            setMajorTicks(count);
        } else {
            slider.setMaximum(0);
            slider.setEnabled(false);
        }
        int max = count - 1;
        if (max < 0) max = 0;
    }

    private void setMajorTicks(int count) {
        if (count > 15) {
            slider.setMajorTickSpacing(5);
        } else if (count > 9) {
            slider.setMajorTickSpacing(2);
        } else {
            slider.setMajorTickSpacing(1);
        }
    }


    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        observable.notifyObservers();
    }

    public int getIndex() {
        return slider.getValue();
    }

}
