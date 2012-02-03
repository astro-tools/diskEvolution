package edu.asu.sese.diskEvolution;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class RadialPlotView extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private RadialGrid radialGrid;
    private DensityGrid densityGrid;
    private double minimumRadius;
    private double maximumRadius;
    private double minimumDensity;
    private double maximumDensity;
    
    int width;
    int height;
  
    
    public RadialPlotView(RadialGrid radialGrid, DensityGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;
        minimumRadius = 0.9*radialGrid.getMinimumRadius();
        maximumRadius = 1.1*radialGrid.getMaximumRadius();
        minimumDensity = 0.1;
        maximumDensity = 1.0e5;
        setPreferredSize(new Dimension(320, 240));
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();
        width = d.width;
        height = d.height;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, width, height);
        
        drawDensityCurve(g2);
        drawBoundingRectangle(g2);
    }

    private void drawDensityCurve(Graphics2D g2) {
        int count = densityGrid.getCount();
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        double x = calcX(radialGrid.getMidpoint(0));
        double y = calcY(densityGrid.getValue(0));
        path.moveTo(x, y);
        for (int i = 1; i < count; ++i) {
             x = calcX(radialGrid.getMidpoint(i));
             y = calcY(densityGrid.getValue(i));
            path.lineTo(x, y);
        }
        g2.setColor(Color.blue);
        g2.draw(path);
    }

    private double calcY(double density) {
        double logDensity = Math.log((1e-15 + density)/minimumDensity)
                / Math.log(maximumDensity/minimumDensity);
        return height * (1.0 - logDensity);
    }

    private double calcX(double radius) {       
        double logRadius = Math.log(radius/minimumRadius)
                / Math.log(maximumRadius/minimumRadius);
        return width * logRadius;
    }

    private void drawBoundingRectangle(Graphics2D g2) {
        Shape rectangle = new Rectangle2D.Double(0, 0, width-1, height-1);
        g2.setColor(Color.black);
        g2.draw(rectangle);
    }

    public RadialGrid getRadialGrid() {
        return radialGrid;
    }

    public void setRadialGrid(RadialGrid radialGrid) {
        this.radialGrid = radialGrid;
    }
}
