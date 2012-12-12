package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class SimulationTimeStep {
	  private MassFlowGrid massFlowGrid;
	    private RadialGrid radialGrid;
	    private DensityGrid densityGrid;
	    private ViscosityGrid viscosityGrid;

	
public int timeMax = 20; 
public double timestep;
public double mdot;

	public void intializeTimeStep() {
		for (int i = 0; i < timeMax; ++i) {
			timestep = 0.0;
			mdot = 3*Math.PI * densityGrid.getValue(i)* viscosityGrid.getValue(i);
			timestep +=  timestep* mdot;
		
		}
	}
	



   
}
