package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class MassMover {
	  private MassFlowGrid massFlowGrid;
	  private DensityGrid densityGrid;
	  private Double timeStep;


	public MassMover(DensityGrid density, MassFlowGrid massFlow,
			RadialGrid radialGrid) {
	}
	public double mdot1, mdot2;
	public double moveMass() {
		int count = massFlowGrid.getCount();
		Double step = getTimeStep();
		double value1, value2, difference, density = 0.0;
		for (int i=1; i<count; ++i) {

		 mdot1 = massFlowGrid.getValue(i);
		 mdot2 = massFlowGrid.getValue(i+1);
		 value1 = mdot1*step;
		 value2 = mdot2*step;
		 difference = value2 - value1;
		 density = densityGrid.getValue(i);
		 density += difference;
		}
		return density;
	}
	public double getTimeStep() {
		return timeStep;
	}
	public void setTimeStep(Double timeStep) {
		this.timeStep = timeStep;
	}
	




}
