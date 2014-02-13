package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class SodiumDensityGrid extends MidpointGrid {

	    
	    public SodiumDensityGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

		private double sodiumDensityFloor;

	    public double getSodiumrDensityFloor() {
	        return sodiumDensityFloor;
	    }

	    
	    public SodiumDensityGrid(DensityGrid densityGrid) {
	        super(densityGrid.getRadialGrid());
	        for (int i = 0; i < zoneCount; ++i) {
	        	double sodiumDensity = sodiumConstant(i) * densityGrid.getValue(i);
	            setValue(i, sodiumDensity);
	        }
	    }

	    private double sodiumConstant(int i) {
	        double sodiumConstant = 0.0025;
			return sodiumConstant;
	    }

	    public double getTotalSodiumMass() {
	        double sodiumMass = 0.0;
	        for (int i = 0; i < zoneCount; ++i) {
	            sodiumMass += getValue(i) * getArea(i);
	        }
	        return sodiumMass;
	    }
	    
	    public double calculateSodiumDensityFloor(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double totalMass = calculateTotalSodiumMass(density0, radius0, exponent, rin, rout);
	        double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
	        sodiumDensityFloor = 0.0001 * totalMass / area;
	        return sodiumDensityFloor;
	    }
	    
	    private double calculateTotalSodiumMass(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double area = rout * rout * Math.pow(rout / radius0, -exponent);
	        area -= rin * rin * Math.pow(rin / radius0, -exponent);
	        area *= 2 * Math.PI / (2-exponent);
	        return density0 * area;
	    }


}




