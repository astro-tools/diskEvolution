package edu.asu.sese.diskEvolution.view;

import org.jfree.chart.ChartPanel;

import edu.asu.sese.diskEvolution.model.ViscosityGrid;
import edu.asu.sese.diskEvolution.plot.PlotView;
import edu.asu.sese.diskEvolution.plot.UnitInterface;
import edu.asu.sese.diskEvolution.util.MidpointAdaptor;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class ViscosityViewGraph {
    
    private PlotView plot;
    private UnitInterface domainUnit;
    private UnitInterface rangeUnit;
    private ViscosityGrid viscosityGrid;

    ViscosityViewGraph(ViscosityGrid viscosityGrid) {
        this.viscosityGrid = viscosityGrid;
        createViscosityGraphs();
    }

    private void createViscosityGraphs() {
        MidpointAdaptor radialGrid = new MidpointAdaptor(viscosityGrid.getRadialGrid());
        String domainLabel = "r";
        String rangeLabel = "ν";
        domainUnit = new Unit("R⊕", "R<sub>⊕</sub>", 
                PhysicalConstants.earthRadiusInCm);
        rangeUnit = new Unit("cm²/s", "cm²/s", 1.0);
        plot = new PlotView(radialGrid, viscosityGrid, domainLabel, rangeLabel, domainUnit, rangeUnit);
        plot.setRangeAxisLogarithmic(0, 1e11);

    }

    public  ChartPanel getComponent() {
        return plot.getChartPanel();
    }

}



