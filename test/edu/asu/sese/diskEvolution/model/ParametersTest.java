package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

public class ParametersTest {

    boolean wasNotified = false;

    @Test
    public void testThatChangingRMinNotifiesOberserver() {
        Parameters parameters = createParametersWithRadialObserver();
        double rmin = 0.9 * parameters.getRmin();
        parameters.setRmin(rmin);
        assertTrue(wasNotified);
    }

    @Test
    public void testThatChangingRMinChangesValue() {
        Parameters parameters = new Parameters();
        double rmin = 0.9 * parameters.getRmin() + 1.0;
        parameters.setRmin(rmin);
        assertEquals(rmin, parameters.getRmin(), 1e-12);
    }

    @Test
    public void testThatChangingRMaxNotifiesOberserver() {
        Parameters parameters = createParametersWithRadialObserver();
        double rmax = 0.9 * parameters.getRmax();
        parameters.setRmax(rmax);
        assertTrue(wasNotified);
    }

    @Test
    public void testThatChangingRMaxChangesValue() {
        Parameters parameters = new Parameters();
        double rmax = 0.9 * parameters.getRmax() + 1.0;
        parameters.setRmax(rmax);
        assertEquals(rmax, parameters.getRmax(), 1e-12);
    }

    @Test
    public void testThatChangingRadius0ChangesValue() {
        Parameters parameters = new Parameters();
        double radius0 = 0.9 * parameters.getRadius0() + 1.0;
        parameters.setRadius0(radius0);
        assertEquals(radius0, parameters.getRadius0(), 1e-12);
    }

    @Test
    public void testThatChangingRadius0NotifiesOberserver() {
        Parameters parameters = createParametersWithDensityObserver();
        double radius0 = 0.9 * parameters.getRadius0();
        parameters.setRadius0(radius0);
        assertTrue(wasNotified);
    }
    
    @Test
    public void testThatChangingDensity0ChangesValue() {
        Parameters parameters = new Parameters();
        double density0 = 0.9 * parameters.getDensity0() + 1.0;
        parameters.setDensity0(density0);
        assertEquals(density0, parameters.getDensity0(), 1e-12);
    }

    @Test
    public void testThatChangingDensity0NotifiesOberserver() {
        Parameters parameters = createParametersWithDensityObserver();
        double density0 = 0.9 * parameters.getDensity0();
        parameters.setDensity0(density0);
        assertTrue(wasNotified);
    }

    @Test
    public void testThatChangingExponentChangesValue() {
        Parameters parameters = new Parameters();
        double exponent = 0.9 * parameters.getExponent() + 1.0;
        parameters.setExponent(exponent);
        assertEquals(exponent, parameters.getExponent(), 1e-12);
    }

    @Test
    public void testThatChangingExponentNotifiesOberserver() {
        Parameters parameters = createParametersWithDensityObserver();
        double exponent = 0.9 * parameters.getExponent();
        parameters.setExponent(exponent);
        assertTrue(wasNotified);
    }
    
    private Parameters createParametersWithRadialObserver() {
        Parameters parameters = new Parameters();
        Observer observer = createObserver();
        parameters.addRadialParameterObserver(observer);
        return parameters;
    }

    private Parameters createParametersWithDensityObserver() {
        Parameters parameters = new Parameters();
        Observer observer = createObserver();
        parameters.addDensityParameterObserver(observer);
        return parameters;
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
