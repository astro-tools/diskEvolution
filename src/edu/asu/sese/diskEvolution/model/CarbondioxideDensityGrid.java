package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class CarbondioxideDensityGrid extends MidpointGrid {

	    
	    public CarbondioxideDensityGrid(RadialGrid radialGrid) {
		super(radialGrid);
	}

	    private double carbondioxideDensityFloor;

	    public double getCarbondioxideDensityFloor() {
	        return carbondioxideDensityFloor;
	    }

	    
	    public CarbondioxideDensityGrid(DensityGrid densityGrid) {
	        super(densityGrid.getRadialGrid());
	        for (int i = 0; i < zoneCount; ++i) {
	        	double carbondioxideDensity = carbondioxideConstant(i) * densityGrid.getValue(i);
	            setValue(i, carbondioxideDensity);
	        }
	    }

	    private double carbondioxideConstant(int i) {
	        double carbondioxideConstant = 0.004;
			return carbondioxideConstant;
		}


		public double getTotalCarbondioxideMass() {
	        double carbondioxideMass = 0.0;
	        for (int i = 0; i < zoneCount; ++i) {
	        	carbondioxideMass += getValue(i) * getArea(i);
	        }
	        return carbondioxideMass;
	    }
	    
	    public double calculateCarbondioxideDensityFloor(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double totalMass = calculateTotalCarbondioxideMass(density0, radius0, exponent, rin, rout);
	        double area = Math.PI * Math.pow(radialGrid.getMaximumRadius(), 2);
	        carbondioxideDensityFloor = 0.0001 * totalMass / area;
	        return carbondioxideDensityFloor;
	    }
	    
	    private double calculateTotalCarbondioxideMass(double density0, double radius0, 
	            double exponent, double rin, double rout) {
	        double area = rout * rout * Math.pow(rout / radius0, -exponent);
	        area -= rin * rin * Math.pow(rin / radius0, -exponent);
	        area *= 2 * Math.PI / (2-exponent);
	        return density0 * area;
	    }


}



