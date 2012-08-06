package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class MassFlowGridTest {

	RadialGrid radialGrid;
	int zoneCount = 100;
	MassFlowGrid grid;

	@Before
	public void setup() { 
		double rmin = 0.1 * PhysicalConstants.auInCm;
		double rmax = 40.0 * PhysicalConstants.auInCm;
		double deltar0 = 0.01 * PhysicalConstants.auInCm;
		radialGrid = new RadialGrid(rmin, rmax, deltar0, zoneCount);
		grid = new MassFlowGrid(radialGrid);
	}

	@Test
	public void testGridSize() {
		assertEquals(zoneCount+1, grid.getCount());
	}

	@Test
	public void testGetValue() {
		assertEquals(0.0, grid.getValue(1), 1e-14);
	}
	
	@Test
	public void testLastValueIsZero() {
		assertEquals(0.0, grid.getValue(zoneCount), 1e-14);
	}
}

