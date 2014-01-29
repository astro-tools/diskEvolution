package edu.asu.sese.diskEvolution.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.model.ViscositySnapshotCollection;

public class ResultsViewIII extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private ViscosityView viscosityView;
    private ViscositySnapshotSelector selector;
    private ViscositySnapshotCollection snapshotCollection;

    public ResultsViewIII(SimulationRunner runner) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.snapshotCollection = runner.getViscositySnapshotCollection();
        setupSnapshotSelector();
        setupViscosityView();
        selector.addObserver(this);
    }


    private void setupViscosityView() {
    	viscosityView = new ViscosityView(snapshotCollection);
        add(viscosityView.getComponent());
    }

    private void setupSnapshotSelector() {
        selector = new ViscositySnapshotSelector(snapshotCollection);
        add(selector.getComponent());
    }


    @Override
    public void update(Observable o, Object arg) {
        int selected = selector.getIndex();
        viscosityView.showGraph(selected);
        System.out.println("slider changed: " + selected);
    }
}
