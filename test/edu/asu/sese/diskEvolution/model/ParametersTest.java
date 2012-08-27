package edu.asu.sese.diskEvolution.model;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

public class ParametersTest {

    boolean wasNotified = false;

    @Test
    public void testRadialParameterOberserver() {
        Parameters parameters = new Parameters();
        
        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                wasNotified = true;
            }
        };
        parameters.addRadialParameterObserver(observer);
        
        double rmin = 0.9 * parameters.getRmin();
        parameters.setRmin(rmin);
        
        assertTrue(wasNotified);
    }
}
