package edu.asu.sese.diskEvolution.controller;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.MassFlowGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class SimulationTimeStep {
	  private MassFlowGrid massFlowGrid;
	    private RadialGrid radialGrid;
	    private DensityGrid densityGrid;

	
public int timeMax = 20;
public double timestep;

	public void intializeTimeStep() {
		for (int i = 0; i < timeMax; ++i) {
			timestep = 0.0;
//			timestep += timestep + timestep*getMdot(i);
			timestep = timestep + 0.02739726027; //10*days/year
			
			
			
		}
	}
	



   
}
