package edu.asu.sese.diskEvolution.model;

public class MidpointGrid {

    protected int zoneCount;
    protected double[] value;
    protected RadialGrid radialGrid;

    public MidpointGrid(RadialGrid radialGrid) {
        this.radialGrid = radialGrid;
        zoneCount = radialGrid.getIntervalCount();
        value = new double[zoneCount];
    }

    public int getCount() {
        return zoneCount;
    }

    public double getValue(int i) {
        return value[i];
    }

    public void initializeWithPowerLaw(double value0, double radius0, 
            double exponent) {
        for (int i = 0; i < zoneCount; ++i) {
            double radius = radialGrid.getMidpoint(i);
            value[i] = value0 * Math.pow(radius/radius0, exponent);
        }   
    }

}