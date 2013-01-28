package edu.asu.sese.diskEvolution.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.model.DensityGrid;
import edu.asu.sese.diskEvolution.plot.ArrayGrid;
import edu.asu.sese.diskEvolution.plot.GridInterface;
import edu.asu.sese.diskEvolution.plot.PlotView;
import edu.asu.sese.diskEvolution.plot.UnitInterface;
import edu.asu.sese.diskEvolution.util.MidpointAdaptor;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.RadialGrid;
import edu.asu.sese.diskEvolution.util.Unit;

public class DiskViewGraph {
    
    private PlotView plot;
    private UnitInterface domainUnit;
    private UnitInterface rangeUnit;
    private DensityGrid densityGrid;

    DiskViewGraph(DensityGrid densityGrid) {
        this.densityGrid = densityGrid;
        createDensityGraphs();
    }

    private void createDensityGraphs() {
        MidpointAdaptor radialGrid = new MidpointAdaptor(densityGrid.getRadialGrid());
        String domainLabel = "r";
        String rangeLabel = "Σ";
        domainUnit = new Unit("R⊕", "R<sub>⊕</sub>", 
                PhysicalConstants.earthRadiusInCm);
        rangeUnit = new Unit("g/cm²", "g/cm²", 1.0);

        plot = new PlotView(radialGrid, densityGrid, domainLabel, rangeLabel, domainUnit, rangeUnit);
    }

    public JComponent getComponent() {
        return plot.getChartPanel();
    }

}
