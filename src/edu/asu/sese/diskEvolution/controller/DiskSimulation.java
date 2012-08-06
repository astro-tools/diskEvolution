package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.*;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class DiskSimulation {
    
    public DiskSimulation() {
        setupSimulation();
    }

    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;

    public void setupSimulation() {
        double rmin = 0.1 * PhysicalConstants.auInCm;
        double rmax = 200.0 * PhysicalConstants.auInCm;
        double deltar0 = 0.1 * PhysicalConstants.auInCm;
        int intervalCount = 50;
        radialGrid = new RadialGrid(rmin , rmax , deltar0 , intervalCount);
        
        densityGrid = new DensityGrid(getRadialGrid());
        double density0 = 1e3;
    	double radius0 = PhysicalConstants.auInCm;
    	double exponent = -1.5;
    	getDensityGrid().initializeWithPowerLaw(density0, radius0, exponent);
        
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid.initializeWithPowerLaw(1e12, radius0, 1.0);
        
        massFlowGrid = new MassFlowGrid(getRadialGrid());
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }


}
