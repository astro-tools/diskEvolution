package edu.asu.sese.diskEvolution.model;

public class RadialGrid {

    private double[] boundary;
    private double[] interval;
    private double[] area;
    private double[] midpoint;
    private int intervalCount;
    private double scaleFactor;

    public RadialGrid(double rmin, double rmax, 
            double deltar0, int intervalCount) {
        this.intervalCount = intervalCount;

        scaleFactor = calculateScaleFactorUsingNewtonsMethod(
                rmax - rmin, deltar0, intervalCount);

        boundary = new double[intervalCount+1];
        interval = new double[intervalCount];
        area = new double[intervalCount];
        midpoint = new double[intervalCount];
        boundary[0] = rmin;
        double step = deltar0;
        for (int i=0; i<intervalCount; ++i) {
            interval[i] = step;
            boundary[i+1] = boundary[i] + step;
            area[i] = Math.PI * (boundary[i+1]*boundary[i+1] 
                                - boundary[i]*boundary[i]);
            midpoint[i] = Math.sqrt(boundary[i+1] * boundary[i]);
            step *= scaleFactor;
        }
    }

    public final double[] getBoundaries() {
        return boundary;
    }

    public double getBoundaryPoint(int i) {
        return boundary[i];
    }

    public final double[] getIntervals() {
        return interval;
    }

    public double getInterval(int i) {
        return interval[i];
    }

    public int getIntervalCount() {
        return intervalCount;
    }

    public double getArea(int i) {
        return area[i];
    }
    
    public double getMidpoint(int i) {
        return midpoint[i];
    }

    private static double calculateScaleFactorUsingNewtonsMethod(
            double range, double firstStep, int count) {
        double scaleFactor = Math.log(range) / Math.log(firstStep);
        double target = range / firstStep;
        double currentValue = geometricSeries(scaleFactor, count);
        while (Math.abs(target - currentValue) / target > 1e-14) {
            double slope =
                    scaleDerivativeOfGeometricSeries(scaleFactor, count);
            scaleFactor += (target - currentValue) / slope;
            currentValue = geometricSeries(scaleFactor, count);
        }
        return scaleFactor;
    }

    private static double geometricSeries(double scale, int n) {
        return ((Math.pow(scale,n)-1)/(scale -1));
    }

    private static double scaleDerivativeOfGeometricSeries(
            double scale, int n) {
        return n * Math.pow(scale, n-1)/(scale-1)
                - (Math.pow(scale, n)-1)/Math.pow(scale-1, 2);
    }

    public double getMinimumRadius() {
        return boundary[0];
    }

    public double getMaximumRadius() {
        return boundary[intervalCount];
    }
}
