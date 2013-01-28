package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;

public class Snapshot {

    DensityGrid density;
    
    Snapshot(DiskSimulation simulation) {
        density = new DensityGrid(simulation.getDensityGrid());
    }

	public DensityGrid getDensityGrid() {
		return density;
	}
}
