package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.TimeStep;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class SimulationRunner {
    private CurrentSimulationTime currentSimulationTime;
	private TimeStep simulationTimeStep;
    private double totalDuration;
	
	public SimulationRunner() {
	    simulationTimeStep = new TimeStep();
	    simulationTimeStep.setTime(10.0 * PhysicalConstants.day);
	    totalDuration = 30.0 * PhysicalConstants.year;
	}

	public void run() {
        currentSimulationTime = new CurrentSimulationTime();
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

}
