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

    public void initializeWithPowerLaw(double density0, double radius0, 
            double d, double rin, double rout) {
        super.initializeWithPowerLaw(density0, radius0, d);
        RadialGrid radialGrid = getRadialGrid();
        
        for (int i = 0; i < zoneCount; i++) {
        	double innerBoundary = radialGrid.getBoundaryPoint(i);
        	double outerBoundary = radialGrid.getBoundaryPoint(i+1);
            double density = getValue(i);
        	
            if (outsideDisk(innerBoundary, outerBoundary, rin, rout)) {
                density = 0.0;
            }
            
            if (onInnerEdge(innerBoundary, outerBoundary, rin)) {
                density *= fractionWithinInnerEdge(
                        rin, innerBoundary, outerBoundary);
            }
            
        	if (onOuterEdge(innerBoundary, outerBoundary, rout)) {
        	    density *= fractionWithinOuterEdge(
        	            rout, innerBoundary, outerBoundary);
        	}
        	
            setValue(i, density);
        }
    }

    private boolean outsideDisk(double innerBoundary, double outerBoundary,
            double rin, double rout) {
        return (outerBoundary < rin) || (innerBoundary > rout);
    }

    private boolean onInnerEdge(double innerBoundary, double outerBoundary,
            double rin) {
        return (innerBoundary < rin) && (outerBoundary > rin);
    }

    private boolean onOuterEdge(double innerBoundary, double outerBoundary,
            double rout) {
        return (innerBoundary < rout) && (outerBoundary > rout);
    }

    private double fractionWithinInnerEdge(double rin, double innerBoundary,
            double outerBoundary) {
        return (outerBoundary * outerBoundary - rin * rin)/
        		(outerBoundary * outerBoundary - innerBoundary * innerBoundary);
    }

    private double fractionWithinOuterEdge(double rout, double innerBoundary,
            double outerBoundary) {
        return (rout * rout - innerBoundary * innerBoundary)/
        		(outerBoundary * outerBoundary - innerBoundary * innerBoundary);
    }
}
