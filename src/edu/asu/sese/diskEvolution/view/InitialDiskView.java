package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.model.InitialConditions;
import edu.asu.sese.diskEvolution.plot.RadialPlotView;
import edu.asu.sese.diskEvolution.util.MidpointAdaptor;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import edu.asu.sese.diskEvolution.util.Unit;

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
    private MidpointAdaptor adaptedRadialGrid;

    public InitialDiskView(RadialGrid radialGrid, DensityGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;
        adaptedRadialGrid = new MidpointAdaptor(radialGrid);
    }

    public InitialDiskView(InitialConditions conditions) {
        this.conditions = conditions;
        rmin = PhysicalConstants.earthRadiusInCm * 0.0;
        rmax = PhysicalConstants.earthRadiusInCm * 8.9;
        vmin = 10.0;
        vmax = 2.0e8;
        double deltar0 = PhysicalConstants.earthRadiusInCm * 0.01;
        int intervalCount = 1000;
        radialGrid = new RadialGrid(rmin, rmax, deltar0, intervalCount);
        adaptedRadialGrid = new MidpointAdaptor(radialGrid);
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

        String radialLabel = "r";
        Unit radialUnit = new Unit("R⊕", "R<sub>⊕</sub>", 
                PhysicalConstants.earthRadiusInCm);
        String densityLabel = "Σ (g/cm²)";
        Unit densityUnit = new Unit("g/cm²", "g/cm²", 1.0);
        plot = new RadialPlotView(adaptedRadialGrid, densityGrid, 
                radialLabel, densityLabel, radialUnit, densityUnit);        
        plot.setAxisLimits(rmin, rmax, vmin, vmax);

        add(plot, BorderLayout.CENTER);
    }
    
    private void updatePlot() {
        initializeDensityFromConditions();
        plot.updateData(adaptedRadialGrid, densityGrid);
    }

    private void initializeDensityFromConditions() {
        double density0 = conditions.getDensity0();
        double radius0 = conditions.getRadius0();
        double exponent = conditions.getExponent();
        double rin = conditions.getRIn();
        double rout = conditions.getROut();
        densityGrid.initializeWithPowerLaw(density0, radius0, exponent, rin, rout);
    }
}
