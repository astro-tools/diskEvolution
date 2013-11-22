package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class DensitySnapshot {

    DensityGrid density;
    private double time;
    
    public DensitySnapshot(DiskSimulation simulation) {
        density = new DensityGrid(simulation.getDensityGrid());
        time = simulation.getCurrentTime();
    }

	public DensityGrid getDensityGrid() {
		return density;
	}

    public double getTime() {
        return time;
    }
}


