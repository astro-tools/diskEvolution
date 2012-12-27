package edu.asu.sese.diskEvolution.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.asu.sese.diskEvolution.plot.ArrayGrid;
import edu.asu.sese.diskEvolution.plot.GridInterface;
import edu.asu.sese.diskEvolution.plot.PlotView;
import edu.asu.sese.diskEvolution.plot.UnitInterface;
import edu.asu.sese.diskEvolution.util.PhysicalConstants;
import edu.asu.sese.diskEvolution.util.Unit;

public class DiskView {
    
    private PlotView plot;
    private GridInterface domainGrid;
    private GridInterface rangeGrid;
    private UnitInterface domainUnit;
    private UnitInterface rangeUnit;

    DiskView() {
        createDensityGraphs();
    }

    private void createDensityGraphs() {
        rangeGrid = new ArrayGrid(new double [10]);
        domainGrid = new ArrayGrid(new double [10]);
        String domainLabel = "r";
        String rangeLabel = "Σ";
        domainUnit = new Unit("R⊕", "R<sub>⊕</sub>", 
                PhysicalConstants.earthRadiusInCm);
        rangeUnit = new Unit("g/cm²", "g/cm²", 1.0);

        plot = new PlotView(domainGrid, rangeGrid, domainLabel, rangeLabel, domainUnit, rangeUnit);
    }

    public JComponent getComponent() {
        return plot.getChartPanel();
    }

}
