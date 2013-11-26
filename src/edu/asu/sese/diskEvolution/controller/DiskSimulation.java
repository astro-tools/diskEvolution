package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.TemperatureGrid;
/*import edu.asu.sese.diskEvolution.model.TracerDensityGrid;
import edu.asu.sese.diskEvolution.model.TracerFlowGrid;*/
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class DiskSimulation {

    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private GridFactory factory;
    private InitialConditions initialConditions;
    private TemperatureGrid temperatureGrid;
    private double currentTime;
/*    private TracerFlowGrid tracerFlowGrid;*/
 /*    private TracerDensityGrid tracerDensity;*/
    
    public DiskSimulation(GridFactory factory, 
            InitialConditions initialConditions) {
        this.factory = factory;
        this.initialConditions = initialConditions;
        setupGrids();
    }
    
    private void setupGrids() {
        radialGrid = new RadialGrid(factory);
        setupDensityGrid();
        viscosityGrid = new ViscosityGrid(getRadialGrid());
        viscosityGrid
                .initializeWithPowerLaw(1e9, 
                        initialConditions.getRadius0(), 1.0);
        massFlowGrid = new MassFlowGrid(getRadialGrid());
        temperatureGrid = new TemperatureGrid(getRadialGrid());
        setupTemperatureGrid();
    }

    private void setupDensityGrid() {
        densityGrid = new DensityGrid(getRadialGrid());
        double density0 = initialConditions.getDensity0();
        double radius0 = initialConditions.getRadius0();
        double exponent = initialConditions.getExponent();
        double rin = initialConditions.getRIn();
        double rout = initialConditions.getROut();
        densityGrid.calculateDensityFloor(density0, radius0, exponent, rin, rout);
        densityGrid.initializeWithPowerLaw(density0, radius0, exponent, rin, rout);
    }
    
/*    private void setupTracerDensityGrid(){
    	tracerDensity = new TracerDensityGrid(radialGrid);
    	double cellDensity = 0.01 * PhysicalConstants.lunarMass
    			/densityGrid.getArea(10);
    	
    	tracerDensity.setValue(10, cellDensity);
    }*/
    
   //come back to this later and think about initializing it.
    private void setupTemperatureGrid(){
    	temperatureGrid = new TemperatureGrid(getRadialGrid());
 /*   	double temperatureFloor = 300.0;*/
 
    	double density = initialConditions.getDensity0();
    	double viscosity = 10e9;
    	double keplerianFrequency = 1.0;
/*    	temperatureGrid.CalculateTemperatureFloor(temperatureFloor);*/
    	temperatureGrid.CalculateTemperature(density, viscosity, keplerianFrequency);
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public DensityGrid getDensityGrid() {
        return densityGrid;
    }

    public MassFlowGrid getMassFlowGrid() {
        return massFlowGrid;
    }

    public ViscosityGrid getViscosityGrid() {
        return viscosityGrid;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double time) {
        currentTime = time;
    }

	public RadialGrid getTemperatureGrid() {
		return null;
	}

/*	public TracerDensityGrid getTracerDensityGrid() {
		return tracerDensity;
	}
	public TracerFlowGrid getTracerFlowGrid() {
		return tracerFlowGrid;
	}
*/
}
