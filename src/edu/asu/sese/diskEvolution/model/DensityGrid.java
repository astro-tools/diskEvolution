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
        
        for (int i =0; i < zoneCount; i++){
        	double innerBoundary = grid.getBoundaryPoint(i);
        	double outerBoundary = grid.getBoundaryPoint(i+1);
            double density = getValue(i);
        	
        	if (innerBoundary < rin || outerBoundary > rout) {
        		if ((outerBoundary > rin && innerBoundary < rin) ||
            			(innerBoundary < rout && outerBoundary > rout)){ 
            		
            		if (innerBoundary < rin && outerBoundary > rin){
            		density = getValue(i)*(Math.pow(outerBoundary, 2) - Math.pow(rin, 2))/
            				(Math.pow(outerBoundary, 2) - Math.pow(innerBoundary, 2));
            		}
            		else if (innerBoundary < rout && outerBoundary > rout){
            			density = getValue(i)*(Math.pow(rout, 2)-Math.pow(innerBoundary, 2))/
            					(Math.pow(outerBoundary, 2) - Math.pow(innerBoundary, 2));
            		}            		
            	}
            	
            	else {density = 0.0;}
            	setValue(i, density);
            }   
        }
    }
}
