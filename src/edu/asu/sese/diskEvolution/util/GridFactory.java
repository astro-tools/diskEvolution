package edu.asu.sese.diskEvolution.util;

import java.util.Observable;
import java.util.Observer;


public class GridFactory {

    private double rmin;
    private double rmax;
    private double deltar0;
    private int intervalCount;
    private Observable observable = new SimpleObservable();


    public void initializeParameters() {
        setRmin(0.1 * PhysicalConstants.earthRadiusInCm);
        setRmax(200.0 * PhysicalConstants.earthRadiusInCm);
        setDeltar0(0.1 * PhysicalConstants.earthRadiusInCm);
        setIntervalCount(50);
    }
    
    public double getRmin() {
        return rmin;
    }

    public void setRmin(double rmin) {
        boolean changed = (Math.abs(this.rmin - rmin) > 1e2);
        this.rmin = rmin;
        if (changed) observable.notifyObservers();
    }

    public double getRmax() {
        return rmax;
    }

    public void setRmax(double rmax) {
        boolean changed = (Math.abs(this.rmax - rmax) > 1e2);
        this.rmax = rmax;
        if (changed) observable.notifyObservers();
    }

    public double getDeltar0() {
        return deltar0;
    }

    public void setDeltar0(double deltar0) {
        boolean changed = (Math.abs(this.deltar0 - deltar0) > 1e2);
        this.deltar0 = deltar0;
        if (changed) observable.notifyObservers();
    }

    public int getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(int intervalCount) {
        boolean changed = (this.intervalCount != intervalCount);
        this.intervalCount = intervalCount;
        if (changed) observable.notifyObservers();
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }
}
