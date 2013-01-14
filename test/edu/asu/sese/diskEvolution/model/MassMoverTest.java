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
		double density = densityGrid.getValue(9);
		mover.moveMass();
		double value = densityGrid.getValue(9) + (240.0*PhysicalConstants.hour* 
				(massFlow.getValue(10)- massFlow.getValue(9)));
		assertEquals(value, density, 1e-13);
		
		}		

}
