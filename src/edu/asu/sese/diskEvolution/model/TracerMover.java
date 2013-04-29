package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.TracerDensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class TracerMover {

		private TracerDensityGrid tracerDensityGrid;
	    private MassFlowGrid massFlowGrid;
	    private double timeStep;

	    public TracerMover(TracerDensityGrid density, MassFlowGrid massFlow,
	            RadialGrid radialGrid) {
	        this.tracerDensityGrid = density;
	        this.massFlowGrid = massFlow;
	    }

	    public void moveTracer() {
	        int count = massFlowGrid.getCount();
	        double densityFloor = tracerDensityGrid.getTracerDensityFloor();
	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = massFlowGrid.getValue(i);
	            double mdot2 = massFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = tracerDensityGrid.getValue(i);
	            density += difference / tracerDensityGrid.getArea(i);
	            if (density < densityFloor) density = densityFloor;
	            tracerDensityGrid.setValue(i, density);
	        }

	    }
}