package edu.asu.sese.diskEvolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class SnapshotCollection {

    private static Snapshot snapshot;
	private DiskSimulation simulation;
    private List<Snapshot> collection;
    private SimpleObservable observable;

    public SnapshotCollection() {
        collection = new ArrayList<Snapshot>();
        observable = new SimpleObservable();
    }

    public void takeSnapshot() {
        Snapshot snapshot = new Snapshot(simulation);
        collection.add(snapshot);
        observable.notifyObservers();
    }

    public int getSnapshotCount() {
        return collection.size();
    }

    public Snapshot getSnapshot(int index) {
        return collection.get(index);
    }

    public void setSimulation(DiskSimulation simulation) {
        this.simulation = simulation;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

	public static Snapshot copySnapshot(int i) {
		return snapshot;
	}


}
