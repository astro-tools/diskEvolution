package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class PotassiumDensityGrid extends MidpointGrid {

	    
	    public PotassiumDensityGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

	    private double potassiumDensityFloor;

	    public double getPotassiumDensityFloor() {
	        return potassiumDensityFloor;
	    }

	    
	    public PotassiumDensityGrid(DensityGrid densityGrid) {
	        super(densityGrid.getRadialGrid());
	        for (int i = 0; i < zoneCount; ++i) {
	        	double potassiumDensity = potassiumConstant(i) * densityGrid.getValue(i);
	            setValue(i, potassiumDensity);
	        }
	    }

	    private double potassiumConstant(int i) {
	        double potassiumConstant = 0.00024;
			return potassiumConstant;
		}


		public double getTotalPotassiumMass() {
	        double potassiumMass = 0.0;
	        for (int i = 0; i < zoneCount; ++i) {
	            potassiumMass += getValue(i) * getArea(i);
	        }
	        return potassiumMass;
	    }
	    
	    public double calculatePotassiumDensityFloor(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double totalMass = calculateTotalPotassiumMass(density0, radius0, exponent, rin, rout);
	        double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
	        potassiumDensityFloor = 0.0001 * totalMass / area;
	        return potassiumDensityFloor;
	    }
	    
	    private double calculateTotalPotassiumMass(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double area = rout * rout * Math.pow(rout / radius0, -exponent);
	        area -= rin * rin * Math.pow(rin / radius0, -exponent);
	        area *= 2 * Math.PI / (2-exponent);
	        return density0 * area;
	    }


}
