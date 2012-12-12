package edu.asu.sese.diskEvolution.controller;


import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class SimulationTimeStep {
	  private MassFlowGrid massFlowGrid;
	    private RadialGrid radialGrid;


	    public double timestep;
	    public double mdot;
	    public int intervalCount = radialGrid.getIntervalCount();

	public void TimeStep() {
		for (int i = 0; i < intervalCount; ++i) {
			timestep = 0.0;
			mdot = massFlowGrid.getValue(i);
			timestep +=  timestep* mdot;
		}
	}
	



   
}
