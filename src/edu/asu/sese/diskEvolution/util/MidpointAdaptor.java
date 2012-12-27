package edu.asu.sese.diskEvolution.util;

import edu.asu.sese.diskEvolution.plot.GridInterface;

public class MidpointAdaptor implements GridInterface {
    
    private RadialGrid radialGrid;

    public MidpointAdaptor(RadialGrid radialGrid) {
        this.radialGrid = radialGrid;
    }

    @Override
    public int getCount() {
        return radialGrid.getIntervalCount();
    }

    @Override
    public double getValue(int i) {
        return radialGrid.getMidpoint(i);
    }

}
