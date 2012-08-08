package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.*;

public class DiskSimulation {
    
    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private SimulationData data;

    public DiskSimulation() {
        setupSimulation();
    }

    public void setupSimulation() {
        data = new SimulationData();
        
        radialGrid = new RadialGrid(data.getRmin(), data.getRmax(), 
                data.getDeltar0(), data.getIntervalCount());
        
        densityGrid = new DensityGrid(getRadialGrid());
    	densityGrid.initializeWithPowerLaw(
    	        data.getDensity0(), data.getRadius0(), data.getExponent());
        
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid.initializeWithPowerLaw(1e12, data.getRadius0(), 1.0);
        
        massFlowGrid = new MassFlowGrid(getRadialGrid());
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }

    public  SimulationData getSimulationData() {
        return data;
    }


}
