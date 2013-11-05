package edu.asu.sese.diskEvolution.model;

import java.util.Observer;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.SimpleObservable;
import javax.swing.*;

public class InitialConditions {

    private double radius0;
    private double totalmass0; //private double denisty0;
    private double densityExponent; 
    private SimpleObservable observable = new SimpleObservable();
    private double rin;
    private double rout;
    
    public InitialConditions() {
        initializeParameters();
    }
    
    public void initializeParameters() {
    	setTotalmass0(1.0 * PhysicalConstants.lunarMass); // setDensity0(1e7);
        setRadius0(PhysicalConstants.earthRadiusInCm);
        setRIn(1.0 * PhysicalConstants.earthRadiusInCm);
        setROut(5.0 * PhysicalConstants.earthRadiusInCm);
        setExponent(-1.0);
    }
    
    public double getRadius0() {
        return radius0;
    }

    public void setRadius0(double radius0) {
        boolean changed = (Math.abs(radius0 - this.radius0) > 1e2);
        this.radius0 = radius0;
        if (changed) observable.notifyObservers();
    }

    public double getTotalmass0() { // getDensity0()
        return totalmass0; // return density0;
    }

    public void setTotalmass0(double totalmass0) { //setDenisty0
        boolean changed = (Math.abs(totalmass0 - this.totalmass0) > 1.0); // > 1.0); \\ !!! HELP HERE
        this.totalmass0 = totalmass0;
        if (changed) observable.notifyObservers();
    }

    public double getExponent() {
        return densityExponent;
    }

    public void setExponent(double exponent) {
        boolean changed = (Math.abs(exponent - this.densityExponent) > 1e-6 );
        this.densityExponent = exponent;
        if (changed) observable.notifyObservers();
        if (exponent == -2.0) JOptionPane.showMessageDialog(null, "Undefined exponent value.", "P=2", JOptionPane.ERROR_MESSAGE);
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

    public double getDensity0() { 
        double density0 = (densityExponent + 2.0) * Math.pow(radius0, densityExponent) * totalmass0; 
        density0 /= 2 * Math.PI;
        density0 /= Math.pow(rout, densityExponent + 2.0) 
                - Math.pow(rin, densityExponent + 2.0);
        return density0;
        
        // public double getTotalMass() { 
        //double mass = 2 * Math.PI * density0; 
        //mass /= (densityExponent + 2.0) * Math.pow(radius0, densityExponent);
        //mass *= Math.pow(rout, densityExponent + 2.0) 
             //   - Math.pow(rin, densityExponent + 2.0);
        //return mass;
    }
}
