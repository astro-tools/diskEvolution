package edu.asu.sese.diskEvolution.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.asu.sese.diskEvolution.util.RadialGrid;

public class RadialGridTest {

    double rmin;
    double rmax;
    double deltar0;
    int intervalCount;
    RadialGrid grid;

    @Before
    public void setup() {
        rmin = 1.0;
        rmax = 10.0;
        deltar0 = 0.1;
        intervalCount = 10;
        grid = new RadialGrid(rmin, rmax, deltar0, intervalCount);
    }
    
    @Test
    public void testCalculateScaleFactor() {
        double factor = RadialGrid.calculateScaleFactorUsingNewtonsMethod(
                rmax - rmin, deltar0, intervalCount);
        double expected = 1.4521273829893704;
        assertEquals(expected, factor, 1e-10);
    }
    
    @Test
    public void testCalculateScaleFactorWithManyPoints() {
        deltar0 = 0.01;
        intervalCount = 1000;

        double factor = RadialGrid.calculateScaleFactorUsingNewtonsMethod(
                rmax - rmin, deltar0, intervalCount);
        double expected = 0.9997852445843816;
        assertEquals(expected, factor, 1e-10);
    }
    
    @Test
    public void testRMin() {
        assertEquals(rmin, grid.getBoundaryPoint(0), 1e-14);
    }

    @Test
    public void testRMax() {
        assertEquals(rmax, grid.getBoundaryPoint(intervalCount), 1e-14);
    }

    @Test
    public void testFirstInterval() {
        assertEquals(deltar0, grid.getInterval(0), 1e-14);
    }

    @Test
    public void testIntervalsMatchBoundaryPoints() {
        final double[] boundary = grid.getBoundaries();
        double[] calculatedInterval = new double [intervalCount];
        for (int i = 0; i < intervalCount; ++ i) {
            calculatedInterval[i] = boundary[i+1] - boundary[i];
        }
        assertArrayEquals(calculatedInterval, grid.getIntervals(), 1e-14);
    }

    @Test
    public void testAreaOfFirstInterval() {
        double expect = Math.PI * ((rmin+deltar0)*(rmin+deltar0) - rmin*rmin);
        assertEquals(expect, grid.getArea(0), expect*1e-14);
    }
    
    @Test
    public void testSumOfAllAreas() {
        double expect = Math.PI * (rmax*rmax - rmin*rmin);
        double sum = 0.0;
        for (int i = 0; i < intervalCount; ++i) {
            sum += grid.getArea(i);
        }
        assertEquals(expect, sum, expect*1e-14);
    }
    
    @Test
    public void testFirstMidpoint() {
        double expect = Math.sqrt(rmin * (rmin+deltar0));
        assertEquals(expect, grid.getMidpoint(0), 1e-14);
    } 
    
    @Test
    public void testMinimumRadius() {
        assertEquals(rmin, grid.getMinimumRadius(), 1e-14);
    }
    
    @Test
    public void testMaximumRadius() {
        assertEquals(rmax, grid.getMaximumRadius(), 1e-14);
    }
}
