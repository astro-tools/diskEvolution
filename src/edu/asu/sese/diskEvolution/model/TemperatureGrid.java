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
	    
	    public double CalculateTemperature(double temperature, double density,
	            double viscosity, double keplerianFrequency) {
	    	keplerianFrequency = calculateKeplerianFrequency();
	    	density = densityGrid.getValue(zoneCount);
	    	viscosity = viscosityGrid.getValue(zoneCount);
	    	temperature = density * viscosity;
	    	temperature *= 1.125 * Math.pow(keplerianFrequency, 2.0);
	    	temperature /= PhysicalConstants.stefanBoltzmannConstant;
	    	temperature = Math.pow(temperature, 0.25);
	    	return temperature;
	    	
	    }

		private double calculateKeplerianFrequency() {
			double radius; 
			double keplerianFrequency; 
			radius = radialGrid.getMidpoint(zoneCount);
	        keplerianFrequency = PhysicalConstants.gravitationalConstant *
	        		PhysicalConstants.earthMass;
	        keplerianFrequency /= radius;
	        return keplerianFrequency;
	    }
		public RadialGrid getTemperatureGrid() {
			// TODO Auto-generated method stub
			return null;
		}

}
