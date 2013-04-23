package edu.asu.sese.diskEvolution.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;

public class TracerView extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private MassView massView;
    private DiskView diskView;
    private SnapshotSelector selector;
    private SnapshotCollection snapshotCollection;

    public TracerView(SimulationRunner runner) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.snapshotCollection = runner.getSnapshotCollection();
        setupDiskView();
        setupSnapshotSelector();
        setupMassView();
        selector.addObserver(this);
    }

    private void setupDiskView() {
        diskView = new DiskView(snapshotCollection);
        add(diskView.getComponent());
    }

    private void setupSnapshotSelector() {
        selector = new SnapshotSelector(snapshotCollection);
        add(selector.getComponent());
    }

    private void setupMassView() {
        massView = new MassView(snapshotCollection);
        add(massView.getComponent());
    }

    @Override
    public void update(Observable o, Object arg) {
        int selected = selector.getIndex();
        diskView.showGraph(selected);
        System.out.println("slider changed: " + selected);
    }
}