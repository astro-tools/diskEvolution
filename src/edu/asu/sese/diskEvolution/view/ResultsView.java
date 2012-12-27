package edu.asu.sese.diskEvolution.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
public class ResultsView extends JPanel {
    private static final long serialVersionUID = 1L;
    private MassView massView;
    private DiskView diskView;
    private SnapshotSelector selector;

    public ResultsView() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setupDiskView();
        setupSnapshotSelector();
        setupMassView();
    }

    private void setupMassView() {
        massView = new MassView();
        add(massView.getComponent());
    }

    private void setupSnapshotSelector() {
        selector = new SnapshotSelector();
        add(selector.getComponent());
    }

    private void setupDiskView() {
        diskView = new DiskView();
        add(diskView.getComponent());
    }
}
