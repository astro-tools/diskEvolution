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
        double mass = 0.0;
        for (int i = 0; i < zoneCount; ++i) {
            mass += getValue(i) * getArea(i);
        }
        return mass;
    }

    public void initializeWithPowerLaw(double density0, double rmin, double d,
            double rin, double rout) {
        // Note: this ignores rin and rout!
        super.initializeWithPowerLaw(density0, rmin, d);
        // Now need to set empty bins (inside rin or outside rout) to zero.
    }

}
