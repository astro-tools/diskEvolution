package edu.asu.sese.diskEvolution.controller;

public class SimulationRunner {
    private CurrentSimulationTime currentSimulationTime;
	private SimulationTimeStep simulationTimeStep;

	public void run() {
        currentSimulationTime = new CurrentSimulationTime();
        simulationTimeStep = new SimulationTimeStep();
    }

}
