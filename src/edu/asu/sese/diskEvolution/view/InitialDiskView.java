package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class InitialDiskView extends JPanel {
    private static final long serialVersionUID = 1L;
    RadialPlotView plot;
    private DensityGrid densityGrid;
    private RadialGrid radialGrid;
    private InitialConditions conditions;
    private double rmin;
    private double rmax;
    private double vmin;
    private double vmax;

    public InitialDiskView(RadialGrid radialGrid, DensityGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;
    }

    public InitialDiskView(InitialConditions conditions) {
        this.conditions = conditions;
        rmin = PhysicalConstants.earthRadiusInCm * 1.0;
        rmax = PhysicalConstants.earthRadiusInCm * 10.0;
        vmin = 0.1;
        vmax = 2.0e5;
        double deltar0 = PhysicalConstants.earthRadiusInCm * 0.001;
        int intervalCount = 100;
        radialGrid = new RadialGrid(rmin, rmax, deltar0, intervalCount);
        densityGrid = new DensityGrid(radialGrid);
        initializeDensityFromConditions();

        setFrameContents();
        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                updatePlot();
            }
        };
        this.conditions.addObserver(observer);
    }

    private void setFrameContents() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        plot = new RadialPlotView(radialGrid, densityGrid);        
        plot.setRange(rmin, rmax, vmin, vmax);

        add(plot, BorderLayout.CENTER);
    }
    
    private void updatePlot() {
        initializeDensityFromConditions();
        plot.updateData(radialGrid, densityGrid);
    }

    private void initializeDensityFromConditions() {
        double density0 = conditions.getDensity0();
        double radius0 = conditions.getRadius0();
        double exponent = conditions.getExponent();
        double rin = conditions.getRMin();
        double rout = conditions.getRMax();
        densityGrid.initializeWithPowerLaw(density0, radius0, exponent, rin, rout);
    }
}