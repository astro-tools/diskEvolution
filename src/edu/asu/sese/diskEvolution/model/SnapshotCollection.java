package edu.asu.sese.diskEvolution.model;

import java.util.ArrayList;
import java.util.List;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class SnapshotCollection {

    private DiskSimulation simulation;
    private List<Snapshot> collection;

    public SnapshotCollection(DiskSimulation simulation) {
        this.simulation = simulation;
        collection = new ArrayList<Snapshot>();
    }

    public void takeSnapshot() {
        Snapshot snapshot = new Snapshot(simulation);
        collection.add(snapshot);
    }

    public int getSnapshotCount() {
        return collection.size();
    }

    public Snapshot getSnapshot(int index) {
        return collection.get(index);
    }

}
