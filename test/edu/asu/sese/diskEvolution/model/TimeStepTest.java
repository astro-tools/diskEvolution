package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.asu.sese.diskEvolution.util.RadialGrid;

public class TimeStepTest {

    private TimeStep timeStep;
    private DensityGrid densityGrid;
    private MassFlowGrid massFlowGrid;
    private RadialGrid radialGrid;

    @Before
    public void setup() {
        timeStep = new TimeStep();
        timeStep.setTime(3600.0);
        radialGrid = new RadialGrid(0.0, 10.0, 0.1, 10);
        densityGrid  = new DensityGrid(radialGrid);
        massFlowGrid = new MassFlowGrid(radialGrid);
    }
    
    @Test
    public void testUpdateTime() {
        densityGrid.setValue(1, 1e17 / densityGrid.getArea(1));
        massFlowGrid.setValue(2, 1.0e16);
        double expect = 5.0;
        timeStep.update(densityGrid, massFlowGrid);
        double newTimeStep = timeStep.getTime();
        assertEquals(expect, newTimeStep, 1e-6);
    }
}
