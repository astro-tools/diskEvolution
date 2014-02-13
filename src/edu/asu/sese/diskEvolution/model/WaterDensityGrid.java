package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class WaterDensityGrid extends MidpointGrid {

	    
	    public WaterDensityGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

		private double waterDensityFloor;

	    public double getWaterDensityFloor() {
	        return waterDensityFloor;
	    }

	    
	    public WaterDensityGrid(DensityGrid densityGrid) {
	        super(densityGrid.getRadialGrid());
	        for (int i = 0; i < zoneCount; ++i) {
	        	double waterDensity = waterConstant(i) * densityGrid.getValue(i);
	            setValue(i, waterDensity);
	        }
	    }

	    private double waterConstant(int i) {
	        double waterConstant = 0.0005;
			return waterConstant;
	    }

	    public double getTotalWaterMass() {
	        double waterMass = 0.0;
	        for (int i = 0; i < zoneCount; ++i) {
	            waterMass += getValue(i) * getArea(i);
	        }
	        return waterMass;
	    }
	    
	    public double calculateWaterDensityFloor(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double totalMass = calculateTotalWaterMass(density0, radius0, exponent, rin, rout);
	        double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
	        waterDensityFloor = 0.0001 * totalMass / area;
	        return waterDensityFloor;
	    }
	    
	    private double calculateTotalWaterMass(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double area = rout * rout * Math.pow(rout / radius0, -exponent);
	        area -= rin * rin * Math.pow(rin / radius0, -exponent);
	        area *= 2 * Math.PI / (2-exponent);
	        return density0 * area;
	    }


}
