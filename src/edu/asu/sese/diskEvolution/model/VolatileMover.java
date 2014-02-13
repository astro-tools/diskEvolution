package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.WaterDensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class VolatileMover {

		private WaterDensityGrid volatileDensityGrid;
	    private VolatileFlowGrid volatileFlowGrid;
	    private double timeStep;

	    public VolatileMover(WaterDensityGrid density, VolatileFlowGrid volatileFlow,
	            RadialGrid radialGrid) {
	        this.volatileDensityGrid = density;
	        this.volatileFlowGrid = volatileFlow;
	    }

	    public void moveVolatile() {
	        int count = volatileFlowGrid.getCount();
	        double densityFloor = volatileDensityGrid.getVolatileDensityFloor();
	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = volatileFlowGrid.getValue(i);
	            double mdot2 = volatileFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = volatileDensityGrid.getValue(i);
	            density += difference / volatileDensityGrid.getArea(i);
	            if (density < densityFloor) density = densityFloor;
	            volatileDensityGrid.setValue(i, density);
	        }

	    }
	public double getTimeStep() {
		return timeStep;
	}
		
	public void setTimeStep(double timeStep) {
		this.timeStep = timeStep;
	}
}