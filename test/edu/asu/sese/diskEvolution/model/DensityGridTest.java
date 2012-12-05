package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
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

	@Test
	public void testTotalMassIsZeroWhenDensityIsZero(){
		assertEquals(0.0, grid.getTotalMass(), 1e-12);
	}
	
    @Test
    public void testTotalMassForUniformDensity() {
        double density0 = 1.0e3;
        grid.initializeWithPowerLaw(density0, rmin, 0.0);
        double expect = Math.PI * (rmax * rmax - rmin * rmin) * density0;
        assertEquals(expect, grid.getTotalMass(), 1e10);
    }
    
    @Test 
    public void testTotalMassWithRinAndRout() {
        double density0 = 1.0e3;
        double rin = 1.2 * PhysicalConstants.earthRadiusInCm;
        double rout = 6.0 * PhysicalConstants.earthRadiusInCm;
        grid.initializeWithPowerLaw(density0, rmin, 0.0, rin, rout);
        double expect = Math.PI * (rout * rout - rin * rin) * density0;
        assertEquals(expect, grid.getTotalMass(), 1e10);
    }

 }

