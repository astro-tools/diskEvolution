package edu.asu.sese.diskEvolution.util;


public class MidpointGrid {

    protected int zoneCount;
    private double[] value;
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
        return getValueArray()[i];
    }
    
    public void setValue(int i, double v) {
         value[i] = v;
         
    }

    public void initializeWithPowerLaw(double value0, double radius0, 
            double exponent) {
        for (int i = 0; i < zoneCount; ++i) {
            double radius = radialGrid.getMidpoint(i);
            getValueArray()[i] = value0 * Math.pow(radius/radius0, exponent);
        }   
    }

    public double getArea(int i) {
        return radialGrid.getArea(i);
    }  
    
    public double[] getValueArray() {
        return value;
    }
    
	public RadialGrid getRadialGrid() {
		return radialGrid;
	}

}