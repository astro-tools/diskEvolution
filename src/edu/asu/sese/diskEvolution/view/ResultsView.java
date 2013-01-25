package edu.asu.sese.diskEvolution.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.SimulationRunner;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;

public class ResultsView extends JPanel {
    private static final long serialVersionUID = 1L;
    private MassView massView;
    private DiskView diskView;
    private SnapshotSelector selector;
    private SnapshotCollection snapshotCollection;

    public ResultsView(SimulationRunner runner) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.snapshotCollection = runner.getSnapshotCollection();
        setupDiskView();
        setupSnapshotSelector();
        setupMassView();
    }

    private void setupMassView() {
        massView = new MassView();
        add(massView.getComponent());
    }

    private void setupSnapshotSelector() {
        selector = new SnapshotSelector(snapshotCollection);
        add(selector.getComponent());
    }

    private void setupDiskView() {
        diskView = new DiskView(null);
        add(diskView.getComponent());
    }
}
