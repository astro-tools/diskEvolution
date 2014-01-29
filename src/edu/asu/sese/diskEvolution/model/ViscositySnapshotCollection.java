package edu.asu.sese.diskEvolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class ViscositySnapshotCollection {

    private static ViscositySnapshot snapshot;
	private DiskSimulation simulation;
    private List<ViscositySnapshot> collection;
    private SimpleObservable observable;

    public ViscositySnapshotCollection() {
        collection = new ArrayList<ViscositySnapshot>();
        observable = new SimpleObservable();
    }

    public void takeSnapshot() {
    	ViscositySnapshot snapshot = new ViscositySnapshot(simulation);
        collection.add(snapshot);
        observable.notifyObservers();
    }

    public int getSnapshotCount() {
        return collection.size();
    }

    public ViscositySnapshot getSnapshot(int index) {
        return collection.get(index);
    }

    public void setSimulation(DiskSimulation simulation) {
        this.simulation = simulation;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }


}
