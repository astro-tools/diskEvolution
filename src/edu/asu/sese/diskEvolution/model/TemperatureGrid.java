package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.model.DensityGrid;


public class TemperatureGrid extends MidpointGrid {
	   private DensityGrid densityGrid;
	   private ViscosityGrid viscosityGrid;
	   private double temperatureFloor;

	    public double getTemperatureFloor() {
	        return temperatureFloor;
	    }
	    public TemperatureGrid(RadialGrid radialGrid) {
	        super(radialGrid);
	    }
	        
	    public TemperatureGrid(TemperatureGrid temperatureGrid) {
	        super(temperatureGrid.radialGrid);
	        for (int i = 0; i < zoneCount; ++i) {
	            setValue(i, temperatureGrid.getValue(i));
	        }
	    }
	    
/*	    public double CalculateTemperatureFloor(double temperatureFloor){
	    	temperatureFloor = 300.0;
	    	return temperatureFloor;
	    }*/
	    public void CalculateTemperature() {
	    	for (int i = 0; i < zoneCount; i++){
	    	double temperature;
	    	double radius;
	    	double keplerianFrequency;
	    	radius = radialGrid.getMidpoint(i);
	        keplerianFrequency = PhysicalConstants.gravitationalConstant *
	        		PhysicalConstants.earthMass;
	        keplerianFrequency /= Math.pow(radius, 3.0);
	    	//density = densityGrid.getValue(i);
	    	//viscosity = viscosityGrid.getValue(i);
	    	temperature = densityGrid.getValue(i) * viscosityGrid.getValue(i);
	    	temperature *= 1.125 * keplerianFrequency;
	    	temperature /= PhysicalConstants.stefanBoltzmannConstant;
	    	temperature = Math.pow(temperature, 0.25);
	    	setValue(i, temperature);
	    	}
	     	
	    }
	}
	   

		
		
