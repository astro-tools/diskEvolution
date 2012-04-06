package edu.asu.sese.diskEvolution;

import java.util.Arrays;

import org.junit.*;
import static org.junit.Assert.*;

public class MassMoverTest {	
	
	private RadialGrid radialGrid;
	private DensityGrid density;
	private MassFlowGrid massFlow;
	private MassMover mover;
	private int intervalCount = 100;

	@Before
	public void setup() {
		double rmin = 0.1 * PhysicalConstants.auInCm;
		double rmax = 40.0 * PhysicalConstants.auInCm;
		double deltar0 = 0.01 * PhysicalConstants.auInCm;
		radialGrid = new RadialGrid(rmin, rmax , deltar0 , intervalCount);
		
		density = new DensityGrid(radialGrid);
		density.initializeWithPowerLaw(1.0e3, PhysicalConstants.auInCm, -1.5);
		
		massFlow = new MassFlowGrid(radialGrid);
		
		mover = new MassMover(density, massFlow, radialGrid);
	}
	
	@Test
	public void testDensityUnchangedWhenFlowIsZero() {
		double [] oldValue = Arrays.copyOf(density.value, intervalCount);
		mover.moveMass();
		assertArrayEquals(oldValue, density.value, 1e-3);
	}

}
