package edu.asu.sese.diskEvolution.model;

import java.util.Observer;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class InitialConditions {

    private double radius0;
    private double density0;
    private double exponent;
    private SimpleObservable observable = new SimpleObservable();
    private double rin;
    private double rout;
    
    public InitialConditions() {
        initializeParameters();
    }
    
    public void initializeParameters() {
        setDensity0(1e3);
        setRadius0(PhysicalConstants.earthRadiusInCm);
        setRIn(1.1 * PhysicalConstants.earthRadiusInCm);
        setROut(5.0 * PhysicalConstants.earthRadiusInCm);
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

    public void setRIn(double rmin) {
        boolean changed = (Math.abs(rmin - this.rin) > 1e2);
        this.rin = rmin;
        if (changed) observable.notifyObservers();
    }

    public double getRIn() {
        return rin;
    }


    public void setROut(double rmax) {
        boolean changed = (Math.abs(rmax - this.rout) > 1e2);
        this.rout = rmax;
        if (changed) observable.notifyObservers();
    }

    public double getROut() {
        return rout;
    }

    public double getTotalMass() {
        double mass = 2 * Math.PI * density0;
        mass /= (exponent + 2.0) * Math.pow(radius0, exponent);
        mass *= Math.pow(rout, exponent + 2.0) - Math.pow(rin, exponent + 2.0);
        return mass;
    }
}
