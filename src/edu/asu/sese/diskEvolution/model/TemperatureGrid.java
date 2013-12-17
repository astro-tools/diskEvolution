package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class TemperatureGrid extends MidpointGrid {

	public TemperatureGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

	public TemperatureGrid(TemperatureGrid temperatureGrid) {
		super(temperatureGrid.radialGrid);
		for (int i = 0; i < zoneCount; ++i) {
			setValue(i, temperatureGrid.getValue(i));
		}
	}
}
		
