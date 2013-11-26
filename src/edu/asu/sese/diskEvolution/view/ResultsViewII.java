
package edu.asu.sese.diskEvolution.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.model.TemperatureSnapshotCollection;

public class ResultsViewII extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private TemperatureView temperatureView;
    private TemperatureSnapshotSelector selector;
    private TemperatureSnapshotCollection snapshotCollection;

    public ResultsViewII(SimulationRunner runner) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.snapshotCollection = runner.getTemperatureSnapshotCollection();
        setupSnapshotSelector();
        setupTemperatureView();
        selector.addObserver(this);
    }


    private void setupTemperatureView() {
        temperatureView = new TemperatureView(snapshotCollection);
        add(temperatureView.getComponent());
    }

    private void setupSnapshotSelector() {
        selector = new TemperatureSnapshotSelector(snapshotCollection);
        add(selector.getComponent());
    }


    @Override
    public void update(Observable o, Object arg) {
        int selected = selector.getIndex();
        temperatureView.showGraph(selected);
        System.out.println("slider changed: " + selected);
    }
}
