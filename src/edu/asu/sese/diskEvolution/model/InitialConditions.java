package edu.asu.sese.diskEvolution.model;

import java.util.Observer;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class InitialConditions {

    private double radius0;
    private double density0;
    private double exponent;
    private SimpleObservable observable = new SimpleObservable();
    private double rmin;
    private double rmax;
    
    public InitialConditions() {
        initializeParameters();
    }
    
    public void initializeParameters() {
        setDensity0(1e3);
        setRadius0(PhysicalConstants.earthRadiusInCm);
        setRMin(1.1 * PhysicalConstants.earthRadiusInCm);
        setRMax(5.0 * PhysicalConstants.earthRadiusInCm);
        setExponent(-1.5);
    }
    
    public double getRadius0() {
        return radius0;
    }

    public void setRadius0(double radius0) {
        boolean changed = (Math.abs(radius0 - this.radius0) > 1e2);
        this.radius0 = radius0;
        if (changed) observable.notifyObservers();
    }

    public double getDensity0() {
        return density0;
    }

    public void setDensity0(double density0) {
        boolean changed = (Math.abs(density0 - this.density0) > 1.0);
        this.density0 = density0;
        if (changed) observable.notifyObservers();
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        boolean changed = (Math.abs(exponent - this.exponent) > 1e-6);
        this.exponent = exponent;
        if (changed) observable.notifyObservers();
    }

    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    public void setRMin(double rmin) {
        boolean changed = (Math.abs(rmin - this.rmin) > 1e2);
        this.rmin = rmin;
        if (changed) observable.notifyObservers();
    }

    public double getRMin() {
        return rmin;
    }


    public void setRMax(double rmax) {
        boolean changed = (Math.abs(rmax - this.rmax) > 1e2);
        this.rmax = rmax;
        if (changed) observable.notifyObservers();
    }

    public double getRMax() {
        return rmax;
    }

    public double getTotalMass() {
        return 0.0;
    }
}
