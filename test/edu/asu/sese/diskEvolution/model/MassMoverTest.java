package edu.asu.sese.diskEvolution.model;

import java.util.Arrays;

import org.junit.*;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import static org.junit.Assert.*;

public class MassMoverTest {	
	
	private RadialGrid radialGrid;
	private DensityGrid density;
	private MassFlowGrid massFlow;
	private MassMover mover;
	private int intervalCount = 100;

	@Before
	public void setup() {
		double rmin = 0.1 * PhysicalConstants.earthRadiusInCm;
		double rmax = 40.0 * PhysicalConstants.earthRadiusInCm;
		double deltar0 = 0.01 * PhysicalConstants.earthRadiusInCm;
		radialGrid = new RadialGrid(rmin, rmax , deltar0 , intervalCount);
		
		density = new DensityGrid(radialGrid);
		density.initializeWithPowerLaw(1.0e3, PhysicalConstants.earthRadiusInCm, -1.5);
		
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
