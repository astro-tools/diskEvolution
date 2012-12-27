package edu.asu.sese.diskEvolution.plot;

public class ArrayGrid implements GridInterface {
    
    private double[] data;

    public ArrayGrid(double [] data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public double getValue(int i) {
        return data[i];
    }

}
