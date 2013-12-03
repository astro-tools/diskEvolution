package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.model.DensityGrid;


public class TemperatureGrid extends MidpointGrid{
	   public DensityGrid densityGrid;
	   public ViscosityGrid viscosityGrid;
	   public RadialGrid radialGrid;
	   private double temperatureFloor;
	   


//	    public double getTemperatureFloor() {
//	        return temperatureFloor;
//	    }
	   public TemperatureGrid(RadialGrid radialGrid, DensityGrid densityGrid, ViscosityGrid viscosityGrid) {
           super(radialGrid);
        this.densityGrid = densityGrid;
        this.viscosityGrid = viscosityGrid;
}


	    public TemperatureGrid(TemperatureGrid temperatureGrid) {
	        super(temperatureGrid.radialGrid,temperatureGrid.densityGrid, temperatureGrid.viscosityGrid);
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
	    	double radius = radialGrid.getMidpoint(i);
	    	double keplerianFrequency;
	    	double density = densityGrid.getValue(i);
			double viscosity = viscosityGrid.getValue(i);
	        keplerianFrequency = PhysicalConstants.gravitationalConstant *
	        		PhysicalConstants.earthMass;
	        keplerianFrequency /= Math.pow(radius, 3.0);
	    	temperature =  density * viscosity;
	    	temperature *= 1.125 * keplerianFrequency;
	    	temperature /= PhysicalConstants.stefanBoltzmannConstant;
	    	temperature = Math.pow(temperature, 0.25);
	    	setValue(i, temperature);
	    	}
	     	
	    }
	}
	   

		
		
