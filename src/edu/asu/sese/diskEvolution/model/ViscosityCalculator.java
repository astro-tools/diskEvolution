package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class ViscosityCalculator {

	 private ViscosityGrid viscosityGrid;
	 private RadialGrid radialGrid;
	 private DensityGrid densityGrid;
	 //private TemperatureGrid temperatureGrid;

	    public ViscosityCalculator(ViscosityGrid viscosityGrid,
	            RadialGrid radialGrid, DensityGrid densityGrid) {
	    	this.viscosityGrid = viscosityGrid; 
	        this.radialGrid = radialGrid;
	        this.densityGrid = densityGrid;
	        //this.temperatureGrid = temperatureGrid;
	        
	    }
	    public void calculate() {
	        int count = viscosityGrid.getCount();
	        for (int i=1; i<count-1; ++i) {
	        	double viscosity;
	        	double radius = radialGrid.getMidpoint(i);
	        	double density = densityGrid.getValue(i);
				double keplerianFrequency;
				//double temperature = temperatureGrid.getValue(i);
				keplerianFrequency = PhysicalConstants.gravitationalConstant * PhysicalConstants.earthMass;
				keplerianFrequency /= Math.pow(radius, 3.0);
				keplerianFrequency = Math.pow(keplerianFrequency, 0.5);
				viscosity = Math.pow(3.14, 2) * Math.pow(PhysicalConstants.gravitationalConstant, 2) * Math.pow(density, 2);
				viscosity /= Math.pow(keplerianFrequency, 3);
				
				//viscosity = Math.pow(PhysicalConstants.alpha, 4.0) * 1.125 * density * 
				//		Math.pow(PhysicalConstants.BoltzmannConstant, 4.0);
				//viscosity *= Math.pow(PhysicalConstants.molecularMass, -4.0) * Math.pow(keplerianFrequency, -2.0) *
				//		Math.pow(PhysicalConstants.stefanBoltzmannConstant, -1.0);
				//viscosity = Math.pow(viscosity, 0.33);
				viscosityGrid.setValue(i, viscosity);
	        }
	    }
	}
