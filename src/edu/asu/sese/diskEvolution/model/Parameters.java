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
    private double density0;
    private double radius0;
    private double exponent;
    private Observable radialParameterObservable;
    
    public Parameters() {
        radialParameterObservable = new SimpleObservable();
        initializeDensityParameters();
        initializeGridParameters();
    }

    public void initializeDensityParameters() {
        setDensity0(1e3);
    	setRadius0(PhysicalConstants.auInCm);
    	setExponent(-1.5);
    }

    public void initializeGridParameters() {
        setRmin(0.1 * PhysicalConstants.auInCm);
        setRmax(200.0 * PhysicalConstants.auInCm);
        setDeltar0(0.1 * PhysicalConstants.auInCm);
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
        return density0;
    }

    public void setDensity0(double density0) {
        this.density0 = density0;
    }

    public double getRadius0() {
        return radius0;
    }

    public void setRadius0(double radius0) {
        this.radius0 = radius0;
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public void addRadialParameterObserver(Observer observer) {
        radialParameterObservable.addObserver(observer);
    }

    private void notifyObserversThatRadialParameterChanged() {
        radialParameterObservable.notifyObservers();
    }

}