
package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class TemperatureSnapshot {

    TemperatureGrid temperature;
    private double time;
    
    public TemperatureSnapshot(DiskSimulation simulation) {
        temperature = new TemperatureGrid(simulation.getTemperatureGrid());
        time = simulation.getCurrentTime();
    }

	public TemperatureGrid getTemperatureGrid() {
		return temperature;
	}

    public double getTime() {
        return time;
    }
}

