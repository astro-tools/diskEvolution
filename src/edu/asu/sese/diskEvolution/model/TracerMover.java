package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.TracerDensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class TracerMover {

		private TracerDensityGrid tracerDensityGrid;
	    private TracerFlowGrid tracerFlowGrid;
	    private double timeStep;

	    public TracerMover(TracerDensityGrid density, TracerFlowGrid tracerFlow,
	            RadialGrid radialGrid) {
	        this.tracerDensityGrid = density;
	        this.tracerFlowGrid = tracerFlow;
	    }

	    public void moveTracer() {
	        int count = tracerFlowGrid.getCount();
	        double densityFloor = tracerDensityGrid.getTracerDensityFloor();
	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = tracerFlowGrid.getValue(i);
	            double mdot2 = tracerFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = tracerDensityGrid.getValue(i);
	            density += difference / tracerDensityGrid.getArea(i);
	            if (density < densityFloor) density = densityFloor;
	            tracerDensityGrid.setValue(i, density);
	        }

	    }
	public double getTimeStep() {
		return timeStep;
	}
		
	public void setTimeStep(double timeStep) {
		this.timeStep = timeStep;
	}
}