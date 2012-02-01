package edu.asu.sese.diskEvolution;

public class DensityGrid {

    private int zoneCount;
    private double[] value;
    private RadialGrid radialGrid;

    public DensityGrid(RadialGrid radialGrid) {
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

    public void initialize(double density0, double radius0, double exponent) {
        for (int i = 0; i < zoneCount; ++i) {
            double radius = radialGrid.getMidpoint(i);
            value[i] = density0 * Math.pow(radius/radius0, -exponent);
        }   
    }
    
}
