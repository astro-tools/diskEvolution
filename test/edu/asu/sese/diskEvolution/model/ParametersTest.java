package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParametersTest {

    public class TestObserver implements ParameterObserver {
        boolean wasNotified = false;
        @Override
        public void notifyRadialParameterChanged() {
            wasNotified = true;
        }
    }
    
    
    @Test
    public void testRadialParameterOberserver() {
        Parameters parameters = new Parameters();
        TestObserver observer = new TestObserver();
        parameters.addObserver(observer);
        double rmin = 0.9 * parameters.getRmin();
        parameters.setRmin(rmin);
        assertTrue(observer.wasNotified);
    }

}
