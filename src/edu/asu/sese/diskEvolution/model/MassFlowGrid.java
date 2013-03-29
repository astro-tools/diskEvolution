package edu.asu.sese.diskEvolution.model;

import edu.asu.sese.diskEvolution.util.RadialGrid;

public class MassFlowGrid {

	private int gridCount;
	private double[] value;

	public MassFlowGrid(RadialGrid radialGrid) {
		gridCount = radialGrid.getIntervalCount()+1;
		value = new double [gridCount];
	}
	

	public int getCount() {
		return gridCount;
	}


	public double getValue(int i) {
		return value[i];
	}

    public void setValue(int i, double data) {
        value[i] = data;
    }

}
