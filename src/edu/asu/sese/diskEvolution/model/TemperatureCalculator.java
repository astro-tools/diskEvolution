package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class TemperatureCalculator {

	 private TemperatureGrid temperatureGrid;
	 private RadialGrid radialGrid;
	 private DensityGrid densityGrid;
	 

	    public TemperatureCalculator(TemperatureGrid temperatureGrid,
	            RadialGrid radialGrid, DensityGrid densityGrid) {
	        this.temperatureGrid = temperatureGrid;
	        this.radialGrid = radialGrid;
	        this.densityGrid = densityGrid;
	        
	    }
	    public void calculate() {
	        int count = temperatureGrid.getCount();
	        for (int i=1; i<count-1; ++i) {
	        	double temperature;
				double radius = radialGrid.getMidpoint(i);
				double keplerianFrequency;
				double density = densityGrid.getValue(i);
				keplerianFrequency = PhysicalConstants.gravitationalConstant
						* PhysicalConstants.earthMass;
				keplerianFrequency /= Math.pow(radius, 3.0);
				temperature = density * Math.pow(keplerianFrequency, 0.5);
				temperature *= 1.125 * PhysicalConstants.alpha * PhysicalConstants.BoltzmannConstant;
				temperature /= PhysicalConstants.stefanBoltzmannConstant * PhysicalConstants.molecularMass;
				temperature = Math.pow(temperature, 0.33);
				temperatureGrid.setValue(i, temperature);
	        }
	    }
	}

//TempCalculator 