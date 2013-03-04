package edu.asu.sese.diskEvolution.model;

import java.util.Arrays;

import org.junit.*;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import static org.junit.Assert.*;

public class MassMoverTest {	
	
	private RadialGrid radialGrid;
	private DensityGrid densityGrid;
	private MassFlowGrid massFlow;
	private MassMover mover;
	private int intervalCount = 100;

	@Before
	public void setup() {
		double rmin = 0.1 * PhysicalConstants.earthRadiusInCm;
		double rmax = 40.0 * PhysicalConstants.earthRadiusInCm;
		double deltar0 = 0.01 * PhysicalConstants.earthRadiusInCm;
		radialGrid = new RadialGrid(rmin, rmax , deltar0 , intervalCount);
		
		densityGrid = new DensityGrid(radialGrid);
		densityGrid.initializeWithPowerLaw(1.0e3, PhysicalConstants.earthRadiusInCm, -1.5);
		
		massFlow = new MassFlowGrid(radialGrid);
		
		mover = new MassMover(densityGrid, massFlow, radialGrid);
	}
	
	@Test
	public void testDensityUnchangedWhenFlowIsZero() {
		double [] oldValue = Arrays.copyOf(densityGrid.getValueArray(), intervalCount);
		mover.moveMass();
		assertArrayEquals(oldValue, densityGrid.getValueArray(), 1e-3);
	}
	
	@Test
	public void testDensityChangeWithMassMovement(){
		int index = 9;
        double oldDensity = densityGrid.getValue(index);
		double outerFlowRate = 100.0;
		double innerFlowRate = 150.0;
		double timeStep = 80.0;
        massFlow.setValue(index + 1, outerFlowRate);
        massFlow.setValue(index, innerFlowRate);
        mover.setTimeStep(timeStep);
		mover.moveMass();
		double massFlowDifference = innerFlowRate - outerFlowRate;
        double area = densityGrid.getArea(index);
        double expect = oldDensity + timeStep * massFlowDifference / area;
        double newDensity = densityGrid.getValue(index);
		assertEquals(expect, newDensity, 1e-13);		
	}		

}
