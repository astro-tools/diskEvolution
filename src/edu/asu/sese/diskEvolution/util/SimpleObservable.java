package edu.asu.sese.diskEvolution.util;

public class SimpleObservable extends java.util.Observable {
    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}
