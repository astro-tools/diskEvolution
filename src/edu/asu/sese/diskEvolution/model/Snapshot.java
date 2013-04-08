package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class Snapshot {

    DensityGrid density;
    
    public Snapshot(DiskSimulation simulation) {
        density = new DensityGrid(simulation.getDensityGrid());
    }

	public DensityGrid getDensityGrid() {
		return density;
	}
}


