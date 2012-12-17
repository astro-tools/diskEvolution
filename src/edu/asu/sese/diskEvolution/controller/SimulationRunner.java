package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.TimeStep;

public class SimulationRunner {
    private CurrentSimulationTime currentSimulationTime;
	private TimeStep simulationTimeStep;
	
	public SimulationRunner() {
	    simulationTimeStep = new TimeStep();
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

}
