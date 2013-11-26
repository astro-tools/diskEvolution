package edu.asu.sese.diskEvolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class DensitySnapshotCollection {

    private static DensitySnapshot snapshot;
	private DiskSimulation simulation;
    private List<DensitySnapshot> collection;
    private SimpleObservable observable;

    public DensitySnapshotCollection() {
        collection = new ArrayList<DensitySnapshot>();
        observable = new SimpleObservable();
    }

    public void takeSnapshot() {
        DensitySnapshot snapshot = new DensitySnapshot(simulation);
        collection.add(snapshot);
        observable.notifyObservers();
    }

    public int getSnapshotCount() {
        return collection.size();
    }

    public DensitySnapshot getSnapshot(int index) {
        return collection.get(index);
    }

    public void setSimulation(DiskSimulation simulation) {
        this.simulation = simulation;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }


}
