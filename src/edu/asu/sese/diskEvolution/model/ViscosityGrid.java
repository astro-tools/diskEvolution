package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class ViscosityGrid extends MidpointGrid {

    public ViscosityGrid(RadialGrid radialGrid) {
        super(radialGrid);
    }

    public ViscosityGrid(ViscosityGrid viscosityGrid) {
		super(viscosityGrid.radialGrid);
		for (int i = 0; i < zoneCount; ++i) {
			setValue(i, viscosityGrid.getValue(i));
		}
	}
}
		
