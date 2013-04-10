package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class TracerDensityGrid extends MidpointGrid {

	    
	    public TracerDensityGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

		private double tracerDensityFloor;

	    public double getTracerDensityFloor() {
	        return tracerDensityFloor;
	    }

	    
	    public TracerDensityGrid(DensityGrid densityGrid) {
	        super(densityGrid.getRadialGrid());
	        for (int i = 0; i < zoneCount; ++i) {
	            setValue(i, densityGrid.getValue(i));
	        }
	    }

	    public double getTotalTracerMass() {
	        double tracerMass = 0.0;
	        for (int i = 0; i < zoneCount; ++i) {
	            tracerMass += getValue(i) * getArea(i);
	        }
	        return tracerMass;
	    }
	    
	    public double calculateTracerDensityFloor(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double totalMass = calculateTotalMass(density0, radius0, exponent, rin, rout);
	        double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
	        tracerDensityFloor = 0.0001 * totalMass / area;
	        return tracerDensityFloor;
	    }
	    
	    private double calculateTotalMass(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double area = rout * rout * Math.pow(rout / radius0, -exponent);
	        area -= rin * rin * Math.pow(rin / radius0, -exponent);
	        area *= 2 * Math.PI / (2-exponent);
	        return density0 * area;
	    }


}
