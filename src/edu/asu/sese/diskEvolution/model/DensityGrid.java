package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.RadialGrid;


public class DensityGrid extends MidpointGrid {

    public DensityGrid(RadialGrid radialGrid) {
        super(radialGrid);
    }

    public void initializeWithPowerLaw(Parameters p) {
        super.initializeWithPowerLaw(p.getDensity0(), 
                p.getRadius0(), p.getExponent());
    }
    
}
