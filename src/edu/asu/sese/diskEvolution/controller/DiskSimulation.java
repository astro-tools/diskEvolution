package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.*;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import edu.asu.sese.diskEvolution.util.SimpleObservable;

public class DiskSimulation extends SimpleObservable {

    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private InitialConditions initialConditions;
    private GridFactory factory;
    private SimulationRunner runner;

    public DiskSimulation() {
        setupSimulation();
    }

    public void setupSimulation() {
        initialConditions = new InitialConditions();
        factory = new GridFactory();
//        setupGrids();
        setupRunner();
    }

    private void setupRunner() {
        runner = new SimulationRunner(this);
    }

    private void setupGrids() {
        radialGrid = new RadialGrid(factory);

        setupDensityGrid();

        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid
                .initializeWithPowerLaw(1e12, initialConditions.getRadius0(), 1.0);

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

    public InitialConditions getInitialConditions() {
        return initialConditions;
    }

    public SimulationRunner getRunner() {
        return runner;
    }

}
