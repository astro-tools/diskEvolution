package edu.asu.sese.diskEvolution.model;

import java.util.Observer;

import edu.asu.sese.diskEvolution.util.GridFactory;

public class Parameters {
    private InitialConditions initialConditions;
    private GridFactory gridFactory;
    
    public Parameters() {
        initialConditions = new InitialConditions();
        initialConditions.initializeParameters();
        gridFactory = new GridFactory();
        gridFactory.initializeParameters();
    }

    public double getRmin() {
        return gridFactory.getRmin();
    }

    public void setRmin(double rmin) {
        gridFactory.setRmin(rmin);
    }

    public double getRmax() {
        return gridFactory.getRmax();
    }

    public void setRmax(double rmax) {
        gridFactory.setRmax(rmax);
    }

    public double getDeltar0() {
        return gridFactory.getDeltar0();
    }

    public void setDeltar0(double deltar0) {
        gridFactory.setDeltar0(deltar0);
    }

    public int getIntervalCount() {
        return gridFactory.getIntervalCount();
    }

    public void setIntervalCount(int intervalCount) {
        gridFactory.setIntervalCount(intervalCount);
    }

    public double getDensity0() {
        return initialConditions.getDensity0();
    }

    public void setDensity0(double density0) {
        initialConditions.setDensity0(density0);
    }

    public double getRadius0() {
        return initialConditions.getRadius0();
    }

    public void setRadius0(double radius0) {
        initialConditions.setRadius0(radius0);
    }

    public double getExponent() {
        return initialConditions.getExponent();
    }

    public void setExponent(double exponent) {
        initialConditions.setExponent(exponent);
    }

    public void addRadialParameterObserver(Observer observer) {
        gridFactory.addObserver(observer);
    }

    public void addDensityParameterObserver(Observer observer) {
        initialConditions.addObserver(observer);
    }
}