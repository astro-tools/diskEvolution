package edu.asu.sese.diskEvolution.util;

import edu.asu.sese.diskEvolution.plot.UnitInterface;

public class Unit implements UnitInterface {

    private String label;
    private String htmlLabel;
    private double scale;
    
    public Unit(String label, String htmlLabel, double scale) {
        this.label = label;
        this.htmlLabel = htmlLabel;
        this.scale = scale;
    }

    public String getHtmlLabel() {
        return htmlLabel;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public double getScale() {
        return scale;
    }
}
