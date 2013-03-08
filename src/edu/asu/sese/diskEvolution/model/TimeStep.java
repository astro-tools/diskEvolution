package edu.asu.sese.diskEvolution.model;

public class TimeStep {
    private double time;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void update(DensityGrid densityGrid, MassFlowGrid massFlowGrid) {
        double newTimeStep = 2.0 * time;
        for (int i = 0; i < densityGrid.getCount(); ++i) {
            double mass = densityGrid.getValue(i) * densityGrid.getArea(i);
            double massMoved = 1e8;
            massMoved += Math.abs(massFlowGrid.getValue(i));
            massMoved += Math.abs(massFlowGrid.getValue(i+1));
            double intervalTimeStep = 0.5 * mass / massMoved;
            if (intervalTimeStep > 1.0 && intervalTimeStep <= newTimeStep) {
                newTimeStep = intervalTimeStep;
            }
        }
        time = newTimeStep;
    }
}
