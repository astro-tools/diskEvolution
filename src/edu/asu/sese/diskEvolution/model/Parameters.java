package edu.asu.sese.diskEvolution.model;

import java.util.Observable;
import java.util.Observer;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class Parameters {
    private double rmin;
    private double rmax;
    private double deltar0;
    private int intervalCount;
    private Observable radialParameterObservable;
    private Observable densityParameterObservable;
    private InitialConditions initialConditions;

    
    public Parameters() {
        radialParameterObservable = new SimpleObservable();
        densityParameterObservable = new SimpleObservable();
        initialConditions = new InitialConditions();
        initializeDensityParameters();
        initializeGridParameters();
    }

    public void initializeDensityParameters() {
        setDensity0(1e3);
    	setRadius0(PhysicalConstants.earthRadiusInCm);
    	setExponent(-1.5);
    }

    public void initializeGridParameters() {
        setRmin(0.1 * PhysicalConstants.earthRadiusInCm);
        setRmax(200.0 * PhysicalConstants.earthRadiusInCm);
        setDeltar0(0.1 * PhysicalConstants.earthRadiusInCm);
        setIntervalCount(50);
    }

    public double getRmin() {
        return rmin;
    }

    public void setRmin(double rmin) {
        boolean changed = Math.abs(this.rmin - rmin) > 1e2;
        this.rmin = rmin;
        if (changed) notifyObserversThatRadialParameterChanged();
    }

    public double getRmax() {
        return rmax;
    }

    public void setRmax(double rmax) {
        boolean changed = Math.abs(this.rmax - rmax) > 1e2;
        this.rmax = rmax;
        if (changed) notifyObserversThatRadialParameterChanged();
    }

    public double getDeltar0() {
        return deltar0;
    }

    public void setDeltar0(double deltar0) {
        boolean changed = Math.abs(this.deltar0 - deltar0) > 1e2;
        this.deltar0 = deltar0;
        if (changed) notifyObserversThatRadialParameterChanged();
    }

    public int getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(int intervalCount) {
        boolean changed = (this.intervalCount != intervalCount);
        this.intervalCount = intervalCount;
        if (changed) notifyObserversThatRadialParameterChanged();
    }

    public double getDensity0() {
        return initialConditions.getDensity0();
    }

    public void setDensity0(double density0) {
        double oldDensity0 = initialConditions.getDensity0();
        boolean changed = Math.abs(oldDensity0 - density0) > 1.0;
        initialConditions.setDensity0(density0);
        if (changed) notifyObserversThatDensityParameterChanged();
    }

    public double getRadius0() {
        return initialConditions.getRadius0();
    }

    public void setRadius0(double radius0) {
        double oldRadius0 = initialConditions.getRadius0();
        boolean changed = Math.abs(oldRadius0 - radius0) > 1e3;
        initialConditions.setRadius0(radius0);
        if (changed) notifyObserversThatDensityParameterChanged();
    }

    public double getExponent() {
        return initialConditions.getExponent();
    }

    public void setExponent(double exponent) {
        double oldExponent = initialConditions.getExponent();
        boolean changed = Math.abs(oldExponent - exponent) > 1e-6;
        initialConditions.setExponent(exponent);
        if (changed) notifyObserversThatDensityParameterChanged();
    }

    public void addRadialParameterObserver(Observer observer) {
        radialParameterObservable.addObserver(observer);
    }

    private void notifyObserversThatRadialParameterChanged() {
        radialParameterObservable.notifyObservers();
    }

    public void addDensityParameterObserver(Observer observer) {
        densityParameterObservable.addObserver(observer);
    }
    
    private void notifyObserversThatDensityParameterChanged() {
        densityParameterObservable.notifyObservers();
    }

}