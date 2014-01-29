package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class ViscositySnapshot {

	ViscosityGrid viscosity;
    private double time;
    
    public ViscositySnapshot(DiskSimulation simulation) {
    	viscosity = new ViscosityGrid(simulation.getViscosityGrid());
        time = simulation.getCurrentTime();
    }

	public ViscosityGrid getViscosityGrid() {
		return viscosity;
	}

    public double getTime() {
        return time;
    }
}
