package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class DiskSimulation {

    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private GridFactory factory;
    private InitialConditions initialConditions;

    public DiskSimulation(GridFactory factory, 
            InitialConditions initialConditions) {
        this.factory = factory;
        this.initialConditions = initialConditions;
        setupGrids();
    }
    
    private void setupGrids() {
        radialGrid = new RadialGrid(factory);
        setupDensityGrid();
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid
                .initializeWithPowerLaw(1e12, 
                        initialConditions.getRadius0(), 1.0);
        massFlowGrid = new MassFlowGrid(getRadialGrid());
    }

    private void setupDensityGrid() {
        densityGrid = new DensityGrid(getRadialGrid());
        double density0 = initialConditions.getDensity0();
        double radius0 = initialConditions.getRadius0();
        double exponent = initialConditions.getExponent();
        densityGrid.initializeWithPowerLaw(density0, radius0, exponent);
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }

    public MassFlowGrid getMassFlowGrid() {
        return massFlowGrid;
    }

    public ViscosityGrid getViscosityGrid() {
        return viscosityGrid;
    }

}
