package edu.asu.sese.diskEvolution.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.controller.DiskSimulation;
import edu.asu.sese.diskEvolution.model.MidpointGrid;
import edu.asu.sese.diskEvolution.util.RadialGrid;

public class DiskView extends JPanel {
    private static final long serialVersionUID = 1L;
    RadialPlotView plot;
    private MidpointGrid densityGrid;
    private RadialGrid radialGrid;
    private DiskSimulation simulation;

    public DiskView(RadialGrid radialGrid, MidpointGrid densityGrid) {
        this.radialGrid = radialGrid;
        this.densityGrid = densityGrid;

        setFrameContents();
    }

    public DiskView(DiskSimulation simulation) {
        this(simulation.getRadialGrid(), simulation.getDensityGrid());
        this.simulation = simulation;
        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object object) {
                updatePlot();
            }
        };
        simulation.addObserver(observer);
    }

    private void setFrameContents() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        plot = new RadialPlotView(radialGrid, densityGrid);
        add(plot, BorderLayout.CENTER);
    }
    
    private void updatePlot() {
        radialGrid = simulation.getRadialGrid();
        densityGrid = simulation.getDensityGrid();

        plot.updateData(radialGrid, densityGrid);
        
        System.out.println("trying to update plot!");
        System.out.println("radial grid rmin is:" + radialGrid.getMinimumRadius());
    }

}
