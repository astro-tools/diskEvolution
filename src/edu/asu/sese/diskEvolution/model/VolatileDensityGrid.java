package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class VolatileDensityGrid extends MidpointGrid {

	    
	    public VolatileDensityGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

		private double volatileDensityFloor;

	    public double getVolatileDensityFloor() {
	        return volatileDensityFloor;
	    }

	    
	    public VolatileDensityGrid(DensityGrid densityGrid) {
	        super(densityGrid.getRadialGrid());
	        for (int i = 0; i < zoneCount; ++i) {
	            setValue(i, densityGrid.getValue(i));
	        }
	    }

	    public double getTotalVolatileMass() {
	        double volatileMass = 0.0;
	        for (int i = 0; i < zoneCount; ++i) {
	            volatileMass += getValue(i) * getArea(i);
	        }
	        return volatileMass;
	    }
	    
	    public double calculateVolatileDensityFloor(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double totalMass = calculateTotalVolatileMass(density0, radius0, exponent, rin, rout);
	        double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
	        volatileDensityFloor = 0.0001 * totalMass / area;
	        return volatileDensityFloor;
	    }
	    
	    private double calculateTotalVolatileMass(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double area = rout * rout * Math.pow(rout / radius0, -exponent);
	        area -= rin * rin * Math.pow(rin / radius0, -exponent);
	        area *= 2 * Math.PI / (2-exponent);
	        return density0 * area;
	    }


}
