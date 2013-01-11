package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class MassMover {
    private DensityGrid densityGrid;
    private MassFlowGrid massFlowGrid;
    private double timeStep;

    public MassMover(DensityGrid density, MassFlowGrid massFlow,
            RadialGrid radialGrid) {
        this.densityGrid = density;
        this.massFlowGrid = massFlow;
    }

    public void moveMass() {
        int count = massFlowGrid.getCount();
        double value1, value2, difference, density = 0.0;
        for (int i = 0; i < count - 1; ++i) {

            double mdot1 = massFlowGrid.getValue(i);
            double mdot2 = massFlowGrid.getValue(i + 1);
            value1 = mdot1 * timeStep;
            value2 = mdot2 * timeStep;
            difference = value2 - value1;
            density = densityGrid.getValue(i);
            density += difference;
            densityGrid.setValue(i, density);
        }
    }

	public double getTimeStep() {
		return timeStep;
	}
	
	public void setTimeStep(double timeStep) {
		this.timeStep = timeStep;
	}
}
