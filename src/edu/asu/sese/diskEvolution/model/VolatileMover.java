package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.WaterDensityGrid;
import edu.asu.sese.diskEvolution.model.CarbondioxideDensityGrid;
import edu.asu.sese.diskEvolution.model.SodiumDensityGrid;
import edu.asu.sese.diskEvolution.model.PotassiumDensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class VolatileMover {

		private WaterDensityGrid waterDensityGrid;
		private CarbondioxideDensityGrid carbondioxideDensityGrid;
		private SodiumDensityGrid sodiumDensityGrid;
		private PotassiumDensityGrid potassiumDensityGrid;
	    private VolatileFlowGrid volatileFlowGrid;
	    private double timeStep;

	    public VolatileMover(WaterDensityGrid waterDensity, VolatileFlowGrid volatileFlow,
	            RadialGrid radialGrid) {
	        this.waterDensityGrid = waterDensity;
	        this.volatileFlowGrid = volatileFlow;
	    }

	    public void moveVolatile() {
	        int count = volatileFlowGrid.getCount();
	        double waterDensityFloor = waterDensityGrid.getWaterDensityFloor();
	        double carbondioxideDensityFloor = carbondioxideDensityGrid.getCarbondioxideDensityFloor();
	        double sodiumDensityFloor = sodiumDensityGrid.getSodiumrDensityFloor();
	        double potassiumDensityFloor = potassiumDensityGrid.getPotassiumDensityFloor();
	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = volatileFlowGrid.getValue(i);
	            double mdot2 = volatileFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = waterDensityGrid.getValue(i);
	            density += difference / waterDensityGrid.getArea(i);
	            if (density < waterDensityFloor) density = waterDensityFloor;
	            waterDensityGrid.setValue(i, density);
	        }
	        
	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = volatileFlowGrid.getValue(i);
	            double mdot2 = volatileFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = carbondioxideDensityGrid.getValue(i);
	            density += difference / carbondioxideDensityGrid.getArea(i);
	            if (density < carbondioxideDensityFloor) density = carbondioxideDensityFloor;
	            carbondioxideDensityGrid.setValue(i, density);
	        }

	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = volatileFlowGrid.getValue(i);
	            double mdot2 = volatileFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = sodiumDensityGrid.getValue(i);
	            density += difference / sodiumDensityGrid.getArea(i);
	            if (density < sodiumDensityFloor) density = sodiumDensityFloor;
	            sodiumDensityGrid.setValue(i, density);
	        }
	        
	        for (int i = 0; i < count - 1; ++i) {
	        	double mdot1 = volatileFlowGrid.getValue(i);
	            double mdot2 = volatileFlowGrid.getValue(i + 1);

	            double value1 = mdot1 * timeStep;
	            double value2 = mdot2 * timeStep;
	            double difference = value2 - value1;
	            double density = potassiumDensityGrid.getValue(i);
	            density += difference / potassiumDensityGrid.getArea(i);
	            if (density < potassiumDensityFloor) density = potassiumDensityFloor;
	            potassiumDensityGrid.setValue(i, density);
	        }
	    }
	public double getTimeStep() {
		return timeStep;
	}
		
	public void setTimeStep(double timeStep) {
		this.timeStep = timeStep;
	}
}