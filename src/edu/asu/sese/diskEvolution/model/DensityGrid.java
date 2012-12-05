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

    public void initializeWithPowerLaw(double density0, double radius0, double d,
            double rin, double rout) {
        super.initializeWithPowerLaw(density0, radius0, d);
        RadialGrid grid = getRadialGrid();
        for (int index =0; index < zoneCount; index++){
            if (grid.getBoundaryPoint(index+1) < rin 
         		   || grid.getBoundaryPoint(index) > rout) {
         	   setValue(index, 0.0);
            }	
        }
     
    }



}

