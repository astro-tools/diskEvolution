package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import edu.asu.sese.diskEvolution.util.PhysicalConstants;

public class InitialConditionsTest {

    boolean wasNotified = false;
    private InitialConditions initialConditions;
    
    @Before
    public void before() {
        initialConditions = createInitialConditionsWithObserver();
    }

    @Test
    public void testThatChangingRMinNotifiesOberserver() {
        double rmin = 0.9 * initialConditions.getRMin();
        initialConditions.setRMin(rmin);
        assertTrue(wasNotified);
    }

    @Test
    public void testThatChangingRMinChangesValue() {
        double rmin = 0.9 * initialConditions.getRMin() + 1.0;
        initialConditions.setRMin(rmin);
        assertEquals(rmin, initialConditions.getRMin(), 1e-12);
    }

    @Test
    public void testThatChangingRMaxNotifiesOberserver() {
        double rmax = 0.9 * initialConditions.getRMax();
        initialConditions.setRMax(rmax);
        assertTrue(wasNotified);
    }

    @Test
    public void testThatChangingRMaxChangesValue() {
        double rmax = 0.9 * initialConditions.getRMax() + 1.0;
        initialConditions.setRMax(rmax);
        assertEquals(rmax, initialConditions.getRMax(), 1e-12);
    }
    
    @Test
    public void testThatChangingRadius0NotifiesOberserver() {
        double radius0 = 0.9 * initialConditions.getRadius0();
        initialConditions.setRadius0(radius0);
        assertTrue(wasNotified);
    }

    @Test
    public void testThatChangingRadius0ChangesValue() {
        double radius0 = 0.9 * initialConditions.getRadius0() + 1.0;
        initialConditions.setRadius0(radius0);
        assertEquals(radius0, initialConditions.getRadius0(), 1e-12);
    }
    
    /// Mass is 2*pi*sigma0 [rmax^(p+2) - rmin^(p+2)]/ (p+2)*r0^p
    /// For an exponent of p=-1.5, sigma0 of 5 g/cm, r0=3cm, rmax=4 cm, rmin=2cm,
    /// mass should be 191.2498322660473 g.
    @Test
    public void testThatInitialMassIsCorrect() {
        initialConditions.setRadius0(3.0);
        initialConditions.setDensity0(5.0);
        initialConditions.setExponent(-1.5);
        initialConditions.setRMin(2.0);
        initialConditions.setRMax(4.0);
        double expect = 191.2498322660473;
        assertEquals(expect, initialConditions.getTotalMass(), 1e-12);
    }
    
    private InitialConditions createInitialConditionsWithObserver() {
        InitialConditions initialConditions = new InitialConditions();
        Observer observer = createObserver();
        initialConditions.addObserver(observer);
        return initialConditions;
    }
    
    private Observer createObserver() {
        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                wasNotified = true;
            }
        };
        return observer;
    }
}
