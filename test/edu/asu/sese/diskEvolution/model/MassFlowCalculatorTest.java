package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class MassFlowCalculatorTest {

    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private ViscosityGrid viscosityGrid;
    private MassFlowGrid massFlowGrid;
    private MassFlowCalculator massFlowCalculator;

    @Before
    public void setUp() throws Exception {
        double rmin = 1.0;
        double rmax = 10.0;
        double deltar0 = 0.2;
        radialGrid = new RadialGrid(rmin, rmax, deltar0, 10);
        massFlowGrid = new MassFlowGrid(radialGrid);
        densityGrid = new DensityGrid(radialGrid);
        double density0 = 1.0;
        densityGrid.initializeWithPowerLaw(density0, rmin, -1.0);
        viscosityGrid = new ViscosityGrid(radialGrid);
        double viscosity0 = 2.0;
        viscosityGrid.initializeWithPowerLaw(viscosity0 , rmin, 1.0);
        massFlowCalculator = 
               new MassFlowCalculator(massFlowGrid, radialGrid, 
                       densityGrid, viscosityGrid);
    }

    @Test
    public void testMassFlowIsZeroWhenViscosityIsZero() {
        viscosityGrid.initializeWithPowerLaw(0.0, 1.0, 1.0);
        massFlowCalculator.calculate();
        for (int i=0; i<11; ++i) {
            assertEquals(0.0, massFlowGrid.getValue(i), 1e-14);    
        }
    }
    
    @Test
    public void testSecondGridValue() {
        massFlowCalculator.calculate();
        double r1 = radialGrid.getBoundaryPoint(1);
        double rmid1 = radialGrid.getMidpoint(0);
        double rmid2 = radialGrid.getMidpoint(1);
        double density1 = densityGrid.getValue(0);
        double density2 = densityGrid.getValue(1);
        double viscosity1 = viscosityGrid.getValue(0);
        double viscosity2 = viscosityGrid.getValue(1);
        
        double term1 = Math.sqrt(rmid1) * density1 * viscosity1;
        double term2 = Math.sqrt(rmid2) * density2 * viscosity2;
        double prefactor = 6.0 * Math.PI * Math.sqrt(r1);
        double expect = prefactor * (term2 - term1) /(rmid2 - rmid1);        
        
        assertEquals(expect, massFlowGrid.getValue(1), 1e-12);
    }
    
    @Test
    public void testInnerEdgeIsExtrapolated() {
        massFlowCalculator.calculate();
        double expect = 2.0 * massFlowGrid.getValue(1) 
                - massFlowGrid.getValue(2);
        assertEquals(expect, massFlowGrid.getValue(0), 1e-12);
    }
    
    @Test
    public void testOuterEdgeIsZero() {
        massFlowCalculator.calculate();
        assertEquals(0.0, massFlowGrid.getValue(massFlowGrid.getCount()-1),
                1e-12);
    }

    @Test
    public void testMassFlowIsZeroPowerLawCancels() {
        viscosityGrid.initializeWithPowerLaw(1.0 , 1.0, 1.0);
        densityGrid.initializeWithPowerLaw(2.0, 1.0, -1.5);
        massFlowCalculator.calculate();
        for (int i=0; i<11; ++i) {
            assertEquals(0.0, massFlowGrid.getValue(i), 1e-12);    
        }
    }

    @Test
    public void testMassFlowIsNotOutwardAtStar() {
        densityGrid.initializeWithPowerLaw(1.0 , 1.0, -2.0);
        massFlowCalculator.calculate();
        assertTrue(massFlowGrid.getValue(0) >= 0.0);
    }

}
