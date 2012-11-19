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
    
    @Test
    @Ignore
    public void testThatInitialMassIsCorrect() {
        double radius0 = initialConditions.getRadius0();
        double density0 = initialConditions.getDensity0();
        double exponent = initialConditions.getExponent();
        double rmin = initialConditions.getRMin();
        double rmax = initialConditions.getRMax();
        double expect = 2 * Math.PI * density0;
        expect /= (exponent + 2.0) * Math.pow(radius0, exponent);
        expect *= Math.pow(rmax, exponent + 2.0) - Math.pow(rmin, exponent + 2.0);
        assertEquals(expect, initialConditions.getTotalMass(), PhysicalConstants.lunarMass * 1e-6);
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
