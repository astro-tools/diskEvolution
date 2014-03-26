package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.RadialGrid;

public class TimeStep {
    private double time;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void update(ViscosityGrid viscosityGrid, RadialGrid radialGrid) {        
        double newTimeStep = 2.0 * time;
        
        newTimeStep 
            = calculateMaximumTimeStep(viscosityGrid, radialGrid, newTimeStep);
     
        if (newTimeStep > 1.001 * time) { 
            time *= 1.001;
        }else {
            time = newTimeStep;
        }
    }

    private double calculateMaximumTimeStep(ViscosityGrid viscosityGrid, RadialGrid radialGrid,
           double newTimeStep) {
        for (int i = 1; i < viscosityGrid.getCount()-1; ++i) {
            double viscosity = viscosityGrid.getValue(i);
            
             
            double outerMidpoint = radialGrid.getMidpoint(i);
            double innerMidpoint = radialGrid.getMidpoint(i-1);
            double deltaR = outerMidpoint - innerMidpoint;
            
            double intervalTimeStep = 0.25 * Math.pow(deltaR, 2) / viscosity;
            if (intervalTimeStep > 0.0 && intervalTimeStep <= newTimeStep) {
                newTimeStep = intervalTimeStep;
            }
        }
        return newTimeStep;
    }
}
