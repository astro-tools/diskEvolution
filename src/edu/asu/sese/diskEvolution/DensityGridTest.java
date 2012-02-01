package edu.asu.sese.diskEvolution;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DensityGridTest {
    DensityGrid grid;
    RadialGrid radialGrid;
    int zoneCount = 100;
    
    @Before
    public void setup() { 
        double rmin = 0.1 * PhysicalConstants.auInCm;
        double rmax = 40.0 * PhysicalConstants.auInCm;
        double deltar0 = 0.01 * PhysicalConstants.auInCm;
        radialGrid = new RadialGrid(rmin, rmax, deltar0, zoneCount);
        grid = new DensityGrid(radialGrid);
    }
    
    @Test
    public void testGridSize() {
        assertEquals(zoneCount, grid.getCount());
    }
    
    @Test
    public void testGetValue() {
        assertEquals(0.0, grid.getValue(1), 1e-14);
    }

    
    @Test
    public void testInitialization() {
        grid.initialize(1.0e3, PhysicalConstants.auInCm, 1.5);
        assertEquals(29441.198549676064, grid.getValue(0), 1e-10);
    }
}
