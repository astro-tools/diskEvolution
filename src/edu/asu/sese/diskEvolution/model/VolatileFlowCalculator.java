package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.RadialGrid;

public class VolatileFlowCalculator {
	private VolatileFlowGrid volatileFlowGrid;
    private RadialGrid radialGrid;
    private VolatileDensityGrid volatileDensityGrid;
    private ViscosityGrid viscosityGrid;

    public VolatileFlowCalculator(VolatileFlowGrid volatileFlowGrid,
            RadialGrid radialGrid, VolatileDensityGrid volatileDensityGrid,
            ViscosityGrid viscosityGrid) {
        this.volatileFlowGrid = volatileFlowGrid;
        this.radialGrid = radialGrid;
        this.volatileDensityGrid = volatileDensityGrid;
        this.viscosityGrid = viscosityGrid;
    }

    public void calculate() {
        int count = volatileFlowGrid.getCount();
        for (int i=1; i<count-1; ++i) {
            double outerMidpoint = radialGrid.getMidpoint(i);
            double density = volatileDensityGrid.getValue(i);
			double viscosity = viscosityGrid.getValue(i);
			double difference = Math.sqrt(outerMidpoint) * density * viscosity;

            double innerMidpoint = radialGrid.getMidpoint(i-1);
            density = volatileDensityGrid.getValue(i-1);
            viscosity = viscosityGrid.getValue(i-1);
            difference -= Math.sqrt(innerMidpoint) * density * viscosity;

            double deltaR = outerMidpoint - innerMidpoint;
            double boundaryPoint = radialGrid.getBoundaryPoint(i);
            double value = 
                    6.0 * Math.PI * Math.sqrt(boundaryPoint)
                    * difference / deltaR;
            volatileFlowGrid.setValue(i, value);
        }

        double extrapolated = 2 * volatileFlowGrid.getValue(1)
                - volatileFlowGrid.getValue(2);
        if (extrapolated < 0.0) {
            extrapolated = 0.0;
        }
        volatileFlowGrid.setValue(0, extrapolated);
        volatileFlowGrid.setValue(count-1, 0.0);
       
    }

}


