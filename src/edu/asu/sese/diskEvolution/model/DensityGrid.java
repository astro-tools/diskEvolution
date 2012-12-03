package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class DensityGrid extends MidpointGrid {
    
    public DensityGrid(RadialGrid radialGrid) {
        super(radialGrid);
    }

    public void initializeWithPowerLaw(Parameters p) {
        super.initializeWithPowerLaw(p.getDensity0(), 
                p.getRadius0(), p.getExponent());
    }
    public double getTotalMass() {
        double density0 = 1e3;
        double rmin = 0.1*PhysicalConstants.earthRadiusInCm;
		double deltar0 = 0.01*PhysicalConstants.earthRadiusInCm;
        double mass = 0.0;
        for (int i = 1; i < zoneCount; ++i) {
        	rmin = rmin + deltar0;
			mass += density0*Math.PI * ((rmin+deltar0)*(rmin+deltar0) - rmin*rmin);
        }
        return mass;
    }   
}
