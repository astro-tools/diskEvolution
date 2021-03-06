package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.TemperatureGrid;
import edu.asu.sese.diskEvolution.model.WaterDensityGrid;
import edu.asu.sese.diskEvolution.model.VolatileFlowGrid;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.util.GridFactory;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
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
    private VolatileFlowGrid volatileFlowGrid;
     private WaterDensityGrid volatileDensity;
    
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
        viscosityGrid.initializeWithPowerLaw(1e9, 
            initialConditions.getRadius0(), 1.0);
        massFlowGrid = new MassFlowGrid(getRadialGrid());
        temperatureGrid = new TemperatureGrid(getRadialGrid());
    }

    private void setupDensityGrid() {
        densityGrid = new DensityGrid(getRadialGrid());
        double density0 = initialConditions.getDensity0();
        double radius0 = initialConditions.getRadius0();
        double exponent = initialConditions.getExponent();
        double rin = initialConditions.getRIn();
        double rout = initialConditions.getROut();
      //  densityGrid.calculateDensityFloor(density0, radius0, exponent, rin, rout);
        densityGrid.initializeWithPowerLaw(density0, radius0, exponent, rin, rout);
    }
    
    private void setupVolatileDensityGrid(){
    	volatileDensity = new WaterDensityGrid(radialGrid);
    	double cellDensity = 0.01 * PhysicalConstants.lunarMass
    			/densityGrid.getArea(10);
    	
    	volatileDensity.setValue(10, cellDensity);
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

	public TemperatureGrid getTemperatureGrid() {
		return temperatureGrid;
	}

	public WaterDensityGrid getVolatileDensityGrid() {
		return volatileDensity;
	}
	public VolatileFlowGrid getVolatileFlowGrid() {
		return volatileFlowGrid;
	}

}