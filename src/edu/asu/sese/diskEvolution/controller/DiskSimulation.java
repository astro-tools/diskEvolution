package edu.asu.sese.diskEvolution.controller;

import java.util.Observable;
import java.util.Observer;

import edu.asu.sese.diskEvolution.model.*;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class DiskSimulation extends SimpleObservable {
    
    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private Parameters parameters;
    private InitialConditions initialConditions;

    public DiskSimulation() {
        setupSimulation();
    }

    public void setupSimulation() {
        parameters = new Parameters();
        initialConditions = new InitialConditions();
        setupGrids();
        watchForChangingParameters();
    }

    private void setupGrids() {
        radialGrid = new RadialGrid(parameters);
        
        setupDensityGrid();
        
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid.initializeWithPowerLaw(1e12, parameters.getRadius0(), 1.0);
        
        massFlowGrid = new MassFlowGrid(getRadialGrid());
    }

    private void setupDensityGrid() {
        densityGrid = new DensityGrid(getRadialGrid());
    	densityGrid.initializeWithPowerLaw(parameters);
    }

    private void watchForChangingParameters() {
        Observer radialObserver = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                setupGrids();
                notifyObservers();
            }
        };
        parameters.addRadialParameterObserver(radialObserver);
        Observer densityObserver = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                System.out.println("Density changed!");
                setupDensityGrid();
                notifyObservers();
            }
        };
        parameters.addDensityParameterObserver(densityObserver);    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }

    public Parameters getSimulationData() {
        return parameters;
    }

    public InitialConditions getInitialConditions() {
        return initialConditions;
    }


}
