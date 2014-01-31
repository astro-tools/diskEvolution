package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class ViscosityCalculator {

	 private ViscosityGrid viscosityGrid;
	 private RadialGrid radialGrid;
	 private DensityGrid densityGrid;
	 private TemperatureGrid temperatureGrid;

	    public ViscosityCalculator(ViscosityGrid viscosityGrid,
	            RadialGrid radialGrid, DensityGrid densityGrid,
	            TemperatureGrid temperatureGrid) {
	    	this.viscosityGrid = viscosityGrid;
	        this.radialGrid = radialGrid;
	        this.densityGrid = densityGrid;
	        this.temperatureGrid = temperatureGrid;
	        
	    }
	    public void calculate() {
	        int count = viscosityGrid.getCount();
	        for (int i=1; i<count; ++i) {
	        	double viscosity;
	        	double radius = radialGrid.getMidpoint(i);
				double keplerianFrequency;
				double temperature = temperatureGrid.getValue(i);
				keplerianFrequency = PhysicalConstants.gravitationalConstant * PhysicalConstants.earthMass;
				keplerianFrequency /= Math.pow(radius, 3.0);
				viscosity = PhysicalConstants.alpha * PhysicalConstants.BoltzmannConstant * temperature;
				viscosity /= PhysicalConstants.molecularMass * Math.pow(keplerianFrequency, 0.5);
				viscosityGrid.setValue(i, viscosity);
	        }
	    }
	}
