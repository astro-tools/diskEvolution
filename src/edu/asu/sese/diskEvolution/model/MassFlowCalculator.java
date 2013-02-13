package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class MassFlowCalculator {

    private MassFlowGrid massFlowGrid;
    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;

    public MassFlowCalculator(MassFlowGrid massFlowGrid,
            RadialGrid radialGrid, DensityGrid densityGrid,
            ViscosityGrid viscosityGrid) {
        this.massFlowGrid = massFlowGrid;
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;
        this.viscosityGrid = viscosityGrid;
    }

    public void calculate() {
        int count = massFlowGrid.getCount();
        for (int i=1; i<count-1; ++i) {
            double outerMidpoint = radialGrid.getMidpoint(i);
            double innerMidpoint = radialGrid.getMidpoint(i-1);
            double boundaryPoint = radialGrid.getBoundaryPoint(i);
            double deltaR = outerMidpoint - innerMidpoint;
            double density = densityGrid.getValue(i);
			double viscosity = viscosityGrid.getValue(i);
			double difference = Math.sqrt(outerMidpoint) * density * viscosity;
            System.out.println("outerMidPoint = " + outerMidpoint + " density = " + density +  " viscosity = " + viscosity);
            difference -= Math.sqrt(innerMidpoint) 
                    * densityGrid.getValue(i-1)
                    * viscosityGrid.getValue(i-1);

            double value = 
                    6.0 * Math.PI * Math.sqrt(boundaryPoint)
                    * difference / deltaR;
            massFlowGrid.setValue(i, value);
        }

        double extrapolated = 2 * massFlowGrid.getValue(1)
                - massFlowGrid.getValue(2);
        if (extrapolated < 0.0) {
            extrapolated = 0.0;
        }
        massFlowGrid.setValue(0, extrapolated);
        massFlowGrid.setValue(count-1, 0.0);
       
    }

}
