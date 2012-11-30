package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class DensityGridTest {
	 double rmin;
	 double rmax;
	 double deltar0;
	 double radius0;
	 double density0;
	 double exponent;
	 
	 DensityGrid grid;
	 RadialGrid radialGrid;
	 int zoneCount = 100;
	     
    @Before
    public void setup() {
       rmin = 0.1 * PhysicalConstants.earthRadiusInCm;
       rmax = 40.0 * PhysicalConstants.earthRadiusInCm;
       deltar0 = 0.01 * PhysicalConstants.earthRadiusInCm;
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
        grid.initializeWithPowerLaw(1.0e3, PhysicalConstants.earthRadiusInCm, -1.5);
        double rcheck = Math.sqrt(0.1 * 0.11);
        double expected = 1.0e3 * Math.pow(rcheck, -1.5);
        assertEquals(expected, grid.getValue(0), 1e-10);
    }
    
//	@Test
//  public void testTotalMassIsZeroWhenDensityIsZero(){
//		assertEquals(0.0, grid.getTotalMass(), 1e-12);
//	}
	
    @Test
    public void testTotalMass() {
        double mass = 1.52e21;
        assertEquals(mass, grid.getTotalMass(), 1e19);
    }

 }
