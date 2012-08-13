package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.*;

public class DiskSimulation {
    
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
        
        radialGrid = new RadialGrid(parameters);
        
        densityGrid = new DensityGrid(getRadialGrid());
    	densityGrid.initializeWithPowerLaw(parameters);
        
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid.initializeWithPowerLaw(1e12, parameters.getRadius0(), 1.0);
        
        massFlowGrid = new MassFlowGrid(getRadialGrid());
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }

    public  Parameters getSimulationData() {
        return parameters;
    }


}
