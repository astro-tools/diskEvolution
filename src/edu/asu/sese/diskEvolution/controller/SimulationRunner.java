package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.MassFlowCalculator;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.MassMover;
import edu.asu.sese.diskEvolution.model.SnapshotCollection;
import edu.asu.sese.diskEvolution.model.TimeStep;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class SimulationRunner {
	private TimeStep simulationTimeStep;
    private double totalDuration;
    private double snapshotInterval;
    
    private GridFactory gridFactory;
    private Application application;
    private DiskSimulation simulation;
    private InitialConditions initialConditions;
    private MassMover massMover;
    private MassFlowCalculator massFlowCalculator;
    private SnapshotCollection snapshotCollection;
	
	public SimulationRunner(Application diskSimulation) {
	    this.application = diskSimulation;
	    gridFactory = application.getGridFactory();
	    initialConditions = application.getInitialConditions();
	    simulationTimeStep = new TimeStep();
	    snapshotCollection = new SnapshotCollection();
	    setDefaultParameters();
	}

    private void setDefaultParameters() {
        simulationTimeStep.setTime(10.0 * PhysicalConstants.hour);
	    totalDuration = 30.0 * PhysicalConstants.year;
        totalDuration = 500.0 * PhysicalConstants.year;
	    snapshotInterval = 1.0 * PhysicalConstants.year;
        snapshotInterval = 20.0 * PhysicalConstants.year;
    }

	public void run() {
	    simulation = new DiskSimulation(gridFactory, initialConditions);
	    snapshotCollection.setSimulation(simulation);
	    createMassMover();
	    createMassFlowCalculator();
        System.out.println("Running simulation...");
        snapshotCollection.takeSnapshot();
        double time = 0.0;
        double nextSnapshotTime = snapshotInterval;
        while (time < totalDuration){
        	massFlowCalculator.calculate();
        	
        	DensityGrid densityGrid = simulation.getDensityGrid();
            MassFlowGrid massFlowGrid = simulation.getMassFlowGrid();
            simulationTimeStep.update(densityGrid, massFlowGrid);
            
        	double timeStep = simulationTimeStep.getTime();
			massMover.setTimeStep(timeStep);
        	massMover.moveMass();
        	if (time >= nextSnapshotTime) {
        	    snapshotCollection.takeSnapshot();
        	    nextSnapshotTime += snapshotInterval;
        	}
        	time +=  timeStep;
        }
    }

    private void createMassFlowCalculator() {
        MassFlowGrid massFlowGrid = simulation.getMassFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        DensityGrid densityGrid = simulation.getDensityGrid();
        ViscosityGrid viscosityGrid = simulation.getViscosityGrid();
        massFlowCalculator = new MassFlowCalculator(
                massFlowGrid, radialGrid, densityGrid, viscosityGrid);
    }

    private void createMassMover() {
        DensityGrid density = simulation.getDensityGrid();
        MassFlowGrid massFlow = simulation.getMassFlowGrid();
        RadialGrid radialGrid = simulation.getRadialGrid();
        massMover = new MassMover(density , massFlow, radialGrid);
    }

    public double getSimulationTimeStep() {
        return simulationTimeStep.getTime();
    }

    public void setSimulationTimeStep(double timeStep) {
        simulationTimeStep.setTime(timeStep);
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double duration) {
        totalDuration = duration;
    }

    public double getSnapshotInterval() {
        return snapshotInterval;
    }

    public void setSnapshotIntervalView(double snapshotInterval) {
        this.snapshotInterval = snapshotInterval;
    }

    public GridFactory getGridFactory() {
        return gridFactory;
    }

    public SnapshotCollection getSnapshotCollection() {
        return snapshotCollection;
    }

}
