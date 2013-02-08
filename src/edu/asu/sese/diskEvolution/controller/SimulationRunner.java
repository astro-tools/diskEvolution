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
        simulationTimeStep.setTime(10.0 * PhysicalConstants.day);
	    totalDuration = 30.0 * PhysicalConstants.year;
	    totalDuration = 1.0 * PhysicalConstants.year;
	    snapshotInterval = 1.0 * PhysicalConstants.year;
	    snapshotInterval = 10.0 * PhysicalConstants.day;
    }

	public void run() {
	    simulation = new DiskSimulation(gridFactory, initialConditions);
	    snapshotCollection.setSimulation(simulation);
	    createMassMover();
	    createMassFlowCalculator();
        System.out.println("Running simulation...");
        snapshotCollection.takeSnapshot();
        double time = 0.0;
        while (time < totalDuration){
        	massFlowCalculator.calculate();
        	
        	double timeStep = simulationTimeStep.getTime();
			massMover.setTimeStep(timeStep);
//        	massMover.moveMass();
        	snapshotCollection.takeSnapshot();
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
