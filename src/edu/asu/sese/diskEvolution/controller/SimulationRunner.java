package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.TimeStep;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class SimulationRunner {
    private CurrentSimulationTime currentSimulationTime;
	private TimeStep simulationTimeStep;
    private double totalDuration;
    private double snapshotInterval;
    
    private GridFactory gridFactory;
    private Application application;
    private DiskSimulation simulation;
    private InitialConditions initialConditions;
	
	public SimulationRunner(Application diskSimulation) {
	    this.application = diskSimulation;
	    gridFactory = application.getGridFactory();
	    initialConditions = application.getInitialConditions();
	    simulationTimeStep = new TimeStep();
	    setDefaultParameters();
	}

    private void setDefaultParameters() {
        simulationTimeStep.setTime(10.0 * PhysicalConstants.day);
	    totalDuration = 30.0 * PhysicalConstants.year;
	    snapshotInterval = 1.0 * PhysicalConstants.year;
    }

	public void run() {
	    simulation = new DiskSimulation(gridFactory, initialConditions);
        currentSimulationTime = new CurrentSimulationTime();
        System.out.println("Running simulation...");
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

}
