package edu.asu.sese.diskEvolution.util;

public class Unit {

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

    public String getLabel() {
        return label;
    }

    public double getScale() {
        return scale;
    }
}
