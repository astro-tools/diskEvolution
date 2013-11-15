package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class DensityGrid extends MidpointGrid {
    
    private double densityFloor;

    public double getDensityFloor() {
        return densityFloor;
    }

    public DensityGrid(RadialGrid radialGrid) {
        super(radialGrid);
    }
    
    public DensityGrid(DensityGrid densityGrid) {
        super(densityGrid.radialGrid);
        for (int i = 0; i < zoneCount; ++i) {
            setValue(i, densityGrid.getValue(i));
        }
    }

    public double getTotalMass() {
        double mass = 0.0;
        for (int i = 0; i < zoneCount; ++i) {
           mass += getValue(i) * getArea(i);
        }
        return mass;
    }
    
    public double calculateDensityFloor(double density0, double radius0, 
       double exponent, double rin, double rout) {
       double totalMass = calculateTotalMass(density0, radius0, exponent, rin, rout); 
       double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
       densityFloor = 0.01 * totalMass / area;
       return densityFloor;
    }
    
    private double calculateTotalMass(double density0, double radius0, 
            double exponent, double rin, double rout) {
        double area = rout * rout * Math.pow(rout / radius0, -exponent);
        area -= rin * rin * Math.pow(rin / radius0, -exponent);
        area *= 2 * Math.PI / (2-exponent);
        return density0 * area;
    }

    public void initializeWithPowerLaw(double density0, double radius0, 
            double exponent, double rin, double rout) {
        super.initializeWithPowerLaw(density0, radius0, exponent);
        RadialGrid radialGrid = getRadialGrid();
        
        for (int i = 0; i < zoneCount; i++) {
        	double innerBoundary = radialGrid.getBoundaryPoint(i);
        	double outerBoundary = radialGrid.getBoundaryPoint(i+1);
            double density = getValue(i);
        	
            if (outsideDisk(innerBoundary, outerBoundary, rin, rout)) {
                density = densityFloor;
            }
            
            if (onInnerEdge(innerBoundary, outerBoundary, rin)) {
                double x = fractionWithinInnerEdge(
                        rin, innerBoundary, outerBoundary);
                density = density * x + (1.0 - x) * densityFloor;
            }
            
        	if (onOuterEdge(innerBoundary, outerBoundary, rout)) {
        	    double x = fractionWithinOuterEdge(
        	            rout, innerBoundary, outerBoundary);
        	    density = density * x + (1.0 - x) * densityFloor;
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
