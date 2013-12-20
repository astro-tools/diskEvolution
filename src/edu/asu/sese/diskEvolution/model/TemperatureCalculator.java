package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;


public class TemperatureCalculator {

	 private TemperatureGrid temperatureGrid;
	 private RadialGrid radialGrid;
	 private DensityGrid densityGrid;
	 private ViscosityGrid viscosityGrid;

	    public TemperatureCalculator(TemperatureGrid temperatureGrid,
	            RadialGrid radialGrid, DensityGrid densityGrid,
	            ViscosityGrid viscosityGrid) {
	        this.temperatureGrid = temperatureGrid;
	        this.radialGrid = radialGrid;
	        this.densityGrid = densityGrid;
	        this.viscosityGrid = viscosityGrid;
	    }
	    public void calculate() {
	        int count = temperatureGrid.getCount();
	        for (int i=1; i<count-1; ++i) {
	        	double temperature;
				double radius = radialGrid.getMidpoint(i);
				double keplerianFrequency;
				double density = densityGrid.getValue(i);
				double viscosity = viscosityGrid.getValue(i);
				keplerianFrequency = PhysicalConstants.gravitationalConstant
						* PhysicalConstants.earthMass;
				keplerianFrequency /= Math.pow(radius, 3.0);
				temperature = density * viscosity;
				temperature *= 1.125 * keplerianFrequency;
				temperature /= PhysicalConstants.stefanBoltzmannConstant;
				temperature = Math.pow(temperature, 0.25);
				temperatureGrid.setValue(i, temperature);
	        }
	    }
	}

//TempCalculator 