package edu.asu.sese.diskEvolution.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class TemperatureSnapshotCollection {

    private static TemperatureSnapshot snapshot;
	private DiskSimulation simulation;
    private List<TemperatureSnapshot> collection;
    private SimpleObservable observable;

    public TemperatureSnapshotCollection() {
        collection = new ArrayList<TemperatureSnapshot>();
        observable = new SimpleObservable();
    }

    public void takeSnapshot() {
        TemperatureSnapshot snapshot = new TemperatureSnapshot(simulation);
        collection.add(snapshot);
        observable.notifyObservers();
    }

    public int getSnapshotCount() {
        return collection.size();
    }

    public TemperatureSnapshot getSnapshot(int index) {
        return collection.get(index);
    }

    public void setSimulation(DiskSimulation simulation) {
        this.simulation = simulation;
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }


}
