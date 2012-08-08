package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.model.MidpointGrid;
import edu.asu.sese.diskEvolution.model.RadialGrid;

public class DiskView extends JPanel {
    private static final long serialVersionUID = 1L;
    RadialPlotView plot;
    private MidpointGrid densityGrid;
    private RadialGrid radialGrid;

    public DiskView(RadialGrid radialGrid, MidpointGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;

        setFrameContents();
    }

    public DiskView(DiskSimulation simulation) {
        this(simulation.getRadialGrid(), simulation.getDensityGrid());
    }

    private void setFrameContents() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        plot = new RadialPlotView(radialGrid, densityGrid);
        add(plot, BorderLayout.CENTER);
    }

}
