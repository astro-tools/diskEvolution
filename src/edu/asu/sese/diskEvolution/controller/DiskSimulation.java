package edu.asu.sese.diskEvolution.controller;

import java.util.Observable;
import java.util.Observer;

import edu.asu.sese.diskEvolution.model.*;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class DiskSimulation extends SimpleObservable {
    
    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private Parameters parameters;

    public DiskSimulation() {
        setupSimulation();
    }

    public void setupSimulation() {
        parameters = new Parameters();
        setupGrids();
        watchForChangingParameters();
    }

    private void setupGrids() {
        radialGrid = new RadialGrid(parameters);
        
        densityGrid = new DensityGrid(getRadialGrid());
    	densityGrid.initializeWithPowerLaw(parameters);
        
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid.initializeWithPowerLaw(1e12, parameters.getRadius0(), 1.0);
        
        massFlowGrid = new MassFlowGrid(getRadialGrid());
    }

    private void watchForChangingParameters() {
        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                System.out.println("Something changed!");
                setupGrids();
                notifyObservers();
            }
        };
        parameters.addRadialParameterObserver(observer);
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }

    public Parameters getSimulationData() {
        return parameters;
    }


}
