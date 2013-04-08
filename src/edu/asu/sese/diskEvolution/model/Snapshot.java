package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class Snapshot {

    DensityGrid density;
    private double time;
    
    public Snapshot(DiskSimulation simulation) {
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


